package ko.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class MyBatisTest {

	public static void main(String[] args) {
		// myBatis(자바랑 쿼리랑 분리!)를 이용하여 DB자료를 처리하는 작업 순서
		
		// 1. myBatis의 환경설정 파일을 읽어와 실행시킨다.
		SqlSessionFactory sessionFactory = null;
		
		try {
			// 1-1. xml문 읽어오기
			// 설정파일의 인코딩 설정(한글 처리를 위해서)
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			// mybatis 설정 관련된 부분 저장할 곳! 이름은 걍 지은 거
			Reader rd = Resources.getResourceAsReader("config/mybatis-config.xml"); // 파일명만 쓰면 클래스 패스
			
			// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			sessionFactory = new SqlSessionFactoryBuilder().build(rd);
			rd.close(); // Reader객체 닫기
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행
		// 2-1. insert 작업 연습
		System.out.println("insert 작업 시작...");
		
		// 1) 저장할 데이터를 VO에 담음
		MemberVO mv = new MemberVO();
		mv.setMemId("d002");
		mv.setMemName("강감찬");
		mv.setMemTel("010-222-2222");
		mv.setMemAddr("경남 진해시");
		
		// 2) SqlSession 객체 변수를 이용하여 해당 쿼리문 실행
		// 	형식] sqlSession.insert("namespace값.id값", 파라미터객체)
		//	 반환값 : 성공한 레코드 수
		
		SqlSession sqlSession = sessionFactory.openSession(false); 
		// 생략 가능 = false => autoCommit 여부 >> commit할지 rollback할지 알아서 할게
		
		try {
			
			int cnt = sqlSession.insert("memberTest.insertMember", mv);
			
			if(cnt > 0) {
				System.out.println("insert 작업 성공");
				sqlSession.commit(); // 커밋
			}else {
				System.out.println("insert 작업 실패!!");
			}
			
		}catch(PersistenceException ex) {
			ex.printStackTrace();
			sqlSession.rollback(); // 롤백
		}finally {
			sqlSession.close();  // 세션 닫기
		}
		System.out.println("-----------------------------------");
		
		// 2-2. update 작업 연습
		System.out.println("update 작업 시작...");
		
		MemberVO mv2 = new MemberVO();
		mv2.setMemId("d002");
		mv2.setMemName("아무개");
		mv2.setMemTel("010-5555-5555");
		mv2.setMemAddr("대구시");
		
		sqlSession = sessionFactory.openSession();
		
		try {
			// update()메서드의 반환값도 성공한 레코드 수이다.
			int cnt = sqlSession.update("memberTest.updateMember", mv2);
			
			if(cnt > 0) { // 성공!
				System.out.println("update 작업 성공");
				sqlSession.commit();
			}else {
				System.out.println("update 작업 실패!!");
			}
			
		}catch(PersistenceException ex) {
			ex.printStackTrace();
			sqlSession.rollback();
		}finally {
			sqlSession.close();
		}
		
		System.out.println("-----------------------------");
		
		// 2-3. delete 연습
		/*
		 * System.out.println("delete 작업 시작..");
		 * 
		 * sqlSession = sessionFactory.openSession();
		 * 
		 * try {
		 * 
		 * int cnt = sqlSession.delete( "memberTest.deleteMember", "hihi"); if(cnt > 0)
		 * { System.out.println("delete 작업 성공"); sqlSession.commit(); }else {
		 * System.out.println("delete 작업 실패"); }
		 * 
		 * }catch(PersistenceException ex) { ex.printStackTrace(); sql~.rollback(); }finally {
		 * sqlSession.close(); }
		 */
		
		// 2-4. select 연습
		// 1) 응답 결과가 여러 개인 경우 => selectList
		System.out.println("select 연습(결과가 여러개일 경우) 시작");
		System.out.println();
		
		sqlSession = sessionFactory.openSession(true);
		
		try {
			
			List<MemberVO> memList = sqlSession.selectList("memberTest.selectAll");
			
			for(MemberVO mv3 : memList) {
				System.out.println("회원ID : " + mv3.getMemId());
				System.out.println("회원이름 : " + mv3.getMemName());
				System.out.println("회원전화 : " + mv3.getMemTel());
				System.out.println("회원주소 : " + mv3.getMemAddr());
				System.out.println("-------------------------------");
			}
			System.out.println("목록 출력 끝.");
		}catch(PersistenceException ex) {
			ex.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		// 2) 응답 결과가 1개일 경우
		System.out.println("select 연습(결과가 한 개일 경우) 시작");
		System.out.println();
		
		sqlSession = sessionFactory.openSession(true);
		
		try {
			// 응답 결과가 1개가 확실할 경우에는 selectOne() 메서드 사용
			MemberVO mv4 = sqlSession.selectOne("memberTest.getMember", "d002");
			System.out.println("회원ID : " + mv4.getMemId());
			System.out.println("회원이름 : " + mv4.getMemName());
			System.out.println("회원전화 : " + mv4.getMemTel());
			System.out.println("회원주소 : " + mv4.getMemAddr());
			System.out.println("-------------------------------");
			
		}catch(PersistenceException ex) {
			ex.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		
		
	}
}
