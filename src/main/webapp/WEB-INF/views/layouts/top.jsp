<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="<%= request.getContextPath()%>"></c:set>

<div class="top-content">
	<div class="section-logo">
		<a href="${contextPath}/">
			<img id="logo-image" src="<c:url value='/resources/static/img/main/demand_logo.png' />">
		</a>
	</div>
	<div class="section-login">
		<a href="${contextPath}/login"><i class="fa fa-sign-in" aria-hidden="true"></i><span>로그인</span></a>		
	</div>
	
	<div class="section-gnb">
		<ul>
			<li><a>회사소개</a></li>
			<li><a>서비스</a></li>
			<li><a>IR</a></li>
			<li><a>문의</a></li>
		</ul>
	</div>
	
	<div class="section-menu">
	
		<div class="menu-icon">
			<i class="fa fa-bars" aria-hidden="true"></i>	
		</div>
		
		<div class="menu-close-icon hide">
			<i class="fa fa-times" aria-hidden="true"></i>
		</div>		
	</div>
	
	<div class="section-mobile-gnb">
		<ul class="mobile-gnb-list">
			<li><a>회사소개</a></li>
			<li><a>서비스</a></li>
			<li><a>IR</a></li>
			<li><a>문의</a></li>
			<li><a href="${contextPath}/login">로그인</a></li>
		</ul>
	</div>
</div>

<script>
	$('.menu-close-icon').click(function(){
		$('.menu-icon').removeClass("hide");
		$('.section-mobile-gnb').removeClass("translate220");
		$('#container').removeClass("translate220");
		$('#footer').removeClass("translate220");
		$(this).addClass("hide");
	});
	
	$('.menu-icon').click(function(){
		$('.menu-close-icon').removeClass("hide");
		$('.section-mobile-gnb').addClass("translate220");
		$('#container').addClass("translate220");
		$('#footer').addClass("translate220");
		$(this).addClass("hide");
		
		
	});
	
	
	
</script>