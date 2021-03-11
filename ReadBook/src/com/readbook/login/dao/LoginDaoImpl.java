package com.readbook.login.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.readbook.login.dto.LoginDto;

public class LoginDaoImpl extends SqlMapConfig implements LoginDao{

	private String namespace="login.";
	
	@Override
	public int SignupUser(LoginDto dto) {
		int res = 0;
		SqlSession session = null;
		System.out.println("daoimpl로 들어온값");
		System.out.println(dto.getUser_id());
		System.out.println(dto.getUser_pass());
		System.out.println(dto.getUser_name());
		System.out.println(dto.getUser_email());
		
		try {
			session = getSqlSessionFactory().openSession();
			res = session.insert(namespace+"SignupUser",dto);
			
			if(res > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("daoimpl에러");
		}finally {
			session.close();
		}
		return res;
	}

	@Override
	public int idChk(String user_id) {
		int res = 0;
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession();
			res = session.selectOne(namespace+"idChk",user_id);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return res;
	}

	@Override
	public LoginDto LoginUser(LoginDto dto) {
		//List<LoginDto>list = new ArrayList<LoginDto>();
		SqlSession session = null;
		LoginDto logindto = null;
		
		try {
			session = getSqlSessionFactory().openSession();
			logindto = session.selectOne(namespace+"LoginUser",dto);
			System.out.println(logindto+"::다오임플");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return logindto;
	}

	@Override
	public String searchId(LoginDto dto) {
		String user_id = null;
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession();
			user_id = session.selectOne(namespace+"searchId",dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return user_id;
	}

	@Override
	public String searchPw(LoginDto dto) {
		String user_pass = null;
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession();
			user_pass = session.selectOne(namespace+"searchPw",dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return user_pass;
	}

	@Override
	public LoginDto selectKakaoAccount(LoginDto dto) {
		LoginDto kakaodto = new LoginDto();
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession();
			kakaodto = session.selectOne(namespace+"selectKakaoAccount",dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return kakaodto;
	}

	@Override
	public int insertKakaoAccount(LoginDto dto) {
		int res = 0;
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession();
			res = session.insert(namespace+"insertKakaoAccount",dto);
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
