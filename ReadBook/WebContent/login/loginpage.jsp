<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>  
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 카카오 로그인 -->
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<!-- 카카오 로그인 -->   
<title>Insert title here</title>
</head>
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
         <h3 class="login-tab"><a class="active" href="#login-tab-content">Login</a></h3>
         <h3 class="signup-tab"><a href="#signup-tab-content">Sign Up</a></h3>
      </div><!--.tabs-->
   <!-- 선택영역 -->
   <!-- 회원가입영역 -->
      <div class="tabs-content" style="margin-top: 5%;">
         <div id="signup-tab-content">
            <form class="signup-form" action="../login.do" method="post">
               <input type="hidden" name="command" value="signupres"/>
               <input type="text" class="input" id="user_id" name="user_id" autocomplete="off" placeholder="Id" value=""/>
               
               <input type="button" class="idChk" onclick="idChk();" value="중복체크">&nbsp;&nbsp;&nbsp;
               <span id="usingStatus" style="display:none;"></span>
               
               
               
               <input type="password" class="input" id="user_pass" name="user_pass" autocomplete="off" placeholder="Password" style="margin-top:2%;">
               <input type="text" class="input" id="user_name" name="user_name" autocomplete="off" placeholder="Username">
               <input type="email" class="input" id="user_email" name="user_email" autocomplete="off" placeholder="Email">
               
               <input type="submit" class="button" value="Sign Up" style="background:red;"/>
            </form><!--.login-form-->
            <div class="help-text">
               <p>By signing up, you agree to our</p>
               <p><a href="#">Terms of service</a></p>
            </div><!--.help-text-->
         </div><!--.signup-tab-content-->
   <!-- 회원가입영역 -->
   <!-- 로그인영역 -->
         <div id="login-tab-content" class="active">
            <form class="login-form" action="../login.do" method="post">
               <input type="hidden" name="command" value="loginres"/>
               <input type="text" class="input" id="user_login" name="user_id" autocomplete="off" placeholder="Id">
               <input type="password" class="input" id="user_pass" name="user_pass" autocomplete="off" placeholder="Password">
               <input type="checkbox" class="checkbox" id="remember_me">

               <input type="submit" class="button" value="Login"><br/><br/>
            <!-- 카카오 로그인 버튼 노출 영역
               <a id="kakao-login-btn" style="margin-left:30%;"></a><br/>
               <a href="http://developers.kakao.com/logout"></a>
             //카카오 로그인 버튼 노출 영역 -->
            </form><!--.login-form-->
            <div class="help-text">
               <p style="font-size:20px;"><a href="#" onclick="location.href='../login.do?command=searchInfo';">Forget your Id & Password?</a></p>
            </div><!--.help-text-->
         </div><!--.login-tab-content-->
   <!-- 로그인영역 -->
      </div><!--.tabs-content-->
   </div><!--.form-wrap-->
   <%@ include file="../home/footer.jsp" %>   
</section>
<!-- 아이디 중복체크 영역 -->
<script type='text/javascript'>
   function idChk(){
      
      var user_id = document.getElementById("user_id").value;
      
      location.href="../login.do?command=idChk&user_id="+user_id;
   }
</script>
<%
   String canuse = request.getParameter("canuse");
   String user_id = request.getParameter("user_id");
   
   System.out.println(canuse+"::jsp단 넘어온 canuse");
////////////////////////////////////////////////////////////
   if(canuse!=null || user_id!=null){
      
   
      if(canuse.equals("true")){   //아이디 사용가능하면
         
%>
   <script type='text/javascript'>
   $(function(){
      canuse();
   });   
   </script>
<%
      }else if(canuse.equals("false")){
%>
   <script type='text/javascript'>
   $(function(){
      cannotuse();
   });   
   </script>
<%
      }
   }   
%>
<script type='text/javascript'>
$(function(){
   tab = $('.tabs h3 a');
   
   $('.login-tab').click(function(event){
      $(this).html('<a class="active" href="#login-tab-content">Login</a>');
      $('.signup-tab').html('<a href="#signup-tab-content">Sign Up</a>');

         event.preventDefault();
         tab.removeClass('active');
         $(this).addClass('active');

         tab_content = $(this).attr('href');
         $('div[id$="tab-content"]').removeClass('active');
         $(tab_content).addClass('active');
         $('#login-tab-content').show();
         $('#signup-tab-content').hide();
      });
   
   $('.signup-tab').click(function(event){
      $(this).html('<a class="active" href="#signup-tab-content">Login</a>');
      $('.login-tab').html('<a href="#login-tab-content">Sign Up</a>');

         event.preventDefault();
         tab.removeClass('active');
         $(this).addClass('active');

         tab_content = $(this).attr('href');
         $('div[id$="tab-content"]').removeClass('active');
         $(tab_content).addClass('active');
         $('#signup-tab-content').show();
         $('#login-tab-content').hide();
      });
      
      
   });

