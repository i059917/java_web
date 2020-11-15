import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.jdbc.Driver");
		
		String url = "jdbc:mysql://localhost:3306/java_web";
		String user = "root";
		String password = "administrator";
		
		Connection con = DriverManager.getConnection(url, user, password);
		
		Statement statement = con.createStatement();
		
		int row = statement.executeUpdate("DELETE FROM STUDENT WHERE ID = 1001");
		System.out.println(row + " deleted");
		
		ResultSet resultSet = statement.executeQuery("SELECT * FROM STUDENT");
		
		while(resultSet.next()) {
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			String gender = resultSet.getString(3);
			int age = resultSet.getInt(4);
			System.out.println(id + " " + name  + " " + gender + " " + age);
		}
		
		try {
			resultSet.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
			statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
