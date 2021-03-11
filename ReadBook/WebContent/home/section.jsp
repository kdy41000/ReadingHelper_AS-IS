<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!--  -->
<link href="../resources/css/home/mediaQuery.css"    
	  rel="stylesheet"    
	  type="text/css" /> 
    <link rel="stylesheet" type="text/css" href="style.css">
    <link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<!--  -->
<div class="webcontroller"style="text-align: center; margin-top:8%; ">
	<img src="../resources/images/home/crying.png">
	<h2 >이 프로젝트는 모바일환경에서 만 동작합니다.</h2><br/>
</div>
<div class="mobilecontroller">
	<%@ include file="header.jsp" %>
	<body class="mainBody">
	<section style="height:100%;">
	<h2 style="text-align: center; font-size: 60px;">나의 독서력은?</h2><br/>
		<!-- <div class="Iam">
		<p>This is</p>
		<b>
		  <div class="innerIam">
		    사진촬영 으로<br /> 
		   당신의 속독능력을<br />
		    측정해줍니다.<br />
		    친구들과 경쟁하며<br />
		    독서능력을 향상시키세요<br />
		    we can do it !
		    </div>
		</b>
		
		</div> -->
		<!--  -->
		
		
		<!-- home/mainbookicon.png--><img style="width: 100%; height: 700px; margin-top: -10%;"alt="img" src="../resources/images/home/mainbookicon.png"/>
		<br/>
		<img class="changecolor" onclick="location.href='../measure.do?command=measure&user_id=${sessionScope.logindto.user_id }';" style="width: 30%; height: 250px; margin: 5% 0% 0% 13%;"alt="img" src="../resources/images/home/stopwatch-1.png"/>
		<img class="changecolor" onclick="location.href='../takepicture.do?command=takepicture&user_id=${sessionScope.logindto.user_id }';" style="width: 30%; height: 270px; margin: 5% 0% 0% 13%;"alt="img" src="../resources/images/home/photo-camera.png"/>
		<img class="changecolor" onclick="location.href='../record.do?command=record&currentpage=1&user_id=${sessionScope.logindto.user_id }';" style="width: 30%; height: 250px; margin: 5% 0% 0% 13%;"alt="img" src="../resources/images/home/calendar.png"/>
		<img class="changecolor" onclick="location.href='../rank.do?command=rank';" style="width: 30%; height: 250px; margin: 3% 0% 0% 13%;"alt="img" src="../resources/images/home/star-1.png"/>
	
	<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	</section>
	</body>
</div>
<%@ include file="footer.jsp" %>
</html>