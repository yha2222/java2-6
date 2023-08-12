package me.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import me.util.JDBCUtil;

public class HotelMain {
	
	public static void main(String[] args) {
		HotelMain hm = new HotelMain();
		hm.start();
	}

		private Connection conn;
		private Statement stmt;
		private PreparedStatement pstmt;
		private ResultSet rs;
		
		private Scanner scan = new Scanner(System.in);
		
		// 메뉴 출력
		public void displayMenu() {
			System.out.println();
			System.out.println("***********************");
			System.out.println("호텔 문을 열었습니다.");
			System.out.println("***********************");
			System.out.println();
			System.out.println("******************************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인		2.체크아웃		3.객실상태		4.업무종료");
			System.out.println("******************************************");
			System.out.println("메뉴 선택 >> ");
		}
	
		public void start() {
			int choice;
			
			do {
				displayMenu();
				choice = scan.nextInt();
				
				switch(choice) {
					case 1 :
						checkIn();
						break;
					case 2 :
						checkOut();
						break;
					case 3 :
						roomSt();
						break;
					case 4:
						System.out.println("***********************");
						System.out.println(" 호텔 문을 닫았습니다. ");
						System.out.println("***********************");
						break;
					default :
						System.out.println("잘못된 번호임");
				}
			}while(choice != 4);  // 4(종료)가 아니면 반복해라ㅏㅏ
		}

		private void roomSt() {
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.createStatement();
				String sql = "select * from hotel_mng";
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					String roomNum = rs.getString("ROOM_NUM");
					String guestNum = rs.getString("GUEST_NAME");
					
					System.out.print("방번호 : " + roomNum);
					System.out.println("  투숙객 : " + guestNum);
				}
				System.out.println("--------------------------------");
				System.out.println("출력 작업 끝!");
				
			}catch (SQLException ex) {
				ex.printStackTrace();
			}finally {
				JDBCUtil.close(conn, stmt, pstmt, rs);
			}
			
		}

		private void checkOut() {
			System.out.println();
			System.out.println("어느 방을 체크아웃 하시겠습니까?");
			System.out.println("방번호 입력 => ");
			String roomNum = scan.next();
			
			try {
				
				conn = JDBCUtil.getConnection();
				String sql = "delete from hotel_mng where ROOM_NUM = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, roomNum); // 숫자 해당 순서에 변수 넣는다??
				
				int cnt = pstmt.executeUpdate();
				
				if(cnt > 0) {
					System.out.println(roomNum + "체크아웃 성공!");
				}else {
					System.out.println(roomNum + "체크아웃 실패!");
				}
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			}finally {
				JDBCUtil.close(conn, stmt, pstmt, rs);
			}
			
			
		}

		private void checkIn() {

			boolean isExist = false;
			String rN = ""; 
			
			do {
				System.out.println();
				System.out.println("어느 방에 체크인 하시겠습니까?");
				System.out.println("방번호 >> ");
				
				rN = scan.next();
				
				isExist = checkNumber(rN);
				
				if(isExist) {
					System.out.println("이미 예약된 방입니다.\n다시 입력해주세요.");
				}
				
			}while(isExist);

			System.out.println("체크인 성명 입력");
			System.out.println("   >> ");
			String gName = scan.next();
			
			System.out.println(rN);
			System.out.println(gName);
			
			try {
				
				conn = JDBCUtil.getConnection();
				
				String sql = "INSERT INTO hotel_mng("
						+ "ROOM_NUM, " + "GUEST_NAME"
						+ ") VALUES (?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, rN);
				pstmt.setString(2, gName);
				
				int cnt = pstmt.executeUpdate();
				
				if(cnt > 0) {
					System.out.println("회원 등록 성공!");
				}else {
					System.out.println("회원 등록 실패!");
				}
				
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			}finally {
				JDBCUtil.close(conn, stmt, pstmt, rs);
			}
			
		}

		private boolean checkNumber(String rN) {
			boolean isExist = false;
			
			try {
				
				conn = JDBCUtil.getConnection();
				
				String sql = "select count(*) as cnt"
						+ " from hotel_mng where ROOM_NUM = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, rN);
				rs = pstmt.executeQuery();
				
				int cnt = 0;
				
				while(rs.next()) {
					cnt = rs.getInt("CNT");
				} //??????????????
				
				if(cnt > 0) {
					isExist = true;
				}
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			}finally {
				JDBCUtil.close(conn, stmt, pstmt, rs);
			}
			
			return isExist;
		}
		
		
}
