<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>


<div class="container-content">
   <div class="section-image-banner">
      <div class="image-banner-header"></div>

      <div class="image-banner-content"></div>


      <div class="image-banner-bottom">
         <i id="arrow" class="fa fa-angle-down" aria-hidden="true"></i>
      </div>

   </div>

   <div class="section-servicedesign-intro">
      <div class="intro-header">
         <h1>SERVICE DESIGN PROCESS</h1>
      </div>
      <div class="intro-content">
         <p>
            Demand는 서비스디자인을 통해 사람들의 삶에서 새로운 경험을 창출하고자 합니다.<br /> 다양한 경험을 바탕으로
            현장에서 바로 적용할 수 있는 서비스디자인 방법론을 지속적으로 개발하고 있습니다.
         </p>
      </div>
   </div>

   <div class="section-servicedesign-process">
      <div class="process-content">
            <ul class="process-list">
               <li>
                  <div class="content-item">
                     <div class="item-title">
                        <h2>1. Opportunity Discovering</h2>
                     </div>
                     <div class="item-content">
                        <p>
                           비즈니스(또는 서비스) 현황 분석, 사용자 관찰, 사용자 인터뷰 등의 디자인 방법론을<br /> 통해 최종
                           사용자가 실질적으로 필요한 부분을 도출하게 되며, 도출된 내용에<br /> 기반하여 서비스 개선 기회를 포착하고
                           새로운 서비스 컨셉을 개발합니다.
                        </p>
                     </div>
                  </div>
               </li>

               <li>
                  <div class="content-item">
                     <div class="item-title">
                        <h2>2. Service Developing</h2>
                     </div>
                     <div class="item-content">
                        <p>
                           사용자 니즈를 바탕으로 선행 사례 및 관련 기술 등을 고려하여 서비스 전략<br /> 아이디어를 도출하고, 이를
                           최종 소비자에게 전달할 수 있는 최적의 전달 모델을<br /> 개발합니다. 그리고 이를 다양한 프로토타이핑
                           방법론을 활용해 서비스의 실효성을<br /> 검증합니다.
                        </p>
                     </div>
                  </div>
               </li>

               <li>
                  <div class="content-item">
                     <div class="item-title">
                        <h2>3. Business Modeling</h2>
                     </div>
                     <div class="item-content">
                        <p>
                           비즈니스(또는 서비스)모델을 실질적으로 적용하기 위해 Service life cycle에 따른<br />
                           이해관계자 역할, 수익구조, 사업화모델, 브랜딩 전략, 운영방안, 변화관리, 적용 전<br /> 등을 개발하여,
                           즉각적으로 사업화에 쓰일 수 있는 구체적인 서비스 모델을<br /> 제시합니다.
                        </p>
                     </div>
                  </div>
               </li>
            </ul>
      </div>
   </div>

</div>

<script>
$("#arrow").click(function(){
    $('html, body').animate({
       scrollTop: $(".section-servicedesign-intro").offset().top
    }, 600);
 });
</script>
