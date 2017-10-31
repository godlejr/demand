<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<% String title = request.getParameter("title"); %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>

<div class="section-question-banner">
   <div class="banner-header">
      <h1><%=title%></h1>
   </div>
</div>