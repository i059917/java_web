package pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class Pool {
	
	private static List<Connection> connectionList = new ArrayList<>();
	
	static {
		for(int i = 0; i < 3; i++ ) {
			String url = "jdbc:mysql://localhost:3306/java_web";
			String root = "root";
			String passWord = "administrator";
			try {
				Connection con = DriverManager.getConnection(url, root, passWord);
				connectionList.add(con);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// 并发 Concurrency
	// 同步 Synchronization
	
	// User 1.
	// User 2.
	// 排队执行
	synchronized public static Connection getConnection() throws Exception {
		while(connectionList.isEmpty()) {
			Thread.sleep(1000);
		}
		
		Connection con = connectionList.remove(0);
		return con;
	}
	
	synchronized public static void returnConnection(Connection con) {
		connectionList.add(con);
	}
}
