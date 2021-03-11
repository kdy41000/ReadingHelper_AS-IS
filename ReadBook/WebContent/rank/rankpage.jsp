<%@page import="com.readbook.login.dto.LoginDto"%>
<%@page import="java.util.List"%>
<%@page import="com.readbook.rank.dto.RankDto"%>
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
<link href="../resources/css/rank/rankpage.css"    
	  rel="stylesheet"    
	  type="text/css" /> 
</head>
<script type="text/javascript" src="../resources/js/rank/rankpage.js"></script>
<%
	List<RankDto>ranklist = (List<RankDto>)session.getAttribute("ranklist");
	LoginDto logindto = (LoginDto)session.getAttribute("logindto");
%>
<body>
<%@ include file="../home/header.jsp" %>
<section>
<img alt="img" src="../resources/images/rank/rankicon.png" style="width:8%; height:70px; margin: 10% 0% 0% 38%;"/>
<div style="text-align:center;"><br/>
	<h1 style="margin-top: -8%;">랭킹</h1><br/>
	
	<p>랭킹은 소요시간과 글자수에 따른 평균치를 기준으로 선정됩니다.</p>
	<a>리딩헬퍼는 정해진 시간동안 Reading속도를  높여 집중력을 향상시키는데 도움됩니다.</a>
</div>
<div class="table-responsive">
	<table style="margin: 5% 0% 0% 1%;">
		<thead>	
			<tr>
				<th style="width:120px;">Rank</th>
				<th style="width:250px;">User</th>
				<th>시간</th>
				<th>글자수</th>
				<th>평균치</th>
			</tr>
		</thead>
		<tbody>
<%
	int ranking = 0;
	for(int i = 0; i < ranklist.size(); i++){
		ranking = i+1;
		//System.out.println(ranking+"::랭킹");
%>
	<tr>
<%
		
		if(ranking==1){
%>					
				<td><img alt="img" src="../resources/images/rank/rankOne.png" style="width:90%; height:60px;"/></td>
<%
		}else if(ranking==2){
%>
				<td><img alt="img" src="../resources/images/rank/rankTwo.png" style="width:90%; height:60px;"/></td>
<%		
		}else if(ranking==3){
%>			
			    <td><img alt="img" src="../resources/images/rank/rankThree.png" style="width:90%; height:60px;"/></td>
<%		
		}else{
%>
			  <td style="font-size:25px; font-weight:bold; color:brown;"><%=ranking%></td>
<%			
		}

	if(logindto==null){
%>
	<td><%=ranklist.get(i).getUser_id() %></td>
				<td><%=ranklist.get(i).getRecord_readingtime() %>분</td>
				<td><%=ranklist.get(i).getRecord_readingcharacter() %>자</td>
				<td><%=ranklist.get(i).getRecord_readingavg() %></td>
<%		
	}else{
	if(logindto.getUser_id().equals(ranklist.get(i).getUser_id())){
%>
	<td style="color:lightcoral; font-weight:bold;"><%=ranklist.get(i).getUser_id() %></td>
	<td style="color:lightcoral; font-weight:bold;"><%=ranklist.get(i).getRecord_readingtime() %>분</td>
	<td style="color:lightcoral; font-weight:bold;"><%=ranklist.get(i).getRecord_readingcharacter() %>자</td>
	<td style="color:lightcoral; font-weight:bold;"><%=ranklist.get(i).getRecord_readingavg() %></td>
<%
	}else{
%>				
				<td><%=ranklist.get(i).getUser_id() %></td>
				<td><%=ranklist.get(i).getRecord_readingtime() %>분</td>
				<td><%=ranklist.get(i).getRecord_readingcharacter() %>자</td>
				<td><%=ranklist.get(i).getRecord_readingavg() %></td>
<%
	}
%>				
			</tr>
<%
	}
	}
%>			
		</tbody>
	</table>
</div>


<%@ include file="../home/footer.jsp" %>		
</section>		

</body>
</html>