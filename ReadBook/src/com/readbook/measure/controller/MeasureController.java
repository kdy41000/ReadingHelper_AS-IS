package com.readbook.measure.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/measure.do")
public class MeasureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MeasureController() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
		String command = request.getParameter("command");
		
		if(command.equals("measure")) {
			String user_id = request.getParameter("user_id");
			System.out.println(user_id+"**********");
			if(user_id.length() > 0) {
				response.sendRedirect("measure/measurepage.jsp");
			}else {
				//jsResponse("로그인이 필요합니다.", "login/loginpage.jsp", response);
				HttpSession session = request.getSession();
				session.setAttribute("msg", "로그인이 필요합니다.");
				session.setAttribute("loc", "login/loginpage.jsp");
				response.sendRedirect("alert/alert.jsp");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
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
