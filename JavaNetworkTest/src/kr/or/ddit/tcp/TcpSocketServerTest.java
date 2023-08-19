package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpSocketServerTest {

	public static void main(String[] args) throws IOException {
		
	   //소켓이란?
       //두 호스트간 통신을 하기 위한 양 끝단(Endpoint)을 말한다.
      
       //TCP 소켓 통신을 하기 위해 ServerSocket객체 생성하기.
       ServerSocket server = new ServerSocket(7777); // int 포트번호 지정
       //0부터 1023까지는 예약된 숫자라 그 뒤부터 써야됨. 1521은 오라클
       //ip는 로컬이라 필요x
       System.out.println("서버가 접속을 기다립니다...");
      
       // accept()메서드는 Client에서 연결 요청이 올 때까지 계속 기다린다.
       // 연결 요청이 오면 Socket(객체)를 생성해서 Client의 Socket과 연결한다
       Socket socket = server.accept(); //클라이언트에서 소켓이 만들어지면(new Socket)리턴됨. 기다리는게 join이랑 비슷
       // 클라이언트가 접속 요청 안하면 여기서 멈춰있음
       // 스레드랑 비슷한 개념???
       // 소켓은 둘만 쓸 수 있는 한쌍. 실전화기처럼. ServerSocket이 한쌍을 만들어줌

		//----------------------------------------
		// 이 이후는 클라이언트와 연결된 후의 작업을 진행하면 됨
		System.out.println("접속한 클라이언트 정보");
		System.out.println("주소: " + socket.getInetAddress());
		
		// 클라이언트에게 메세지 보내기
		
		 //OutputStream객체를 구성하여 전송한다.
	     //접속한 Socket의 getOutputStream()메서드를 이용하여 구한다
	     OutputStream out = socket.getOutputStream(); //받는 상대는 InputStream

		
		DataOutputStream dos = new DataOutputStream(out);
		dos.writeUTF("어서오세오..");  // 메시지 보내기
		System.out.println("메시지를 보냈습니다.");
		
		dos.close();
		server.close();
	}
}
