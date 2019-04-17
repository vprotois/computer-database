<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			Application - Computer Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<h1 id="homeTitle"> ${number_computers} Computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="" method="GET" class="form-inline">
						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" value="${search}"/> 
							<input type="submit" id="searchsubmit" value="Filter by name" class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="add">Add Computer	</a> 
					<a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">
						Edit
					</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="delete" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->
						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						
						<th>Computer name
						<a href="${urlPath}?index=${previous_page_index}&search=${search}&size=${size}&order=name&asc=false">
						<i class="fa fa-arrow-down"></i></a>
						<a href="${urlPath}?index=${previous_page_index}&search=${search}&size=${size}&order=name&asc=true">
						<i class="fa fa-arrow-up"></i></a>
						</th>
						
						<th>Introduced date
						<a href="${urlPath}?index=${previous_page_index}&search=${search}&size=${size}&order=introduced&asc=false">
						<i class="fa fa-arrow-down"></i></a>
						<a href="${urlPath}?index=${previous_page_index}&search=${search}&size=${size}&order=introduced&asc=true">
						<i class="fa fa-arrow-up"></i></a>
						</th>
						
						<th>Discontinued date
						<a href="${urlPath}?index=${previous_page_index}&search=${search}&size=${size}&order=discontinued&asc=false">
						<i class="fa fa-arrow-down"></i></a>
						<a href="${urlPath}?index=${previous_page_index}&search=${search}&size=${size}&order=discontinued&asc=true">
						<i class="fa fa-arrow-up"></i> </a>
						</th>
						
						
						<th>Company
						<a href="${urlPath}?index=${previous_page_index}&search=${search}&size=${size}&order=company_id&asc=false">
						<i class="fa fa-arrow-down"></i></a>
						<a href="${urlPath}?index=${previous_page_index}&search=${search}&size=${size}&order=company_id&asc=true">
						<i class="fa fa-arrow-up"></i></a>
						</th>
						
					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${page_data}" var="computer">
						<tr>
							<td class="editMode">
							  <input type="checkbox" name="cb" class="cb" value="${computer.id}"></td>
							 <td> <a  href="<c:url value="/edit?id=${computer.id}"/>" onclick="" id ="name">
							    ${computer.name}</a></td>
							<td>${computer.introduced}</td>
							<td>${computer.discontinued}</td>
							<td>${computer.company}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="btn-group btn-group-sm pull-right" role="group">
			  <a href="${urlPath}?index=${previous_page_index}&search=${search}&size=10">
			   <button type="button" class="btn btn-default"> 10</button>
			  </a> 
			<a href="${urlPath}?index=${previous_page_index}&search=${search}&size=50">
			   <button type="button" class="btn btn-default"> 50</button>
			  </a> 
			<a href="${urlPath}?index=${previous_page_index}&search=${search}&size=100">
			   <button type="button" class="btn btn-default"> 100</button>
			  </a> 
		</div>
		<div class="container text-center">
			<ul class="pagination">
				<li><a href="${urlPath}?index=${previous_page_index}&search=${search}&order=${order}"
					aria-label="Previous" id="previous"> <span aria-hidden="true">&laquo;</span>
				</a> <a href="${urlPath}?index=${next_page_index}&search=${search}&order=${order}"
					 aria-label="Next"> <span aria-hidden="true" id="next">&raquo;</span>
				</a></li>
			</ul>
		</div>
	</footer>
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/dashboard.js"></script>
</body>
</html>