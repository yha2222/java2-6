package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {

	private DatagramSocket ds;
	private DatagramPacket dp;
	private byte[] msg; // 패킷송수신을 위한 바이트배열 선언 - 패킷에 담는 데이터=>바이트배열=>상대방포트번호 등 중요 정보
	
	public UdpServer(int port) {
		try {
			ds = new DatagramSocket(8888); // 메시지 수신을 위한 포트번호 설정 - 세팅 안하면 랜덤
		} catch (SocketException ex) {
			ex.printStackTrace();
		}
	}
	
	public void start() throws IOException{
		while(true) {
			// 데이터를 수신하기 위한 패킷을 생성
			msg = new byte[1]; // 1바이트만 한 이유 : 지금 굳이 받을 필요 없음
			dp = new DatagramPacket(msg, msg.length); // 상대방 데이터 담기 위한 공간:패킷
			
			System.out.println("패킷 수신 대기 중..");
			
			// 패킷을 통해 데이터를 수신(Receive)한다
			ds.receive(dp); // 상대방이 send 할 때까지 멈춤
			
			System.out.println("패킷 수신 완료..");
			
			// 수신한 패킷으로부터 client의 IP주소와 Port번호를 얻음
			InetAddress addr = dp.getAddress();
			int port = dp.getPort();
			
			// 서버의 현재 시간을 시분초 형태([hh:mm:ss])로 반환
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]"); // 상대방이 receive하고 있다는 가정 하에
			
			String time = sdf.format(new Date()); // 서버의 현재시간
			
			msg = time.getBytes(); // 현재시간을 바이트 배열로 변환
			
			// 패킷을 생성해서 Client에게 전송
			dp = new DatagramPacket(msg, msg.length, addr, port);
			ds.send(dp);
		}
	}
	public static void main(String[] args) throws IOException {
		new UdpServer(8888).start();
	}
}
