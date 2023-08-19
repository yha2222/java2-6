package kr.or.ddit.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TcpFileClient {

	/*
	 	클라이언트는 서버에 접속하여 서버가 보내주는 요청 파일을 받아 하드디스크에 저장한다
	 */
	
	private Socket socket;
	private FileOutputStream fos;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public void clientstart() {
		File file = new File("d:/D_Other/spongebob.png"); // 저장할 파일 경로 - 파일명 똑같아야 됨
		
		try {
			
			socket = new Socket("192.168.145.41", 7777);
			
			// 소켓 접속이 성공하면 받고 싶은 파일명을 보낸다
			dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(file.getName()); // 다운로드 받고 싶은 파일명 보내기
			
			dis = new DataInputStream(socket.getInputStream());
			
			String resultMsg = dis.readUTF();
			
			if(resultMsg.equals("OK")) {
				fos = new FileOutputStream(file);
				
				BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				
				int data = 0;
				while((data = bis.read()) != -1) {
					bos.write(data);
				}
				
				bis.close();
				bos.close();
				System.out.println("파일 다운로드 완료...");
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new TcpFileClient().clientstart();
	}
}
