package kr.or.ddit.util;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	// 클래스 정보 로딩되는 시점에 처음 한번만 실행 => static 블록 안에 넣어놓기 권장
	static {
		try {
			// 드라이버 로딩 확인
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 완료!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	// 커넥션 객체 가져오기 
	// @return Connection 객체, 예외 발생 시 null 리턴함
	public static Connection getConnection() {
		
		try {
			return DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"pc22", "java");   // 정상적일 때 커넥션 객체
		} catch (SQLException e) {
			e.printStackTrace();
			return null;  // 비정상일 때 null값 리턴
		}
	}
	// 나 천재 ? ㅋㅋ
	// 자원반납
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
