<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
<c:set var="sessionUser" value="${sessionScope.user}"></c:set>


<div class="container-content" style="background: #f5f5f5;">
	<jsp:include page="../main/customerCenterNavigator.jsp" flush="true" />


	<div class="section-notice-intro">
		<div class="intro-header">
			<h1>공지사항</h1>
		</div>
		<div class="intro-content">
			<p>Demand의 새로운 소식을 전해드립니다.</p>
		</div>
	</div>

	<div class="content-body">
		<div class="section-notice-list">
			<div class="notice-list-header">
				<c:if test="${sessionUser ne null}">
					<div class="section-write" onclick="javascript:navigateToNewNotice()">
						<div class="write-content">
							<span>글쓰기</span>
						</div>
					</div>
				</c:if>

				<div class="section-category-list">
					<ul>
						<c:choose>
							<c:when test="${chosenNoticeCategory eq null}">
								<li><a class="current" href="${contextPath}/notices">전체보기</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${contextPath}/notices">전체보기</a></li>
							</c:otherwise>
						</c:choose>

						<c:forEach var="noticeCategory" items="${noticeCategories }">
							<c:choose>
								<c:when test="${noticeCategory.id eq chosenNoticeCategory.id }">
									<li><a class="current" href="${contextPath}/notices?noticeCategoryId=${noticeCategory.id}">${noticeCategory.name} </a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${contextPath}/notices?noticeCategoryId=${noticeCategory.id}">${noticeCategory.name}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ul>
				</div>

				<div class="section-category-selector">
					<div class="selector-button">
						<div class="button-content">
							<c:choose>
								<c:when test="${chosenNoticeCategory ne null }">
									<span>${chosenNoticeCategory.name}</span>
								</c:when>
								<c:otherwise>
									<span>전체보기</span>
								</c:otherwise>
							</c:choose>
						</div>

						<div class="button-icon">
							<i class="fa fa-chevron-down" aria-hidden="true"></i>
						</div>
					</div>
					<div class="selector-filter">
						<ul>
							<a href="${contextPath}/notices"><li>전체보기</li></a>
							<c:forEach var="noticeCategory" items="${noticeCategories}">
								<a href="${contextPath}/notices?noticeCategoryId=${noticeCategory.id}"><li>
										${noticeCategory.name}</li></a>
							</c:forEach>
						</ul>
					</div>
				</div>

				<div class="section-search">
					<div class="search-content">
						<input type="text" id="search-text"
							onkeypress="javascript:if(event.keyCode == 13){search()}"
							value="${search}" placeholder="" />
					</div>

					<div class="search-submit">
						<span>검색</span>
					</div>
				</div>
			</div>

			<div class="notice-list-content"></div>

			<div class="notice-list-bottom"></div>
		</div>
	</div>
</div>


<script>
	$('.selector-button').click(function() {
		var self = this;
		$('.selector-filter').slideToggle();

	});
	
	function navigateToNewNotice(){
		var url = "${contextPath}/notices/new" ;
	 	location.href = url; 
	}

	function search() {
		var search = $(document.getElementById('search-text')).val();

		var url = "${contextPath}/notices?search=" + search;
		location.href = url;
	}
</script>