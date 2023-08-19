package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class URLConnectionTest {
   public static void main(String[] args) throws IOException {
      
      // URLConnection => 애플리케이션과 URL간의 통신 연결을 위한 추상 클래스
      
      // 특정 서버(예:네이버)에 접속하여 정보 가져오기
      URL url = new URL("https://www.naver.com/index.html");
      
      // Header 정보 가져오기
      
      // URLConnection객체 생성하기
      URLConnection urlConn = url.openConnection();
      
      System.out.println("Content-Type : " + urlConn.getContentType());
      System.out.println("Encoding : " + urlConn.getContentEncoding());
      System.out.println("Content : " + urlConn.getContent());
      System.out.println();
      
      // 전체 Header정보 출력하기
      Map<String, List<String>> headerMap = urlConn.getHeaderFields();
      
      // Header의 key값 구하기
      Iterator<String> iterator = headerMap.keySet().iterator();
      
      while(iterator.hasNext()) {
         String key = iterator.next();
         System.out.println(key + " : " + headerMap.get(key));
      }
      System.out.println("----------------------------------------");
      
      // HTTPInputStream타입을 InputStream타입으로 바꿔줬어요
      InputStream is = (InputStream) urlConn.getContent();
      InputStreamReader isr = new InputStreamReader(is);
      
      int data = 0;
      
      while((data = isr.read()) != -1) {
         System.out.print((char) data);
      }
   }
}