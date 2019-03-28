<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title>Computer Database</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- Bootstrap -->
<link href=<c:url value ="/ressources/css/bootstrap.min.css"/>
	rel="stylesheet" media="screen">
<link href=<c:url value ="/ressources/css/font-awesome.css"/>
	rel="stylesheet" media="screen">
<link href=<c:url value = "/ressources/css/main.css"/> rel="stylesheet"
	media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="/dashboard"> Application - Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">	
			<div class="alert alert-danger">
				Error 500: An error has occured!
				<br/>
					${exception.getMessage()}
			</div>
		</div>
	</section>

	<script src="ressources/js/jquery.min.js"></script>
	<script src="ressources/js/bootstrap.min.js"></script>
	<script src="ressources/js/dashboard.js"></script>

</body>
</html>