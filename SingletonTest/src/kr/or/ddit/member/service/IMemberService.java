package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

/*
 	Service의 Interface
 */
public interface IMemberService {
	/*
	 	회원정보 등록을 위한 메서드
	 	mv DB에 수정할 데이터가 담겨진 MemberVO객체
	 	DB작없이 성공하면 1이상의 값이 반환, 실패하면 0이 반환됨.
	 */
	
	public int registMember(MemberVO mv);
	
	/*
	 	회원정보 수정하기 위한 메서드
	 	mv DB에 수정할 데이터가 담겨진 MemberVO객체
	 	DB작없이 성공하면 1이상의 값이 반환, 실패하면 0이 반환됨.
	 */
	public int modifyMember(MemberVO mv);
	
	/*
	 	해당 ID에 해당하는 회원정보를 삭제하기 위한 메서드
	 	memId 삭제할 회원ID
	 	삭제 성공하면 1, 실패하면 0이 반환.
	 */
	public int removeMember(String memId);
	
	/*
	 	해당 ID에 해당하는 회원정보가 존재하는지 확인하기 위한 메서드
	 	memId 체크할 회원ID
	 	해당 회원이 존재하면 true, 존재하지 않으면 false 리턴함.
	 */
	public boolean checkMember(String memId);
	
	/*
	 	전체 회원정보를 가져오기 위한 메서드
	 	전체 회원정보를 담은 리스트
	 */
	public List<MemberVO> selectAll();
	
	/*
 	회원 정보를 검색하기 위한 메서드
 	검색할 회원 정보를 담은 MemberVo 객체
 	검색된 회원 정보를 담은 List 객체
 	검색 조건 담는 param 있어야 됨 => searchMember(검색조건)
 */
	public List<MemberVO> searchMember(MemberVO mv);

}
