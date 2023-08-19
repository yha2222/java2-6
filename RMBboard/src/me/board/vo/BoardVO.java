package me.board.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardVO {

	private String brdNo;
	private String brdTitle;
	private String brdWriter;
	private Date brdDt;
	private String brdCont;
	
	public BoardVO(String brdNo, String brdTitle, String brdWriter, String brdCont) {
		super();
		this.brdNo = brdNo;
		this.brdTitle = brdTitle;
		this.brdWriter = brdWriter;
		this.brdCont = brdCont;
	}
	
	public BoardVO() {
		
	}

	public String getBrdNo() {
		return brdNo;
	}

	public void setBrdNo(String brdNo) {
		this.brdNo = brdNo;
	}

	public String getBrdTitle() {
		return brdTitle;
	}

	public void setBrdTitle(String brdTitle) {
		this.brdTitle = brdTitle;
	}

	public String getBrdWriter() {
		return brdWriter;
	}

	public void setBrdWriter(String brdWriter) {
		this.brdWriter = brdWriter;
	}

	public Date getBrdDt() {
		return brdDt;
	}
	
	// ???????
	public String getBrdDtDisplay() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		return sdf.format(this.brdDt);
		
	}

	public void setBrdDt(Date brdDt) {
		this.brdDt = brdDt;
	}

	public String getBrdCont() {
		return brdCont;
	}

	public void setBrdCont(String brdCont) {
		this.brdCont = brdCont;
	}

	@Override
	public String toString() {
		return "BoardVO [brdNo=" + brdNo + ", brdTitle=" + brdTitle + ", brdWriter=" + brdWriter + ", brdDt=" + brdDt
				+ ", brdCont=" + brdCont + "]";
	}
	
}
