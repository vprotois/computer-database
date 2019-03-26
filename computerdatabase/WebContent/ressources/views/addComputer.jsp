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
			<a class="navbar-brand" href="dashboard.html"> Application -
				Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form action="add" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" id="computerName"
									name="computerName" required="required"
									placeholder="Computer name">
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="text" class="form-control" name="introduced"
									id="introduced" placeholder="Introduced date">
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="text" class="form-control" name="discontinued"
									id="discontinued" placeholder="Discontinued date">
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									<option value="0">--</option>
									<c:forEach items="${companies}" var="company">
										<option value="${company.id}">${company.name}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" id="add_button" value="add"
								class="btn btn-primary"> or <a href="dashboard"
								id="cancel_button" class="btn btn-default">Cancel</a>
						</div>

					</form>
				</div>
			</div>
		</div>
	</section>
	<script src="ressources/js/jquery.min.js"></script>
	<script src="ressources/js/bootstrap.min.js"></script>
	<script src="ressources/js/validator.js"></script>

</body>
</html>