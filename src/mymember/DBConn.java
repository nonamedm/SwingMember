package mymember;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn {
	
	private static String driver = "oracle.jdbc.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String dbUid = "PRACTICE";
	private static String dbPwd= "1234";
	
	private static Connection 		  conn = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet 		  rs = null;
	
	public DBConn() {
		
	}
	

	
	public void close(){
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			
		}
		
	}

	public static Connection getInstance() {		// 
		if(conn!=null)			// null 일때만 아래 try문을 사용해서 new 하는거고 이후에는 기존값 그대로 리턴
			return conn;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,dbUid,dbPwd);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	
	
}
