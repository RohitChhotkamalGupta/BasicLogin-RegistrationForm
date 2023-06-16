import java.sql.*;

public class UserDAO {

	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/information";
		String username = "root";
		String password = "";

		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	public boolean ifUserExists(String username) throws SQLException {
		String sql = "select username from userdetails where username='"+username+"'";
		Connection connection = UserDAO.getConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
		
	}
	public boolean isValidUser(String uname,String pass) throws SQLException {
		String sql = "select * from userdetails where username='"+uname+"' and password='"+pass+"'";
		Connection connection = UserDAO.getConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean insertUser(String username, String password) throws SQLException {
		String sql = "INSERT INTO userdetails VALUES (?, ?)";
		Connection connection = UserDAO.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		int rowsAffected = preparedStatement.executeUpdate();
	    if (rowsAffected > 0) {
	        return true;
	    } else {
	        return false;
	    }
	}
}
