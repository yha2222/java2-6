package kr.or.ddit.basic;

public class SingletonTest {
	public static void main(String[] args) {
		
		//MySingleton test1 = new MySingleton();  // private이라 자동 완성 안됨
		
		// getInstance()메서드를 이용하여 객체 생성
		MySingleton test2 = MySingleton.getInstance();
		MySingleton test3 = MySingleton.getInstance();
		
		System.out.println("test2 => " + test2);
		System.out.println("test3 => " + test3);
		
		test2.displayText();
		test3.displayText();
	}
}
