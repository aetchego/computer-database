<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>

<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" media="screen">
<style type='text/css'>
#error {display:none;}
</style>
</head>
<body>
    <%@include file="header.jsp" %>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                <div class="alert alert-danger" role="alert" id="error"></div>
                <c:out value="${error}"/>
                    <h1><spring:message code="add" text="default text" /></h1>
                    
                 <c:url value="/computer/add" var="add"></c:url>  
                <form:form action="${add}" method="POST" modelAttribute="computer">
						<fieldset>
							<spring:bind path="name">
								<div class='form-group ${status.error?"has-error":""}'>
									<form:label path="name">${name}</form:label>
									<form:input type="text" class="form-control" path="name"
										id="computerName" placeholder="${name}" />
									<form:errors path="name" cssClass="help-block" />
								</div>
							</spring:bind>
							<spring:bind path="introduced">
								<div class='form-group ${status.error?"has-error":""}'>
									<form:label path="introduced">${introduced}</form:label>
									<form:input type="date" class="form-control" path="introduced"
										id="introduced" placeholder="${introduced}" />
									<form:errors path="introduced" cssClass="help-block" />
								</div>
							</spring:bind>
							<spring:bind path="discontinued">
								<div class='form-group ${status.error?"has-error":""}'>
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
							<input type="submit" value=<spring:message code="save" text="default text" /> class="btn btn-primary">
							<c:url var="dashboard" value="/dashboard" />
							<a href="${dashboard}" class="btn btn-default"><spring:message code="cancel" text="default text" /></a>
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