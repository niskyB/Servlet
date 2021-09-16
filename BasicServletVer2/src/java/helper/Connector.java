package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=Demo1;"
            + "integratedSecurity=true";
    private static String USER_NAME = "sa";
    private static String PASSWORD = "1234567890";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("Connect successfully!");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
}
