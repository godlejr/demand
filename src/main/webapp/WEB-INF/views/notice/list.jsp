<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
<c:set var="sessionUser" value="${sessionScope.user}"></c:set>
<c:set var="notices" value="${noticePage.getContent()}"></c:set>
<c:set var="noticeCurrentPage" value="${noticePage.getNumber() + 1}"></c:set>
<c:set var="noticeTotalPageNumber" value="${noticePage.getTotalPages()}"></c:set>

<c:if test="${chosenNoticeCategory ne null }">
	<c:set var="chosenNoticeCategoryId" value="${chosenNoticeCategory.id}"></c:set>
</c:if>

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
								<c:when test="${noticeCategory.id eq chosenNoticeCategoryId}">
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

			<div class="notice-list-content">
				<table summary="번호, 카테고리, 제목, 작성일 ">
					<thead>
						<tr>
							<th>No</th>
							<th>Category</th>
							<th>Title</th>
							<th>Date</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="notice" items="${notices}">
							<tr class="notice-item">
								<td class="item-column">${notice.id }</td>
								<td class="item-column">${notice.noticeCategory.name }</td>
								<c:choose>
					                <c:when test="${notice.type eq 1}">
				                		<td class="item-column notification" >
				                			<span>[공지] ${notice.title }</span>
				                		</td>
					                </c:when>
					                <c:otherwise>
					                	<td class="item-column">
					                		<span>${notice.title}</span>
					                	</td>
					                </c:otherwise>
					            </c:choose>
								<td class="item-column">${notice.getCustomCreatedAt() }</td>
							</tr>
							<tr class="notice-detail">
							 	<td colspan="4">
							 		<div class="detail-content">
							 			${notice.content}
							 			
							 			<c:if test="${fn:length(notice.noticeFiles)gt 0}">
								 			<div class="content-attachment">
												<table>
													<tbody>
														<tr>
															<td>
																<span>첨부 파일</span>
															</td>
															<td>
																<ul>
																	<c:forEach var="noticeFile" items="${notice.noticeFiles}">
																		<li><a href="http://static.demand.co.kr/homepage/files/notices/${noticeFile.file.storageName}" target="_blank">${noticeFile.file.originalName} (${noticeFile.file.getFileSizeFormatted()})</a></li>
																	</c:forEach>	
																</ul>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</c:if>
							 		</div>
							 		<c:if test="${sessionUser ne null}">
								 		<div class="detail-bottom">
								 			<div class="bottom-function">
									 			<ul>
									 				<a href="${contextPath}/notices/${notice.id}/edit"><li>수정</li></a>
									 				<a href="${contextPath}/notices/${notice.id}/delete"><li>삭제</li></a>
									 			</ul>
								 			</div>
								 		</div>
							 		</c:if>
							 	</td> 
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="notice-list-bottom">
				<div class="section-pagination">
 
				    <c:if test="${noticeCurrentPage ne 1}">
				        <a href="javascript:paginate(${noticeCurrentPage - 1})" class="prev">이전</a>
				    </c:if>
				    
				    <span>
				        <c:forEach var="i" begin="${startPageNo}" end="${endPageNo}" step="1">
				            <c:choose>
				                <c:when test="${i eq noticeCurrentPage}">
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
				    
				    <c:if test="${noticeCurrentPage ne noticeTotalPageNumber}">
				        <a href="javascript:paginate( ${noticeCurrentPage + 1})" class="next">다음</a>
				    </c:if>
				 
				</div>	
			</div>
		</div>
	</div>
</div>


<script>
	$(".notice-item").click(function(){
		var self = this;
		var selfDetail = $(self).next();
		selfDetail.toggle();
		$(".notice-detail").not(selfDetail).hide();
		self.focus();
	});

	$("#arrow").click(function() {
		$('html, body').animate({
			scrollTop : $(".section-notice-intro").offset().top
		}, 600);
	});

	var chosenNoticeCategoryId = "${chosenNoticeCategoryId}";

	function paginate(pageNo){    
		var page = pageNo -1;
		var url = "${contextPath}/notices?page=" + page;
		if(chosenNoticeCategoryId != ""){
			url = "${contextPath}/notices?page=" + page + "&noticeCategoryId=" + chosenNoticeCategoryId;
		}
		
	    location.href = url;    
	}
	
	$('.search-submit').click(function(){
		search();
	});
	
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

		if(chosenNoticeCategoryId != ""){
			url = "${contextPath}/notices?search=" + search + "&noticeCategoryId=" + chosenNoticeCategoryId;
		}
		location.href = url;
	}
</script>