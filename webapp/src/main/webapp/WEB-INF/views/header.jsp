<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link href="${pageContext.request.contextPath}/css/flag-icon.min.css"
	rel="stylesheet" media="screen">
<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="row">
			<div class="col-sm-10">
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/dashboard"> <spring:message
						code="title" text="default text" />
				</a>
			</div>
			<div class="col-sm">
				<a class="navbar-brand" href="?lang=en"><span
					class="flag-icon flag-icon-us"></span></a> | <a class="navbar-brand"
					href="?lang=fr"><span class="flag-icon flag-icon-fr"></span></a>
				<c:url value="/logout" var="logout" />
				<a href="${logout}" class="navbar-brand"> <span
					class="glyphicon glyphicon-lock"></span>
				</a>
			</div>
		</div>
	</div>
</header>