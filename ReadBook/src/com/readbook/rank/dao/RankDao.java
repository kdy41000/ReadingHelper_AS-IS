package com.readbook.rank.dao;

import java.util.List;

import com.readbook.rank.dto.RankDto;

public interface RankDao {

	public List<RankDto>selectList(); //랭크출력
}
