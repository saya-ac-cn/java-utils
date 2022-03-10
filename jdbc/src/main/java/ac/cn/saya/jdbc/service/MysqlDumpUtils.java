package ac.cn.saya.jdbc.service;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Title: MysqlDumpUtils
 * @ProjectName jdbc
 * @Description: TODO
 * @Author Saya
 * @Date: 2018/11/28 22:47
 * @Description:
 */

public class MysqlDumpUtils {

    /**
     * 数据源连接信息
     */
    private final Connection CONNECTION;

    /**
     * 备份后的文件
     */
    private final File BACK_FILE;

    private final String BACK_DATABASE;
    private final String SQL = "SELECT * FROM ";


    /**
     * @param connection    jdbc连接
     * @param backPath  备份文件地址
     * @param database 要备份的数据库
     */
    public MysqlDumpUtils(Connection connection, String backPath,String database) {
        this.BACK_DATABASE = database;
        this.CONNECTION = connection;
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMddHHmmss");
        String datetime = tempDate.format(new Date());
        this.BACK_FILE = new File(backPath,   database+"_" + datetime + ".sql");
        try(this.CONNECTION) {
            beginBack();
        } catch (Exception e) {
            System.err.println("can not load jdbc driver："+e);
        }
    }

    /**
     * 获取数据库下的所有表名
     */
    private List<String> getTableNames() {
        List<String> tableNames = new ArrayList<String>();
        ResultSet rs = null;
        try {
            // 获取数据库的元数据
            DatabaseMetaData db = CONNECTION.getMetaData();
            // 从元数据中获取到所有的表名
            /**
             * 第一个是数据库名称，对于MySQL，则对应相应的数据库，对于Oracle来说，则是对应相应的数据库实例，可以不填，也可以直接使用Connection的实例对象中的getCatalog()方法返回的值填充；
             * 第二个是模式，可以理解为数据库的登录名，而对于Oracle也可以理解成对该数据库操作的所有者的登录名。对于Oracle要特别注意，其登陆名必须是大写，不然的话是无法获取到相应的数据，而MySQL则不做强制要求。
             * 第三个是表名称，一般情况下如果要获取所有的表的话，可以直接设置为null，如果设置为特定的表名称，则返回该表的具体信息。
             * 第四个是类型标准,以数组形式传值，有"TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL
             * TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和
             * "SYNONYM"这几个经典的类型，一般使用”TABLE”，即获取所有类型为TABLE的表
             *
             * 它返回一个ResultSet对象，有10列，详细的显示了表的类型：
             * TABLE_CAT String => 表类别(可为null)
             * TABLE_SCHEM String => 表模式(可为null)
             * TABLE_NAME String => 表名称
             * TABLE_TYPE String => 表类型。
             * REMARKS String => 表的解释性注释
             * TYPE_CAT String => 类型的类别(可为null)
             * TYPE_SCHEM String => 类型模式(可为null)
             * TYPE_NAME String => 类型名称(可为null)
             * SELF_REFERENCING_COL_NAME String
             * => 有类型表的指定 "identifier" 列的名称(可为 null)
             * REF_GENERATION String
             * 可根据需要使用
             */
            rs = db.getTables(CONNECTION.getCatalog(),null,"%",new String[]{"TABLE"});
            while (rs.next()) {
                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
            System.err.println("getTableNames failure"+ e);
        } finally {
            try {
                if (null != rs) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("close ResultSet failure"+ e);
            }
        }
        return tableNames;
    }

    /**
     * 获取表中所有字段名称
     *
     * @param tableName 表名
     * @return
     */
    private List<String> getColumnNames(String tableName) {
        List<String> columnNames = new ArrayList<String>();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = CONNECTION.prepareStatement(tableSql);
            // 结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            // 表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnNames.add(rsmd.getColumnName(i + 1));
            }
        } catch (SQLException e) {
            System.err.println("getColumnNames failure"+e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                } catch (SQLException e) {
                    System.err.println("getColumnNames close pstem and connection failure"+e);
                }
            }
        }
        return columnNames;
    }

    /**
     * 获取表中所有字段类型
     *
     * @param tableName
     * @return
     */
    private List<String> getColumnTypes(String tableName) {
        List<String> columnTypes = new ArrayList<String>();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = CONNECTION.prepareStatement(tableSql);
            // 结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            // 表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnTypes.add(rsmd.getColumnTypeName(i + 1));
            }
        } catch (SQLException e) {
            System.err.println("getColumnTypes failure"+e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();

                } catch (SQLException e) {
                    System.err.println("getColumnTypes close pstem and connection failur"+e);
                }
            }
        }
        return columnTypes;
    }

    /**
     * <p>
     * 生成建表语句
     * </p>
     *
     * @param tableName
     * @return
     * @author
     */
    private String generateCreateTableSql(String tableName) {
        String sql = String.format("SHOW CREATE TABLE %s", tableName);
        PreparedStatement pstmt = null;
        try {
            pstmt = CONNECTION.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            System.err.println("导出:"+tableName);
            // SHOW CREATE TABLE 是查看表结构，查询后只会返回一行两列数据
            while (rs.next()) {
                // 返回建表语句语句，查询结果的第二列是建表语句，第一列是表名
                return rs.getString(2);
            }
        } catch (Exception e) {
            System.err.println(e);
            try {
                if (null != pstmt) {
                    pstmt.close();
                }
            } catch (Exception e2) {
                System.err.println("关闭流异常"+e);
            }
        }
        return null;
    }

    /**
     * 获取表中字段的所有注释
     *
     * @param tableName
     * @return
     */
    private List<String> getColumnComments(String tableName) {
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        // 列名注释集合
        List<String> columnComments = new ArrayList<>();
        ResultSet rs = null;
        try {
            pStemt = CONNECTION.prepareStatement(tableSql);
            rs = pStemt.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                columnComments.add(rs.getString("Comment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("getColumnComments close ResultSet and connection failure"+e);
                }
            }
        }
        return columnComments;
    }

    /**
     * <p>
     * 备份表数据
     * </p>
     *
     * @param tableName
     * @return
     * @author
     */
    private String bakTableData(String tableName) {
        PreparedStatement pstmt = null;
        try {
            // 备份建表语句
            String createTableSql = generateCreateTableSql("`" + tableName + "`");
            if (Objects.isNull(createTableSql) || "".equals(createTableSql)) {
                throw new SQLException(tableName + "不存在！");
            }
            createTableSql = String.format(
                    "\n\n-- ----------------------------\n-- Table structure for %s\n-- ----------------------------\nDROP TABLE IF EXISTS `%s`;\n%s;\n\n-- ----------------------------\n-- Records of access\n-- ----------------------------\n",
                    tableName, tableName, createTableSql);
            // 写入表结构
            writeFileData(createTableSql);

            // 获取字段类型
            List<String> columnTypes = getColumnTypes(tableName);
            // 获取所有 字段
            List<String> columnNames = getColumnNames(tableName);
            String columnArrayStr = null;
            for (String column : columnNames) {
                if (null == columnArrayStr) {
                    columnArrayStr = "`" + column + "`";
                } else {
                    columnArrayStr = columnArrayStr + "," + "`" + column + "`";
                }
            }

            String sql = String.format("select %s from %s", columnArrayStr, tableName);

            pstmt = CONNECTION.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String rowValues = getRowValues(rs, columnNames.size(), columnTypes);
                // 返回建表语句语句，查询结果的第二列是建表语句，第一列是表名
                String insertSql = String.format("insert into `%s` (%s) values(%s);", tableName, columnArrayStr,
                        rowValues);
                insertSql = insertSql.replace("\n", "\\n");
                insertSql = insertSql + "\n";
                // 写入数据
                writeFileData(insertSql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != pstmt) {
                    pstmt.close();
                }
            } catch (Exception e2) {
                System.err.println("关闭流异常："+e2);
            }
        }
        return null;
    }

    /**
     * <p>
     * 获取表数据一行的所有值
     * </p>
     *
     * @param rs
     * @param size
     * @author
     */
    private String getRowValues(ResultSet rs, int size, List<String> columnTypeList) {
        try {
            String rowValues = null;
            for (int i = 1; i <= size; i++) {
                // 获取字段值
                Object originalColumnValue = getValue(rs, i, columnTypeList.get(i - 1));
                String repairColumnValue;
                if (originalColumnValue instanceof String) {
                    repairColumnValue = "'" + originalColumnValue + "'";
                }else {
                    repairColumnValue = (StringUtils.isEmpty(originalColumnValue))?null:originalColumnValue.toString();
                }
                // 拼接字段值
                if (null == rowValues) {
                    // 判断是否是第一次拼接
                    rowValues = repairColumnValue;
                } else {
                    rowValues = rowValues + "," + repairColumnValue;
                }
            }
            return rowValues;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * <p>
     * 根据类型获取字段值
     * </p>
     *
     * @return
     * @author
     */
    private Object getValue(ResultSet resultSet, Integer index, String columnType) {
        try {
            //System.out.println("列num："+index);
            if ("int".equals(columnType) || "INT".equals(columnType)) {
                // 整数
                Object intValue = resultSet.getObject(index);
                if (null == intValue) {
                    return null;
                }
                return intValue;
            } else if ("bigint".equals(columnType) || "BIGINT".equals(columnType)) {
                // 长整形
                Object value = resultSet.getObject(index);
                if (null == value) {
                    return null;
                }
                return value;
            } else if ("smallint".equals(columnType) || "SMALLINT".equals(columnType)) {
                // 整数
                Object value = resultSet.getObject(index);
                if (null == value) {
                    return null;
                }
                return value;
            } else if ("tinyint".equals(columnType) || "TINYINT".equals(columnType)) {
                // 整数
                Object value = resultSet.getObject(index);
                if (null == value) {
                    return null;
                }
                return value;
            } else if ("mediumint".equals(columnType) || "MEDIUMINT".equals(columnType)) {
                // 长整形
                Object value = resultSet.getObject(index);
                if (null == value) {
                    return null;
                }
                return value;
            } else if ("integer".equals(columnType) || "INTEGER".equals(columnType)) {
                // 整数
                Object value = resultSet.getObject(index);
                if (null == value) {
                    return null;
                }
                return value;
            } else if ("float".equals(columnType) || "FLOAT".equals(columnType)) {
                // 浮点数
                Object value = resultSet.getObject(index);
                if (null == value) {
                    return null;
                }
                return value;
            } else if ("double".equals(columnType) || "DOUBLE".equals(columnType)) {
                // 浮点数
                Object value = resultSet.getObject(index);
                if (null == value) {
                    return null;
                }
                return value;
            } else if ("decimal".equals(columnType) || "DECIMAL".equals(columnType)) {
                // 浮点数-金额类型
                BigDecimal value = resultSet.getBigDecimal(index);
                if (null == value) {
                    return null;
                }
                return value;
            } else if ("char".equals(columnType) || "CHAR".equals(columnType)) {
                // 字符串类型
                String value = resultSet.getString(index);
                value = escapeJson(value);
                return value;
            } else if ("varchar".equals(columnType) || "VARCHAR".equals(columnType)) {
                // 字符串类型
                String value = resultSet.getString(index);
                value = escapeJson(value);
                return value;
            } else if ("tinytext".equals(columnType) || "TINYTEXT".equals(columnType)) {
                // 字符串类型
                String value = resultSet.getString(index);
                value = escapeJson(value);
                return value;
            } else if ("text".equals(columnType) || "TEXT".equals(columnType)) {
                // 字符串类型
                String value = resultSet.getString(index);
                value = escapeJson(value);
                return value;
            } else if ("mediumtext".equals(columnType) || "MEDIUMTEXT".equals(columnType)) {
                // 字符串类型
                String value = resultSet.getString(index);
                value = escapeJson(value);
                return value;
            } else if ("longtext".equals(columnType) || "LONGTEXT".equals(columnType)) {
                // 字符串类型
                String value = resultSet.getString(index);
                value = escapeJson(value);
                return value;
            } else if ("year".equals(columnType) || "YEAR".equals(columnType)) {
                // 时间类型：范围 1901/2155 格式 YYYY
                String year = resultSet.getString(index);
                if (null == year) {
                    return null;
                }
                // 只需要年的字符即可，
                return year.substring(0, 4);
            } else if ("date".equals(columnType) || "DATE".equals(columnType)) {
                // 时间类型：范围 '1000-01-01'--'9999-12-31' 格式 YYYY-MM-DD
                return resultSet.getString(index);
            } else if ("time".equals(columnType) || "TIME".equals(columnType)) {
                // 时间类型：范围 '-838:59:59'到'838:59:59' 格式 HH:MM:SS
                return resultSet.getString(index);
            } else if ("datetime".equals(columnType) || "DATETIME".equals(columnType)) {
                // 时间类型：范围 '1000-01-01 00:00:00'--'9999-12-31 23:59:59' 格式 YYYY-MM-DD HH:MM:SS
                return resultSet.getString(index);
            } else if ("timestamp".equals(columnType) || "TIMESTAMP".equals(columnType)) {
                // 时间类型：范围 1970-01-01 00:00:00/2037 年某时 格式 YYYYMMDD HHMMSS 混合日期和时间值，时间戳
                return resultSet.getString(index);
            } else if ("bit".equals(columnType) || "BIT".equals(columnType)) {
                // 布尔类型：
                if (resultSet.getBoolean(index)) {
                    return 1;
                }
                return 0;
            } else if ("bigint unsigned".equals(columnType) || "BIGINT UNSIGNED".equals(columnType)) {
                // 无符号bigint
                Object intValue = resultSet.getObject(index);
                if (null == intValue) {
                    return null;
                }
                return intValue;
            } else if ("int unsigned".equals(columnType) || "INT UNSIGNED".equals(columnType)) {
                // 无符号整数
                Object intValue = resultSet.getInt(index);
                if (null == intValue) {
                    return null;
                }
                return intValue;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.err.println("获取数据库类型值异常："+e);
            return null;
        }

    }

    /**
     * <开始备份>
     *
     * @author
     */
    public File beginBack() {
        // 初始化语句写入
        writeFileData("SET NAMES utf8mb4;\nSET FOREIGN_KEY_CHECKS = 0;\nuse " + BACK_DATABASE + ";");
        try {
            List<String> tableNames = getTableNames();
            tableNames = tableNames.stream().distinct().collect(Collectors.toList());
            for (String tableName : tableNames) {
                bakTableData(tableName);
            }
        } catch (Exception e) {
            System.err.println("该库不存在该表，正常"+e);
        }
        // 写入结束语句
        writeFileData("\nSET FOREIGN_KEY_CHECKS = 1;");
        return BACK_FILE;
    }

    private String escapeJson(String jsonStr) {
        if (StringUtils.isEmpty(jsonStr)){
            return null;
        }
        StringBuilder escapeJsonStr = new StringBuilder();
        for (int i = 0; i < jsonStr.length(); i++) {
            char c = jsonStr.charAt(i);
            if ((int) c == (int) '\'') {
                escapeJsonStr.append("\\");
            } else if ((int) c == (int) '"') {
                escapeJsonStr.append("\\");
            }
            escapeJsonStr.append(c);
        }
        return escapeJsonStr.toString();
    }

    private void writeFileData(String val){
        try(FileOutputStream outputStream = new FileOutputStream(BACK_FILE, true); FileChannel channel = outputStream.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(val.getBytes().length);
            buffer.put(val.getBytes());
            //此处必须要调用buffer的flip方法
            buffer.flip();
            channel.write(buffer);
            buffer.clear();
        }catch (Exception e){
            System.err.println("文件写入异常:"+e);
        }
    }

}
