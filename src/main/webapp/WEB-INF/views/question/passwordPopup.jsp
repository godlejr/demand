<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>

<div id="container">
	<div class="container-content">
		<div class="content-body"
			style="height: 100%; align-items: center; display: flex;">

			<div id="popup-container" class="popup-container">
				<div class="section-password-popup">
					<div class="popup-item" data-id="${question.id}">
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
								<div class="content-lock">
									<div class="lock-title">
										<c:choose>
											<c:when test="${flag eq 1}" >
												<span>이 글은 수정 하시려면 </span>
												<c:set var="lockContentCss" value="edit" ></c:set>
											</c:when>
											<c:otherwise>
												<span>이 글은 삭제 하시려면 </span>
												<c:set var="lockContentCss" value="delete" ></c:set>
											</c:otherwise>
										</c:choose>
										<span class="${lockContentCss}">비밀번호를 입력해 주세요.</span>
									</div>
									<div class="lock-content">
										<span>비밀번호:</span> <input type="password"  id="question-password" class="lock-text" /> <span class="lock-button"
											onclick="javascript:showQuestionEditAndDelete(${question.id},${flag})">확인</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<script>
						function showQuestionEditAndDelete(id, flag){
							var password = $(document.getElementById('question-password')).val();
							
							checkQuestionPassword(id,password, flag);
						}
						
						function checkQuestionPassword(id, password, flag){
							console.log(flag);
							  $.ajax({
							      type : "POST",
							      url : "${contextPath}/questions/"+ id +"/passwordCheck/",
							      data : {
							    	  password: password
							      } ,
							      success : function(data) {
							         var message = data.message;
							         if (message != null) {
							            alert(message);
							         } else {
							        	 if(flag == 1){
							        		 navigateToQuestionEditPage(id, data);
							        	 }else{
							        		 navigateToQuestionDeletePage(id, data);
							        	 }
							            
							         }
							      }
							   })
						}
						
						function navigateToQuestionEditPage(id, password){
							var url = "${contextPath}/questions/"+ id +"/edit?password=" + password;
							location.href = url;
						}
						
						function navigateToQuestionDeletePage(id, password){
							var url = "${contextPath}/questions/"+ id +"/delete?password=" + password;
							location.href = url;
						}
					</script>
				</div>
			</div>
		</div>
	</div>
</div>