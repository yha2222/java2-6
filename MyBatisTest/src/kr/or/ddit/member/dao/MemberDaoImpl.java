package kr.or.ddit.member.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDaoImpl implements IMemberDao {

	static private MemberDaoImpl instance = null;
	private MemberDaoImpl (){}
	static public MemberDaoImpl getInstance() {
		if(instance == null) instance = new MemberDaoImpl();
		return instance;
	}
	
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	@Override
	public int insertMember(MemberVO mv) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "INSERT INTO mymember ("
						+ "mem_id, " + " mem_name,"
						+ "mem_tel, "+ "mem_addr, " + "reg_dt"
						+ ")values (?, ?, ?, ?, sysdate) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemId());
			pstmt.setString(2, mv.getMemName());
			pstmt.setString(3, mv.getMemTel());
			pstmt.setString(4, mv.getMemAddr());
			
			cnt = pstmt.executeUpdate();
						
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;		
	}

	@Override
	public int updateMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "update mymember " +
						"set mem_name = ?, " + 
						"mem_tel = ?, " + 
						"mem_addr = ? " + 
						"where mem_id =?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemName());
			pstmt.setString(2, mv.getMemTel());
			pstmt.setString(3, mv.getMemAddr());
			pstmt.setString(4, mv.getMemId());
			
			cnt = pstmt.executeUpdate();
	
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		
		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "delete from mymember where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		
		boolean isExist = false; // 없음
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "select count(*) as cnt"
					+ " from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery(); // result set값 리턴 <= select
			
			int cnt = 0;
			
			while(rs.next()) { // 있는지 확인
				cnt = rs.getInt("CNT");
			}
			
			if(cnt > 0) {
				isExist = true;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs); // 메모리 누수 방지
		}
		
		return isExist;
	}

	@Override
	public List<MemberVO> selectAll() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();

		try {
			conn = JDBCUtil3.getConnection();
			
			stmt = conn.createStatement();
			
			String sql = "select * from mymember ";
			
			rs = stmt.executeQuery(sql);
	
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				Date regDt = rs.getTimestamp("reg_dt");
				
				MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
				mv.setRegDt(regDt);
				
				memList.add(mv);
			}
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return memList;
	}


	@Override
	public List<MemberVO> searchMember(MemberVO praramMv) {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from mymember where 1=1";
			
			// dynamic query
			if(praramMv.getMemId() != null && !praramMv.getMemId().equals("")) { // 사용자가 분명히 입력함
				sql += " and mem_id = ? ";
			}
			if(praramMv.getMemName() != null && !praramMv.getMemName().equals("")) {
				sql += " and mem_name = ? ";
			}
			if(praramMv.getMemTel() != null && !praramMv.getMemTel().equals("")) {
				sql += " and mem_tel = ? ";
			}
			if(praramMv.getMemAddr() != null && !praramMv.getMemAddr().equals("")) {
				sql += " and mem_addr like '%' || ? || '%'";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1; // ? 위치는 1부터 시작함
			
			if(praramMv.getMemId() != null && !praramMv.getMemId().equals("")) { // 사용자가 분명히 입력함
				pstmt.setString(index++, praramMv.getMemId());
			}
			if(praramMv.getMemName() != null && !praramMv.getMemName().equals("")) {
				pstmt.setString(index++, praramMv.getMemName());
			}
			if(praramMv.getMemTel() != null && !praramMv.getMemTel().equals("")) {
				pstmt.setString(index++, praramMv.getMemTel());
			}
			if(praramMv.getMemAddr() != null && !praramMv.getMemAddr().equals("")) {
				pstmt.setString(index++, praramMv.getMemAddr());
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				Date regDt = rs.getTimestamp("reg_dt");
				
				MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
				mv.setRegDt(regDt);
				
				memList.add(mv);
			}
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return memList;
	}
	
}
