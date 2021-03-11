package com.readbook.takepicture.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.readbook.record.biz.RecordBiz;
import com.readbook.record.biz.RecordBizImpl;
import com.readbook.record.dao.RecordDao;
import com.readbook.record.dao.RecordDaoImpl;
import com.readbook.record.dto.RecordDto;

@WebServlet("/takepicture.do")
public class TakePictureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TakePictureController() {
      
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
		String command = request.getParameter("command");
		
		if(command.equals("takepicture")) {   //Direct이동
			String user_id = request.getParameter("user_id");
			
			if(user_id.length() > 0) {
				response.sendRedirect("takepicture/takepicturepage.jsp");
			}else {
				//jsResponse("로그인이 필요합니다.", "login/loginpage.jsp", response);
				HttpSession session = request.getSession();
				session.setAttribute("msg", "로그인이 필요합니다.");
				session.setAttribute("loc", "login/loginpage.jsp");
				response.sendRedirect("alert/alert.jsp");
			}
			
		}else if(command.equals("Steptakepicture")) {       //시간측정 완료 후 이동
			int minres = Integer.parseInt(request.getParameter("minres"));
			String min = "";
			if(minres < 10) {
				 min = "0"+Integer.toString(minres);
			}else {
				min = Integer.toString(minres);
			}
			System.out.println(minres+":설정한시간");
			HttpSession session = request.getSession();
			session.setAttribute("min", min);
			response.sendRedirect("takepicture/takepicturepage.jsp");
		
		
		
		}else if(command.equals("gPickJsp")) {  
			response.sendRedirect("takepicture/test01.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		String folder = "";
		String fileName = "";
		int maxSize = 1024*1024*15;			//15mb로 파일 크기 제한
		String encType = "UTF-8";
		String savefile = "img";		//img라는 폴더에 저장
		
		ServletContext scontext = getServletContext();
		folder = scontext.getRealPath(savefile);	
		String prearrayeasy ="";
		String arrayeasy = "";
		int cnt = 0;
		File uploadDir = new File(folder);

		if(!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		MultipartRequest multi = new MultipartRequest(request, folder, maxSize, encType, new DefaultFileRenamePolicy());
		
	  for (Enumeration  files = multi.getFileNames(); files.hasMoreElements() ;) {
		  	cnt+=1;
		  	System.out.println("@@@@@@@@파일갯수 : "+cnt);
		  	String file = (String)files.nextElement();
		  	System.out.println("@@@@@@@@폼 아이디 : "+file);
		  	fileName = multi.getFilesystemName(file);
			System.out.println("@@@@@@@@파일명 : "+fileName);
			//String fullpath = folder + "\\" + fileName; //로컬용
			String fullpath = folder + "/" + fileName; //VM서버용
			System.out.println("@@@@@@@@경로 : "+fullpath);
			
			//서버용 경로 리눅스 배포용20.03.31
			//folder = "/root/apache-tomcat-8.5.53/webapps/ReadBook/resources/images/serverVision";
			//folder = "ReadBook/resources/images/serverVision";
			//folder = "root/apache-tomcat-8.5.53/webapps/ReadBook/resources/images/serverVision";
			//folder = folder = "C:\\work\\Workspace\\ReadBook\\WebContent\\resources\\images\\serverVision";
			//fullpath = folder + "\\" + fileName;
			//서버용 경로 리눅스 배포용20.03.31
			
			try {
				String path = request.getSession().getServletContext().getRealPath("/");
	
				GoogleVisionApiTester biz = new GoogleVisionApiTester();
				
				prearrayeasy = biz.getImage(fullpath);
				System.out.println(prearrayeasy+"::**********************");
				
				arrayeasy += prearrayeasy.trim();
				System.out.println(arrayeasy+"::**********************");
				
				arrayeasy = arrayeasy.replaceAll("\n", "");
				arrayeasy = arrayeasy.replaceAll(" ", "");
				
				session.setAttribute("totalArray", arrayeasy);
				session.setAttribute("totalArraylen", arrayeasy.length());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("@@@@@@@@OCR에러");
				e.printStackTrace();
			}
	}
			response.sendRedirect("takepicture/pictureReadPage.jsp");
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
