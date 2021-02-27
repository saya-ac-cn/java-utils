package sccm;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Title: Main
 * @ProjectName java-utils
 * @Description: TODO
 * @Author saya
 * @Date: 2020/8/6 21:34
 * @Description:
 */

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //1.注册驱动(静态方法)(包名+类名）
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获取连接对象(导包都导sql里面的，不导jdbc里的；多态！报异常是因为用户输入的串可能写错）后面设置下数据格式
        String url="jdbc:mysql://123456789/test?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC&allowMultiQueries=true&rewriteBatchedStatements=true";
        String user="2345678";
        String password="23456789";
        Connection conn= DriverManager.getConnection(url,user,password);
        //System.out.println(conn);报地址为正确sql服务关了可能报错
        //3.获取语句执行平台：
        Statement sta=conn.createStatement();
        //4.执行sql语句
        String sql="select field2,field4 from xls";
        ResultSet rs=sta.executeQuery(sql);
        //5.处理结果集(括号里一般是第1列、第2列，但是可以写字段名)
        List<Line> list = new ArrayList<>();
        while(rs.next()){
            Line item = new Line();
            item.setList(JSONObject.parseArray(rs.getString("field2"),MetterEntity.class));
            item.setContent(rs.getString("field4"));
            list.add(item);
        }
        //6.释放资源
        rs.close();
        sta.close();
        conn.close();
        operation(list);
    }

    public static void operation(List<Line> list){
        for (Line item:list) {
            // 处理每一行
            Map<Long, List<MetterEntity>> listMap = item.getList().stream().collect(Collectors.groupingBy(t -> t.getCollectorId()));
            for (Map.Entry<Long, List<MetterEntity>> collect: listMap.entrySet()) {
                // 得到单一的采集器
                List<MetterEntity> collectValue = collect.getValue();
                send(collect.getKey(),collectValue,item.getContent());
            }
        }
    }

    public static void send(long collectId,List<MetterEntity> collectValue,String command){
        //List<JSONObject> sends = new ArrayList<>(collectValue.size());
        JSONArray array = new JSONArray();
        JSONObject jsonObject = JSONObject.parseObject(command);
        for (MetterEntity collect:collectValue) {
            JSONObject clone = CloneUtils.clone(jsonObject);
            clone.put("serialNo",collect.getMeterId());
            array.add(clone);
        }
        // 下发
        System.out.println(array);
        try {
            HttpRequestUtils.httpPost("http://117.175.139.253:8085/collector/read_power?collectorId="+collectId, null, array, 600000, false, HttpRequestUtils.getClientContext());
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
