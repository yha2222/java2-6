package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpSocketClientTest {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		String serverIp = "192.168.145.13";
		// 자기 자신 컴퓨터를 나타내는 방법
		// IP : 127.0.0.1
		// hostname : localhost
		
		System.out.println(serverIp + " 서버에 접속 중입니다.");
		
		// 소켓을 생성해서 서버에 연결을 요청
		Socket socket = new Socket(serverIp, 7777);
		
		// 연결이 되면 이후의 명령이 실행됨
		System.out.println("연결되었습니다...");
		
		// 서버에서 보내온 메시지 받기
		// 메시지를 받기 위해 InputStream객체를 생성
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		// 서버로부터 받은 메시지 출력하기
		System.out.println("받은 메시지 : " + dis.readUTF());
		
		System.out.println("연결 종료..");
		
		dis.close();
		socket.close();
		
	}
}
