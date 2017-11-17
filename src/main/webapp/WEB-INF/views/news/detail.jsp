<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
<c:if test="${sessionScope.user != null}">
	<c:set var="sessionUser" value="${sessionScope.user}"></c:set>
</c:if>

<div class="container-content" style="margin-top: 120px;  background-color: #f5f5f5;">
	<jsp:include page="../question/banner.jsp" flush="false">
      	<jsp:param name="title" value="보도자료" />
   </jsp:include>
   
	<div class="content-body">
		<div class="section-news-detail">
			<div class="news-detail-header">
				<div class="header-title">
					${news.title}
				</div>
				<div class="header-date">
					${news.getCustomCreatedAt()}
				</div>
			</div>
			<div class="news-detail-content">
				
	 			${news.content}
	 			
	 			<c:if test="${fn:length(news.newsFiles)gt 0}">
		 			<div class="content-attachment">
						<table>
							<tbody>
								<tr>
									<td>
										<span>첨부 파일</span>
									</td>
									<td>
										<ul>
											<c:forEach var="newsFile" items="${news.newsFiles}">
												<li><a href="http://static.demand.co.kr/homepage/files/news/${newsFile.file.storageName}" target="_blank">${newsFile.file.originalName} (${newsFile.file.getFileSizeFormatted()})</a></li>
											</c:forEach>	
										</ul>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</c:if>
				
				<div class="content-function">
		 			<c:if test="${sessionUser ne null}">
			 			<div class="function-items">
				 			<ul>
				 				<a href="${contextPath}/news/${news.id}/edit"><li>수정</li></a>
				 				<a href="${contextPath}/news/${news.id}/delete"><li>삭제</li></a>
				 			</ul>
		 				</div>
		 			</c:if>
				</div>	
			</div>	
		</div>
		
		<div class="section-next-prev-news">
			<table>
				<tbody>
					<c:if test="${prevNews ne null}">
						<tr>
							<td>
								<span>▲ PREV</span>
							</td>
							<td>
								<a href="javascript:navigateToNewsDetail(${prevNews.id})">${prevNews.title}</a>
							</td>
							<td>
								<span>${prevNews.getCustomCreatedAt()}</span>
							</td>
						</tr>
					</c:if>
					<c:if test="${nextNews ne null}">
						<tr>
							<td>
								<span>▼ NEXT</span>
							</td>
							<td>
								<a href="javascript:navigateToNewsDetail(${nextNews.id})">${nextNews.title}</a>
							</td>
							<td>
								<span>${nextNews.getCustomCreatedAt()}</span>
							</td>
						</tr>
					</c:if>
					
				</tbody>
			</table>
		</div>
		
		<div class="section-news-list-button">
			<span onclick="javascript:navigateToNewstList()">목록</span>
		</div>
	</div>
</div>

<script>
function navigateToNewsDetail(id){
	var url = "${contextPath}/news/" +id;
 	location.href = url; 
}

function navigateToNewstList() {
	var url = "${contextPath}/news";
 	location.href = url; 
}
</script>