package ac.cn.saya.jdbc.dao;

import ac.cn.saya.jdbc.service.MysqlDumpUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * @Title: MysqlDAO
 * @ProjectName jdbc
 * @Description: TODO
 * @Author Saya
 * @Date: 2018/11/28 22:50
 * @Description:
 */

/**
 * 应用中采用原生JDBC对数据进行批处理（批量添加、修改、删除）专用DAO，以此提高效率
 */
@Repository("mysqlDAO")
public class MysqlDAO extends JDBCBaseConnection {

    public void mysqlDump(){
        try(SqlSession sqlSession = getSqlSession()) {
            MysqlDumpUtils dumpUtils = new MysqlDumpUtils(sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection(), "/Users/saya/project/java/java-utils/jdbc/src/main/resources","test");
            System.out.println("11");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 采用普通方式提交
     * 十万级别数据写入耗时：11974ms
     * 百万级别数据写入耗时：121912ms
     *
     * @param collect
     */
    public void useOrdinaryWriteMysql(List<Integer> collect) {
        SqlSession sqlSession = null;
        Connection sqlCon = null;//连接对象
        try {
            sqlSession = getSqlSession();//获取sqlSession
            sqlCon = sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();//建立jdbc连接
            String Sql = "insert into member(`name`,`createTime`) values(?,now())";
            PreparedStatement ps = sqlCon.prepareStatement(Sql);
            long startTime = System.currentTimeMillis();//开始执行时间
            for (Integer item : collect) {
                ps.setInt(1, item);
                ps.executeUpdate();//执行添加
            }
            long endTime = System.currentTimeMillis();//结束执行时间
            System.out.println("采用普通方式写入共耗时：" + String.valueOf(endTime - startTime) + "ms");
            ps.close();
            sqlCon.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null)//及时关闭资源
            {
                sqlSession.close();
            }
        }
    }

    /**
     * 采用事务的方式提交
     * 十万级别数据写入耗时：11587ms
     * 百万级别数据写入耗时：114102ms
     *
     * @param collect
     */
    public void useTransactionWriteMysql(List<Integer> collect) {
        SqlSession sqlSession = null;
        Connection sqlCon = null;//连接对象
        try {
            sqlSession = getSqlSession();//获取sqlSession
            sqlCon = sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();//建立jdbc连接
            String Sql = "insert into member(`name`,`createTime`) values(?,now())";
            PreparedStatement ps = sqlCon.prepareStatement(Sql);
            sqlCon.setAutoCommit(false);// 设置手动提交-必须-优化项
            long startTime = System.currentTimeMillis();//开始执行时间
            int i = 1;
            for (Integer item : collect) {
                ps.setLong(1, item);
                ps.addBatch();//积攒SQL
                if (i % 1000 == 0)//当积攒到1000条记录,就执行一次,并且清空记录
                {
                    ps.executeBatch();//执行批处理
                    ps.clearBatch();//清除空间
                }
                i = i + 1;
            }
            //总条数不是批量值整数倍（这里是1000）,则还需要在执行一次
            if (collect.size() % 1000 != 0) {
                ps.executeBatch();
                ps.clearBatch();
            }
            sqlCon.commit();//提交
            long endTime = System.currentTimeMillis();//结束执行时间
            System.out.println("采用事务的方式写入共耗时：" + String.valueOf(endTime - startTime) + "ms");
            ps.close();
            sqlCon.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null)//及时关闭资源
            {
                sqlSession.close();
            }
        }
    }

    /**
     * 采用批处理的方式写入
     * 十万级别数据写入耗时：579ms
     * 百万级别数据写入耗时：6358ms
     *
     * @param collect
     */
    public void useBatchProcessingWriteMysql(List<Integer> collect) {
        SqlSession sqlSession = null;
        Connection sqlCon = null;//连接对象
        try {
            sqlSession = getSqlSession();//获取sqlSession
            sqlCon = sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();//建立jdbc连接
            String Sql = "insert into member(`name`,`createTime`) values(?,now())";
            PreparedStatement ps = sqlCon.prepareStatement(Sql);
            long startTime = System.currentTimeMillis();//开始执行时间
            for (Integer item : collect) {
                ps.setInt(1, item);
                ps.addBatch();//执行添加
            }
            ps.executeBatch();
            long endTime = System.currentTimeMillis();//结束执行时间
            System.out.println("采用批处理方式写入共耗时：" + String.valueOf(endTime - startTime) + "ms");
            ps.close();
            sqlCon.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null)//及时关闭资源
            {
                sqlSession.close();
            }
        }
    }

    /**
     * 采用批处理+事务
     * 十万级别数据写入耗时：601ms
     * 百万级别数据写入耗时：5292ms
     *
     * @param collect
     */
    public void useBatchProcessingAndTransactionWriteMysql(List<Integer> collect) {
        SqlSession sqlSession = null;
        Connection sqlCon = null;//连接对象
        try {
            sqlSession = getSqlSession();//获取sqlSession
            sqlCon = sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();//建立jdbc连接
            String Sql = "insert into member(`name`,`createTime`) values(?,now())";
            PreparedStatement ps = sqlCon.prepareStatement(Sql);
            sqlCon.setAutoCommit(false);// 设置手动提交-必须-优化项
            long startTime = System.currentTimeMillis();//开始执行时间
            int i = 1;
            for (Integer item : collect) {
                ps.setLong(1, item);
                ps.addBatch();//积攒SQL
                if (i % 1000 == 0)//当积攒到1000条记录,就执行一次,并且清空记录
                {
                    ps.executeBatch();//执行批处理
                    ps.clearBatch();//清除空间
                }
                i = i + 1;
            }
            //总条数不是批量值整数倍（这里是1000）,则还需要在执行一次
            if (collect.size() % 1000 != 0) {
                ps.executeBatch();
                ps.clearBatch();
            }
            sqlCon.commit();//提交
            long endTime = System.currentTimeMillis();//结束执行时间
            System.out.println("采用批处理+事务的方式写入共耗时：" + String.valueOf(endTime - startTime) + "ms");
            ps.close();
            sqlCon.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null)//及时关闭资源
            {
                sqlSession.close();
            }
        }
    }

}
