package dao.jdbc;

import java.sql.*;

@Deprecated
public class ConnectDB{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sprint2";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(ConnectDB.DB_URL, ConnectDB.USER_NAME, ConnectDB.PASSWORD);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
}