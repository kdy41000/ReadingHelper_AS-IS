package com.readbook.record.biz;

import java.util.List;

import com.readbook.record.dto.PagingDto;
import com.readbook.record.dto.RecordDto;

public interface RecordBiz {

	public List<RecordDto>selectList(PagingDto pagingdto,String user_id);  //기록리스트 출력
	public int multidelete(String[] seqs); //기록삭제
	public int totalPage(int column,String user_id);
	public int chkBook(RecordDto Recodedto); //기록 insert
}
