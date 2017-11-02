<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
<c:set var="sessionUser" value="${sessionScope.user}"></c:set>
<c:set var="otherQuestions" value="${questionPage.getContent()}"></c:set>
<c:set var="otherQuestionCurrentPage" value="${questionPage.getNumber() + 1}"></c:set>
<c:set var="otherQuestionTotalPageNumber" value="${questionPage.getTotalPages()}"></c:set>

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
								<li onclick="javascript:onClickQuestionFunction(${question.id},1)">수정</li>
								<li onclick="javascript:onClickQuestionFunction(${question.id},2)">삭제</li>
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
							<form:input type="text" hidden="hidden" path="questionId" value="${question.id}"  />
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
												<input type="button" id="submit" value="답변등록" />
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
		
		<div class="section-questions-button">
			<div  class="button-function"  onclick="javascript:navigateToQuestionListPage()">
				<span>목록</span>
			</div>
		</div>
		
		<div class="section-other-questions">
			<div class="other-questions-content">
				<div class="content-header">
					<span class="question-category">'${question.questionCategory.name}'</span><span>의 다른 질문들</span>
				</div>
				
				<div class="content-list">
					<table summary="번호, 카테고리, 제목, 작성일 ">
						<thead>
							<tr>
								<th>Title</th>
								<th>Category</th>
								<th>Date</th>
							</tr>
						</thead>
	
						<tbody>
							<c:forEach var="otherQuestion" items="${otherQuestions}">
								<tr class="other-question-item">
									<td class="item-column" onclick="javascript:navigateToQuestionDetail(${otherQuestion.id})">${otherQuestion.title }</td>
									<td class="item-column">${otherQuestion.questionCategory.name }</td>
									<td class="item-column">${otherQuestion.getCustomCreatedAt()  }</td>
								</tr>
							</c:forEach>
						</tbody>
						
					</table>
				</div>
				<div class="content-bottom">
					<div class="section-pagination">
					    <c:if test="${otherQuestionCurrentPage ne 1}">
					        <a href="javascript:paginate(${otherQuestionCurrentPage - 1})" class="prev">이전</a>
					    </c:if>
					    
					    <span>
					        <c:forEach var="i" begin="${startPageNo}" end="${endPageNo}" step="1">
					            <c:choose>
					                <c:when test="${i eq otherQuestionCurrentPage}">
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
					    
					    <c:if test="${otherQuestionCurrentPage ne otherQuestionTotalPageNumber}">
					        <a href="javascript:paginate( ${otherQuestionCurrentPage + 1})" class="next">다음</a>
					    </c:if>
					 
					</div>	
				</div>
			</div>
		</div>
		
		<div id="popup-container" class="popup-container"></div>
	</div>
</div>

<script>
	var $submit = $("#submit");
	
	$submit.click(function() {
   		var answerForm = $("#answer-form").serialize();
   		sendAnswer(answerForm);
	});
	
	function sendAnswer(answerForm) {
	   $.ajax({
	      type : "POST",
	      url : "${contextPath}/answers/",
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
	
	function paginate(pageNo){    
		var page = pageNo -1;
		var url = "${contextPath}/questions/${question.id}/?page=" + page;		
		location.href = url;
	}
	
	function navigateToQuestionDetail(id){
 		var url = "${contextPath}/questions/" + id;
	    location.href = url; 
 	}
	
	
	function navigateToQuestionListPage() {
	   document.location.href = "${contextPath}/questions";
	}
	
	function onClickQuestionFunction(id, flag){
		showQuestionPasswordCheckPopup(id, flag);
	}
	
	
	function showQuestionPasswordCheckPopup(id, flag){
		
		var documentHtml =$('html');
		
		$.ajax({
	         type : "GET",
	         url : "${contextPath}/questions/" + id + "/passwordCheck",
	         cache: false,
	   		 async: true,	
	         data:{
	        	 flag: flag
	         },
	         success : function(response) {
	            var template = $(response).find('#popup-container').html();
	            var popupContainer = $('#popup-container');
	            
	            popupContainer.html(template).fadeIn(300, 'easeOutQuad');
	            documentHtml.css({'overflow-y':'hidden'});
	            popupContainer.css({'display':'flex'});
	            popupContainer.show();

	            popupContainer.click(function(e){
	               var self = this;
	               if($('.section-password-popup').has(e.target).length === 0){
	                  $(self).hide();
	                  $('html').css({'overflow-y':'auto'});
	               }
	            });
	            
	         }
	      });
	}
	
</script>