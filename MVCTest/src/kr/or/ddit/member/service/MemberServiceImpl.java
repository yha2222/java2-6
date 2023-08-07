package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	
	// Service에서 DAO랑 연결하니까
	// DAO의 각종 메서드들을 사용하기 위해 DAO타입의 변수를 선언하고 거기에 getInstance()로 객체 할당해주기
	private IMemberDao memDao;
	
	// Service도 싱글톤 패턴으로 만들어서 리턴해주기 위해 Service타입의 변수 선언
	private static IMemberService memService;
	
	// 객체 생성 => 생성자
	// 생성자를 private으로 선언, 객체가 new로 더이상 만들어지면 안되기 때문
//	언니 사랑해 그래 나도 사랑해 내 마음 알지? 그럼 나도 알지 모를 수가 없어 맞아 모를 수가 없어 내가 매일매일 언니를 찾아오는데.... 알랍쀼오~~예~~예~~
	private MemberServiceImpl() {
		// 싱글톤패턴으로 만들어진 DAO의 객체를 memDao에 할당시켜줌(getInstance()의 리턴타입이 DAO의 객체(단하나))
		memDao = MemberDaoImpl.getIntance();
	}
	
	// Service의 객체 리턴, 무조건 한개의 객체만 리턴해줌
	public static IMemberService getInstance() {
		if(memService == null) {
			memService = new MemberServiceImpl();
		}
		return memService;
	}

	@Override
	public int registMember(MemberVO mv) {
		
		int cnt = memDao.insertMember(mv);
		
		// 계좌 100만 인출
		// 다오 업데이트
		
		// 계좌 100만 입금
		// 다오 업데이트
		
		return cnt;
	}

	@Override
	public int modifyMember(MemberVO mv) {
		
		int cnt = memDao.updateMember(mv);
		
		return cnt;
	}

	@Override
	public int removeMember(String memId) {
		
		int cnt = memDao.deleteMember(memId);
		
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		
		boolean isExist = memDao.checkMember(memId);
		
		return isExist;
	}

	@Override
	public List<MemberVO> selectAll() {
		
		List<MemberVO> memList = memDao.selectAll();
		
		return memList;
	}

}
