<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
	 response.setHeader("Cache-Control","no-cache");
	 response.setHeader("Pragma","no-cache");
	 response.setDateHeader("Expires",0);
%>


<html lang="ko">
<head>
	<script async src="https://www.googletagmanager.com/gtag/js?id=UA-107199748-1"></script>
	<script>
	  window.dataLayer = window.dataLayer || [];
	  function gtag(){dataLayer.push(arguments)};
	  gtag('js', new Date());
	
	  gtag('config', 'UA-107199748-1');
	</script>
	
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="description" content="Health Care & Service Design">
    <meta name="keywords" content="디맨드,demand,디맨드 주식회사,서비스디자인,헬쓰케어,웰패밀리하우스,굿버디,H2O,힐링사운드,낙상예방,낙상진단">
    <meta name="author" content="DEMAND">
	
	<title><tiles:getAsString name="title" /></title>
	
	<link href="<c:url value='/resources/static/css/style.css' />" rel="stylesheet" type="text/css" />
	
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet" type="text/css">
	
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" />
	<link href="https://cdn.jsdelivr.net/jquery.slick/1.6.0/slick.css" rel="stylesheet" type="text/css" />
	<link href="https://cdn.jsdelivr.net/jquery.slick/1.6.0/slick-theme.css" rel="stylesheet" type="text/css" />
	
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/i18n/jquery-ui-i18n.min.js"></script>
	<script src="https://cdn.jsdelivr.net/jquery.slick/1.6.0/slick.min.js" type="text/javascript"></script>

</head>


<body>
	<header id="header">
		<tiles:insertAttribute name="top" />
	</header>
	
	<div id="container">
		<tiles:insertAttribute name="content" />
	</div>
	
	<footer id="footer">
		<tiles:insertAttribute name="footer" />
	</footer>
</body>

</html>