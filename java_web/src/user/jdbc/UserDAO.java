package user.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datasource.DBCPUtil;
import util.SHAUtil;
public class UserDAO {
	
	public static Connection con = null;
	public static String url = "jdbc:mysql://localhost:3306/java_web";
	public static String root = "root";
	public static String passWord = "administrator";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, root, passWord);	
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("in static");
	}
	
	// Constructor method
	public UserDAO() {
	}
	
	public static void main(String[] args){
		UserDAO dao = new UserDAO();
	}
	
	public int insertUser(User user) throws Exception {
		
		// Connection
		String url = "jdbc:mysql://localhost:3306/java_web";
		String root = "root";
		String passWord = "administrator";
		Connection con = DriverManager.getConnection(url, root, passWord);		
		System.out.println("Connection is class of: " + con.getClass());
		
		// Statement
		PreparedStatement preStatement = con.prepareStatement("INSERT INTO USER VALUES(?, ?, ?, ?, ?)");
		preStatement.setString(1, user.getId());
		preStatement.setString(2, user.getName());
		preStatement.setString(3, user.getGender());
		preStatement.setInt(4, user.getAge());
		preStatement.setString(5, SHAUtil.process(user.getPassword()));
		 
		// Result
		int result = preStatement.executeUpdate();
		return result;
	}
	
	public User getUserById(String id) throws Exception {
		
		// Connection
		String url = "jdbc:mysql://localhost:3306/java_web";
		String root = "root";
		String passWord = "administrator";
		Connection con = DriverManager.getConnection(url, root, passWord);		
		System.out.println("Connection is class of: " + con.getClass());
		
		// Statement
		PreparedStatement preStatement = con.prepareStatement("SELECT * FROM USER WHERE ID = ?");
		preStatement.setString(1, id);
		ResultSet resultSet = preStatement.executeQuery();
		if(resultSet.next()) {
			String userId = resultSet.getString("ID");
			String name = resultSet.getString("NAME");
			String gender = resultSet.getString("GENDER");
			int age = resultSet.getInt("AGE");
			String password = resultSet.getString("PASSWORD");
			User user = new User(userId, name, gender, age, password);
			return user;
		} else {
			return null;
		}
	}
	
	public List<User> getAllUsers() throws Exception {
		
		// Connection
//		String url = "jdbc:mysql://localhost:3306/java_web";
//		String root = "root";
//		String passWord = "administrator";
//		Connection con = DriverManager.getConnection(url, root, passWord);	
		
		// Connection con = Pool.getConnection();
		Connection con = DBCPUtil.getConnection();
		
		System.out.println("Connection is class of: " + con.getClass());
		
		// Statement
		PreparedStatement preStatement = con.prepareStatement("SELECT * FROM USER");
		ResultSet resultSet = preStatement.executeQuery();
		List<User> userList = new ArrayList<User>();
		while(resultSet.next()) {
			String userId = resultSet.getString("ID");
			String name = resultSet.getString("NAME");
			String gender = resultSet.getString("GENDER");
			int age = resultSet.getInt("AGE");
			String password = resultSet.getString("PASSWORD");
			User user = new User(userId, name, gender, age, password);
			userList.add(user);
		}
		
		return userList;
	}
}