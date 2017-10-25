<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
<c:if test="${sessionScope.user != null}">
	<c:set var="sessionUser" value="${sessionScope.user}"></c:set>
</c:if>

<div class="top-content">
	<div class="section-logo">
		<a href="${contextPath}/"> <img id="logo-image"
			src="<c:url value='/resources/static/img/main/demand_logo.png' />">
		</a>
	</div>
	<div class="section-login">
		<c:choose>
			<c:when test="${sessionScope.user ne null}">
				<div class="session-info">
					<span class="login-session" onclick="javascript:navigateToUserDetail(${sessionUser.id})">${sessionUser.name}
						${sessionUser.positionCategory.name}</span> <a
						href="${contextPath}/logout"><i class="fa fa-sign-out"
						aria-hidden="true"></i><span>로그아웃</span></a>
				</div>
			</c:when>

			<c:otherwise>
				<div class="login-button">
					<a href="${contextPath}/login"><i class="fa fa-sign-in"
						aria-hidden="true"></i><span>로그인</span></a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

	<div class="section-gnb">
		<ul>
			<li><a href="${contextPath}/company">회사소개</a></li>
			<li><a href="${contextPath}/serviceDesign">서비스 디자인</a></li>
			<li><a>Health Care</a></li>
			<li><a>뉴스 / IR</a></li>
			<li><a href="${contextPath}/notice">고객센터</a></li>
			<c:if test="${sessionScope.user ne null}">
				<li><a href="${contextPath}/reports">업무보고</a></li>

				<c:if test="${sessionUser.level eq 9}">
					<li><a>관리</a></li>
				</c:if>

			</c:if>
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
			<c:if test="${sessionScope.user ne null}">
				<a href="${contextPath}/users/${sessionUser.id}"><li><span class="session-info">${sessionUser.name}
							${sessionUser.positionCategory.name}</span></li></a>
			</c:if>

			<a href="${contextPath}/company"><li>회사소개</li></a>
			<a href="${contextPath}/serviceDesign"><li>서비스 디자인</li></a>
			<a><li>Health Care</li></a>
			<a><li>뉴스 / IR</li></a>
			<a href="${contextPath}/notice"><li>고객센터</li></a>
			<c:choose>
				<c:when test="${sessionScope.user ne null}">
					<a href="${contextPath}/reports"><li>업무보고</li></a>

					<c:if test="${sessionUser.level eq 9}">
						<a><li>관리</li></a>
					</c:if>

					<a href="${contextPath}/logout"><li>로그아웃</li></a>
				</c:when>

				<c:otherwise>
					<a href="${contextPath}/login"><li>로그인</li></a>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</div>

<script>
	$('.menu-close-icon').click(function() {
		$('.menu-icon').removeClass("hide");
		$('.section-mobile-gnb').removeClass("translate220");
		$('#container').removeClass("translate220");
		$('#footer').removeClass("translate220");
		$(this).addClass("hide");
	});

	$('.menu-icon').click(function() {
		$('.menu-close-icon').removeClass("hide");
		$('.section-mobile-gnb').addClass("translate220");
		$('#container').addClass("translate220");
		$('#footer').addClass("translate220");
		$(this).addClass("hide");

	});
	
	function navigateToUserDetail(id){
		var url = "${contextPath}/users/" + id;
	    location.href = url; 
	}
	
	var currentPathName = window.location.pathname;
	
	if(currentPathName == "${contextPath}/company"){
		$( ".section-gnb ul li:first-child a" ).addClass( "current" );
	}else if(currentPathName == "${contextPath}/serviceDesign"){
		$( ".section-gnb ul li:nth-child(2) a" ).addClass( "current" );
	}else if(currentPathName == "${contextPath}/reports"){
		$( ".section-gnb ul li:nth-child(6) a" ).addClass( "current" );
	}else if(currentPathName == "${contextPath}/customerCenter"){
		$( ".section-gnb ul li:nth-child(5) a" ).addClass( "current" );
	}else{
		//
	}
		
</script>