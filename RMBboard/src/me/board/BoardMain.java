package me.board;

import java.util.Scanner;

import me.board.service.BoardServiceImpl;
import me.board.service.IBoardService;
import me.board.vo.BoardVO;

/*
 	위의 테이블을 작성하고 게시판을 관리하는
	다음 기능들을 구현하시오.
	
	기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
	------------------------------------------------------------

	게시판 테이블 구조 및 시퀀스
	
	create table jdbc_board(
	    board_no number not null,  -- 번호(자동증가)
	    board_title varchar2(100) not null, -- 제목
	    board_writer varchar2(50) not null, -- 작성자
	    board_date date not null,   -- 작성날짜
	    board_content clob,     -- 내용
	    constraint pk_jdbc_board primary key (board_no)
	);
	create sequence board_seq
	    start with 1   -- 시작번호
	    increment by 1; -- 증가값
			
	----------------------------------------------------------
	
	// 시퀀스의 다음 값 구하기
	//  시퀀스이름.nextVal
 */
public class BoardMain {
	
	private Scanner scan;
	
	private IBoardService brdService;
	
	public BoardMain() {
		scan = new Scanner(System.in);
		brdService = new BoardServiceImpl();
	}
	
	// 메뉴 출력 메서드
	public void displayMenu() {
		System.out.println();
		System.out.println("======================");
		System.out.println("  ㅡㅡㅡㅡ 작업 선택 ㅡㅡㅡㅡ      ");
		System.out.println("   1. 전체 목록 출력");
		System.out.println("   2.  새 글 작성    ");
		System.out.println("   3.    수정        ");
		System.out.println("   4.    삭제        ");
		System.out.println("   5.    검색        ");
		System.out.println("   6.    종료        ");
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		System.out.println("======================");
		System.out.println("원하는 작업 선택 >> ");
	}
	
	// 프로그램 시작
	public void start() {
		int ch;
		do {
			displayMenu();
			ch = scan.nextInt();
			
			switch(ch) {
				case 1 :
					checkBoard();
					break;
				case 2 :
					uploadBoard();
					break;
				case 3 :
					updateBoard();
					break;
				case 4 :
					deleteBoard();
					break;
				case 5 :
					searchBoard();
					break;
				case 6:
					System.out.println("  ㅡㅡㅡㅡ 작업 종료 ㅡㅡㅡㅡ      ");
					System.out.println("======================");
					break;
				default :
					System.out.println("없는 번호 입력. 재입력 요망");
			}
		}while(ch != 6);
	}

	private void searchBoard() {
		// TODO Auto-generated method stub
		
	}

	private void deleteBoard() {
		System.out.println();
		
		
	}

	private void updateBoard() {
		boolean isExist = false;
		String brdNo = "";
		
		do {
			System.out.println();
			System.out.println("수정할 게시물 정보 입력@");
			System.out.println("게시물 번호 >> ");
			
			brdNo = scan.next();
			
			isExist = brdService.checkBoard(brdNo);
			
			if(!isExist) {
				System.out.println("게시물 번호가 " + brdNo + "인 게시물은 "
							+ "존재하지 않습니다.\n다시 입력해주세요.");
			}
		}while(!isExist);
		
		System.out.println("게시물 제목");
		String brdTitle = scan.next();
		
		System.out.println("게시물 작성자");
		String brdWriter = scan.next();
		
		scan.nextLine();
		
		System.out.println("게시물 내용");
		String brdCont = scan.nextLine();
		
		BoardVO bv = new BoardVO(brdNo, brdTitle, brdWriter, brdCont);
		
		int cnt = brdService.modifyBoard(bv);
		
		if(cnt > 0) {
			System.out.println(brdNo + "번 게시물 정보 수정 성공");
		}else {
			System.out.println(brdNo + "번 게시물 정보 수정 실패");
		}
	}

	private void uploadBoard() {
		// TODO Auto-generated method stub
		
	}

	private void checkBoard() {
		
		
	}
	
	public static void main(String[] args) {
		BoardMain brdObj = new BoardMain();
		brdObj.start();
	}
	
	

}
