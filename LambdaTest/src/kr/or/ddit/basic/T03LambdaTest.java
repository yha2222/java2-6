package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class T03LambdaTest {

	public static void main(String[] args) {

		List<String> strList = new ArrayList<String>();
		
		strList.add("손동영");
		strList.add("이상민");
		strList.add("지윤서");

		for(String str : strList) {
			System.out.println(str);
		}
		
		/*  strList.forEach(new Consumer<String>() {
		  
		  @Override public void accept(String name) { System.out.println(name); } };
		 */
		// 람다식 - 함수 지향처럼 확인
		System.out.println("------------------------------------");
		strList.forEach(name -> System.out.println(name));
		
		// 로또번호 출력
		System.out.println();
		new Random().ints(1, 46).distinct().limit(6)
			.sorted().forEach(num -> System.out.print(num + " "));
	}

}
