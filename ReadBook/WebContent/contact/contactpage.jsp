<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link href="../resources/css/contact/contactpage.css"    
	  rel="stylesheet"    
	  type="text/css" />  
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../resources/js/contact/contactpage.js"></script>
<body>
<%@ include file="../home/header.jsp" %>
<div id="form-main">
  <div id="form-div" style="margin-top: 45%;">
    	<h1 style="text-align:center;">CONTACT</h1>
    <form class="form" id="form1" action="../contact.do" method="post">
      <input type="hidden" name="command" value="sendMessage"/>
      <p class="name">
        <input name="name" type="text" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="Name" id="name" />
      </p>
      
      <p class="email">
        <input name="email" type="email" class="validate[required,custom[email]] feedback-input" id="email" placeholder="Email" />
      </p>
      
      <p class="text">
        <textarea name="text" class="validate[required,length[6,300]] feedback-input" id="comment" placeholder="Message"></textarea>
      </p>
      
      
      <div class="submit">
        <input type="submit" value="Send" id="button-blue" onclick="checkAll();"/>
        <div class="ease"></div>
      </div>
    </form>
  </div>
<%@ include file="../home/footer.jsp" %>  
</body>
<script type="text/javascript">
	$('.form').submit(function(){
		if($('input[name=name]').val()==""){
			beautyAlt('이름을 입력해 주십시오.');
			return false;
		}else if($('input[name=email]').val()==""){
			beautyAlt('이메일을 입력해 주십시오.');
			return false;
		}else if($('textarea').val()==""){
			beautyAlt('내용을 입력해 주십시오.');
			return false;
		}else{
			return true;
		}
	});
</script>
</html>