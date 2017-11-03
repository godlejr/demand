<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
<c:set var="sessionUser" value="${sessionScope.user}"></c:set>
<c:set var="otherQuestions" value="${questionPage.getContent()}"></c:set>

<div class="container-content">
	<jsp:include page="../question/banner.jsp" flush="false">
		<jsp:param name="title" value="${question.questionCategory.name} Q&A 답변 수정" />
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
				</div>
			</div>
		</div>


		<div class="section-answer">
			<form:form id="answer-form" class="answer-form"
				modelAttribute="answer" method="post">
				<form:input type="text" hidden="hidden" path="questionId"
					value="${question.id}" />
				<table>
					<tbody>
						<tr>
							<td class="form-menu"><span class="mark">A</span></td>
							<td class="form-menu-content"><form:input type="text"
									path="title" placeholder="Re:${question.title}"
									value="${answer.title}" /></td>
						</tr>
						<tr>
							<td class="form-menu"><span>내용</span></td>
							<td class="form-menu-content"><form:textarea path="content"
									class="content-textarea" value="${answer.content}" /></td>
						</tr>
						<tr>
							<td class="form-menu"></td>
							<td class="form-menu-content"><span class="profile-check">프로필
									공개 여부 </span>

								<div class="profile-check-option">
									<form:radiobutton path="type" value="0"
										checked="${answer.type == 0 ? 'checked' : ''}" />
									<span>비공개</span>
									<form:radiobutton path="type" value="1"
										checked="${answer.type != 0 ? 'checked' : ''}" />
									<span>공개</span>
								</div></td>
						</tr>
						<tr>
							<td class="form-menu"></td>
							<td class="form-menu-content">
								<div class="content-submit">
									<input type="button" id="submit" value="수정" />
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form:form>
		</div>

		<div class="section-questions-button">
			<div class="button-function"
				onclick="javascript:navigateToQuestionListPage()">
				<span>목록</span>
			</div>
		</div>
	</div>
</div>

<script>
	var $submit = $("#submit");
	
	$submit.click(function() {
	      var answerForm = $("#answer-form").serialize();
	      updateAnswer(answerForm);
	});
	
	function updateAnswer(answerForm) {
	   $.ajax({
	      type : "POST",
	      url : "${contextPath}/answers/"+${answer.id}+"/edit",
	      data : answerForm ,
	      success : function(data) {
	         var message = data.message;
	         if (message != null) {
	            alert(message);
	         } else {
	            navigateToQuestionListPage();
	         }
	      }
	   })
	}
	
	function navigateToQuestionListPage() {
	   document.location.href = "${contextPath}/questions";
	}

</script>