package MySQL;

import java.sql.*;
public class ConnMysql {
    public static Connection connect() {
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/rqs?useSSL=false&serverTimezone=GMT", "root", "lkw19991124.");
            return conn;
        }
        catch (Exception se){
            se.printStackTrace();
            return null;
        }
    }
    public void disconnect(Connection conn) {
        try {
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Connection conn=null;
        conn = connect();
        if(conn!=null)
            System.out.println("连接成功");
    }
}
