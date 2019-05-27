<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/font-awesome.css"
	rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet" media="screen">
<style type='text/css'>
#error {
	display: none;
}
</style>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		 <div class="container">
		<div class="row">
		<div class="col-sm-9">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard"> 
			<spring:message code="title" text="default text" /> </a>

		</div>
		<div class="col-sm">
		<a class="navbar-brand" href="?lang=en"><spring:message code="english" text="default text" /> |</a><a class="navbar-brand" href="?lang=fr"><spring:message code="french" text="default text" /></a>
		</div>
		</div>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="alert alert-danger" role="alert" id="error"></div>
					<c:out value="${error}" />
					<div class="label label-default pull-right">id:
						${computer.id}</div>
					<h1><spring:message code="edit_page" text="default text" /></h1>

					<c:url value="/computer/edit" var="edit"></c:url>
					<form:form action="${edit}" method="POST" modelAttribute="computer">
						<form:input type="hidden" path="id" id="id" />
						<fieldset>
							<spring:bind path="name">
								<div class='form-group ${status.error?"has-error":""}'>
									<%--                                 <spring:message code="computer.name" var="name"/> --%>
									<form:label path="name">${name}</form:label>
									<form:input type="text" class="form-control" path="name"
										id="computerName" placeholder="${name}" />
									<form:errors path="name" cssClass="help-block" />
								</div>
							</spring:bind>
							<spring:bind path="introduced">
								<div class='form-group ${status.error?"has-error":""}'>
									<%--                                 <spring:message code="computer.introduced" var="introduced"/> --%>
									<form:label path="introduced">${introduced}</form:label>
									<form:input type="date" class="form-control" path="introduced"
										id="introduced" placeholder="${introduced}" />
									<form:errors path="introduced" cssClass="help-block" />
								</div>
							</spring:bind>
							<spring:bind path="discontinued">
								<div class='form-group ${status.error?"has-error":""}'>
									<%--                                 <spring:message code="computer.discontinued" var="discontinued"/> --%>
									<form:label path="discontinued">${discontinued}</form:label>
									<form:input type="date" class="form-control"
										path="discontinued" id="discontinued"
										placeholder="${discontinued}" />
									<form:errors path="discontinued" cssClass="help-block" />
								</div>
							</spring:bind>
							<spring:bind path="brand">
								<div class='form-group ${status.error?"has-error":""}'>
									<form:label path="brand"><spring:message code="company" text="default text" /></form:label>
									<form:select class="form-control" path="brand" id="brand">
										<form:option value="">---</form:option>
										<form:options items="${companies}" itemLabel="name"
											itemValue="name" />
									</form:select>
									<form:errors path="brand" cssClass="help-block" />
								</div>
							</spring:bind>
						</fieldset>
						<div class="actions pull-right">
							<%--                         <spring:message code="form.edit" var="editButton"/> --%>
							<input type="submit" value="Edit" class="btn btn-primary">
							or
							<c:url var="dashboard" value="/dashboard" />
							<a href="${dashboard}" class="btn btn-default">Cancel <%--                         <spring:message code="form.cancel"/> --%>
							</a>
						</div>
					</form:form>

				</div>
			</div>
		</div>
	</section>
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/addComputer.js"></script>
</body>
</html>