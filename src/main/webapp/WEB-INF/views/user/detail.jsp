<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
<c:if test="${sessionScope.user != null}">
	<c:set var="sessionUser" value="${sessionScope.user}"></c:set>
</c:if>

<c:set  var="userAvatarPath" value="http://static.demand.co.kr/homepage/images/users/avatars/" ></c:set>
<c:set var="reports" value="${reportPage.getContent()}"></c:set>
<c:set var="reportCurrentPage" value="${reportPage.getNumber() + 1}"></c:set>
<c:set var="reportTotalPageNumber" value="${reportPage.getTotalPages()}"></c:set>

<div class="container-content" style="margin-top: 120px">
	<div class="content-body">
		<div class="section-user-info">
			<div class="user-info-header">
				<h1>${user.name} ${user.positionCategory.name}</h1>
				<p>개인 정보 페이지 입니다.</p>
				
				<div class="header-content">
					<div class="section-user-profile">
						<div class="user-profile-content">
							<img class="content-avatar" alt="" src="${userAvatarPath}${user.avatarFile.storageName}" />
						
							<c:if test="${user.id eq sessionUser.id}">
								<div class="content-button">
									<span>내 프로필 보기</span>		
								</div>
							</c:if>
						</div>
					</div>
					<c:if test="${user.id eq sessionUser.id}">
						<div class="section-user-wirte">
							<div class="user-write-content">
								<div class="content-report" onclick="javascript:navigateToNewReport()">
									<div class="report-image">
										<i class="fa fa-file-text-o" aria-hidden="true"></i>
									</div>
									<c:choose>
										<c:when test="${user.positionCategory.id eq 1}">
											<span>보고 게시글 작성</span>
										</c:when>
										<c:otherwise>
											<span>업무보고 작성</span>
										</c:otherwise>
									</c:choose>
								</div>
								
								<div class="content-promotion" onclick="javascript:navigateToNewNews()">
									<div class="promotion-image">
										<i class="fa fa-newspaper-o" aria-hidden="true"></i>
									</div>
									<span>홍보 / IR 작성</span>
								</div>
								
								<div class="content-notice" onclick="javascript:navigateToNewNotice()">
									<div class="notice-image">
										<i class="fa fa-file-text-o" aria-hidden="true"></i>
									</div>
									<span>공지사항 작성</span>
								</div>
							</div>
						</div>
					</c:if>
				</div>
			</div>
			
			<div class="user-info-content">
				<div class="content-report-list">
					<div class="report-list-header">
						<c:choose>
							<c:when test="${user.id eq sessionUser.id}">
								<c:choose>
									<c:when test="${user.positionCategory.id eq 1}">
										<h3>대표님의 업무보고 관련 게시글</h3>
									</c:when>
									<c:otherwise>
										<h3>내가 작성한 업무보고</h3>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${user.positionCategory.id eq 1}">
										<h3>대표님의 업무보고 관련 게시글</h3>
									</c:when>
									<c:otherwise>
										<h3>${user.name} ${user.positionCategory.name}의 업무 보고</h3>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="report-list-content">
						<table summary="번호, 제목, 작성일, 작성자, 조회수 ">
							<thead>
								<tr>
									<th>제목</th>
									<th>작성 일자</th>
									<th>조회</th>
								</tr>
							</thead>
		
							<tbody>
								<c:forEach var="report" items="${reports}">
									<tr>
										<c:choose>
							                <c:when test="${report.type eq 1}">
						                		<td >
						                			<span onclick="javascript:navigateToReportDetail(${report.id})">[공지] ${report.title }</span>
						                		</td>
							                </c:when>
							                <c:otherwise>
							                	<td>
							                		<span onclick="javascript:navigateToReportDetail(${report.id})">${report.title}</span>
							                	</td>
							                </c:otherwise>
							            </c:choose>
										<td>${report.getCustomCreatedAt() }</td>
										<td>${report.hits }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="report-list-bottom">
						<div class="section-pagination">
		 
						    <c:if test="${reportCurrentPage ne 1}">
						        <a href="javascript:paginate(${reportCurrentPage - 1})" class="prev">이전</a>
						    </c:if>
						    
						    <span>
						        <c:forEach var="i" begin="${startPageNo}" end="${endPageNo}" step="1">
						            <c:choose>
						                <c:when test="${i eq reportCurrentPage}">
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
						    
						    <c:if test="${reportCurrentPage ne reportTotalPageNumber}">
						        <a href="javascript:paginate( ${reportCurrentPage + 1})" class="next">다음</a>
						    </c:if>
						 
						</div>	
					</div>
				</div>
				<div class="content-schedule">
					<div class="schedule-header">
						<c:choose>
							<c:when test="${user.id eq sessionUser.id}">
								<c:choose>
									<c:when test="${user.positionCategory.id eq 1}">
										<h3>대표님의 일정</h3>
									</c:when>
									<c:otherwise>
										<h3>나의 일정</h3>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${user.positionCategory.id eq 1}">
										<h3>대표님의 일정</h3>
									</c:when>
									<c:otherwise>
										<h3>${user.name} ${user.positionCategory.name}의 일정</h3>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
function paginate(pageNo){    
	var page = pageNo -1;
	var url = "${contextPath}/users/${user.id}?page=" + page;
	
    location.href = url;    
}

function navigateToNewNotice(){
	var url = "${contextPath}/notices/new";
    location.href = url; 
}

function navigateToNewReport(){
	var url = "${contextPath}/reports/new";
    location.href = url; 
}

function navigateToNewNews(){
	var url = "${contextPath}/news/new";
    location.href = url; 
}

function navigateToReportDetail(id){
	var url = "${contextPath}/reports/" +id;
 	location.href = url; 
}

</script>