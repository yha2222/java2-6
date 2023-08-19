package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpClient {

	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] msg; // 데이터 수신을 위한 바이트 배열
	
	public UdpClient() {
		try {
			msg = new byte[100];
			
			ds = new DatagramSocket();
			// 소켓 객체 생성(명시하지 않으면 포트번호는 이용 가능한 임의의 포트번호 할당됨)
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	// 시작 메서드
	public void start() {
		try {
			
			InetAddress serverAddr = InetAddress.getByName("192.168.145.13");
			dp = new DatagramPacket(msg, 1, serverAddr, 8887);
			// msg(+뫃든데이터) => 바이트 배열, 테스트니까?1바이트만 보낼게(다보내려면msg.length),데이터세팅 초기화-length만큼 저장할 수 있도록
			ds.send(dp);
			
			dp = new DatagramPacket(msg, msg.length);
			ds.receive(dp);
			
			System.out.println("현재 서버 시간 => " + new String(dp.getData())); // 바이트 배열로 받으니까 다시 문자열화
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new UdpClient().start();
	}
}
