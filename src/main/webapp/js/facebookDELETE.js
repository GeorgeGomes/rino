   var contagem = 5;
    var interval;
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



    function contar(){
    	onLightLed();
        tirarFoto();
        interval = setInterval("tirarFoto()", 1000);
    }
    
    function tirarOutraFoto(){
    	$("video").show();
    	$("canvas").hide();
    	$("#botoes").hide();
    	contagem = 5;
    }

    function tirarFoto(){
        if(contagem < 0){
            var context = canvas.getContext("2d");      
            //context.drawImage(videoFull, 0, 0, 640, 480);
            imageWidth = canvas.height + 50;
            imageHeight = canvas.height;
            
            var p1 = canvas.width / 2 - imageWidth / 2;
            var p2 = canvas.height / 2 - imageHeight / 2;
            //alert(p1)
            //alert(p2)
            context.drawImage(videoFull, p1, p2,imageWidth,imageHeight);
            
            
            clearInterval(interval);
            
            $("#contagem").html("");
        	$("video").hide();
        	$("canvas").show();
        	$("#botoes").show();
            
        }else{
            var left = $("canvas").width() / 2;
            $("#contagem").css("margin-left",left);
            $("#contagem").html(contagem);
            contagem = contagem -1;
            console.log(left);
        }
    }
    
    function onLightLed(){
    	console.log("Ligando o led de iluminação...")
    	$.ajax("lightLed.xhtml");
    	  
    }