<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ page import="java.util.*"%>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link href="../resources/css/measure/measurepage.css"    
	  rel="stylesheet"    
	  type="text/css" /> 
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<body>
<%@ include file="../home/header.jsp" %>
<section class="container">
<div style="text-align:center;">
<h1 style="margin-top: 10%; font-size: 50px;">읽어온 글자 : ${totalArraylen} 자</h1>
</br></br>
</div>
<fieldset style="width:90%; height:700px; border:1px solid black; margin-left: 3%;" >
	<c:forEach items="${sessionScope.totalArray}" var="totalArray">
		${totalArray} <br/>
	</c:forEach>
</fieldset>
<form action="../record.do" method="post">
	<input type="hidden" name="command" value="recodFormPre">
	<input type="hidden" name="readingtime" value=1>
	<input type="hidden" name="record_readingcharacter" value="${totalArraylen}">
<div style="text-align:center;">
	<button type="submit" id='imageSubmit' class='rounded-circle text-center'>기록하기</button><br/><br/>
</div>
</form>
<!--<h1 style="margin-top: 5%; margin-left: 8%;font-size: 30px;">총 글자 수 : ${totalArraylen}</h1> -->
<%@ include file="../home/footer.jsp" %>
</section>
</body>

<script type="text/javascript">
</script>
</html>