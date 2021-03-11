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
 <link href="../resources/css/measure/takepicture.css"    
 rel="stylesheet"    
 type="text/css" /> 
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<body>
<%@ include file="../home/header.jsp" %>
<section class="container">
<div style="text-align:center;">
<h1 style="margin-top: 10%; font-size: 50px;">읽어온 글자</h1>
</br></br>
</div>
<fieldset style="width:90%; height:700px; border:1px solid black; margin-left: 3%;" >
	<c:forEach items="${sessionScope.totalArray}" var="totalArray">
		${totalArray} <br/>
	</c:forEach>
</fieldset>
<h1 style="margin-top: 5%; margin-left: 8%;font-size: 30px;">총 글자 수 : ${totalArraylen}</h1>
<div style="text-align:center;">
	<button id='imageSubmit' class='rounded-circle text-center'>기록하기</button><br/><br/>
</div>
</section>
<section>
	<p>SCROLL DOWN CSS</p>
	<a href="#" style class="scroll-down" address="true">
	</a>
</section>
<section class="ok">
	<p>OK SCROLL !</p>
	<form action="../record.do" method="post">
		<input type = "hidden" value ="chkBook" name="command"/>
		ID :<input type="text" name="user_id" readonly value="${sessionScope.logindto.user_id }">
		</br>도서명 :<input type="text" name="record_bookname" value="">
		</br>분 :<input type = "number" min = "0" max = "100" step = "1" name = "record_readingtime">  
		</br>읽은글자 수 : <input type="text" name="record_readingcharacter" readonly value="${totalArraylen}">
		</br>페이지 : <input type = "text" name = "record_readingpage" value="">  
		<br><br>
        <input type = "submit" value = "submit"/>
	</form>
<%@ include file="../home/footer.jsp" %>
</section>
<!-- VALUES(RECORDSEQ.NEXTVAL,'BOBY','코로나를위한책','50,51',1,1000,12000,sysdate); -->
<!-- id,책제목, 책페이지, 분, 글자수, 1000  -->
</body>

<script type="text/javascript">
$(function() {
    $('.scroll-down').click (function() {
      $('html, body').animate({scrollTop: $('section.ok').offset().top }, 'slow');
      return false;
    });
  });
</script>
</html>