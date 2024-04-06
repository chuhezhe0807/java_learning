package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ClassName: JavaMySQLDemo
 * Package: mysql
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/4/6 16:30
 * @Version 1.0
 */
public class JavaMySQLDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 加载类，得到mysql连接
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db01", "root", "root");

        // 这里可以编写SQL 【create, select, insert, update, delete】
//        String sql = "create table xiaozhang_goods (id int, name varchar(32), price double, introduce text)";
//        String sql = "insert into xiaozhang_goods values(1, '华为手机', 2000, '这是华为手机')";
        String sql = "drop table xiaozhang_goods"; // 删除表

        // 得到statement对象，把sql语句发送给mysql执行
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);

        statement.close();
        connection.close();
        System.out.println("成功~");
    }
}
