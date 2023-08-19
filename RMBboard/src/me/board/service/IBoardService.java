package me.board.service;

import java.util.List;

import me.board.vo.BoardVO;

// Service의 Interface
public interface IBoardService {

	// 등록 - 등록할 데이터랑 비교
	public int registBoard(BoardVO mv);
	
	// 수정 - 수정할 데이터 담긴 객체
	public int modifyBoard(BoardVO mv);
	
	// 삭제 - 삭제할 게시판 번호
	public int deleteBoard(String brdNo);
	
	// 존재 여부 - 체크할 게시판 번호
	public boolean checkBoard(String brdNo);
	
	// 전체 조회
	public List<BoardVO> selectAll();
	
	// 정보 검색 - List:검색된 회원 정보 담을 객체 / BoardVO:검색할 회원 정보 담을 객체 => mv:검색조건
	public List<BoardVO> searchBoard(BoardVO mv);
}
