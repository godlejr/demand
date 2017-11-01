<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>

<div class="container-content">
	<jsp:include page="./banner.jsp" flush="false">
		<jsp:param name="title" value="서비스 Q&A" />
	</jsp:include>

	<div class="content-body" style="padding-top: 90px;">

		<div class="section-question">
			<div class="question-header">
				<h3>질문 내용을 입력해 주세요.</h3>
			</div>
			<div class="question-content">
				<form:form id="question-form" class="question-form"
					modelAttribute="question" method="post">
					<table>
						<tbody>
							<tr>
								<td class="form-menu">작성자</td>
								<td class="form-menu-content"><form:input type="text"
										path="writer" id="writer" placeholder="한/영 10자 이내" /></td>
							</tr>
							<tr>
								<td class="form-menu">비밀번호</td>
								<td class="form-menu-content"><form:input type="password"
										path="password" id="password" placeholder="특수문자 제외, 18자 이내" /></td>
							</tr>
							<tr>
								<td class="form-menu">카테고리</td>
								<td><form:input type="text" path="questionCategoryId" 
										hidden="hidden" id="questionCategoryId" value="0" />

									<div class="section-category-selector">
										<div class="selector-button">
											<div class="button-content">
												<span id="selector-view">전체보기</span>
											</div>

											<div class="button-icon">
												<i class="fa fa-chevron-down" aria-hidden="true"></i>
											</div>
										</div>

										<div class="selector-filter">
											<ul>
												<li class="filter-item" data-id="0"><span>전체보기</span></li>

												<c:forEach var="questionCategory"
													items="${questionCategories}">
													<li class="filter-item" data-id="${questionCategory.id}">${questionCategory.name}</li>
												</c:forEach>
											</ul>
										</div>
									</div></td>
							</tr>

							<tr>
								<td class="form-menu">제목</td>
								<td class="form-menu-content"><form:input type="text"
										id="title" path="title" class="title-input"
										placeholder="30자 이내" /></td>
							</tr>
							<tr>
								<td class="form-menu">내용</td>
								<td class="form-menu-content"><form:textarea id="content"
										path="content" class="content-input"
										placeholder="* 본 게시판에 질문 작성 시 실명이 아닌 닉네임으로도 작성이 가능합니다.
* 질문 제목 및 내용 작성 시 비방 글, 비속어, 개인정보 노출, 음란, 광고글은 경고 없이 삭제될 수 있습니다." />
								</td>
							</tr>
							<tr>
								<td class="form-menu">공개여부</td>
								<td class="form-menu-content"><form:checkbox id="privacy"
										path="privacy" /> <span>체크하시면, 비공개 게시글이 됩니다.</span></td>

							</tr>
						</tbody>
					</table>

					<div class="form-submit">
						<div class="submit-content">
							<input type="button" id="submit" value="등록" class="submit-button">
							<input type="button" id="cancel" value="취소" class="cancel-button">
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>

<script>
	var $submit = $("#submit"),
		$cancel = $("#cancel");
	
	$cancel.click(function() {
	   navigateToQuestionListPage();
	});
	
	$(".selector-button").click(function() {
	   var self = this;
	   $(".selector-filter").slideToggle();
	});
	
	$(".filter-item").click(function() {
	   var self = this;
	   var questionCategoryId = $(self).attr("data-id");
	   var questionCategoryName = $(self).text();
	   updateSelectorView(questionCategoryId, questionCategoryName);
	});
	
	function updateSelectorView(questionCategoryId, questionCategoryName) {
	   var selectorView = $("#selector-view");
	   var questionCategoryInput = $('#questionCategoryId');
	   var filter = $(".selector-filter");
	
	   questionCategoryInput.val(questionCategoryId);
	   selectorView.text(questionCategoryName);
	   filter.slideToggle();
	}
	
	$submit.click(function() {
	   	var questionCategoryId = $('#questionCategoryId').val();
	   	if(questionCategoryId != "0"){
	    		var questionForm = $("#question-form").serialize();
	   		sendQuestion(questionForm);
	   	}else{
	   		alert("카테고리를 선택하세요.");
	   	}
	});
	
	function sendQuestion(questionForm) {
	
	   $.ajax({
	      type : "POST",
	      url : "${contextPath}/questions/",
	      data : questionForm,
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