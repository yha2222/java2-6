package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/*
 	소켓을 통해 메시지를 보내는 역할 담당
 */

public class Sender extends Thread {
	private Scanner scan;
	private String name;
	private DataOutputStream dos;
	
	public Sender(Socket socket) {
		name = "[" + socket.getLocalAddress() + " : "
				+ socket.getLocalPort() + "]";
		scan = new Scanner(System.in);
		
		try {			
			dos = new DataOutputStream(socket.getOutputStream());
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
		public void run() {
			while(dos != null) {
				try {
					dos.writeUTF(name + " >>> " + scan.nextLine());
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
		}
}
