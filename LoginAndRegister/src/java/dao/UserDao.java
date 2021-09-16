package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import helper.Connector;
import java.util.ArrayList;
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

    public void updatePassword(String username, Integer newPassword) throws Exception {
        try {
            conn = Connector.getConnection();
            String sql = "UPDATE tbl_User SET password = ? WHERE fullName = ?";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, Integer.toString(newPassword));
            preStm.setString(2, username);
            rs = preStm.executeQuery();
        } finally {
            this.closeConnection();
        }
    }

    public void updateAllInformation(String username, Integer password, Integer role) throws Exception {
        try {
            conn = Connector.getConnection();
            String sql = "UPDATE tbl_User SET password = ?, role = ? WHERE fullName = ?";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, Integer.toString(password));
            preStm.setString(2, Integer.toString(role));
            preStm.setString(3, username);
            rs = preStm.executeQuery();
        } finally {
            this.closeConnection();
        }
    }

    public ArrayList<User> getAllUsers() throws Exception {
        ArrayList<User> users = new ArrayList<>();
        try {
            conn = Connector.getConnection();
            String sql = "SELECT * FROM tbl_User";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                String userId = rs.getString("userId");
                Integer password = rs.getInt("password");
                String fullName = rs.getString("fullName");
                Integer role = rs.getInt("role");
                users.add(new User(userId, password, fullName, role));
            }
        } finally {
            this.closeConnection();
        }
        return users;
    }
}
