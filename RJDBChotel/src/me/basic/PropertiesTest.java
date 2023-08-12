package me.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args) {
		
		Properties prop = new Properties();
		
		try {
			
			prop.load(new FileInputStream("./res/db.properties"));
			Enumeration<String> keys = (Enumeration<String>) prop.propertyNames();
			
			while(keys.hasMoreElements()) {
				String key = keys.nextElement();
				String value = prop.getProperty(key);
				System.out.println(key + " => " + value);
			}
			
			System.out.println("출력 끝");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
}
