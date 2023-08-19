package me.board.service;

import java.util.List;

import me.board.dao.BoardDaoImpl;
import me.board.dao.IBoardDao;
import me.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	
	private IBoardDao brdDao;
	
	public BoardServiceImpl() {
		brdDao = BoardDaoImpl.getInstance();
	}

	@Override
	public int registBoard(BoardVO bv) {

		int cnt = brdDao.insertBoard(bv);
		return cnt;
	}

	@Override
	public int modifyBoard(BoardVO bv) {
		int cnt = brdDao.updateBoard(bv);
		return cnt;
	}

	@Override
	public int deleteBoard(String brdNo) {
		int cnt = brdDao.deleteBoard(brdNo);
		return cnt;
	}

	@Override
	public boolean checkBoard(String brdNo) {
		boolean isExist = brdDao.checkBoard(brdNo);
		return isExist;
	}

	@Override
	public List<BoardVO> selectAll() {
		List<BoardVO> brdList = brdDao.selectAll();
		return brdList;
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		List<BoardVO> brdList = brdDao.searchBoard(bv);
		return brdList;
	}

	
}
