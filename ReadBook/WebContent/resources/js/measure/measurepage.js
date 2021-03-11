$(document).ready(function() {
  var min = 1;
  var secs = 0; 
  var run = false;
  
  function disp(val) {
	if(min < 10){
		var mins = "0"+ min;
	} else{
		var mins = min;
	}
    if (val < 10) {
      $('#count').text(mins + " : 0" + val);
    } else {
       $('#count').text(mins + " :  " + val);
    }
  }
  
  disp(secs);
  
  $('#timeChange > div').click(function() {
    if ($(this).is('#plus')) {
      min++;
    } else {
      if (min > 1) {
        min--;
      }
    }
    disp(secs);
  });
  
  $('#timer').click(function() {
	  var minres = min;   //설정한 시간
	  
	  var timer = $(this).text();
	  if(timer=="Start"){
		  $(this).text("Stop");
		  $(this).css("color","red");
	  }else{
		  $(this).text("Start");
		  $(this).css("color","white");
	  }
	
    run = !run;
    var timer = setInterval(function() {
      if (run === false) {
        clearInterval(timer);
      }
      if(min==0 && secs <= 10){      //10초부터 빨간색으로 변경
    	  $('#count').css('color','red');
      }
      
      if(min == 0 && secs == 0) {     //시간 다되면
        clearInterval(timer);
        min = 0;
        secs = 0;
        disp(secs);
        run = false;
        
        $('#timer').hide();  //0초가 되면 Start/Stop버튼 hide
        
        /*오디오 영역*/
        var audio = document.getElementById('audio_play'); 
        if (audio.paused) { 
            audio.play(); 
        }else{ 
            audio.pause(); 
            audio.currentTime = 0 
        } 
        /*오디오 영역*/
        $('#movepage').show();
        $('#movepage').click(function(){
        	location.href="../takepicture.do?command=Steptakepicture&minres="+minres;
        });
        
      } else if (secs < 0) {
        min--;
        secs = 59;
      }
      
			if (run) {
				$('#timer').css('background-color', '#111');
			} else {
				$('#timer').css('background-color', '#333');
			}
			
      disp(secs--);
			
       /*리셋 버튼*/ 
      $('#reset').click(function() {
          $('#count').css('color','black');
          $('#timer').text("Start");
		  $('#timer').css("color","white");
		  $('#timer').show();
		  $('#movepage').hide();
         
        clearInterval(timer);
        min = 1;
        secs = 0;
        disp(secs);    
      });
    }, 1000);
  });
  
});