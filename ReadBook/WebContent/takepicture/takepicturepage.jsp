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
<link href="../resources/css/measure/measurepage.css"    
	  rel="stylesheet"    
	  type="text/css" /> 
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<%@ include file="../home/header.jsp" %>
</head>
<body>
<section class="container">
<div style="text-align:center;">
<h1 style="margin-top: 10%; font-size: 50px;">글자수 사진찍기</h1>

<c:if test="${!empty sessionScope.min }">
<h1 id="time" style="font-size:35px;"><b>소요시간:</b>&nbsp;&nbsp;<a style="color:blue;">${sessionScope.min }분00초</a></h1>
	<div style="color:gray;">
		<h2>소요시간동안 읽은 페이지를 촬영해주세요.</h2>
		<h2>1페이지를 못읽으셨다면 읽은부분을 제외한 나머지 부분은 가리고 촬영해주세요.</h2>
	</div>
</c:if>
	<form action="../takepicture.do" method="post" enctype="multipart/form-data">
		<!--추가 버튼을 눌러보세요~
<div id="attachFileDiv"><input type="file" name="files" value="" size="40"><input type="button" value="추가" onclick="attachFile.add()" style="margin-left:5px"></div>
 -->
		<input type="hidden" name="command" value="ocrCk">
		<!--<input type="file" accept="image/*" capture="camera"  id="fileUpload" name="filename" style="display:none;"/>-->
		<!-- 멀티플 이미지 소스 0322 추가  -->
			<input type="file" accept="image/*" capture="camera"  id="input_imgs" name="filename" style="display:none;" multiple="multiple" />
			<div class="input_Plus">
			</div> 
		<!-- 멀티플 이미지 소스 0322 추가 -->
		<br><br>
		<!--<input type="submit" value="글자읽기" id="imageSubmit" style="font-size: 10pt;">-->
		  <!-- <button type="submit" id='imageSubmit' class='rounded-circle text-center'>다찍었어요</button><br/><br/> -->
		  <button type="submit" id='imageSubmit'  onclick='submitAjax();' class='rounded-circle text-center'>다찍었어요</button><br/><br/>
  		  <!--<button id='repic'>또찍기</button>-->
  		  <br/><br/>
	</form>

</div>
<fieldset style="width:90%; height:700px; border:1px solid black; margin-left: 3%;" >
	<img id="image_section" src="#" alt="사진을 찍어주세요." style="width:100%; height:700px;"/>
	<div class="imgs_wrap">
	
	</div>
</fieldset>
<img onclick="takepicture();" alt="img" src="../resources/images/takepicture/takebutton.png" style="margin: 10% 0% 0% 38%;"/>


<%@ include file="../home/footer.jsp" %>
</section>
</body>
<script type="text/javascript">
$(function() {
	  $('a[href*=#]').on('click', function(e) {
	    e.preventDefault();
	    $('html, body').animate({ scrollTop: $($(this).attr('href')).offset().top}, 500, 'linear');
	  });
	});
var fileInput ="";
var fileNameCnt = 0;
//var nowfileClass="";

function takepicture(){
	//$('#fileUpload').trigger("click");
	fileNameCnt +=1; //
	//form에 input추가 
	fileInput ="<input type='file' accept='image/*' capture='camera' class='input_imgs"+fileNameCnt+"' name='plusname"+fileNameCnt+"'  multiple='multiple' style='display:none;' />"
	$('.input_Plus').append(fileInput);
	
	 $("input[type=file][name=plusname"+fileNameCnt+"]").trigger("click");
	 startFunc();
	//$(nowfileClass).trigger("click");

}
	 //멀티플 이미지 소스 0322 추가  
	 var sel_files = [];
	 
	function startFunc(){
		// alert($("input[name=plusname"+fileNameCnt+"]"));
		 //$("#input_imgs").on("change", handleImgsFilesSelect);
		 $("input[type=file][name=plusname"+fileNameCnt+"]").on("change", handleImgsFilesSelect);
	 }
	 
	 function handleImgsFilesSelect(e){
		 $("fieldset").css("height","auto");
		 var files = e.target.files;
		 var filesArr = Array.prototype.slice.call(files);
		 
		 filesArr.forEach(function(f){
			if(!f.type.match("image.*")){
				alert("확장자는 이미지 확장자만 가능합니다.");
				return;
			} 
			
			sel_files.push(f);
			
			var reader = new FileReader();
			reader.onload = function(e){
				fileNameCnt +=1; //input file의 name을 생성
				
				var img_html = "<img style='width:100%; height:100%;' src=\""+e.target.result +"\" /></br>";
				$(".imgs_wrap").append(img_html);
			}
			reader.readAsDataURL(f);
			
		 });
	 }
	//멀티플 이미지 소스 0322 추가   
</script>
</html>