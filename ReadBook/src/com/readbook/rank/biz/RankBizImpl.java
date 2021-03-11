package com.readbook.rank.biz;

import java.util.List;

import com.readbook.rank.dao.RankDao;
import com.readbook.rank.dao.RankDaoImpl;
import com.readbook.rank.dto.RankDto;

public class RankBizImpl implements RankBiz{

	private RankDao dao = new RankDaoImpl();
	
	@Override
	public List<RankDto> selectList() {

		return dao.selectList();
	}

}
