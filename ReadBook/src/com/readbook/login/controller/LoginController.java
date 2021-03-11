package com.readbook.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.readbook.login.biz.LoginBiz;
import com.readbook.login.biz.LoginBizImpl;
import com.readbook.login.dto.LoginDto;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
		String command = request.getParameter("command");
		LoginBiz biz = new LoginBizImpl();
		
		if(command.equals("login")) {
			
			response.sendRedirect("login/loginpage.jsp?canuse="+ null +"&user_id="+ null);
			
		}else if(command.equals("idChk")) {
			String user_id = request.getParameter("user_id");
			String canuse = "false";
			
			System.out.println("id중복체크컨트롤러:"+user_id);
			
			int res = biz.idChk(user_id);
			if(res == 0) {  //아이디 사용가능
				System.out.println("사용가능");
				canuse = "true";
				response.sendRedirect("login/loginpage.jsp?canuse="+ canuse +"&user_id="+ user_id);
			}else {    //아이디 이미 존재
				System.out.println("사용불가");
				System.out.println(canuse+"::컨트롤러 canuse");
				response.sendRedirect("login/loginpage.jsp?canuse="+ canuse +"&user_id="+ user_id);
			} 

		}else if(command.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("home/section.jsp");
			
		}else if(command.equals("searchInfo")) {
			response.sendRedirect("login/searchInfo.jsp");	
			
		}else if(command.equals("kakaoSignup")) {
			
			String id = request.getParameter("user_id");
			String user_name = request.getParameter("user_name");
			String email = request.getParameter("user_email");
			String user_id = "kakao_"+ id;
			String user_email = "kakao_"+email;
			String user_pass = "kakao_pass";
			
			LoginDto dto = new LoginDto();
			dto.setUser_id(user_id);
			dto.setUser_pass(user_pass);
			dto.setUser_name(user_name);
			dto.setUser_email(user_email);
			
			// 1. select
			LoginDto kakaodto = biz.selectKakaoAccount(dto);
			
			if(kakaodto == null) {  //기존정보가 없으면
				// 2. insert
				int res = biz.insertKakaoAccount(dto);
				if(res > 0) {
					response.sendRedirect("login.do?command=kakaoSignup&user_id="+id+"&user_name="+user_name+"&user_email="+email);
				}else {
					System.out.println("카카오 정보저장 오류");
				}
				
			}else {   //기존 정보가 있으면
				HttpSession session = request.getSession();	
				session.setAttribute("logindto", kakaodto);
				response.sendRedirect("home/section.jsp");
			}
			
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
		String command = request.getParameter("command");
		LoginBiz biz = new LoginBizImpl();
		
		if(command.equals("signupres")) {   //회원가입
			System.out.println("들어왔다 회원가입");
			String user_id = request.getParameter("user_id");
			String user_pass = request.getParameter("user_pass");
			String user_name = request.getParameter("user_name");
			String user_email = request.getParameter("user_email");
			
			System.out.println(user_id);
			System.out.println(user_pass);
			System.out.println(user_name);
			System.out.println(user_email);
			
			LoginDto dto = new LoginDto();
			dto.setUser_id(user_id);
			dto.setUser_pass(user_pass);
			dto.setUser_name(user_name);
			dto.setUser_email(user_email);
			
			int res = biz.SignupUser(dto);
			System.out.println(res+"::res");
			
			HttpSession session = request.getSession();
			if(res > 0) {
				//jsResponse("회원가입이 완료되었습니다.", "login/loginpage.jsp", response);
				session.setAttribute("msg", "회원가입이 완료되었습니다.");
				session.setAttribute("loc", "login/loginpage.jsp");
				response.sendRedirect("alert/alert.jsp");
				
			}else {
				//jsResponse("회원가입을 실패하였습니다.", "login/loginpage.jsp", response);
				session.setAttribute("msg", "회원가입을 실패하였습니다.");
				session.setAttribute("loc", "login/loginpage.jsp");
				response.sendRedirect("alert/alert.jsp");
			}
			
		}else if(command.equals("loginres")) {  //일반 로그인
			String user_id = request.getParameter("user_id");
			String user_pass = request.getParameter("user_pass");
			System.out.println(user_id+"::로그인 커맨드 유저아이디");
			System.out.println(user_pass+"::로그인 커맨드 유저비밀번호");
			
			LoginDto dto = new LoginDto();
			dto.setUser_id(user_id);
			dto.setUser_pass(user_pass);
			
			LoginDto logindto = biz.LoginUser(dto);
			HttpSession session = request.getSession();
			
			if(logindto != null) {  //정보가 있을 시
				session.setAttribute("logindto", logindto);
				response.sendRedirect("home/section.jsp");
			}else{   //정보가 없을 시
				//jsResponse("로그인 정보를 확인해 주십시오.", "login/loginpage.jsp", response);
				session.setAttribute("msg", "로그인 정보를 확인해 주십시오.");
				session.setAttribute("loc", "login/loginpage.jsp");
				response.sendRedirect("alert/alert.jsp");
			}
		}else if(command.equals("searchIdres")) {      //아이디 찾기
			System.out.println("들어왔다.");
			String user_name = request.getParameter("user_name");
			String user_email = request.getParameter("user_email");
			
			LoginDto dto = new LoginDto();
			dto.setUser_name(user_name);
			dto.setUser_email(user_email);
			
			String user_id = biz.searchId(dto);
			
			if(user_id==null) {
				//jsResponse("정보를 확인해 주십시오.", "login/searchInfo.jsp", response);
				HttpSession session = request.getSession();
				session.setAttribute("msg", "정보를 확인해 주십시오.");
				session.setAttribute("loc", "login/searchInfo.jsp");
				response.sendRedirect("alert/alert.jsp");
			}else {
/////////////////////////////
try {
String mail_from =  "<" + user_email + ">"; //보낸사람 
// String mail_to =    "admin<admin@83rpm.com>";
String mail_to =    user_email;     //메일 받는 사람
String title =      "리딩헬퍼 아이디 전송 메일 입니다. "; //제목
String contents =   "보낸 사람 : 리딩헬퍼 ";

System.out.println("222");
mail_from = new String(mail_from.getBytes("UTF-8"), "UTF-8");
mail_to = new String(mail_to.getBytes("UTF-8"), "UTF-8");

Properties props = new Properties();
props.put("mail.transport.protocol", "smtp");
props.put("mail.smtp.host", "smtp.gmail.com");
props.put("mail.smtp.port", "465");
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.smtp.socketFactory.port", "465");
props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
props.put("mail.smtp.socketFactory.fallback", "false");
props.put("mail.smtp.auth", "true");

Authenticator auth = new SMTPAuthenticator();  //보내는 사람 클래스 객체생성

Session sess = Session.getDefaultInstance(props, auth);

MimeMessage msg = new MimeMessage(sess);

msg.setFrom(new InternetAddress(mail_from));
msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail_to));
msg.setSubject(title, "UTF-8");
msg.setContent(contents, "text/html; charset=UTF-8");
msg.setText(new StringBuffer().append("<fieldset style='text-align:center; height: 1100px;'>")
			.append("<legend><h1>리딩헬퍼에서 보낸 메일입니다.</h1></legend><br/>")
			.append("<img alt='png01;' src='https://kr.seaicons.com/wp-content/uploads/2015/09/Mail-icon1.png' />")
			.append("<p style='font-weight:bold; font-size:12px; '>이 메일은 리딩헬퍼에서 보낸 메일로 요청에 따른 아이디 전송 메일입니다.</p><br/>")
			.append("<p style='font-weight:bold; font-size:12px; color:blue;'>아이디 : ["+ user_id +"]</p><br/>")
			.append("<p style='font-weight:bold; font-size:12px; '>아래 버튼 클릭한 후 로그인을 진행하여 주십시오.</p><br/><br/>")
			.append("<button style=\"color:white; background:black; font-weight:bold; width:150px; height:40px; font-size:18px; border-radius:10px;\"><a style=\"color:white; text-decoration: none;\" href=\"http://localhost:8787/ReadBook/login.do?command=login\">Click</a></button>")
			.append("</fieldset>").toString()
			);
				msg.setHeader("Content-type", "text/html; charset=UTF-8");

				Transport.send(msg);
				System.out.println("이메일 전송 성공****************");
				//jsResponse("이메일로 아이디가 전송되었습니다.", "login/loginpage.jsp", response);
				HttpSession session = request.getSession();
				session.setAttribute("msg", "이메일로 아이디가 전송되었습니다.");
				session.setAttribute("loc", "login/loginpage.jsp");
				response.sendRedirect("alert/alert.jsp");

		   } catch (Exception e) {
			   e.printStackTrace();
			   System.out.println("[ERROR]:전송에러");
			   response.sendRedirect("login/loginpage.jsp");
		   } finally {

		   }
		 }
			
		}else if(command.equals("searchPwres")) {
			String user_id = request.getParameter("user_id");
			String user_name = request.getParameter("user_name");
			String user_email = request.getParameter("user_email");
			
			LoginDto dto = new LoginDto();
			dto.setUser_id(user_id);
			dto.setUser_name(user_name);
			dto.setUser_email(user_email);
			
			String user_pass = biz.searchPw(dto);

			if(user_pass == null) {
				//jsResponse("정보를 확인해 주십시오.", "login/searchInfo.jsp", response);
				HttpSession session = request.getSession();
				session.setAttribute("msg", "정보를 확인해 주십시오.");
				session.setAttribute("loc", "login/searchInfo.jsp");
				response.sendRedirect("alert/alert.jsp");
			}else {
/////////////////////////////
try {
String mail_from =  "<" + user_email + ">"; //보낸사람 
//String mail_to =    "admin<admin@83rpm.com>";
String mail_to =    user_email;     //메일 받는 사람
String title =      "리딩헬퍼 비밀번호 전송 메일 입니다. "; //제목
String contents =   "보낸 사람 : 리딩헬퍼 ";

System.out.println("222");
mail_from = new String(mail_from.getBytes("UTF-8"), "UTF-8");
mail_to = new String(mail_to.getBytes("UTF-8"), "UTF-8");

Properties props = new Properties();
props.put("mail.transport.protocol", "smtp");
props.put("mail.smtp.host", "smtp.gmail.com");
props.put("mail.smtp.port", "465");
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.smtp.socketFactory.port", "465");
props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
props.put("mail.smtp.socketFactory.fallback", "false");
props.put("mail.smtp.auth", "true");

Authenticator auth = new SMTPAuthenticator();  //보내는 사람 클래스 객체생성

Session sess = Session.getDefaultInstance(props, auth);

MimeMessage msg = new MimeMessage(sess);

msg.setFrom(new InternetAddress(mail_from));
msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail_to));
msg.setSubject(title, "UTF-8");
msg.setContent(contents, "text/html; charset=UTF-8");
msg.setText(new StringBuffer().append("<fieldset style='text-align:center; height: 1100px;'>")
.append("<legend><h1>리딩헬퍼에서 보낸 메일입니다.</h1></legend><br/>")
.append("<img alt='png01;' src='https://kr.seaicons.com/wp-content/uploads/2015/09/Mail-icon1.png' />")
.append("<p style='font-weight:bold; font-size:12px; '>이 메일은 리딩헬퍼에서 보낸 메일로 요청에 따른 비밀번호 전송 메일입니다.</p><br/>")
.append("<p style='font-weight:bold; font-size:12px; color:blue;'>비밀번호 : ["+ user_pass +"]</p><br/>")
.append("<p style='font-weight:bold; font-size:12px; '>아래 버튼 클릭한 후 로그인을 진행하여 주십시오.</p><br/><br/>")
.append("<button style=\"color:white; background:black; font-weight:bold; width:150px; height:40px; font-size:18px; border-radius:10px;\"><a style=\"color:white; text-decoration: none;\" href=\"http://localhost:8787/ReadBook/login.do?command=login\">Click</a></button>")
.append("</fieldset>").toString()
);
	msg.setHeader("Content-type", "text/html; charset=UTF-8");

	Transport.send(msg);
	System.out.println("이메일 전송 성공****************");
	
	//jsResponse("이메일로 비밀번호가 전송되었습니다.", "login/loginpage.jsp", response);
	HttpSession session = request.getSession();
	session.setAttribute("msg", "이메일로 비밀번호가 전송되었습니다.");
	session.setAttribute("loc", "login/loginpage.jsp");
	response.sendRedirect("alert/alert.jsp");

} catch (Exception e) {
   e.printStackTrace();
   System.out.println("[ERROR]:전송에러");
   response.sendRedirect("login/loginpage.jsp");
} finally {

}
			}
		}
		
		doGet(request, response);
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException { //스크립트 메시지를 편하게 사용하기 위한 메서드(out.println으로 사용해도 된다.)
		String res = "<script type='text/javascript'>"
				   + " alert('"+msg+"');"
				   + " location.href='"+url+"';"
				   + "</script>";
		PrintWriter out = response.getWriter();
		out.println(res);
	}

}
