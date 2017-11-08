<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<div class="container-content">
	<jsp:include page="../main/mobileAppNavigator.jsp" flush="false" />

	<div class="section-mobileapp-intro">
		<div class="intro-wrapper">
			<div class="wrapper-header">
				<c:choose>
					<c:when test="${chosenMobileApp.id eq 1}">
						<c:set var="introImageUrl"
							value="/resources/static/img/main/mobileapp_wellfamilyhouse.png" />
					</c:when>

					<c:when test="${chosenMobileApp.id eq 2}">
						<c:set var="introImageUrl"
							value="/resources/static/img/main/mobileapp_h2o.png" />
					</c:when>

					<c:when test="${chosenMobileApp.id eq 3}">
						<c:set var="introImageUrl"
							value="/resources/static/img/main/mobileapp_goodbuddy.jpg" />
					</c:when>
				</c:choose>

				<img src="<c:url value='${introImageUrl}'/>" />
			</div>

			<div class="wrapper-content">
				<p>${chosenMobileApp.intro}</p>
			</div>
		</div>
	</div>


	<c:choose>
		<c:when test="${chosenMobileApp.id eq 1}">
			<c:set var="backgroundImageUrl"
				value="/resources/static/img/main/mobileapp_wellfamilyhouse_bg.jpg" />
		</c:when>

		<c:when test="${chosenMobileApp.id eq 2}">
			<c:set var="backgroundImageUrl"
				value="/resources/static/img/main/mobileapp_h2o_bg.jpg" />
		</c:when>

		<c:when test="${chosenMobileApp.id eq 3}">
			<c:set var="backgroundImageUrl"
				value="/resources/static/img/main/mobileapp_goodbuddy_bg.jpg" />
		</c:when>
	</c:choose>

	<div class="section-mobileapp-object"
		style="background: url('<c:url value='${backgroundImageUrl}' />'); background-size: cover; background-repeat: no-repeat; background-position: center;">
		<div class="object-content">
			<ul>
				<c:choose>
					<c:when test="${chosenMobileApp.id eq 1}">
						<c:set var="firstCategoryImageUrl"
							value="/resources/static/img/main/mobileapp_wellfamilyhouse_category1.png" />
						<c:set var="secondCategoryImageUrl"
							value="/resources/static/img/main/mobileapp_wellfamilyhouse_category2.png" />
						<c:set var="lastCategoryImageUrl"
							value="/resources/static/img/main/mobileapp_wellfamilyhouse_category3.png" />

						<c:set var="firstCategoryImageTitle" value="가족들의 소식공유" />
						<c:set var="secondCategoryImageTitle" value="소리로 떠나는 추억 여행" />
						<c:set var="lastCategoryImageTitle" value="낙상 위험 진단" />

						<c:set var="firstCategoryImageContent"
							value="글과 사진을 공유하며 <br>가족들에게 즐거운 소식을 전해보세요" />
						<c:set var="secondCategoryImageContent"
							value="추억의 소리를 들으며 기억력을 증진시키고<br>가족들과 공유해보세요" />
						<c:set var="lastCategoryImageContent"
							value="낙상 위험을 진단하는 3가지 방법으로 <br>위험도를 평가해보세요" />
					</c:when>

					<c:when test="${chosenMobileApp.id eq 2}">
						<c:set var="firstCategoryImageUrl"
							value="/resources/static/img/main/mobileapp_h2o_category1.png" />
						<c:set var="secondCategoryImageUrl"
							value="/resources/static/img/main/mobileapp_h2o_category2.png" />
						<c:set var="lastCategoryImageUrl"
							value="/resources/static/img/main/mobileapp_h2o_category3.png" />

						<c:set var="firstCategoryImageTitle" value="맥파를 통한 자율신경 균형 측정" />
						<c:set var="secondCategoryImageTitle" value="백색소음을 활용한 힐링사운드" />
						<c:set var="lastCategoryImageTitle" value="호흡조절법 / 명상법" />

						<c:set var="firstCategoryImageContent"
							value="스마트폰 카메라를 활용하여 맥파를 측정하고,<br>스트레스 정도를 확인해보세요" />
						<c:set var="secondCategoryImageContent"
							value="자연에서 들을 수 있는 백색소음을 통해<br> 집중력을 향상시켜보세요" />
						<c:set var="lastCategoryImageContent"
							value="스트레스 관리를 위해<br>호흡조절법과 명상법을 이용해보세요" />
					</c:when>

					<c:when test="${chosenMobileApp.id eq 3}">
						<c:set var="firstCategoryImageUrl"
							value="/resources/static/img/main/mobileapp_goodbuddy_category1.png" />
						<c:set var="secondCategoryImageUrl"
							value="/resources/static/img/main/mobileapp_goodbuddy_category2.png" />
						<c:set var="lastCategoryImageUrl"
							value="/resources/static/img/main/mobileapp_goodbuddy_category3.png" />

						<c:set var="firstCategoryImageTitle" value="건강한 생활 습관" />
						<c:set var="secondCategoryImageTitle" value="건강관리 수첩" />
						<c:set var="lastCategoryImageTitle" value="관리자 메세지" />

						<c:set var="firstCategoryImageContent"
							value="활동량, 혈압, 혈당 등을 기록하고<br>자신의 변화를 아바타의 변화를 통해 확인해보세요" />
						<c:set var="secondCategoryImageContent"
							value="비만 예방/관리 방법, 운동, 스트레스 관리 방법 등<br>올바른 건강관리 방법을 확인해보세요" />
						<c:set var="lastCategoryImageContent"
							value="그 외 궁금한 건강정보를 전문가와 함께 고민해보세요" />
					</c:when>
				</c:choose>


				<li class="content-item">
					<div class="item-left">
						<img src="<c:url value="${firstCategoryImageUrl}"/>">
					</div>
					<div class="item-right">
						<div class="right-header">
							<h2>${firstCategoryImageTitle}</h2>
						</div>
						<div class="right-content">
							<p>${firstCategoryImageContent}</p>
						</div>
					</div>
				</li>
				<li class="content-item">
					<div class="item-left">
						<img src="<c:url value="${secondCategoryImageUrl}"/>">
					</div>
					<div class="item-right">
						<div class="right-header">
							<h2>${secondCategoryImageTitle}</h2>
						</div>
						<div class="right-content">
							<p>${secondCategoryImageContent}</p>
						</div>
					</div>
				</li>
				<li class="content-item">
					<div class="item-left">
						<img src="<c:url value="${lastCategoryImageUrl}"/>">
					</div>
					<div class="item-right">
						<div class="right-header">
							<h2>${lastCategoryImageTitle}</h2>
						</div>
						<div class="right-content">
							<p>${lastCategoryImageContent}</p>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>

	<div class="section-mobileapp-preview">
		<div class="preview-wrapper">
			<div class="wrapper-header">
				<h1>서비스 미리보기</h1>
			</div>

			<div class="wrapper-content">
				<ul>
					<c:choose>
						<c:when test="${chosenMobileApp.id eq 1}">
							<c:set var="firstPreviewImageUrl"
								value="/resources/static/img/main/mobileapp_wellfamilyhouse_preview1.jpg" />
							<c:set var="secondPreviewImageUrl"
								value="/resources/static/img/main/mobileapp_wellfamilyhouse_preview2.jpg" />
							<c:set var="lastPreviewImageUrl"
								value="/resources/static/img/main/mobileapp_wellfamilyhouse_preview3.jpg" />

							<c:set var="firstPreviewImageContent" value="메인" />
							<c:set var="secondPreviewImageContent" value="추억소리" />
							<c:set var="lastPreviewImageContent" value="신체능력 평가" />
						</c:when>

						<c:when test="${chosenMobileApp.id eq 2}">
							<c:set var="firstPreviewImageUrl"
								value="/resources/static/img/main/mobileapp_h2o_preview1.jpg" />
							<c:set var="secondPreviewImageUrl"
								value="/resources/static/img/main/mobileapp_h2o_preview2.jpg" />
							<c:set var="lastPreviewImageUrl"
								value="/resources/static/img/main/mobileapp_h2o_preview3.jpg" />

							<c:set var="firstPreviewImageContent" value="메인" />
							<c:set var="secondPreviewImageContent" value="스트레스 측정" />
							<c:set var="lastPreviewImageContent" value="몸챙김 호흡법" />
						</c:when>

						<c:when test="${chosenMobileApp.id eq 3}">
							<c:set var="firstPreviewImageUrl"
								value="/resources/static/img/main/mobileapp_goodbuddy_preview1.jpg" />
							<c:set var="secondPreviewImageUrl"
								value="/resources/static/img/main/mobileapp_goodbuddy_preview2.jpg" />
							<c:set var="lastPreviewImageUrl"
								value="/resources/static/img/main/mobileapp_goodbuddy_preview3.jpg" />

							<c:set var="firstPreviewImageContent" value="메인" />
							<c:set var="secondPreviewImageContent" value="건강관리 수첩" />
							<c:set var="lastPreviewImageContent" value="관리자 메세지" />
						</c:when>

					</c:choose>
					<li>
						<div class="content-item">
							<img src="<c:url value='${firstPreviewImageUrl}'/>" />
							<div class="item-content">
								<span>${firstPreviewImageContent}</span>
							</div>
						</div>
					</li>
					<li>
						<div class="content-item">
							<img src="<c:url value='${secondPreviewImageUrl}'/>" />
							<div class="item-content">
								<span>${secondPreviewImageContent}</span>
							</div>
						</div>
					</li>
					<li>
						<div class="content-item">
							<img src="<c:url value='${lastPreviewImageUrl}'/>" />
							<div class="item-content">
								<span>${lastPreviewImageContent}</span>
							</div>
						</div>
					</li>
				</ul>
			</div>

			<div class="wrapper-bottom">
				<ul>
					<li><a href="${chosenMobileApp.downloadUrl}">구글플레이 다운로드</a></li>
					<li><a href="${contextPath}/mobileApps/${chosenMobileApp.id}/privacy">개인정보 처리방침</a></li>
					<li><a href="${contextPath}/mobileApps/${chosenMobileApp.id}/agreement">이용약관</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>