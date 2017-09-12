<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container-content">
	<div class="section-video-banner">
		<video muted="muted"  loop autoplay >
			<source  src="<c:url value='/resources/static/video/main/main_banner.mp4' />">
		</video>
		
		<div class="video-banner-content">
			<h2>디맨드, 헬쓰케어 & 서비스 디자인</h2>
			<p>
			</p>
		</div>
		
		<div class="video-banner-alternative"></div>
	</div>
</div>

<script>
$('.section-video-banner video').on('loadedmetadata', function() {
    var $width, $height,
        $videoWidth = this.videoWidth,
        $videoHeight = this.videoHeight,
        $aspectRatio = $videoWidth / $videoHeight;
        
    (adjustSize = function() {

        $width = $(window).width();
        $height = $(window).height();
                    
        $boxRatio = $width / $height;
                    
        $adjustRatio = $aspectRatio / $boxRatio;
                    
        $('#section-video-banner').css({'width' : $width+'px', 'height' : $height+'px'}); 
                    
        if($boxRatio < $aspectRatio) { 
            $vid = $('.section-video-banner video').css({'width' : $width*$adjustRatio+'px'}); 
        } else {
            $vid = $('.section-video-banner video').css({'width' : $width+'px'});
        }

    })();
           
    $(window).resize(adjustSize);
});

function isMobile() {
    var userAgent = navigator.userAgent;
    if (userAgent.match(/iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson/i) != null || userAgent.match(/LG|SAMSUNG|Samsung/) != null) {
        return true;
    }else{
        return false;
    }
}

if(!isMobile()) {
    $('.section-video-banner video').prop('autoplay', true);
}else{
    $('.section-video-banner video').prop('autoplay', false);
}
</script>