<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
				<spring:message code="lang.title"/>
			 </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1><spring:message code="lang.add.title"/></h1>
					<form action="add" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName"><spring:message code="lang.name"/></label> <input
									type="text" class="form-control" id="computerName"
									name="computerName" required="required"
									placeholder="Computer name">
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message code="lang.introduced"/></label> <input
									type="text" class="form-control" name="introduced"
									id="introduced" placeholder="Introduced date">
							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message code="lang.discontinued"/></label> <input
									type="text" class="form-control" name="discontinued"
									id="discontinued" placeholder="Discontinued date">
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message code="lang.company"/></label> <select
									class="form-control" id="companyId" name="companyId">
									<option value="">--</option>
									<c:forEach items="${companies}" var="company">
										<option value="${company.id}">${company.name}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">	
							<input type="submit" id="add_button" value="<spring:message code="lang.addComp"/>"
								class="btn btn-primary"> <spring:message code="lang.or"/> <a href="dashboard"
								id="<spring:message code="lang.cancel"/>" class="btn btn-default">Cancel</a>
						</div>

					</form>
				</div>
			</div>
		</div>
	</section>
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/validator.js"></script>

</body>
</html>