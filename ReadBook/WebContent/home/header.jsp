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
<link href="../resources/css/home/header.css"    
	  rel="stylesheet"    
	  type="text/css" />  
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../resources/js/home/header.js"></script>
<body>
<header>
<div class="bg-cover" class="none"></div>
<img alt="img" src="../resources/images/home/logo.png" style="width:10%; height:80px; position:absolute; margin: 1.5% 0% 0% 35%;"/>
<div class="prompt" onclick="location.href='../home.do?command=home';">리딩헬퍼</div>

<a class="hamburger-shell">
	<div class="hamb top"></div>
		<div class="hamb middle"></div>
	<div class="menu-name">MENU</div>

<ul id="menu">
		<a href="#" onclick="location.href='../home.do?command=home';"> <li>Home</li></a>
		<a href="#" onclick="location.href='../measure.do?command=measure&user_id=${sessionScope.logindto.user_id }';"> <li>독서력 측정하기</li></a>
		<a href="#" onclick="location.href='../takepicture.do?command=takepicture&user_id=${sessionScope.logindto.user_id }';"> <li>글자수 사진 찍기</li></a>
		<a href="#" onclick="location.href='../record.do?command=record&currentpage=1&user_id=${sessionScope.logindto.user_id }';"> <li>오늘의 기록</li></a>
		<a href="#" onclick="location.href='../rank.do?command=rank';"> <li>랭킹</li></a>
		<a href="#" onclick="location.href='../aboutus.do?command=aboutus';"> <li>About Us</li></a>
		<a href="#" onclick="location.href='../contact.do?command=contact';"> <li>Contact</li></a>
		<c:if test="${empty sessionScope.logindto }">
			<button id="loginbtn" onclick="location.href='../login.do?command=login';">Login</button>
		</c:if>
		<c:if test="${not empty sessionScope.logindto }">
			<button id="logoutbtn" onclick="location.href='../login.do?command=logout';">Logout</button>
		</c:if>
</ul>
<hr/>
</a>	
</header>

</body>
</html>