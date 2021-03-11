package com.readbook.login.dao;

import java.util.List;

import com.readbook.login.dto.LoginDto;

public interface LoginDao {

	public int SignupUser(LoginDto dto);  //회원가입
	public int idChk(String user_id);  //아이디 중복체크
	public LoginDto LoginUser(LoginDto dto);  //일반로그인
	public String searchId(LoginDto dto);  //아이디 찾기
	public String searchPw(LoginDto dto);  //비밀번호 찾기
	public LoginDto selectKakaoAccount(LoginDto dto); //카카오select
	public int insertKakaoAccount(LoginDto dto);  //카카오 회원가입
}
