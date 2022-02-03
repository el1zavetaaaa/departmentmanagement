<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employees</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: lightgrey">
        <div class="container col-md-5">
            <h2> Department Management App</h2>
        </div>


        <a href="<%=request.getContextPath()%>/"
           class="nav-link">Departments</a>

    </nav>
</header>

<div class="container">

    <p><h3 align="center">List of Employees</h3></p>
    <p>
        <a href="<%=request.getContextPath()%>/form" class="btn btn-success">Add
            New Employee</a>
    </p>

</div>

<div class="container">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Email</th>
            <th>Salary</th>
            <th>Hire Date</th>
            <th>Department Id</th>
            <th>Edit</th>
            <th>Delete</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="employee" items="${requestScope.allConnectedEmployees}">
            <tr>
                <td><c:out value="${employee.email}"/></td>
                <td><c:out value="${employee.salary}"/></td>
                <td><c:out value="${employee.hireDate}"/></td>
                <td><c:out value="${employee.department_id}"/></td>
                <td><a href="editemployee?id=<c:out value='${employee.id}' />">Edit</a></td>
                <td><a href="deleteemployee?id=<c:out value='${employee.id}' />">Delete</a></td>
            </tr>
        </c:forEach>

        </tbody>

    </table>
</div>

</body>
</html>
