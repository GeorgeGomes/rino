   var contagem = 5;
   var interval;
 /*   var interval;
    var videoFull;


    window.addEventListener("DOMContentLoaded", function() {
        var canvas = document.getElementById("canvas"),
        context = canvas.getContext("2d"),
        video = document.getElementById("video"),
        videoObj = { "video": true },
        errBack = function(error) {
                console.log("Video capture error: ", error.code); 
        };  
        if(navigator.getUserMedia) {
            navigator.getUserMedia(videoObj, function(stream) {
                video.src = stream;
                video.play();
            }, errBack);
        } else if(navigator.webkitGetUserMedia) {
            navigator.webkitGetUserMedia(videoObj, function(stream){
                video.src = window.webkitURL.createObjectURL(stream);
                video.play();
            }, errBack);
        }
        else if(navigator.mozGetUserMedia) {
            navigator.mozGetUserMedia(videoObj, function(stream){
                video.src = window.URL.createObjectURL(stream);
                video.play();
            }, errBack);
        }
    }, false);
*/
    function sendImage(){
    	 $('#formImage\\:postarFacebook').hide();
         $('#formImage\\:tirarOutraFoto').hide();
         $('.loading').show();
    	$("#formImage\\:image").val(canvas.toDataURL('image/png'))
    }

    function contar(){
    	//onLightLed();
        tirarFoto();
        interval = setInterval("tirarFoto()", 1000);
    }
    
    function tirarOutraFoto(){
        window.reload();
    }

    function tirarFoto(){
        if(contagem < 0){
            //onFlashLed();
        		
        	
        	
            $("#contagem").html("");
        	$("#botoes").show();
        	clearInterval(interval);
            
        }else{
            var left = $("video").width() / 2;
            $("#contagem").css("margin-left",left);
            $("#contagem").html(contagem);
            contagem = contagem -1;
        }
    }
    
    function onLightLed(){
    	console.log("Ligando o led de iluminação...");
    	$.ajax("/rino/app/general/lightLed.xhtml");
    	  
    }
    
    function onFlashLed(){
    	console.log("Ligando o flash...");
    	$.ajax("/rino/app/general/flashLed.xhtml");
    }