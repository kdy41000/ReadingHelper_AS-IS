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
<link href="../resources/css/aboutus/aboutuspage.css"    
     rel="stylesheet"    
     type="text/css" />  
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../resources/js/aboutus/aboutuspage.js"></script>
<body>
<%@ include file="../home/header.jsp" %>
<section class="about" id="about">
            <div class="container" style="text-align:center; margin-top: -10%;">
                <div class="heading text-center">
                    <h2>About
                        <span>Us</span></h2>
                    <p>리딩헬퍼는 독서력향상을 위해 개발되었습니다.
                        <br>
                           많은 분들의 이용 부탁드립니다.
                    </p>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <img src="../resources/images/aboutus/aboutusmain.jpg" alt="about" class="img-fluid" width="100%" style="margin-left:1%;">
                    </div><br/>
                    <div class="col-lg-6">
                        <h3>리딩헬퍼의 사용용도 </h3>
                        <p>자신이 읽은 책의 글자수를 체크하고, Reading 소요시간을 체크할 수 있습니다. <br/>
                                                           총기록을 확인 및 랭킹에 도전하면서 독서력을 향상시킬 수 있습니다.</p>
                        <br/>
                        <h2>리딩헬퍼는 이런분들에게 적합합니다.</h2>
                        <div class="row">
                            <div class="col-md-6">
                                <h4>
                                   <i class="far fa-star"></i>1.독서력 향상을 희망하시는분</h4>
                            </div>
                     
                            <div class="col-md-6">
                                <h4>
                                    <i class="far fa-star"></i>2.자신의 독서능력을 확인하고 싶으신분</h4>
                            </div>
                         
                            <div class="col-md-6">
                                <h4>
                                   <i class="far fa-star"></i>3.자신의 독서습관을 확인하고 싶으신분</h4>
                            </div> 
                            <br/>
                            <h1 style="color:#7f4936;">Step</h1>
                            <img alt="img" src="../resources/images/aboutus/step.png"/>
                           
                        </div>
                    </div>
                </div>
            </div>
        </section>
<%@ include file="../home/footer.jsp" %>        
</body>
</html>