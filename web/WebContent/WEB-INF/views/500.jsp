<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
	<title>Computer Database</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- Bootstrap -->
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" media="screen">
	<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" media="screen">
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
			<div class="alert alert-danger">
				<spring:message code="500" text="default text" />
				<br/>
				<!-- stacktrace -->
			</div>
		</div>
	</section>

	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>

</body>
</html>