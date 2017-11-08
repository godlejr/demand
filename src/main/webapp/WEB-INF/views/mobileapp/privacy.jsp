<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>

<div class="container-content" style="margin-top: 120px">
	<div class="content-body">
		<div class="section-mobileapp-privacy">
			<div class="privacy-header">
				<h1>${mobileApp.name } 개인정보처리방침</h1>
				<p>${mobileApp.name }사용자개인정보를 최우선으로 합니다.</p>
			</div>


			<div class="privacy-content">
				<p>${mobileApp.privacy }</p>
			</div>
		</div>
	</div>

</div>
