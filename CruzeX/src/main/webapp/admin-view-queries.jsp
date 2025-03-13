<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, CruzeX.webapp.Model.Query" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin - View Queries</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
      <img style="border-radius:50%;width: 70px;height: 70px; margin:0 10px 0 50px " alt="" src="img/x (4).webp">
    <a class="navbar-brand fw-bold" href="#"> MCC</a>
    <div class="collapse navbar-collapse">
      <div class="navbar-nav">
        <a class="nav-link active" href="AdminHomePage.jsp">Dashboard</a>
        <a class="nav-link" href="VehicleController">Manage Vehicle</a>
        <a class="nav-link" href="DriverController">Manage Driver</a>
        <a class="nav-link" href="CustomerController">Manage Customer</a>
        <a class="nav-link" href="QueryController?type=getAllQueries">View Queries</a>
      </div>
    </div>
  </div>
</nav>

<div class="container mt-5">
    <h2 class="mb-4">User Queries</h2>

    <c:choose>
        <c:when test="${not empty sessionScope.queryList}">
            <div class="table-responsive">
                <table class="table table-bordered table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th>#</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Message</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="count" value="0"/>
                        <c:forEach var="q" items="${sessionScope.queryList}" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td>${q.name}</td>
                                <td>${q.email}</td>
                                <td>${q.message}</td>
                                <td>
                                    <form action="QueryController" method="post">
                                        <input type="hidden" name="type" value="delete">
                                        <input type="hidden" name="email" value="${q.email}">
                                        <button class="btn btn-danger btn-sm" onclick="return confirm('Delete this query?')">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info">No queries submitted yet.</div>
        </c:otherwise>
    </c:choose>

</div>

</body>
</html>
