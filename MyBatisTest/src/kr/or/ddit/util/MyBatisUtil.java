package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("config/mybatis-config.xml");
			
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd); // Builder로 sqlSessionFactory 생성
			rd.close(); // Reader객체 닫기
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/*
	 * SqlSession객체를 제공하는 팩토리 메서드
	 *   SqlSession 객체
	 */
	public static SqlSession getInstance() {
		return sqlSessionFactory.openSession();
	}
	
	/*
	 * SqlSession객체를 제공하는 팩토리 메서드
	 *  autoCommit 오토커밋 여부
	 *   SqlSession 객체
	 */
	public static SqlSession getInstance(boolean autoCommit) {
		return sqlSessionFactory.openSession(autoCommit);
	}
}
