<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
<style type='text/css'>
#order {display:none;}
</style>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
		<div class="row">
		<div class="col-sm-9">
			<a class="navbar-brand" href="dashboard"> 
			<spring:message code="title" text="default text" /> </a>

		</div>
		<div class="col-sm">
		<a class="navbar-brand" href="?lang=en">English |</a><a class="navbar-brand" href="?lang=fr">French</a>
		</div>
		</div>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${infos.computers} <spring:message code="found" text="default text" /></h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="dashboard" method="GET"
						class="form-inline">

							<div class="input-group mb-3">
								<select class="custom-select" id="inputGroupSelect01" name="order">
									<option selected value="computer.id"><spring:message code="order" text="default text" /></option>
									<option value="computer.name"><spring:message code="computer" text="default text" /></option>
									<option value="computer.introduced"><spring:message code="intro" text="default text" /></option>
									<option value="computer.discontinued"><spring:message code="discon" text="default text" /></option>
									<option value="company.name"><spring:message code="company" text="default text" /></option>
								</select>
							</div>
							
							<div class="input-group mb-3">
								<select class="custom-select" id="order" name="sens">
									<option selected value="ASC">ASC</option>
									<option value="DESC">DESC</option>
								</select>
							</div>

						<input type="search" id="searchbox" name="name"
							class="form-control" placeholder=<spring:message code="search_name" text="default text" /> /> 
						<input
							type="submit" id="searchsubmit" name="search" value=<spring:message code="search" text="default text" />
							class="btn btn-primary" />

					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer"
						href="${pageContext.request.contextPath}/computer/add"><spring:message code="add" text="default text" />
						</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();"><spring:message code="edit" text="default text" /></a>
				</div>
			</div>

		</div>

		<form id="deleteForm"
			action="${pageContext.request.contextPath}/computer/delete" method="POST">
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


						<th><spring:message code="computer" text="default text" /></th>
						<th><spring:message code="intro" text="default text" /></th>
						<!-- Table header for Discontinued Date -->
						<th><spring:message code="discon" text="default text" /></th>
						<!-- Table header for Company -->
						<th><spring:message code="company" text="default text" /></th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->

				<tbody id="results">
					<c:forEach items="${computers}" var="s">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value=${s.id}></td>
							<td><a
								href="${pageContext.request.contextPath}/computer/edit?id=${s.id}&name=${s.name}&introduced=${s.introduced}&discontinued=${s.discontinued}&brand=${s.brand}">${s.name}</a>
							</td>
							<td>${s.introduced}</td>
							<td>${s.discontinued}</td>
							<td>${s.brand}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>


	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
			<c:if test="${page.current > 1}" >
				<li><a href="dashboard?current=${page.current - 1}" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
				</a></li>
			</c:if>
				<c:forEach begin="1" end="${infos.pages}" varStatus="loop">
					<li><a href="dashboard?current=${loop.index}">${loop.index}</a></li>
				</c:forEach>
				<c:if test="${page.current < infos.pages}" >
				<li><a href="dashboard?current=${page.current + 1}" aria-label="Next"> <span
						aria-hidden="true">&raquo;</span>
				</a></li>
				</c:if>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<a href="dashboard?limit=10" class="btn btn-default">10</a> <a
					href="dashboard?limit=50" class="btn btn-default">50</a> <a
					href="dashboard?limit=100" class="btn btn-default">100</a>
			</div>
		</div>

	</footer>
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>

</body>
</html>