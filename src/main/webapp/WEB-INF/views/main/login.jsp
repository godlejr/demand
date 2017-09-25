<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>

<div class="container-content"
	style="background-image: url('<c:url value='/resources/static/img/main/login_background.jpg' />'); background-size: cover; background-repeat: no-repeat; background-position: center; height: 100%; align-items: center; display: flex;">
	<div class="section-login">
		<div class="login-header">
			<a href="${contextPath}/"> <img class="logo-image"
				src="<c:url value='/resources/static/img/main/demand_logo.png' />" /></a>
		</div>

		<div class="login-content">
			<form:form class="login-form" action="${contextPath}/login"  method="post" modelAttribute="user">
				<div class="form-email">
					<div class="email-left">
						<i class="fa fa-user" aria-hidden="true"></i>
					</div>

					<div class="email-right">
						<form:input path="email" class="text-email" name="email" type="text" placeholder="email" />
					</div>
				</div>

				<div class="form-password">
					<div class="password-left">
						<i class="fa fa-lock" aria-hidden="true"></i>
					</div>
					<div class="password-right">
						<form:input path="password" name="password" class="text-password" type="password" placeholder="password" />
					</div>
				</div>

				<input value="로그인" type="submit" class="form-button">

			</form:form>
		</div>

		<div class="login-bottom">
			<div class="bottom-content">
				<a href="" class="join-button"><span>아이디 신청</span></a> | <a href=""><span>비밀번호
						찾기</span></a>
			</div>
		</div>
	</div>

	<div id="join-container" class="join-container"></div>


</div>


<script>
   $(".join-button").click(function(event){
      event.preventDefault();
      showJoin();
   });

   function showJoin(){
      $.ajax({
         type : "GET",
         url : "${pageContext.request.contextPath}/join",
         success : function(response) {
            var template = $(response).find('#join-container').html();
            var joinContainer = $('#join-container');
            
            joinContainer.html(template).fadeIn(300, 'easeOutQuad');
            joinContainer.css({'display':'flex'});
            $('html').css({'overflow-y':'hidden'});
            joinContainer.show();

            joinContainer.click(function(e){
               var self = this;
               if($('.section-join').has(e.target).length === 0){
                  $(self).hide();
               }
            });
            
         }
      });
   }
</script>
