package com.readbook.record.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.readbook.record.biz.RecordBiz;
import com.readbook.record.biz.RecordBizImpl;
import com.readbook.record.dto.PagingDto;
import com.readbook.record.dto.RecordDto;


@WebServlet("/record.do")
public class RecordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RecordController() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
		String command = request.getParameter("command");
		
		RecordBiz biz = new RecordBizImpl();
		
		if(command.equals("record")) {
			int currentpage = Integer.parseInt(request.getParameter("currentpage"));
			System.out.println(currentpage+"커랜트 페이지 1찍혀야된다.");
			String user_id = request.getParameter("user_id");
			System.out.println(user_id+"*********");
			System.out.println(user_id);
			
			if(user_id.length() > 0) {
				
				
				PagingDto pagingdto = new PagingDto();
				pagingdto.setCurrentpage(currentpage);
				pagingdto.setColumn(20);   //게시글은 20개씩
				pagingdto.setUnderpagescale(5);    //아래 페이지 수의 크기는 5개씩
				pagingdto.setTotalpage(biz.totalPage(pagingdto.getColumn(),user_id));  //마지막 페이지 번호
				
				System.out.println(pagingdto.getColumn()+"::컨트롤러의 column(20)나와야됨");
				
			List<RecordDto>list = biz.selectList(pagingdto,user_id);
			System.out.println(list.size()+"::리스트사이즈");
			HttpSession session = request.getSession();
			session.setAttribute("recordlist", list);
			session.setAttribute("pagingdto", pagingdto);
			response.sendRedirect("record/recordpage.jsp");
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
		
		String command = request.getParameter("command");
		RecordBiz biz = new RecordBizImpl();
		
		if(command.equals("multidelete")) {
			System.out.println("삭제 컨트롤러 들어왔다.");
			//int currentpage = Integer.parseInt(request.getParameter("currentpage"));
			//System.out.println("기록삭제currentpage:"+currentpage);
			String user_id = request.getParameter("user_id");
			String [] seqs = request.getParameterValues("Chk");
			
			int res = biz.multidelete(seqs);
			
			if(res > 0) {
				//jsResponse("기록이 삭제되었습니다.", "record.do?command=record&user_id="+user_id+"&currentpage=1", response);
				HttpSession session = request.getSession();
				session.setAttribute("msg", "기록이 삭제되었습니다.");
				session.setAttribute("loc", "record.do?command=record&user_id="+user_id+"&currentpage=1");
				response.sendRedirect("alert/alert.jsp");
			}
		}else if(command.equals("recodFormPre")) {
			//파라미터
			//int readingtime = Integer.parseInt(request.getParameter("record_readingtime"));
			int readingcharacter =  Integer.parseInt(request.getParameter("record_readingcharacter"));
			
			HttpSession session = request.getSession();
			//session.setAttribute("readingtime", readingtime);
			session.setAttribute("readingcharacter", readingcharacter);
			System.out.println("::읽어온 글자수::"+readingcharacter);
			response.sendRedirect("takepicture/recodeForm.jsp");
			
		}else if(command.equals("chkBook")) {  
		RecordBiz recodBiz = new RecordBizImpl();
		RecordDto Recodedto = new RecordDto();
	
		//파라미터
		int readingtime = Integer.parseInt(request.getParameter("record_readingtime"));
		int readingcharacter =  Integer.parseInt(request.getParameter("record_readingcharacter"));
		
		//dto set
		Recodedto.setUser_id(request.getParameter("user_id"));
		Recodedto.setRecord_bookname(request.getParameter("record_bookname"));
		Recodedto.setRecord_readingpage(request.getParameter("record_readingpage"));
		
		Recodedto.setRecord_readingtime(readingtime);
		Recodedto.setRecord_readingcharacter(readingcharacter);
		Recodedto.setRecord_readingavg(readingcharacter/readingtime);
		
		int res = recodBiz.chkBook(Recodedto);
		
		System.out.println("기록이"+res+"개 insert되었습니다.");
		if(res > 0) {
			//response.sendRedirect("home/section.jsp");
			HttpSession session = request.getSession();
			session.setAttribute("msg", "기록완료</br>오늘의 기록에서 확인해보세요.");
			session.setAttribute("loc", "home/section.jsp");
			response.sendRedirect("alert/alert.jsp");
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
