<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>

<div class="section-image-banner"
	style="background: url('<c:url value='/resources/static/img/main/notice_banner.png' />'); background-size: cover; background-repeat: no-repeat; background-position: center;">
	<div class="image-banner-header"></div>

	<div class="image-banner-content"></div>


	<div class="image-banner-bottom">
		<i id="arrow" class="fa fa-angle-down" aria-hidden="true"></i>
	</div>

</div>

<div class="section-customercenter-navi">
	<ul>
		<li onclick="javascript:navigateToNoticeList()"><span>공지사항</span></li>
		<li onclick="javascript:navigateToQuestionList()"><span>서비스 Q&A</span></li>
	</ul>
</div>


<script>
	var currentPathName = window.location.pathname;
	
	if(currentPathName == "${contextPath}/notices"){
		$( ".section-customercenter-navi ul li:first-child" ).addClass( "current" );
	}else{
		$( ".section-customercenter-navi ul li:nth-child(2)" ).addClass( "current" );
	}
	
	
	function navigateToQuestionList(){
		var url = "${contextPath}/questions";
	    location.href = url; 
	}
	
	function navigateToNoticeList(){
		var url = "${contextPath}/notices";
	    location.href = url; 
	}

</script>
