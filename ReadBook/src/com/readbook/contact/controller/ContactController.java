package com.readbook.contact.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.readbook.login.controller.SMTPAuthenticator;

@WebServlet("/contact.do")
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ContactController() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
		String command = request.getParameter("command");
		
		if(command.equals("contact")) {
			response.sendRedirect("contact/contactpage.jsp");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
		String command = request.getParameter("command");
		
		if(command.equals("sendMessage")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String text = request.getParameter("text");
			
			System.out.println(name+"::"+email+"::"+text);
			
			String admin_email = "kdy41000@gmail.com";
			
			try {
				String mail_from =  "<" + admin_email + ">"; //보낸사람 
				// String mail_to =    "admin<admin@83rpm.com>";
				String mail_to =    admin_email;     //메일 받는 사람
				String title =      "리딩헬퍼 고객 문의메일 입니다. "; //제목
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
				msg.setText(new StringBuffer().append("<fieldset style='text-align:center; height: 600px;'>")
							.append("<legend><h1>리딩헬퍼에서 보낸 메일입니다.</h1></legend><br/>")
							.append("<img alt='png01;' src='https://kr.seaicons.com/wp-content/uploads/2015/09/Mail-icon1.png' />")
							.append("<p style='font-weight:bold; font-size:12px; '>이 메일은 리딩헬퍼에서 보낸 메일로 고객 문의 메일입니다.</p><br/>")
							.append("<p style='font-weight:bold; font-size:12px; color:blue;'>이름 : ["+ name +"]</p><br/>")
							.append("<p style='font-weight:bold; font-size:12px; color:blue;'>이메일 : ["+ email +"]</p><br/>")
							.append("<p style='font-weight:bold; font-size:12px; color:blue;'>내용 : ["+ text +"]</p><br/>")
							.append("<p style='font-weight:bold; font-size:12px; '>고객문의사항을 확인 후 상기 메일로 답장해 주십시오.</p><br/><br/>")
							.append("</fieldset>").toString()
							);
								msg.setHeader("Content-type", "text/html; charset=UTF-8");

								Transport.send(msg);
								System.out.println("이메일 전송 성공****************");
								
								//jsResponse("관리자이메일로 문의사항이 전송되었습니다.", "contact/contactpage.jsp", response);
								HttpSession session = request.getSession();
								session.setAttribute("msg", "관리자이메일로 문의사항이 전송되었습니다.");
								session.setAttribute("loc", "contact/contactpage.jsp");
								response.sendRedirect("alert/alert.jsp");
						   } catch (Exception e) {
							   e.printStackTrace();
							   System.out.println("[ERROR]:전송에러");
							   response.sendRedirect("contact/contactpage.jsp");
						   } finally {

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
