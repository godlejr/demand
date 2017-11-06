<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>

<div class="section-image-banner"
	style="background: url('<c:url value='/resources/static/img/main/mobileapp_banner.jpg' />'); background-size: cover; background-repeat: no-repeat; background-position: center;">
</div>

<div class="section-mobileapp-navi">
	<ul>
		<c:forEach var="mobileApp" items="${mobileApps}">

			<c:choose>
				<c:when test="${mobileApp.id eq chosenMobileApp.id }">
					<c:set var="currentMobileAppClass" value="current"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="currentMobileAppClass" value=""></c:set>
				</c:otherwise>
			</c:choose>

			<li class="${currentMobileAppClass}"
				onclick="javascript:navigateToMobileApps(${mobileApp.id})"><span>${mobileApp.name}</span></li>

		</c:forEach>
	</ul>
</div>


<script>
	function navigateToMobileApps(id){
	    var url = "${contextPath}/mobileApps/"+id;
	     location.href = url; 
	 }
</script>