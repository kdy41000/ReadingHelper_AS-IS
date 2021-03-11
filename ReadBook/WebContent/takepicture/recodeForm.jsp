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
<link href="../resources/css/record/recodeForm.css"    
	  rel="stylesheet"    
	  type="text/css" />  
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../resources/js/contact/contactpage.js"></script>
<body>
<%@ include file="../home/header.jsp" %>
<div id="form-main">
  <div id="form-div" style="margin-top: 45%;">
    	<h1 style="text-align:center;">기록하기</h1>
    <form class="form" id="form1" action="../record.do" method="post">
      <input type = "hidden" value ="chkBook" name="command"/>
      <p class="ID">
        <input name="user_id" type="text" readonly value="${sessionScope.logindto.user_id }" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="ID"  />
      </p>
      <p class="도서명">
        <input name="record_bookname" type="text" value="" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="도서명 (필수)"  />
      </p>
      <p class="분">
	      <c:if test="${!empty sessionScope.min }">
			<input name="record_readingtime" type="text" value="${sessionScope.min }" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" />
		  </c:if>
		  <c:if test="${empty sessionScope.min }">
		  	<input name="record_readingtime" type="text" value="" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="분 (필수)"  />
		  </c:if>
     </p>
      <p class="읽은글자 수">
        <input name="record_readingcharacter" type="text" readonly value="${sessionScope.readingcharacter }" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="글자수"  />
      </p>
   		<p class="페이지">
        <input name="record_readingpage" type="text" value="" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="페이지"  />
      </p>
      <div class="submit">
        <input type="submit" value="Send" id="button-blue"/>
        <div class="ease"></div>
      </div>
    </form>
  </div>
  <script type="text/javascript">
	$('.form').submit(function(){
		if($('input[name=record_bookname]').val()==""){
			beautyAlt('도서명을 입력해주세요.');
			return false;
		}else if($('input[name=record_readingtime]').val()==""){
			beautyAlt('소요시간을 입력하세요.');
			return false;
		}else{
			return true;
		}
	});
</script>
<%@ include file="../home/footer.jsp" %>  
</body>
</html>