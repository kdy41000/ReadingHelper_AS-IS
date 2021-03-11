package com.readbook.rank.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.readbook.rank.dto.RankDto;

public class RankDaoImpl extends SqlMapConfig implements RankDao{

	private String namespace = "rank.";
	
	@Override
	public List<RankDto> selectList() {
		List<RankDto>list = new ArrayList<RankDto>();
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession();
			list = session.selectList(namespace+"selectList");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}

		return list;
	}

}
