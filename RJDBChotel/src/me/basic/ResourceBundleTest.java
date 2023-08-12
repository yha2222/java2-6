package me.basic;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleTest {

	public static void main(String[] args) {
		ResourceBundle bundle = ResourceBundle.getBundle("db", Locale.CANADA);
		
		Enumeration<String> keys = bundle.getKeys();
		
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = bundle.getString(key);
			
			System.out.println(key + " => " + value);
		}
		System.out.println("출력 끝");
	}
}
