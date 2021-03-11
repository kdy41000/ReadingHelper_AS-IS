package com.readbook.record.dao;

import java.util.List;

import com.readbook.record.dto.PagingDto;
import com.readbook.record.dto.RecordDto;

public interface RecordDao {

	public List<RecordDto>selectList(int startseq, int endseq ,String user_id);  //기록리스트 출력
	public int multidelete(String[] seqs); //기록삭제
	public int totalpage(String user_id);
	public int chkBook(RecordDto Recodedto);
}
