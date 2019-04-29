<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello world</h1>
	<br/> I love : 
	${label} <br/>
	<c:out value="Hello you"></c:out>
	<c:forEach items="${friends}" var="s">
		${s}
	</c:forEach>

</body>
</html>