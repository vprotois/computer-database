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
			<a class="navbar-brand" href="dashboard"> <spring:message
					code="lang.title" />
			</a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>
						<spring:message code="lang.delete.title" />
					</h1>
					<div class="container" style="margin-top: 10px;">

						<c:choose>
							<c:when test="${notDeletedMap.isEmpty()}">
    					  	<spring:message code="lang.deleted" />
   						</c:when>
							<c:otherwise>
								<table class="table table-striped table-bordered">
									<tbody id="results">
										<c:forEach items="${notDeletedMap}" var="entry">
											<tr>
												<td>${entry.key}
												<td>${entry.value}
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:otherwise>
						</c:choose>

					</div>

				</div>
			</div>
		</div>
	</section>
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>