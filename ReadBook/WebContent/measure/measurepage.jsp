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
<link href="../resources/css/measure/measurepage.css"    
	  rel="stylesheet"    
	  type="text/css" />  
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../resources/js/measure/measurepage.js"></script>
<body>
<%@ include file="../home/header.jsp" %>
<section>
<div class="container">
  <h1 class="text-center">독서력 측정하기</h1>
  
   <div id='count'></div>
  <div id='timeChange'>
    <div id="minus" onclick="timeCh(1)" class='rounded text-center'> - </div>
    <div id='plus' onclick="timeCh(2)"class='rounded text-center'> + </div>
  </div>
  <button id='timer' class='rounded-circle text-center'>Start</button><br/><br/>
  <button id='reset'>Reset</button>
  
  <!-- 오디오 영역 -->
  <audio id='audio_play' src='../resources/audio/measure/alram.mp3'></audio> 
  <!-- 오디오 영역 -->
  <button id="movepage" style="display:none;">MovePage</button>
 
</div>
<%@ include file="../home/footer.jsp" %>
</section>

<script type="text/javascript">

</script>

</body>
</html>