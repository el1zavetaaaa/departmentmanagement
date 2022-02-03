<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add/Edit Employees</title>
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
            <h1> Department Management App</h1>
        </div>


        <a href="<%=request.getContextPath()%>/"
           class="nav-link">Departments</a>

    </nav>
</header>

<div class="container">
    <p></p>
</div>

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${employee != null}">

            <form action="updateemployee" method="post">
                </c:if>
                <c:if test="${employee == null}">
                <form action="add" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${employee != null}">
                                Edit Employee
                            </c:if>
                            <c:if test="${employee == null}">
                                Add New Employee
                            </c:if>

                        </h2>
                    </caption>
                    <c:if test="${employee != null}">
                        <input type="hidden" name="id" value="<c:out value='${employee.id}' />"/>
                    </c:if>
                    <fieldset class="form-group">
                        <label>Email</label>
                        <p>
                            <input type="email" placeholder="email"
                                   value="<c:out value='${employee.email}' />" class="form-control"
                                   name="email" required="required"></p>
                        <p></p>
                        <label>Department Id</label>
                        <p>
                            <input type="text" pattern="[0-9]{0,1,2}+" placeholder="department id"
                                   value="<c:out value='${employee.department_id}' />" class="form-control"
                                   name="department_id" ></p>
                        <label>Salary</label>
                        <p>
                            <input type="text" pattern="[0-9]{3,4}$" placeholder="salary 100 - 1000 $"
                                   value="<c:out value='${employee.salary}' />" class="form-control"
                                   name="salary" required pattern="required"></p>
                        <label>Hire Date</label>
                        <p>
                            <input type="date"
                                   value="<c:out value='${employee.hireDate}' />" class="form-control"
                                   name="hire_date" required="required"></p>
                    </fieldset>
                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>

</body>
</html>
