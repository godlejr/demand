<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>

<div class="container-content">
	<jsp:include page="./banner.jsp" flush="false">
		<jsp:param name="title" value="${question.questionCategory.name } Q&A" />
	</jsp:include>

	<div class="content-body" style="padding-top: 90px;">
		<div class="section-question">
			<div class="question-header">
				<h3>질문 내용을 수정해 주세요.</h3>
			</div>
			<div class="question-content">
				<form:form id="question-form" class="question-form"
					modelAttribute="question" method="post">
					<form:input path="questionCategoryId" type="text"
						value="${question.questionCategory.id}" hidden="hidden" />
					<table>
						<tbody>
							<tr>
								<td class="form-menu">작성자</td>
								<td class="form-menu-content"><form:input type="text"
										path="writer" id="writer" placeholder="한/영 10자 이내"
										value="${question.writer}" /></td>
							</tr>
							<tr>
								<td class="form-menu">비밀번호</td>
								<td class="form-menu-content"><form:input type="password"
										path="password" id="password" placeholder="특수문자 제외, 18자 이내"
										value="${question.password}" /></td>
							</tr>
							<tr>
								<td class="form-menu">카테고리</td>
								<td class="form-menu-content"><input type="text"
									value="${question.questionCategory.name }" readonly /></td>
							</tr>

							<tr>
								<td class="form-menu">제목</td>
								<td class="form-menu-content"><form:input type="text"
										id="title" path="title" class="title-input"
										placeholder="30자 이내" value="${question.title}" /></td>
							</tr>
							<tr>
								<td class="form-menu">내용</td>
								<td class="form-menu-content"><form:textarea id="content"
										path="content" class="content-input"
										placeholder="* 본 게시판에 질문 작성 시 실명이 아닌 닉네임으로도 작성이 가능합니다.
* 질문 제목 및 내용 작성 시에 비방 글, 비속어, 개인정보 노출, 음란, 광고글은 경고 없이 삭제될수  있습니다."
										value="${question.content}" /></td>
							</tr>
							<tr>
								<td class="form-menu">공개여부</td>
								<td class="form-menu-content"><c:choose>
										<c:when test="${question.type eq 1}">
											<form:checkbox id="privacy" path="privacy" checked="checked" />
										</c:when>

										<c:otherwise>
											<form:checkbox id="privacy" path="privacy" />
										</c:otherwise>
									</c:choose> <span>체크하시면, 비공개 게시글이 됩니다.</span></td>

							</tr>
						</tbody>
					</table>

					<div class="form-submit">
						<div class="submit-content">
							<input type="submit" id="submit" value="수정" class="submit-button">
							<input type="button" id="cancel" value="취소" class="cancel-button">
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>

<script>
	$("#submit").click(function(){
	    var self = this;
	    var questionForm = $("#question-form").serialize();
	
	    updateQuestion(questionForm);
	 });
	
	 $(".cancel-button").click(function(){
	    navigateToQuestionList();
	 });
	
	 function updateQuestion(questionForm) {
	    $.ajax({
	       type: "POST",
	       data: questionForm,
	       url: "${contextPath}/questions/${question.id}/edit",
	       success: function(data){
	          var message = data.message;
	          if(message != null){
	             alert(message);
	          }else {
	             navigateToQuestionList();
	          }
	       }
	    });
	 }
	
	 function navigateToQuestionList(){
	    document.location.href = "${contextPath}/questions";
	 }   
</script>