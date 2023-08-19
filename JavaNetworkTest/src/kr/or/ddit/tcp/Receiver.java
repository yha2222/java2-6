package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

// 이 클래스는 소켓에서 메시지를 받아와 화면에 출력하는 역할 담당
public class Receiver extends Thread {
	private DataInputStream dis;
	
	public Receiver(Socket socket) {
		try {
			dis = new DataInputStream(socket.getInputStream());
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(dis != null) {
			try {
				System.out.println(dis.readUTF());
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
