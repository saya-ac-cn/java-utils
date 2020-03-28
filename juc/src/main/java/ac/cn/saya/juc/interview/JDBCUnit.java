package ac.cn.saya.juc.interview;

import java.sql.*;

/**
 * @Title: JDBCUnit
 * @ProjectName spring-boot-webflux
 * @Description: TODO
 * @Author Administrator
 * @Date: 2020/3/10 0010 15:36
 * @Description:JDBC
 */

public class JDBCUnit {

    public void read(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("","","");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select store_name from e_device limit 10");
            while (resultSet.next()){
                System.out.println(resultSet.getString(0));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void write(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("", "", "");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into e_device values(?)");
            preparedStatement.setString(1,"xxx");
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
