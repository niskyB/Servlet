package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Connector {
    // DAO DATA ACCESS OBJECT

    private static String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=Demo1;"
            + "integratedSecurity=true";
    private static String USER_NAME = "sa";
    private static String PASSWORD = "1234567890";

    static public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;

//        try {
//            Context context = new InitialContext();
//            Context end = (Context) context.lookup("java:comp/env");
//            DataSource env = (DataSource) end.lookup("DBCon");
//            Connection conn = env.getConnection();
//            return conn;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
    }
}
