package com.readbook.record.dto;

import java.util.Date;

public class PagingDto {
	private int startseq;  //현재 페이지의 첫번째 게시글의 seq
	private int endseq;    //현재 페이지의 마지막 게시글의 seq
	private int underpagescale;  //한번에 표시해줄 아래 페이지 번호의 크기(1 2 3 4 5 or 1 2 3)
	private int pagegroup; //페이지 그룹(1~5,6~10)
	private int totalpage;  //전제 게시글의 수로 계산할 마지막 page번호
	private int currentpage;  //현재 페이지
	private int column;  //한 페이지의 게시글 수(10개씩 끊을 예정)
	
	private int record_seq;
	private String user_id;
	private String record_bookname;
	private String record_readingpage;
	private int record_readingtime;
	private int record_readingcharacter;
	private int record_readingavg;	
	private Date record_readingdate;
	
	public PagingDto() {
		
	}

	public PagingDto(int startseq, int endseq, int underpagescale, int pagegroup, int totalpage, int currentpage,
			int column, int record_seq, String user_id, String record_bookname, String record_readingpage,
			int record_readingtime, int record_readingcharacter, int record_readingavg, Date record_readingdate) {
		super();
		this.startseq = startseq;
		this.endseq = endseq;
		this.underpagescale = underpagescale;
		this.pagegroup = pagegroup;
		this.totalpage = totalpage;
		this.currentpage = currentpage;
		this.column = column;
		this.record_seq = record_seq;
		this.user_id = user_id;
		this.record_bookname = record_bookname;
		this.record_readingpage = record_readingpage;
		this.record_readingtime = record_readingtime;
		this.record_readingcharacter = record_readingcharacter;
		this.record_readingavg = record_readingavg;
		this.record_readingdate = record_readingdate;
	}

	public int getStartseq() {
		return startseq;
	}

	public void setStartseq(int startseq) {
		this.startseq = startseq;
	}

	public int getEndseq() {
		return endseq;
	}

	public void setEndseq(int endseq) {
		this.endseq = endseq;
	}

	public int getUnderpagescale() {
		return underpagescale;
	}

	public void setUnderpagescale(int underpagescale) {
		this.underpagescale = underpagescale;
	}

	public int getPagegroup() {
		return pagegroup;
	}

	public void setPagegroup(int pagegroup) {
		this.pagegroup = pagegroup;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRecord_seq() {
		return record_seq;
	}

	public void setRecord_seq(int record_seq) {
		this.record_seq = record_seq;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getRecord_bookname() {
		return record_bookname;
	}

	public void setRecord_bookname(String record_bookname) {
		this.record_bookname = record_bookname;
	}

	public String getRecord_readingpage() {
		return record_readingpage;
	}

	public void setRecord_readingpage(String record_readingpage) {
		this.record_readingpage = record_readingpage;
	}

	public int getRecord_readingtime() {
		return record_readingtime;
	}

	public void setRecord_readingtime(int record_readingtime) {
		this.record_readingtime = record_readingtime;
	}

	public int getRecord_readingcharacter() {
		return record_readingcharacter;
	}

	public void setRecord_readingcharacter(int record_readingcharacter) {
		this.record_readingcharacter = record_readingcharacter;
	}

	public int getRecord_readingavg() {
		return record_readingavg;
	}

	public void setRecord_readingavg(int record_readingavg) {
		this.record_readingavg = record_readingavg;
	}

	public Date getRecord_readingdate() {
		return record_readingdate;
	}

	public void setRecord_readingdate(Date record_readingdate) {
		this.record_readingdate = record_readingdate;
	}

	
	
}
