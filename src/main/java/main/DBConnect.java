package main;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private String ip = "localhost";
    private int port = 3306;
    private String dbname = "world_population";
    private String username = "root";
    private String password = "";
    private Connection conn;
    private DBConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbname, username, password);
            System.out.println("Database is connected...");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    private static DBConnect dbConnect = new DBConnect();
    public static Connection getConn() {
        return dbConnect.conn;
    }
}

