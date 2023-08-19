package kr.or.ddit.udp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpFileReceiver {
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] buffer;
	
	public UdpFileReceiver(int port) {
		try {
			// 데이터 수신을 위한 포트번호 설정
			ds = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}	
	
	// 시작메서드
	public void start() throws IOException {
		long fileSize = 0;
		long totalReadBytes = 0;
		
		int readBytes = 0;
		
		System.out.println("파일 수신 대기중...");
		
		String str = new String(receiveData()).trim();
		// 상대방이 데이터 보낼 때까지 대기
		// 제일 처음에 str에는 start 받음
		
		if(str.equals("start")) { // Sender에서 start를 보낸 경우
			
			// 파일명 받기 , str = 파일명
			str = new String(receiveData()).trim();
			
			FileOutputStream fos = new FileOutputStream("d:/D_Other/" + str);
			
			// 전송 파일 크기(byte) 받기
			str = new String(receiveData()).trim();
			
			fileSize = Long.parseLong(str);
			
			long startTime = System.currentTimeMillis();
			
			while(true) {
				byte[] data = receiveData();
				
				readBytes = dp.getLength(); // receive한 데이터의 총 length 알려줌
				
				fos.write(data, 0, readBytes); // 위에서 안 데이터 여기서 사용
				
				totalReadBytes += readBytes;
				
				System.out.println("진행 상태 : " + totalReadBytes + "/"
						+ fileSize +" Byte(s) ("
						+ (totalReadBytes * 100 / fileSize) + " %)");
				
				if(totalReadBytes >= fileSize) {
					break;
				}
				
			}
			
			long endTime = System.currentTimeMillis();
			long diffTime = endTime - startTime;
			double transferSpeed = fileSize / diffTime;
			
			System.out.println("걸린 시간 : " + diffTime + " (ms)");
			System.out.println("평균 수신 속도 : " + transferSpeed
					+ " (Bytes/ms)");
			System.out.println("수신 완료...");
			
			fos.close();
			ds.close();
		}
	}

	/*
	 	데이터 수신하기
	 	@return 수신한 바이트배열 데이터
	 */
	private byte[] receiveData() throws IOException {

		buffer = new byte[1000]; // 버퍼 초기화
		dp = new DatagramPacket(buffer, buffer.length);
		ds.receive(dp);
		
		return dp.getData();
	}
	
	public static void main(String[] args) throws Exception {
		new UdpFileReceiver(8888).start();
	}
}
