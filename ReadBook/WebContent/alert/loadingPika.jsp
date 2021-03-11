<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로딩페이지</title>
</head>
<link href="../resources/css/home/loadingPika.css"    
	  rel="stylesheet"    
	  type="text/css" />  
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<body>
		<div class="loading"  >
            <img src="http://a.top4top.net/p_1990j031.gif" alt="Loading">
        </div>
        <div class="mouse original"></div>
</body>
<div class="text">
  <p>문서</p>
  <p>
    <span class="word wisteria">Loading..</span>
    <span class="word belize">판독중..</span>
    <span class="word pomegranate">Loading..</span>
    <span class="word green">읽고있어요..</span>
    <span class="word midnight">핔카...</span>
  </p>
</div>
<script type="text/javascript">
//피카피
function showLoadingBar() {
	$('.loading').show();
	$('.mouse original').show();
	
}
function hideLoadingBar() { 
	$('.loading').hide();
	$('.mouse original').hide();
}

$(".loading").height($(window).height());
$(".loading").width($(window).width());

    
$(".loading img").css({
    paddingTop: ($(".loading").height() - $(".loading img").height()) / 2,
    paddingLeft: ($(".loading").width() - $(".loading img").width()) / 2
});

$(window).resize(function () {
   
    "use strict";
    
    $(".loading").height($(window).height());
    $(".loading").width($(window).width());


    $(".loading img").css({
        paddingTop: ($(".loading").height() - $(".loading img").height()) / 2,
        paddingLeft: ($(".loading").width() - $(".loading img").width()) / 2
    });
    
});

$(window).mousemove(function (e) {
   
    "use strict";
    
    $(".original").css({
        left: e.pageX - 16,
        top: e.pageY - 16
    });
    
});

$("body").on("click", function (e) {
   
    "use strict";
    
    $(".original").clone(true).appendTo("body").css({
        left: e.pageX - 16,
        top: e.pageY - 16
    }).removeClass("original");
    
});//피카쥬

//글씨
var words = document.getElementsByClassName('word');
var wordArray = [];
var currentWord = 0;

words[currentWord].style.opacity = 1;
for (var i = 0; i < words.length; i++) {
  splitLetters(words[i]);
}

function changeWord() {
  var cw = wordArray[currentWord];
  var nw = currentWord == words.length-1 ? wordArray[0] : wordArray[currentWord+1];
  for (var i = 0; i < cw.length; i++) {
    animateLetterOut(cw, i);
  }
  
  for (var i = 0; i < nw.length; i++) {
    nw[i].className = 'letter behind';
    nw[0].parentElement.style.opacity = 1;
    animateLetterIn(nw, i);
  }
  
  currentWord = (currentWord == wordArray.length-1) ? 0 : currentWord+1;
}

function animateLetterOut(cw, i) {
  setTimeout(function() {
        cw[i].className = 'letter out';
  }, i*80);
}

function animateLetterIn(nw, i) {
  setTimeout(function() {
        nw[i].className = 'letter in';
  }, 340+(i*80));
}

function splitLetters(word) {
  var content = word.innerHTML;
  word.innerHTML = '';
  var letters = [];
  for (var i = 0; i < content.length; i++) {
    var letter = document.createElement('span');
    letter.className = 'letter';
    letter.innerHTML = content.charAt(i);
    word.appendChild(letter);
    letters.push(letter);
  }
  
  wordArray.push(letters);
}

changeWord();
setInterval(changeWord, 4000);

//글씨

</script>
</html>