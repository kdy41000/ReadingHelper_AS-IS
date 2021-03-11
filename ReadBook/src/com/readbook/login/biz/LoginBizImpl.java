package com.readbook.login.biz;

import java.util.List;

import com.readbook.login.dao.LoginDao;
import com.readbook.login.dao.LoginDaoImpl;
import com.readbook.login.dto.LoginDto;

public class LoginBizImpl implements LoginBiz{

	private LoginDao dao = new LoginDaoImpl();
	
	@Override
	public int SignupUser(LoginDto dto) {
		
		return dao.SignupUser(dto);
	}

	@Override
	public int idChk(String user_id) {
		
		return dao.idChk(user_id);
	}

	@Override
	public LoginDto LoginUser(LoginDto dto) {

		return dao.LoginUser(dto);
	}

	@Override
	public String searchId(LoginDto dto) {
		
		return dao.searchId(dto);
	}

	@Override
	public String searchPw(LoginDto dto) {

		return dao.searchPw(dto);
	}

	@Override
	public LoginDto selectKakaoAccount(LoginDto dto) {
		
		return dao.selectKakaoAccount(dto);
	}

	@Override
	public int insertKakaoAccount(LoginDto dto) {
		
		return dao.insertKakaoAccount(dto);
	}

}
