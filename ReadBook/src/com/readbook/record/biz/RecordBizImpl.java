package com.readbook.record.biz;

import java.util.List;

import com.readbook.record.dao.RecordDao;
import com.readbook.record.dao.RecordDaoImpl;
import com.readbook.record.dto.PagingDto;
import com.readbook.record.dto.RecordDto;

public class RecordBizImpl implements RecordBiz{

	private RecordDao dao = new RecordDaoImpl();
	
	@Override
	public List<RecordDto> selectList(PagingDto pagingdto,String user_id) {

		int currentpage = pagingdto.getCurrentpage();
		int column = pagingdto.getColumn();
		System.out.println(column+"::bizimpl의 column(20)나와야됨");
		int startseq = column * (currentpage - 1) + 1;
		int endseq = column * currentpage;
		System.out.println(endseq+"::bizimpl의 endseq (20)나와야됨");
		
		return dao.selectList(startseq, endseq, user_id);
	}

	@Override
	public int multidelete(String[] seqs) {

		return dao.multidelete(seqs);
	}

	@Override
	public int totalPage(int column,String user_id) {
		int totalpage = (int)Math.ceil((double)dao.totalpage(user_id)/column);
		return totalpage;
	}

	@Override
	public int chkBook(RecordDto Recodedto) {
		System.out.println("chkBookBIz");
		
		return dao.chkBook(Recodedto);
	}

	
}
