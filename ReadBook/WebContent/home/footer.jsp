<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
footer{ 
     position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
    height: 130px;
  	background:#f4f4f4;
  }
</style>
</head>
<body style="margin:0%; padding:0%;">
<footer><br/>
<address style="text-align:center; font-style: inherit; color:#8b8b8b;">made by DY and BY<br/>문의메일 : kdy41000@naver.come<br/>&copy;ReadBook Mobile Web Application</address>
</footer>
</body>
<script type="text/javascript">
   function beautyAlt(msg){
      Swal.fire({
           title: msg,
           text: "버튼을 클릭해 주세요!",
           icon: 'warning',
           confirmButtonColor: '#3085d6',
           confirmButtonText: '확인'
         })
   }
</script>
<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
</html>