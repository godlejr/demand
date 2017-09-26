<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>

<div id="container">
	<div class="container-content">
		<div class="content-body"
			style="height: 100%; align-items: center; display: flex;">


			<div id="join-container" class="join-container">
				<div class="section-join">
					<div class="join-header">
						<span>아이디 신청</span>
					</div>

					<div class="join-content">

						<form:form id="join-form" class="join-form" method="post"
							modelAttribute="user">

							<div class="form-left">
								<div class="left-email">
									<form:checkbox id="isEmailChecked" path="emailCheck"
										hidden="hidden" />
									<form:input id="join-email" class="input-text email"
										type="text" placeholder="이메일 주소" path="email" />

									<div class='email-error' id="email-check-error" hidden="hidden">
										<span>사용중인 이메일입니다.</span>
									</div>

									<div class='email-error' id="email-check-success"
										hidden="hidden">
										<span>사용 가능한 이메일입니다.</span>
									</div>

								</div>

								<div class="left-password">
									<form:input id="join-password" class="input-text password"
										type="password" placeholder="비밀번호" path="password" />
								</div>

								<div class="left-password-confirm">
									<form:input id="join-password-confirm" class="input-text"
										type="password" placeholder="비밀번호 확인" path="passwordConfirm" />
								</div>

								<div class="left-name">
									<form:input id="join-name" class="input-text name" type="text"
										placeholder="이름" path="name" />
								</div>

								<div class="left-registration">
									<form:input id="join-registration" class="input-text"
										type="text" placeholder="주민번호 ('-' 없이 입력)" path="registrationNo" />
								</div>
							</div>

							<div class="form-right">
								<div class="right-email">
									<input type="button" value="중복 확인" class="email-button"
										onclick="onClickEmailCheck();">
								</div>

							</div>

						</form:form>

					</div>

					<div class="join-bottom">
						<input value="신청" type="button" class="form-button"
							onclick="onClickSubmit();">
					</div>

					<script>
						var View = function() {
							var EmailCheckError = $(document
									.getElementById('email-check-error'));
							var EmailCheckSuccess = $(document
									.getElementById('email-check-success'));

							function init() {

							}

							var public = {
								removeJoinError : function() {
									$('.join-error').remove();
								},
								removeEmailError : function() {
									$('.left-email').find('.join-error')
											.remove();
								},
								addErrorMessage : function(id, messsage) {
									$(document.getElementById("join-" + id))
											.after(
													"<div class='join-error'><span>"
															+ messsage
															+ "</span></div>");
								},
								setEmailChecked : function(checked) {
									$(document.getElementById('isEmailChecked'))
											.prop('checked', checked);
								},
								showEmailCheckError : function() {
									EmailCheckError.show();
								},
								goneEmailCheckError : function() {
									EmailCheckError.hide();
								},
								showEmailCheckSuccess : function() {
									EmailCheckSuccess.show();
								},
								goneEmailCheckSuccess : function() {
									EmailCheckSuccess.hide();
								},
								navigateToLogin: function(isJoined){
									var url = "${contextPath}/login?isJoined=" +isJoined;
								 	location.href = url; 
								}

							}

							init();
							return public;
						}

						var Presenter = function(_view) {
							var interactor;
							var view;

							function init() {
								view = _view;
							}

							var public = {
								getView : function() {
									return view;
								},
								setInteractor : function(_interactor) {
									interactor = _interactor;
								},
								onClickJoin : function(user) {
									interactor.setJoin(user);
								},
								onClickEmailCheck : function(email) {
									if (email.length > 0) {
										interactor.setEmailCheck(email);
									}
								},
								onSuccessEmailCheck : function(data) {
									view.removeEmailError();

									if (data) {
										view.setEmailChecked(false);
										view.showEmailCheckError();
										view.goneEmailCheckSuccess();
									} else {
										view.setEmailChecked(true);
										view.showEmailCheckSuccess();
										view.goneEmailCheckError();
									}
								},
								onSuccessJoin : function(data) {
									
									view.removeJoinError();

									var errorMessages = data.errorMessages;
									
									if(errorMessages != null){
										
										$.each(
											errorMessages,
											function(index, value) {
												var id;
	
												if (value.field == "passwordConfirm") {
													id = "password-confirm";
												} else {
													id = value.field;
												}
	
												if (value.field == "emailCheck") {
													id = "email";
												}
	
												view.addErrorMessage(id, value.message);
										});
									
									}else{
										var isJoined = data;
										
										view.navigateToLogin(isJoined);
									}
									
								}

							}

							init();
							return public;
						}

						var Interactor = function(_presenter) {
							var presenter;

							function init() {
								presenter = _presenter;
							}

							var public = {
								setJoin : function(user) {
									$.ajax({
										type : "POST",
										url : "${contextPath}/join",
										data : user,
										success : function(data) {
											presenter.onSuccessJoin(data);
										}
									});
								},
								setEmailCheck : function(email) {
									$.ajax({
										type : "get",
										url : "${contextPath}/emailCheck",
										data : {
											email : email
										}
									}).success(function(data) {
										presenter.onSuccessEmailCheck(data);
									});
								}
							}

							init();
							return public;

						}

						var presenter = new Presenter(new View());
						presenter.setInteractor(new Interactor(presenter));

						function onClickSubmit() {
							var user = $('#join-form').serialize();
							presenter.onClickJoin(user);
						}

						function onClickEmailCheck() {
							var email = $(document.getElementById('join-email'));
							presenter.onClickEmailCheck(email.val());
						}
					</script>
				</div>
			</div>

		</div>
	</div>
</div>