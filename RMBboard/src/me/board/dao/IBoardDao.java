package me.board.dao;

import java.util.List;

import me.board.vo.BoardVO;

public interface IBoardDao {
	
	public int insertBoard(BoardVO bv);
	
	public int updateBoard(BoardVO bv);
	
	public int deleteBoard(String brdNo);
	
	public boolean checkBoard(String brdNo);
	
	public List<BoardVO> selectAll();
	
	public List<BoardVO> searchBoard(BoardVO mv);

	
}
