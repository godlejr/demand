<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>

<div class="container-content" style="margin-top: 120px">
	<div class="content-body">
		<div class="section-mobileapp-agreement">
			<div class="agreement-header">
				<h1>${mobileApp.name } 이용약관</h1>
				<p>${mobileApp.name } 서비스의 이용과 관련한 사항을 규정합니다.</p>
			</div>


			<div class="agreement-content">
				<p>${mobileApp.agreement }</p>
			</div>
		</div>

	</div>

</div>
