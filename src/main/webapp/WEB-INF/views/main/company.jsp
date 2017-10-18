<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-content">
	<div class="section-image-banner">
		<div class="image-banner-header"></div>

		<div class="image-banner-content"></div>


		<div class="image-banner-bottom">
			<i id="arrow" class="fa fa-angle-down" aria-hidden="true"></i>
		</div>

	</div>
	<div class="section-company-intro">
		<div class="intro-header">
			<img src="<c:url value='/resources/static/img/main/demand_ci.png' />">
		</div>

		<div class="intro-content">
			<p>
				Demand는 자체 개발한 서비스 경험디자인 방법론을 바탕으로, 기업 및 공공기관을 대상으로 서비스디자인 컨설팅 및
				프로젝트를 수행합니다. <br> 디자인, 심리학, 경영학 등을 전공한 전문가와 함께 ‘Design Human
				Life’라는 비전을 이루고자 다양한 산업과 연계한 서비스 모델을 구축하고 있으며, <br> 이를 통해 쌓인
				가치와 지식을 사회 각 분야로 공유하고 있습니다.
			</p>
		</div>
	</div>
	<div class="section-company-object">
		<div class="object-left">
			<div class="left-header">
				<h1>DESIGN HUMAN LIFE</h1>
			</div>
			<div class="left-content">
				<p>
					우리는 디자인을 ‘심미적으로 아름답게 만드는 것’ 만이 아닌, ‘사람들의 삶을 더 풍요롭게 만드는 과정’이라고
					생각합니다.<br> 사람들에게 필요한 경험을 전달해주는 것이야 말로 디자인의 진정한 가치라고 믿기 때문입니다.<br>
					이 급변하는 세상의 흐름 속에서 시민들의 관점으로 보다 편안하고, 삶의 의미를 느낄 수 있는 디자인을 함으로써 인간
					친화적인 세상을 구현하는 것이 저희의 목표입니다.
				</p>
			</div>
		</div>

		<div class="object-right">
			<img
				src="<c:url value='/resources/static/img/main/company_object_banner.png' />">
		</div>
	</div>

	<div class="section-company-business-intro">
		<div class="intro-header">
			<h1>OUR BUSINESS</h1>
		</div>
		<div class="intro-content">
			<ul>
				<li>
					<div class="content-item">
						<img class="item-image"
							src="<c:url value='/resources/static/img/main/svc_dsg.png' />">
						<h2 class="item-title">서비스디자인</h2>
						<p>자체 개발한 서비스경험디자인 방법론을 바탕으로 , 세계 최고의 기업을 대상으로 서비스디자인 컨설팅을 수행합니다.</p>
					</div>
				</li>
				<li>
					<div class="content-item">
						<img class="item-image"
							src="<c:url value='/resources/static/img/main/healthcare.png' />">
						<h2 class="item-title">헬스케어 사업</h2>
						<p>여러 분야의 전문가와 함께 쉽고 재미있게 즐기는 다양한 정신, 신체 건강관리 서비스를 제공합니다.</p>
					</div>
				</li>
				<li>
					<div class="content-item">
						<img class="item-image"
							src="<c:url value='/resources/static/img/main/randd.png' />">
						<h2 class="item-title">정부 R&D 사업</h2>
						<p>서비스디자인을 바탕으로 정부 R&D사업을 수행하여 사용자 조사 및 다양한 서비스 모델을 개발합니다. </p>
					</div>
				</li>
				<li>
					<div class="content-item">
						<img class="item-image"
							src="<c:url value='/resources/static/img/main/tech.png' />">
						<h2 class="item-title">기술 사업화</h2>
						<p>정부 R&D 사업을 통해 개발된 기술 및 자원을 활용하여 사업화를 진행합니다. </p>
					</div>
				</li>
			</ul>
		</div>
	</div>
	
	<div class="section-company-partners">
	
	</div>

</div>

<script>
$("#arrow").click(function(){
    $('html, body').animate({
       scrollTop: $(".section-company-intro").offset().top
    }, 600);
 });
</script>