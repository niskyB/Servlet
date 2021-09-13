package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import helper.Connector;
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

}
