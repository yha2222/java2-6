package me.board.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import me.board.vo.BoardVO;
import me.util.MyBatisUtil;

public class BoardDaoImpl implements IBoardDao {
	
	static private BoardDaoImpl instance = null;
	private BoardDaoImpl() {}
	static public BoardDaoImpl getInstance() {
		if(instance == null) instance = new BoardDaoImpl();
		return instance;
	}

	@Override
	public int insertBoard(BoardVO bv) {
		SqlSession session = MyBatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			cnt = session.insert("board.insertBoard", bv);
			if(cnt > 0) {
				session.commit();
			}
		} catch (PersistenceException ex) {
			session.rollback();
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		SqlSession session = MyBatisUtil.getInstance();
		
		try {
			
			cnt = session.update("board.updateBoard", bv);
			
			if(cnt > 0) {
				session.commit();
			}
			
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(String brdNo) {

		int cnt = 0;
		SqlSession session = MyBatisUtil.getInstance();
		
		try {
			
			cnt = session.delete("board.deleteBoard");
			
			if(cnt > 0) {
				session.commit();
			}
			
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}
		
		return cnt;
	}

	@Override
	public boolean checkBoard(String brdNo) {

		boolean isExist = false;
		SqlSession session = MyBatisUtil.getInstance(true);
		
		try {
			
			int cnt = session.selectOne("board.checkBoard", brdNo);
			
			if(cnt > 0) {
				isExist = true;
			}
			
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return isExist;
	}

	@Override
	public List<BoardVO> selectAll() {

		List<BoardVO> brdList = new ArrayList<BoardVO>();
		SqlSession session = MyBatisUtil.getInstance(true);
		
		try {
			
			brdList = session.selectList("board.selectAll");
			
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}

		return brdList;
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO paramBv) {
		List<BoardVO> brdList = new ArrayList<BoardVO>();
		SqlSession session = MyBatisUtil.getInstance(true);
		
		try {
			
			brdList = session.selectList("board.searchBoard", paramBv);
			
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return brdList;
	}

	
}
