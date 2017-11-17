<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
<c:set var="sessionUser" value="${sessionScope.user}"></c:set>
<c:set var="newsList" value="${newsPage.getContent()}"></c:set>
<c:set var="newsListCurrentPage" value="${newsPage.getNumber() + 1}"></c:set>
<c:set var="newsListTotalPageNumber" value="${newsPage.getTotalPages()}"></c:set>
<c:set var ="newsFileUrl" value="http://static.demand.co.kr/homepage/files/news"></c:set>

<div class="container-content" >
	<div class="section-image-banner" style="background: url('<c:url value='/resources/static/img/main/news_item_bg.png' />'); background-size: cover; background-repeat: no-repeat; background-position: center;">
		<div class="image-banner-header"></div>

		<div class="image-banner-content"></div>


		<div class="image-banner-bottom">
			<i id="arrow" class="fa fa-angle-down" aria-hidden="true"></i>
		</div>

	</div>
	
	<div class="section-news-intro">
		<div class="intro-header">
			<h1>보도자료</h1>
		</div>
		<div class="intro-content">
			<p>Demand의 새로운 소식을 전달해드립니다.</p>
		</div>
	</div>
	
	<div class="section-news-list">
		<div class="news-list-content">
			<ul>
				<c:forEach var="news" items="${newsList}">
					<li>
						<div class="content-info">
							<div class="info-avatar" onclick="javascript:navigateToNewsDetail(${news.id})" >
								<img alt="" src="${newsFileUrl}/${news.avatarFile.storageName}">
							</div>
							<div class="info-title" onclick="javascript:navigateToNewsDetail(${news.id})">
								<span>${news.title}</span>
							</div>
							<div class="info-date">
								<span>${news.getCustomCreatedAt()}</span>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="news-list-bottom">
			<div class="section-pagination">
			    <c:if test="${newsListCurrentPage ne 1}">
			        <a href="javascript:paginate(${newsListCurrentPage - 1})" class="prev">이전</a>
			    </c:if>
			    		    
			    <span>
			        <c:forEach var="i" begin="${startPageNo}" end="${endPageNo}" step="1">
			            <c:choose>
			                <c:when test="${i eq newsListCurrentPage}">
			                    <b><font size=+2>
			                            <a href="javascript:paginate(${i})" class="choice">${i}</a>
			                        </font>
			                    </b>
			                </c:when>
			                <c:otherwise>
			                    <a href="javascript:paginate(${i})">${i}</a>
			                </c:otherwise>
			            </c:choose>
			        </c:forEach>
			    </span>
			    
			    <c:if test="${newsListCurrentPage ne newsListTotalPageNumber}">
			        <a href="javascript:paginate( ${newsListCurrentPage + 1})" class="next">다음</a>
			    </c:if>
			 
			</div>	
		</div>
	</div>
	
</div>

<script>
	$("#arrow").click(function() {
		$('html, body').animate({
			scrollTop : $(".section-news-intro").offset().top
		}, 600);
	});
	
	
	function paginate(pageNo){    
		var page = pageNo -1;
		var url = "${contextPath}/news?page=" + page;
		
	    location.href = url;    
	}
	
	function navigateToNewsDetail(id){
		var url = "${contextPath}/news/" + id;
	    location.href = url; 
	}
</script>