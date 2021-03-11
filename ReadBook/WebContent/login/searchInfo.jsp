<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../resources/css/login/loginpage.css"    
	  rel="stylesheet"    
	  type="text/css" />  
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../resources/js/login/loginpage.js"></script>
<body>
<%@ include file="../home/header.jsp" %>
<section>
<div class="form-wrap" style="margin-top: 40%;">
	<!-- 선택영역 -->
		<div class="tabs">
			<h3 class="login-tab"><a class="active" href="#login-tab-content">SearchId</a></h3>
			<h3 class="signup-tab"><a href="#signup-tab-content">SearchPw</a></h3>
		</div><!--.tabs-->
	<!-- 선택영역 -->
	<!-- 비밀번호찾기 영역 -->
		<div class="tabs-content" style="margin-top: 13%;">
			<div id="signup-tab-content">
				<form class="signup-form" action="../login.do" method="post">
					<input type="hidden" name="command" value="searchPwres"/>
					<input type="text" class="input" id="user_id" name="user_id" autocomplete="off" placeholder="Id"/>
					<input type="text" class="input" id="user_name" name="user_name" autocomplete="off" placeholder="Name">
					<input type="email" class="input" id="user_email" name="user_email" autocomplete="off" placeholder="Email">
					<p style="color:lightgray;">정보입력 시 이메일로 비밀번호가 전송됩니다.</p><br/>
					<input type="submit" class="button" value="Search For Pw" style="background:red;"/>
				</form><!--.login-form-->
				
			</div><!--.signup-tab-content-->
	<!-- 비밀번호찾기 영역 -->
	<!-- 아이디찾기 영역 -->
			<div id="login-tab-content" class="active">
				<form class="login-form" action="../login.do" method="post">
					<input type="hidden" name="command" value="searchIdres"/>
					<input type="text" class="input" id="user_name" name="user_name" autocomplete="off" placeholder="Name">
					<input type="email" class="input" id="user_email" name="user_email" autocomplete="off" placeholder="Email">
					<p style="color:lightgray;">정보입력 시 이메일로 아이디가 전송됩니다.</p><br/>
					<input type="submit" class="button" value="Search For Id" style="background:red;"><br/><br/>

				</form><!--.login-form-->
				
			</div><!--.login-tab-content-->
	<!-- 아이디찾기 영역 -->
		</div><!--.tabs-content-->
	</div><!--.form-wrap-->
	<%@ include file="../home/footer.jsp" %>	
</section>
</body>
</html>