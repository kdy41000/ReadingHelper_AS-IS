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
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
<script type="text/javascript">
window.onload = function(){	
	Swal.fire({
		  title: '${sessionScope.msg}',
		  text: "버튼을 클릭해 주세요!",
		  icon: 'warning',
		  confirmButtonColor: '#3085d6',
		  confirmButtonText: '확인'
		}).then(() => {
			 location.href="../${sessionScope.loc}";
				});
}
</script>	
<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
</body>
</html>