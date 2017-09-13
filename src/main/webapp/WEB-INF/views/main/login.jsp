<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>

<c:set var="contextPath" value="<%= request.getContextPath()%>"></c:set>

<div class="container-content">
	<div class="content-body"
		style="background-image: url('./resources/static/img/main/login_background.jpg'); background-size: cover; background-repeat: no-repeat; background-position: center; height: 100%; align-items: center; display: flex;">
		<div class="section-login">
			<div class="login-header">
				<a href="${contextPath}/"> <img class="logo-image"
					src="<c:url value='/resources/static/img/main/demand_logo.png' />" /></a>
			</div>

			<div class="login-content">
				<form:form class="login-form" method="post" modelAttribute="user">
					<div class="form-email">
						<div class="email-left">
							<i class="fa fa-user" aria-hidden="true"></i>
						</div>

						<div class="email-right">
							<form:input  class="text-email" type="text" placeholder="email" path="email"/>
						</div>
					</div>

					<div class="form-password">
						<div class="password-left">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</div>
						<div class="password-right">
							<form:input  class="text-password" type="password" placeholder="password" path="password"/>
						</div>
					</div>

					<input type="submit" class="form-button" value="로그인">

				</form:form>
			</div>

			<div class="login-bottom">
				<c:if test="${message != null}">
					<c:choose>
						<c:when test="${isJoined == true}">
							<div class="login-message join">
								<span>${message}</span>
							</div>

						</c:when>
						<c:otherwise>
							<div class="login-message">
								<span>${message}</span>
							</div>
						</c:otherwise>
					</c:choose>
				</c:if>
				<div class="bottom-content">
					<a href=""><span>아이디 신청</span></a> | <a href=""><span>비밀번호
							찾기</span></a>
				</div>
			</div>
		</div>
	</div>
</div>
