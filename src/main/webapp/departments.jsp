<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Department Management App</title>
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
          <h2 > Department Management App</h2>
        </div>

    </nav>
</header>

<div class="container">

    <p>  <h3 align="center">List of Departments</h3> </p>
    <p>
        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
            New Department</a>
    </p>

</div>

<div class="container">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Name</th>
            <th>Edit</th>
            <th>Delete</th>
            <th>Employees</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="department" items="${requestScope.listDepartment}">
            <tr>

                <td><c:out value="${department.name}"/></td>
                <td><a href="edit?id=<c:out value='${department.id}' />">Edit</a></td>
                <td><a href="delete?id=<c:out value='${department.id}' />">Delete</a></td>
                <td><a href="employees?id=<c:out value='${department.id}' />">Employees</a></td>

            </tr>
        </c:forEach>

        </tbody>

    </table>
</div>


</body>
</html>
