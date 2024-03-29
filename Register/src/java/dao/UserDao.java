package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import helper.Connector;
import java.sql.SQLException;
import java.util.UUID;
import model.User;

public class UserDao {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }

        if (preStm != null) {
            preStm.close();
        }

        if (conn != null) {
            conn.close();
        }
    }

    public User getUserByUsername(String fullName) throws Exception {
        try {
            conn = Connector.getConnection();
            String sql = "SELECT * FROM tbl_user WHERE fullName = ?";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, fullName);
            rs = preStm.executeQuery();
            if (rs.next()) {
                Integer password = rs.getInt("password");
                Integer role = rs.getInt("role");
                String userId = rs.getString("userId");
                User user = new User(userId, password, fullName, role);

                return user;
            }
        } finally {
            this.closeConnection();
        }

        return null;
    }

    public boolean addUser(String username, Integer password) throws Exception {
        boolean check = false;
        try {
            UUID uuid = UUID.randomUUID();
            conn = Connector.getConnection();
            System.out.println(uuid.toString().getBytes("UTF-8"));
            String sql = "INSERT tbl_User VALUES (N'" + uuid.toString().getBytes("UTF-8") + "', " + password + ", N'" + username + "', 2)";

            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            check = true;
        } finally {
            this.closeConnection();
        }
        return check;
    }

    public String getLastId() throws SQLException {
        conn = Connector.getConnection();
        String sql = "SELECT * FROM tbl_User ORDER BY userId DESC LIMIT 1";
        preStm = conn.prepareStatement(sql);
        rs = preStm.executeQuery();
        if (rs.next()) {
            return rs.getString("userId");
        }
        return null;
    }
}
