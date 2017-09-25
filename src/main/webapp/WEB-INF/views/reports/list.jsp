<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
<c:set var="reports" value="${reportPage.getContent()}"></c:set>
<c:set var="reportCurrentPage" value="${reportPage.getNumber() + 1}"></c:set>
<c:set var="reportTotalPageNumber" value="${reportPage.getTotalPages()}"></c:set>


int startPage = ((currentPageNo - 1) / sizeOfPage) * sizeOfPage + 1;
		int endPage = startPage + sizeOfPage - 1;

<div class="container-content" style="margin-top: 120px">
	<div class="content-body">
		<div class="section-report-list">
			<div class="report-list-header">
				<h1>업무 보고</h1>
				<p>한 주간의 업무를 보고한 리스트 입니다.</p>
				
				<div class="header-content">
					<div class= "section-write">
						<div class="write-content" onclick="javascript:navigateToNewReport()">
							<span>글쓰기</span>
						</div>
					</div>
					
					<div class= "section-search">
						<div class="search-content"> 
							<input type="text" id="search-text"  onkeypress="javascript:if(event.keyCode == 13){search()}" value="${search}" placeholder="제목, 내용, 보고자 검색"/>
						</div>
						
						<div class="search-submit">
							<span>검색</span>
						</div>
					</div>
				</div>
			</div>

			<div class="report-list-content">
				<table summary="번호, 제목, 작성일, 작성자, 조회수 ">
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th>보고자</th>
							<th>작성 일자</th>
							<th>조회</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="report" items="${reports}">
							<tr>
								<td>${report.id }</td>
								<c:choose>
					                <c:when test="${report.type eq 1}">
				                		<td class="notification" >
				                			<span onclick="javascript:navigateToReportDetail(${report.id})">[공지] ${report.title }</span>
				                		</td>
					                </c:when>
					                <c:otherwise>
					                	<td>
					                		<span onclick="javascript:navigateToReportDetail(${report.id})">${report.title}</span>
					                	</td>
					                </c:otherwise>
					            </c:choose>

								<td>${report.user.name } ${report.user.positionCategory.name }</td>
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
	</div>

</div>

<script type="text/javascript">
	function paginate(pageNo){    
		var page = pageNo -1;
		var url = "${contextPath}/reports?page=" + page;
		
	    location.href = url;    
	}
	
	$('.search-submit').click(function(){
		search();
	});
	
	function navigateToNewReport(){
		var url = "${contextPath}/reports/new";
	    location.href = url; 
	}
	
	function navigateToReportDetail(id){
		var url = "${contextPath}/reports/" +id;
	 	location.href = url; 
	}
	
	function search(){
		var search = $(document.getElementById('search-text')).val();
	
		var url = "${contextPath}/reports?search=" + search;
	    location.href = url; 
	}
</script>