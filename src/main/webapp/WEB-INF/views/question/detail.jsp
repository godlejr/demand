<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
<c:set var="sessionUser" value="${sessionScope.user}"></c:set>


<div class="container-content">
	<jsp:include page="./banner.jsp" flush="false">
		<jsp:param name="title" value="${question.questionCategory.name} Q&A" />
	</jsp:include>

	<div class="content-body" style="padding-top: 90px;">
		<div class="section-question">
			<div class="question-content" style="display: flex;"
				data-id="${question.id}">
				<div class="content-left">
					<span>Q</span>
					<c:if test="${question.answerCheck eq true }">
						<div class="question-check">
							<span>답변완료</span>
						</div>
					</c:if>
				</div>

				<div class="content-right">
					<div class="question-title">
						<h2>${question.title}</h2>
					</div>

					<div class="question-description">
						<div class="question-writer">
							<span>${question.writer}</span>
						</div>

						<div class="question-date">
							<span>${question.getCustomCreatedAt()}</span>
						</div>

						<div class="question-category">
							<span>${question.questionCategory.name }</span>
						</div>
					</div>

					<div class="question-memo">
						<div class="memo-content">
							<p>${question.content}</p>
						</div>
					</div>

					<div class="question-bottom">
						<div class="bottom-function">
							<ul>
								<li>수정</li>
								<li>삭제</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>

		<c:choose>
			<c:when test="${question.questionAnswer ne null }">
				<c:set var="answer" value="${question.questionAnswer.answer}" />
				<div class="section-answer">
					<div class="answer-content" style="display: flex;"
						data-id="${answer.id}">
						<div class="content-left">
							<span>A</span>
						</div>

						<div class="content-right">
							<div class="answer-title">
								<h2>${answer.title}</h2>
							</div>

							<div class="answer-description">
								<div class="answer-writer">
									<c:choose>
										<c:when test="${answer.type eq 0}">
											<span>Demand</span>
										</c:when>
										<c:otherwise>
											<span>${answer.user.name}</span>
										</c:otherwise>
									</c:choose>
								</div>

								<div class="answer-date">
									<span>${answer.getCustomCreatedAt()}</span>
								</div>

								<div class="answer-category">
									<span>${question.questionCategory.name }</span>
								</div>
							</div>

							<div class="answer-memo">
								<div class="memo-content">
									<p>${answer.content}</p>
								</div>
							</div>

							<c:if test="${sessionUser ne null }">
								<div class="answer-bottom">
									<div class="bottom-function">
										<ul>
											<li>수정</li>
											<li>삭제</li>
										</ul>
									</div>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<c:if test="${sessionUser ne null }">
					<div class="section-answer">
						<form:form id="answer-form" class="answer-form"
							modelAttribute="answer" method="post">
							<table>
								<tbody>
									<tr>
										<td class="form-menu"><span class="mark">A</span></td>
										<td class="form-menu-content"><form:input type="text"
												path="title" placeholder="Re:${question.title}" /></td>
									</tr>
									<tr>
										<td class="form-menu"><span>내용</span></td>
										<td class="form-menu-content"><form:textarea
												path="content" class="content-textarea"/></td>
									</tr>
									<tr>
										<td class="form-menu"></td>
										<td class="form-menu-content">
											<span class="profile-check">프로필 공개 여부 </span>
											
											<div class="profile-check-option">
												<form:radiobutton path="type" value="0" /><span>비공개</span>
												<form:radiobutton path="type" value="1" /><span>공개</span>
											</div>
										</td>
									</tr>
									<tr>
										<td class="form-menu"></td>
										<td class="form-menu-content">
											<div class="content-submit">
												<input type="submit" value="답변등록" />
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</form:form>
					</div>
				</c:if>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<script>
	
</script>