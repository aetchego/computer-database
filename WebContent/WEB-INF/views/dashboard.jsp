<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
                ${computerNumber} Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="search" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="name" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" name="searchComputer" value="Filter by computer" class="btn btn-primary" />
                        <input type="submit" id="searchsubmit" name="searchCompany" value="Filter by company" class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="${pageContext.request.contextPath}/addComputer">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
<!--            <div class="btn-group" role="group" aria-label="Button group with nested dropdown"> -->
<!--   <button type="button" class="btn btn-secondary">1</button> -->
<!--   <button type="button" class="btn btn-secondary">2</button> -->

<!--   <div class="btn-group" role="group"> -->
<!--     <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
<!--       Dropdown -->
<!--     </button> -->
<!--     <div class="dropdown-menu" aria-labelledby="btnGroupDrop1" color=red> -->
<!--       <a class="dropdown-item" href="#">Dropdown link</a> -->
<!--       <a class="dropdown-item" href="#">Dropdown link</a> -->
<!--     </div> -->
<!--   </div> -->
<!-- </div> -->
            
            
            
            
        </div>

        <form id="deleteForm" action="${pageContext.request.contextPath}/delete" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span> 
                        </th>
                        

                        <th>
                            Computer name
                        </th>
                        <th>
                            Introduced date
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                
                <tbody id="results">
                <c:forEach items="${computers}" var="s">
                    <tr>
                        <td class="editMode">
                            <input type="checkbox" name="cb" class="cb" value=${s.id}>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/editComputer?id=${s.id}&name=${s.name}&in=${s.introduced}&out=${s.discontinued}&company=${s.brand}">${s.name}</a>
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
                <li>
                    <a href="dashboard?previous=true" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
              </li>
              <c:forEach begin="1" end="${pageNumber}" varStatus="loop">
              <li><a href="dashboard?pageAt=${loop.index}">${loop.index}</a></li>
              </c:forEach>
         
              <li>
                <a href="dashboard?next=true" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
		
        <div class="btn-group btn-group-sm pull-right" role="group" >
            <a href="dashboard?size=10" class="btn btn-default">10</a>
            <a href="dashboard?size=50" class="btn btn-default">50</a>
            <a href="dashboard?size=100" class="btn btn-default">100</a>
        </div>
       </div>

    </footer>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>

</body>
</html>