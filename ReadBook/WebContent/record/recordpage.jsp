<%@page import="com.readbook.record.dto.PagingDto"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.readbook.record.dto.RecordDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link href="../resources/css/record/recordpage.css"    
	  rel="stylesheet"    
	  type="text/css" />  
<link href="../resources/css/record/pagenation.css"    
	  rel="stylesheet"    
	  type="text/css" />
<link href="../resources/css/record/checkbox.css"    
	  rel="stylesheet"    
	  type="text/css" /> 	   	  
<style type="text/css">
#deletebtn{
	    margin-bottom: 1%;
    	width: 10%;
   	 	height: 38px;
    	background: red;
    	color: white;
    	font-weight: bold;
    	font-size: 18px;
    	border-radius: 8px;
}    	
</style>	  
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<%
PagingDto currentpage = (PagingDto)session.getAttribute("pagingdto");
int pagegroup = (int)Math.ceil((double)currentpage.getCurrentpage()/currentpage.getUnderpagescale());
int startseq = currentpage.getUnderpagescale() * (pagegroup - 1) + 1;
int endseq = currentpage.getUnderpagescale() * pagegroup;
int totalpage = currentpage.getTotalpage();

	List<RecordDto>recordlist = (List<RecordDto>)session.getAttribute("recordlist");
%>
<body>
<%@ include file="../home/header.jsp" %>
<section>
<img alt="img" src="../resources/images/record/recordicon.png" style="width:7%; height:60px; margin: 1% 0% 0% 35%;"/>
	<div class="container">
		
			<div class="wrapper" style="margin-top:-10%;">
			<h1 style="text-align: center;">오늘의 기록</h1>
				<table>
					<thead>
						<tr>
							<th><input type="checkbox" name="allChk" onclick="allChk(this.checked);"/></th>
							
							
							<th>책제목</th>
							<th style="width: 1%;">페이지</th>
							<th style="width: 1%;">시간</th>
							<th>글자수</th>
							<th>평균치</th>							
							<th>날짜</th>
						</tr>
					</thead>
					<tbody>
					
<form action="../record.do" method="post" id="multideleteform">
<input type="hidden" name="command" value="multidelete"/>
<input type="hidden" name="user_id" value="${sessionScope.logindto.user_id }"/>
<input type="hidden" name="currentpage" value="<%=currentpage %>"/>
<%
	for(int i = 0; i < recordlist.size(); i++){
%>			
			   <tr>
			    <td class="up-down"><input type="checkbox" name="Chk" value="<%=recordlist.get(i).getRecord_seq() %>"/></td>
   
                <td class="team"><%=recordlist.get(i).getRecord_bookname() %></td>
                <td class="up-down"><%=recordlist.get(i).getRecord_readingpage() %></td>
                <td class="points"><%=recordlist.get(i).getRecord_readingtime() %>분</td>
                <td class="up-down"><%=recordlist.get(i).getRecord_readingcharacter() %>자</td>
                <td class="rank"><%=recordlist.get(i).getRecord_readingavg() %></td>
<%
	Date readingdate = recordlist.get(i).getRecord_readingdate();
	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");	
	String readingdateres = dateformat.format(readingdate);
%>                
                <td class="up-down"><%=readingdateres %></td>
              </tr>             
<%
	}
%>            					

<button type="submit" id="deletebtn"/> Delete</button>
					</tbody>
				</table>
			</div>
		</div>
</form>
<div class="pagination" style="margin-top: -1%;">
<%
	if(pagegroup > 1){
%>	
	<a href="#" onclick="location.href='../record.do?command=record&currentpage=<%=startseq-1 %>&user_id=${sessionScope.logindto.user_id}';" class="prev str">prev</a>
<%
	}
	for(int pagenum = startseq; pagenum <= ((endseq < totalpage)?endseq:totalpage); pagenum++){
%>
	<a href="#" onclick="location.href='../record.do?command=record&currentpage=<%=pagenum %>&user_id=${sessionScope.logindto.user_id}';" class="pager"><%=pagenum %></a>	
<%
	}
	if(endseq < currentpage.getTotalpage()){
%>
	<a href="#" onclick="location.href='../record.do?command=record&currentpage=<%=endseq+1 %>&user_id=${sessionScope.logindto.user_id}';" class="next str">next</a>
<%		
	}

%>
</div>	
<%@ include file="../home/footer.jsp" %>		
</section>		

<script type="text/javascript">
	function allChk(ele){
		$('input:checkbox[name="Chk"]').each(function(){  //each() : index기준으로 반복하는 함수
			if(ele==true){   //true==checked
				this.checked = true;    //each함수로 반복하면서 this(chk의 index)를 checked=true로 전체체크
			}else{           //false==unchecked
				this.checked = false;   //each함수로 반복하면서 this(chk의 index)를 checked=false로 전체해제
			}
		});
	}
	
	$(function(){
			$("#multideleteform").click(function(){
				if($("#multideleteform input:checked").length==0){   //유효성검사   
					alert("하나 이상 체크해주십시오.");
					return false;   //이벤트 전파막기
				}else{
					$('#multideleteform').submit();
					
				}	
			});
	});
</script>
</body>
</html>