package dao;

import helper.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;
import model.User;

public class UserDao {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public void closeConnection() throws Exception {
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

    public User getUserByName(String fullName) throws Exception {
        try {
            conn = Connector.getConnection();
            String sql = "SELECT * FROM tbl_user WHERE fullName = ?";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, fullName);
            rs = preStm.executeQuery();
            while (rs.next()) {
                String userId = rs.getString("userId");
                Integer password = rs.getInt("password");
                Integer role = rs.getInt("role");

                return new User(userId, fullName, password, role);
            }
        } finally {
            this.closeConnection();
        }
        return null;
    }

    public void addUser(String userName, Integer password) throws Exception {
        try {
            conn = Connector.getConnection();
            String sql = "INSERT tbl_User (userId, password, fullName, role) VALUES (? , ?, ?, 2)";

            UUID uuid = UUID.randomUUID();

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, uuid.toString().substring(30));
            preStm.setString(2, Integer.toString(password));
            preStm.setString(3, userName);
            rs = preStm.executeQuery();
        } finally {
            this.closeConnection();
        }
    }

    public void updatePassword(String userName, Integer newPassword) throws Exception {
        try {
            conn = Connector.getConnection();
            String sql = "UPDATE tbl_User SET password = ? WHERE fullName = ?";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, Integer.toString(newPassword));
            preStm.setString(2, userName);
            preStm.executeUpdate();
        } finally {
            this.closeConnection();
        }
    }

    public ArrayList<User> getUserList() throws Exception {
        try {
            ArrayList<User> arrs = new ArrayList<>();
            conn = Connector.getConnection();
            String sql = "SELECT * FROM tbl_User";

            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                String userId = rs.getString("userId");
                Integer password = rs.getInt("password");
                String fullName = rs.getString("fullName");
                Integer role = rs.getInt("role");
                arrs.add(new User(userId, fullName, password, role));
            }
            return arrs;
        } finally {
            this.closeConnection();
        }
    }

    public void updateUser(String userName, Integer password, Integer role) throws Exception {
        try {
            conn = Connector.getConnection();
            String sql = "UPDATE tbl_User SET password = ?, role = ? WHERE fullName = ?";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, Integer.toString(password));
            preStm.setString(2, Integer.toString(role));
            preStm.setString(3, userName);
            preStm.executeUpdate();
        } finally {
            this.closeConnection();
        }
    }
}
