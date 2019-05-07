<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href=<c:url value ="/resources/css/bootstrap.min.css"/>
	rel="stylesheet" media="screen">
<link href=<c:url value ="/resources/css/font-awesome.css"/>
	rel="stylesheet" media="screen">
<link href=<c:url value = "/resources/css/main.css"/> rel="stylesheet"
	media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> 
			<spring:message	code="lang.title" />
			</a>
		</div>
	</header>
	<section id="main">
		<div class="pull-left">
			<c:if test="${param.failed}"><spring:message	code="lang.incorrect" /></c:if>
			<form id="loginForm" action="/authenticate" method="POST" class="form-horizontal">
				<input type=text name="username" class="form-control"
					placeholder="<spring:message code="lang.login"/>" /> 
				<input type=password name="password"
					class="form-control"placeholder="<spring:message code="lang.password"/>"> 
				<input type=submit id="submit_button" value="submit"class="btn btn-primary">
			</form>
		</div>
	</section>
</body>