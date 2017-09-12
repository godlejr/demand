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
			<c:when test="${sessionScope.user != null}">
				<div class="session-info">
					<span class="login-session">${sessionUser.name}
						${sessionUser.positionCategory.name}님 </span>
					
					<a href="${contextPath}/logout"><i class="fa fa-sign-out"
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
			<li><a>회사소개</a></li>
			<li><a>서비스 디자인</a></li>
			<li><a>Health Care</a></li>
			<li><a>IR</a></li>
			<li><a>문의</a></li>
			<c:if test="${sessionScope.user != null}">
				<li><a>Report</a></li>
				
				<c:if test="${user.level == 9}">
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
			<c:if test="${sessionScope.user != null}">
				<li><a><span class="session-info">${sessionUser.name}
						${sessionUser.positionCategory.name}님</span></a></li>
			</c:if>
			
			<li><a>회사소개</a></li>
			<li><a>서비스 디자인</a></li>
			<li><a>Health Care</a></li>
			<li><a>IR</a></li>
			<li><a>문의</a></li>
			<c:choose>
				<c:when test="${sessionScope.user != null}">
					<li><a>Report</a></li>
					
					<c:if test="${user.level == 9}">
						<li><a>관리</a></li>
					</c:if>
					
					<li><a href="${contextPath}/logout">로그아웃</a></li>
				</c:when>
	
				<c:otherwise>
					<li><a href="${contextPath}/login">로그인</a></li>
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
</script>