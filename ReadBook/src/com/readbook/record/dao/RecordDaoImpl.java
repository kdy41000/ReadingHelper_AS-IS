package com.readbook.record.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.readbook.record.dto.PagingDto;
import com.readbook.record.dto.RecordDto;

public class RecordDaoImpl extends SqlMapConfig implements RecordDao{

	private String namespace = "record.";
	
	@Override
	public List<RecordDto> selectList(int startseq, int endseq ,String user_id) {
		List<RecordDto>list = new ArrayList<RecordDto>();
		PagingDto dto = new PagingDto();
		SqlSession session = null;
		Map<String,String>hashmap = new HashMap<String,String>();
		
		System.out.println(startseq+"::"+endseq+"::"+user_id);
		
		try {
			hashmap.put("start_seq", Integer.toString(startseq));
			hashmap.put("end_seq", Integer.toString(endseq));
			hashmap.put("user_id", user_id);
			
			session = getSqlSessionFactory().openSession();
			list = session.selectList(namespace+"selectList",hashmap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return list;
	}

	
	@Override
	public int multidelete(String[] seqs) {
		SqlSession session = null;
		int res = 0;
		Map<String,String[]>map = new HashMap<String, String[]>();
		map.put("seqs", seqs);
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete(namespace+"multidelete",map);
			
			if(res == seqs.length) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return res;
	}

	@Override
	public int totalpage(String user_id) {
		int res = 0;
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession();
			res = session.selectOne(namespace + "totalpage",user_id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return res;
	}


	@Override
	public int chkBook(RecordDto Recodedto) {
		int res = 0;
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession();
			res = session.insert(namespace + "chkBook",Recodedto);
			System.out.println("기록 res : "+res);
			
			if(res > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return res;
	}
	
}
