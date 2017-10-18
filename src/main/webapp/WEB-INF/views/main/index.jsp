<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>

<div class="container-content">
	<div class="section-video-banner">
		<video muted="muted"  loop autoplay >
			<source  src="<c:url value='/resources/static/video/main/main_banner.mp4' />">
		</video>
		
		<div class="video-banner-content">
			<div class ="section-demand-intro">
				<div class="intro-content">
					<div class="content-title">
						<h1>DESIGN HUMAN LIFE</h1>
					</div>
					<div class="content-body">
						<p>Demand의 목표는 인간 친화적인 디자인을 통해, <br> 사람들의 삶을 풍요롭게 만드는 것입니다</p>
					</div>
					<div class="content-button" onclick="javascript:navigateToCompany()">
		               <span >MORE</span>
		            </div>
		           
	            </div>
            </div>
		</div>
		
		<div class="video-banner-bottom">
			<i id="arrow" class="fa fa-angle-down" aria-hidden="true"></i>
		</div>
		
		<div class="video-banner-alternative"></div>
	</div>
	<div class="section-design-intro">
         <div class="intro-header">
            <div class="header-title">
               <h1>Service Design</h1>
            </div>

            <div class="header-title-sub">
               <p>
                  제조업, 서비스업, 공공서비스업을 망라하는 다양한 현장에 적용 가능한 서비스 디자인 프로세스를 통해,<br>
                  Demand는 여러분의 비즈니스 파트너로서 최적의 솔루션을 제공합니다
               </p>
            </div>

            <div class="header-button">
               <span>MORE</span>
            </div>
         </div>

         <div class="intro-content">
            <ul>
               <li>
                  <div class="content-item">
                     <h1 class="item-title">1</h1>
                     <h2 class="item-title-sub">
                        Opportunity<br>Discovering
                     </h2>
                  </div>
               </li>

               <li>
                  <div class="content-item">
                     <h1 class="item-title">2</h1>
                     <h2 class="item-title-sub">
                        Service<br>Developing&nbsp;
                     </h2>
                  </div>
               </li>

               <li>
                  <div class="content-item">
                     <h1 class="item-title">3</h1>
                     <h2 class="item-title-sub">
                        Business<br>Modeling&nbsp; &nbsp;
                     </h2>
                  </div>
               </li>
            </ul>
         </div>
      </div>

      <div class="section-healthcare-intro">
         <div class="intro-header">
            <div class="header-title">
               <h1>Healthcare Service</h1>
            </div>

            <div class="header-title-sub">
               <p>
                  다양한 사용자층을 대상으로 하는 인간친화적 Healthcare Service를 제공함으로써 <br>사람들이
                  보다 건강하고 활기차게 생활할 수 있도록 돕고있습니다
               </p>
            </div>

            <div class="header-button">
               <span>MORE</span>
            </div>
         </div>

         <div class="intro-content">
            <ul>
               <li>
                  <div class="content-item">
                     <img class="item-image" src="<c:url value='/resources/static/img/main/healthcare_wellfamilyhouse_logo.png' />">
                     <h2 class="item-title">WellFamilyHouse</h2>
                     <p class="item-title-sub">
                        현대 가족의 라이프 스타일에 맞춰 서로의 프라이버시를 존중하며 유대관계를 증진할 수 있는 스마트 디바이스 기반의 텔레케어 시스템입니다.
                     </p>
                  </div>
               </li>

               <li>
                  <div class="content-item">
                     <img  class="item-image" src="<c:url value='/resources/static/img/main/healthcare_h2o_logo.png' />">
                     <h2 class="item-title">H2O<br>(Happy Healthy One)</h2>
                     <p class="item-title-sub">
                        통합 스트레스 관리 프로그램으로, 수면의 질 및 스트레스 수준에 대한 주관적인 평가를 기반으로 적절한 프로그램을 추천합니다.
                     </p>
                  </div>
               </li>

               <li>
                  <div class="content-item">
                     <img  class="item-image" src="<c:url value='/resources/static/img/main/healthcare_goodbuddy_logo.png' />">
                     <h2 class="item-title">Good buddy</h2>
                     <p class="item-title-sub">
                        통합 신체건강 관리 프로그램으로, 맞춤형 관리 방안을 제공하여 효과적으로 대사증후군을 예방/관리할 수 있는 서비스입니다.
                     </p>
                  </div>
               </li>
            </ul>
         </div>

     </div>
	
	 <div class="section-promote-question-intro">
   		<div class="intro-header">
          <div class="header-title">
             <h1>디맨드를 확인해보세요</h1>
          </div>

          <div class="header-title-sub">
             <p>
		               디맨드 관련 대내외 홍보와, 보도 및 최근 자료 등을 확인해보세요. 
             </p>
          </div>
       	</div>
   		<div class="intro-content">
   			<div class="content-left">
   				<span>홍보</span>
	   		</div>
	   		<div class="content-right">
	   			<span>문의</span>
	   		</div>
   		</div>
   		
   		
   </div>
</div>

<script>

function navigateToCompany(){
	var url = "${contextPath}/company";
    location.href = url; 
}

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

$("#arrow").click(function(){
    $('html, body').animate({
       scrollTop: $(".section-design-intro").offset().top
    }, 600);
 });
</script>