<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
<c:set var="questions" value="${questionPage.getContent()}"></c:set>
<c:set var="questionCurrentPage" value="${questionPage.getNumber() +1}"></c:set>
<c:set var="questionTotalPage" value="${questionPage.getTotalPages()}"></c:set>
<c:set var="sessionUser" value="${sessionScope.user}"></c:set>

<c:if test="${chosenQuestionCategory ne null}">
	<c:set var="chosenQuestionCategoryId"
		value="${chosenQuestionCategory.id}"></c:set>
</c:if>

<div class="container-content" style="background-color: #f5f5f5;">
	<jsp:include page="../main/customerCenterNavigator.jsp" flush="true" />

	<div class="section-question-list-intro">
		<div class="intro-header">
			<h1>서비스 Q&A</h1>
			<p>서비스 문의사항에 대해 답변드립니다.</p>
		</div>
	</div>

	<div class="content-body">

		<div class="section-question-list">
			<div class="question-list-header">
				<div class="section-write"
					onclick="javascript:navigateToNewQuestion()">
					<div class="write-content">
						<span>글쓰기</span>
					</div>
				</div>

				<div class="section-category-list">
					<ul>
						<c:choose>
							<c:when test="${chosenQuestionCategory eq null}">
								<li><a class="current" href="${contextPath}/questions">전체보기</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${contextPath}/questions">전체보기</a></li>
							</c:otherwise>
						</c:choose>

						<c:forEach var="questionCategory" items="${questionCategories}">
							<c:choose>
								<c:when
									test="${questionCategory.id eq chosenQuestionCategoryId}">
									<li><a class="current"
										href="${contextPath}/questions?questionCategoryId=${questionCategory.id}">${questionCategory.name}</a></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="${contextPath}/questions?questionCategoryId=${questionCategory.id}">${questionCategory.name}</a></li>
								</c:otherwise>
							</c:choose>

						</c:forEach>
					</ul>
				</div>

				<div class="section-category-selector">
					<div class="selector-button">
						<div class="button-content">
							<c:choose>
								<c:when test="${chosenQuestionCategory ne null}">
									<span>${chosenQuestionCategory.name}</span>
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
							<a href="${contextPath}/questions">
								<li>전체보기</li>
							</a>
							<c:forEach var="questionCategory" items="${questionCategories}">
								<a
									href="${contextPath}/questions?questionCategoryId=${questionCategory.id}">
									<li>${questionCategory.name}</li>
								</a>
							</c:forEach>
						</ul>
					</div>
				</div>

				<div class="section-search">
					<div class="search-content">
						<input type=text id="search-text"
							onkeypress="javascript:if(event.keyCode == 13){search()}"
							value="${search}">
					</div>

					<div class="search-submit">
						<span>검색</span>
					</div>
				</div>
			</div>

			<div class="question-list-content">
				<ul>
					<c:forEach var="question" items="${questions}">
						<li>
							<div class="content-item" data-id="${question.id}">
								<div class="item-left">
									<span>Q</span>

									<c:if test="${question.answerCheck eq true }">
										<div class="question-check">
											<span>답변완료</span>
										</div>
									</c:if>
								</div>

								<div class="item-right">
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

									<div class="question-content">
										<c:choose>
											<c:when test="${(question.type eq 1) and (sessionUser eq null) }">
												<div class="content-lock">
													<div class="lock-title">
														<span>이 글은 비밀글입니다. 내용을 확인하시려면 비밀번호를 입력해 주세요.</span>
													</div>
													<div class="lock-content">
														<span>비밀번호:</span> <input type="password"
															class="lock-text" /> <span class="lock-button"
															onclick="javascript:showQuestion(${question.id}, this)">확인</span>
													</div>
												</div>

											</c:when>

											<c:otherwise>
												<div class="content-memo">
													<p>${question.content}</p>
												</div>
												
												<div class="content-detail">	
													<span onclick="javascript:navigateToQuestionDetail(${question.id})">자세히 보기 </span>
												</div>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>


			<div class="question-list-bottom">
				<div class="section-pagination">
					<c:if test="${questionCurrentPage ne 1}">
						<a href="javascript:paginate(${questionCurrentPage - 1 })"
							class="prev">&lt;</a>
					</c:if>

					<c:forEach var="i" begin="${startPageNo }" end="${endPageNo }"
						step="1">
						<c:choose>
							<c:when test="${questionCurrentPage eq i }">
								<b> <font size=+2> <a
										href="javascript:paginate(${i})" class="choice">${i}</a>
								</font>
								</b>
							</c:when>

							<c:otherwise>
								<a href="javascript:paginate(${i})">${i}</a>
							</c:otherwise>

						</c:choose>
					</c:forEach>
					

					<c:if test="${questionCurrentPage ne questionTotalPage}">
						<a href="javascript:paginate(${questionCurrentPage + 1 })"
							class="next">&gt;</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	var chosenQuestionCategoryId = "${chosenQuestionCategoryId}";
	
	$('.search-submit').click(function(){
		search();
	});
	
	function search() {
		var search = $(document.getElementById('search-text')).val();
		var url = "${contextPath}/questions?search=" + search;

		if(chosenQuestionCategoryId != ""){
			url = "${contextPath}/questions?search=" + search + "&questionCategoryId=" + chosenQuestionCategoryId;
		}
		location.href = url;
	}

	
	
 	$('.selector-button').click(function() {
		var self = this;
		$('.selector-filter').slideToggle();
	});
 	
 	
	function navigateToQuestionDetail(id, password){
		var url = "${contextPath}/questions/" + id + "?password=" + password;
	    location.href = url; 
 	}

	
	function showQuestion(id, self){
		var lockedContent = $(self).parent();
		var password = lockedContent.find('.lock-text').val();
	
	   	checkQuestionByIdAndPassword(id, password, self);
	}
	
	function checkQuestionByIdAndPassword(id, password, self){
		$.ajax({
	        type:"POST",
	        url:"${contextPath}/questions/"+ id +"/unlock",
	        data:{
	            password: password
	        }
	    }).success(function(data){
	    	var lockedContainer = $(self).parent().parent();
	    	
	    	if(data == ""){
	 		   	alert("비밀번호가 일치하지 않습니다.");
	    	}else{
	    		var questionContentTemplate = "<div class='content-memo'><p>" + data.content+ 
	    		"</p></div><div class='content-detail'><span onclick='javascript:navigateToQuestionDetail(" + data.id + 
	    		"," + JSON.stringify(data.password) + ")'>자세히 보기 ></span></div>";
	    			
	    		lockedContainer.after(questionContentTemplate);
	    		lockedContainer.hide();
	    	}
	    });
	}
	
	
	function navigateToNewQuestion(){
	   var url = "${contextPath}/questions/new";
	   location.href = url;
	}
	
	
	function paginate(pageNo){
	   var page = pageNo - 1;
	   
	   var url = "${contextPath}/questions?page=" + page;
	   
	   if(chosenQuestionCategoryId != ""){
	      url = "${contextPath}/questions?page=" + page + "&questionCategoryId=" + chosenQuestionCategoryId;
	   }
	   
	   location.href = url;
	}

   
</script>