</script>
<script type='text/javascript'>
   function canuse(){
      
      $('.signup-form > input[name=user_id]').val('<%=user_id %>');
      $('#usingStatus').text('사용가능한 아이디 입니다.');
      $('#usingStatus').css('color','blue');
      $('#usingStatus').show();
      
      //test1();
      $('.login-tab').html('<a href="#login-tab-content">Login</a>');
      $('.signup-tab').html('<a class="active" href="#signup-tab-content">Sign Up</a>');
      
      $('#signup-tab-content').show();
      $('#login-tab-content').removeClass('active');
      
   }
   
   function cannotuse(){
      
      $('.signup-form > input[name=user_id]').val('<%=user_id %>');
      $('#usingStatus').text('이미 사용중인 아이디 입니다.');
      $('#usingStatus').css('color','red');
      $('#usingStatus').show();

      $('.login-tab').html('<a href="#login-tab-content">Login</a>');
      $('.signup-tab').html('<a class="active" href="#signup-tab-content">Sign Up</a>');
      
      $('#signup-tab-content').show();
      $('#login-tab-content').removeClass('active');
      
   }
   
</script>

<!-- 아이디 중복체크 영역 -->

<!-- 유효성 검사 영역 -->
<script type='text/javascript'>
$(function(){
   $('.signup-form').submit(function(){
      
      if($('input[name=user_id]').val()==""){
    	  beautyAlt('아이디를 입력해 주십시오.');
         return false;
      }else if($('#usingStatus').text()=="이미 사용중인 아이디 입니다."){
    	  beautyAlt('아이디 중복체크를 진행 해 주십시오.');
         return false;
      }else if($('input[name=user_pass]').val()==""){
    	  beautyAlt("비밀번호를 입력해 주십시오.");
         return false;
      }else if($('input[name=user_name]').val()==""){
    	  beautyAlt("이름을 입력해 주십시오.");
         return false;
      }else if($('input[name=user_email]').val()==""){
    	  beautyAlt("이메일을 입력해 주십시오.");
         return false;
      }else{
         return true;
      }
   });
});

   
</script>
<!-- 유효성 검사 영역 -->
 <!-- 카카오 로그인 버튼 노출 영역 -->
  <script type='text/javascript'>

  //<![CDATA[
    // 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('9ab48ce9061d4e7aefcee8e042fac975');  //여기서 아까 발급받은 키 중 javascript키를 사용해준다.
    // 카카오 로그인 버튼을 생성합니다.
  
    Kakao.Auth.createLoginButton({

          container: '#kakao-login-btn',

          success: function(authObj) {

            Kakao.API.request({

              url: '/v2/user/me',

              success: function(res) {

                    //alert(JSON.stringify(res)); //<---- kakao.api.request 에서 불러온 결과값 json형태로 출력

                    //alert(JSON.stringify(authObj)); //<----Kakao.Auth.createLoginButton에서 불러온 결과값 json형태로 출력

                    console.log(res.id);//<---- 콘솔 로그에 id 정보 출력(id는 res안에 있기 때문에  res.id 로 불러온다)

                    console.log(res.kaccount_email);//<---- 콘솔 로그에 email 정보 출력 (어딨는지 알겠죠?)

                    console.log(res.properties['nickname']);//<---- 콘솔 로그에 닉네임 출력(properties에 있는 nickname 접근 
               var user_email = res.kakao_account.email;
                   var user_id = res.id;
                   var user_name = res.properties['nickname'];
                   
                   //alert(user_email+"::카카오이메일");
                   //alert(user_id+"::카카오아이디");
                   //alert(user_name+"::카카오이름");
                   location.href="../login.do?command=kakaoSignup&user_email="+user_email+"&user_name="+user_name+"&user_id="+user_id;   
                // res.properties.nickname으로도 접근 가능 )

                    console.log(authObj.access_token);//<---- 콘솔 로그에 토큰값 출력

                  }

                })

              },

              fail: function(error) {

                beautyAlt(JSON.stringify(error));

              }

            });

       //]]>

</script>
<!-- 카카오 로그인 버튼 노출 영역 -->   
<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
</body>
</html>