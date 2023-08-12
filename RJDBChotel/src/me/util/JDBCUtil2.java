package me.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil2 {
	
	static Properties prop;
	
	static {
		prop = new Properties();
		
		try {
			prop.load(new FileInputStream("./res/db.properties"));
			
			Class.forName(prop.getProperty("drv"));
			System.out.println("드라이버 로딩 완료");
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					prop.getProperty("juso"),
					prop.getProperty("ireum"),
					prop.getProperty("bibun"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void close(Connection conn, Statement stmt,
						PreparedStatement pstmt, ResultSet rs) {
		if(conn != null)try {conn.close();}catch(SQLException ex) {}
		if(stmt != null)try {stmt.close();}catch(SQLException ex) {}
		if(pstmt != null)try {pstmt.close();}catch(SQLException ex) {}
		if(rs != null)try {rs.close();}catch(SQLException ex) {}
	}
}
