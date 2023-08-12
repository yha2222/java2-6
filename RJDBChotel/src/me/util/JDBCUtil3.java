package me.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class JDBCUtil3 {

	static ResourceBundle bundle;
	
	static {
		bundle = ResourceBundle.getBundle("db");
		
		try {
			
			Class.forName(bundle.getString("ireum"));
			System.out.println("드라이버 로딩 완료!");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					bundle.getString("drv"),
					bundle.getString("bibun"),
					bundle.getString("juso"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void close(Connection conn, 
								Statement stmt, 
								PreparedStatement pstmt, 
								ResultSet rs) {
	if(rs != null) try {rs.close();}catch(SQLException ex) {}
	if(stmt != null) try {stmt.close();}catch(SQLException ex) {}
	if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
	if(conn != null) try {conn.close();}catch(SQLException ex) {}
}
}
