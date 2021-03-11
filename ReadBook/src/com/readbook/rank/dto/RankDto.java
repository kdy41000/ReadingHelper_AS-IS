package com.readbook.rank.dto;

import java.util.Date;

public class RankDto {

	private int record_seq;
	private String user_id;
	private String record_bookname;
	private String record_readingpage;
	private int record_readingtime;
	private int record_readingcharacter;
	private int record_readingavg;	
	private Date record_readingdate;
	
	public RankDto() {
		
	}

	public RankDto(int record_seq, String user_id, String record_bookname, String record_readingpage,
			int record_readingtime, int record_readingcharacter, int record_readingavg, Date record_readingdate) {
		super();
		this.record_seq = record_seq;
		this.user_id = user_id;
		this.record_bookname = record_bookname;
		this.record_readingpage = record_readingpage;
		this.record_readingtime = record_readingtime;
		this.record_readingcharacter = record_readingcharacter;
		this.record_readingavg = record_readingavg;
		this.record_readingdate = record_readingdate;
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
