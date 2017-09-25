<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>


<div class="container-content" style="margin-top: 120px">
	<div class="content-body">
		<div class="section-report">
			<div class="report-header">
				<h1>업무 보고</h1>
				<p>한 주간의 업무한 내용를 보고한 자료입니다.</p>
			</div>

			<div class="report-content" style="margin-top: 50px;">
				<div class="content-description">
					<div class="description-content">
						<div class="report-title">
							<strong>${report.title }</strong>
						</div>
						<div class="report-hits">
							<span>조회 수</span> <span>${report.hits}</span>
						</div>
					</div>
					<div class="description-bottom">
						<div class="report-writer">
							<span>${report.user.name }
								${report.user.positionCategory.name } </span>
						</div>

						<div class="report-date">
							<span>${report.getCustomCreatedAt() }</span>
						</div>

					</div>
				</div>
				<div class="content-body">${report.content }</div>
				
				<c:if test="${fn:length(report.reportFiles)gt 0}">
					<div class="content-attachment">
						<table>
							<tbody>
								<tr>
									<td>
										<span>첨부 파일</span>
									</td>
									<td>
										<ul>
											<c:forEach var="reportFile" items="${report.reportFiles}">
												<li><a href="http://static.demand.co.kr/homepage/files/reports/${reportFile.file.storageName}" target="_blank">${reportFile.file.originalName}</a></li>
											</c:forEach>	
										</ul>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>
			
			<div class="report-bottom">
				<table>
					<tbody>
						<c:if test="${prevReport ne null}">
							<tr>
								<td>
									<span>이전 글</span>
								</td>
								<td>
									<a href="javascript:navigateToReportDetail(${prevReport.id})">${prevReport.title}</a>
								</td>
							</tr>
						</c:if>
						<c:if test="${nextReport ne null}">
							<tr>
								<td>
									<span>다음 글</span>
								</td>
								<td>
									<a href="javascript:navigateToReportDetail(${nextReport.id})">${nextReport.title}</a>
								</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<script>
	function navigateToReportDetail(id){
		var url = "${contextPath}/reports/" +id;
	 	location.href = url; 
	}
</script>