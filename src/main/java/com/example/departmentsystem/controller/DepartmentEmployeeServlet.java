package com.example.departmentsystem.controller;

import com.example.departmentsystem.dao.DepartmentDao;
import com.example.departmentsystem.dao.EmployeeDao;
import com.example.departmentsystem.model.Department;
import com.example.departmentsystem.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;


@WebServlet("/")
public class DepartmentEmployeeServlet extends HttpServlet {

    private DepartmentDao departmentDao;

    private EmployeeDao employeeDao;

    @Override
    public void init() {
        departmentDao = new DepartmentDao();
        employeeDao = new EmployeeDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(req, resp);
                    break;
                case "/insert":
                    insertDepartment(req, resp);
                    break;
                case "/delete":
                    deleteDepartment(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp);
                    break;
                case "/update":
                    updateDepartment(req, resp);
                    break;
                case "/employees":
                    selectEmployeesByDepartmentId(req, resp);
                    break;
                case "/form":
                    showNewEmployeeForm(req, resp);
                    break;
                case "/add":
                    insertEmployee(req, resp);
                    break;
                case "/deleteemployee":
                    deleteEmployee(req, resp);
                    break;
                case "/editemployee":
                    showEditFormForEmployee(req, resp);
                    break;
                case "/updateemployee":
                    updateEmployee(req, resp);
                    break;
                default:
                    listDepartment(req, resp);
                    break;
            }

        } catch (SQLException | ParseException ex) {
            throw new ServletException(ex);
        }


    }

    private void listDepartment(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        Set<Department> listDepartment = departmentDao.selectAllDepartments();
        req.setAttribute("listDepartment", listDepartment);
        req.getRequestDispatcher("/departments.jsp").forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/department_form.jsp").forward(req, resp);
    }

    private void insertDepartment(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, SQLException {
        String name = req.getParameter("name");
        Department newDepartment = new Department();
        newDepartment.setName(name);
        departmentDao.insertDepartment(newDepartment);
        resp.sendRedirect(req.getContextPath() + "/");
    }

    private void deleteDepartment(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        departmentDao.deleteDepartment(id);
        resp.sendRedirect(req.getContextPath() + "/");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        Department existingDepartment = departmentDao.selectDepartment(id);
        req.setAttribute("department", existingDepartment);
        req.getRequestDispatcher("/department_form.jsp").forward(req, resp);

    }

    private void updateDepartment(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");

        Department updatedDepartment = new Department(id, name);
        departmentDao.updateDepartment(updatedDepartment);
        resp.sendRedirect(req.getContextPath() + "/");
    }

    private void selectEmployeesByDepartmentId(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, SQLException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        Set<Employee> allConnectedEmployees = employeeDao.selectEmployeesByDepartmentId(id);
        req.setAttribute("allConnectedEmployees", allConnectedEmployees);
        req.getRequestDispatcher("/employees.jsp").forward(req, resp);
        System.out.println(allConnectedEmployees);
    }

    private void showNewEmployeeForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/employee_form.jsp").forward(req, resp);
    }


    private void insertEmployee(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, SQLException, ParseException {
        String email = req.getParameter("email");
        final int department_id = Integer.parseInt(req.getParameter("department_id"));
        int salary = Integer.parseInt(req.getParameter("salary"));
        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("hire_date"));
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Employee newEmployee = new Employee();
        newEmployee.setEmail(email);
        newEmployee.setDepartment_id(department_id);
        newEmployee.setSalary(salary);
        newEmployee.setHireDate(sqlDate);
        employeeDao.insertEmployee(newEmployee);
        resp.sendRedirect(req.getContextPath() + "/");
    }

    private void deleteEmployee(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        employeeDao.deleteEmployee(id);
        resp.sendRedirect(req.getContextPath() + "/");
    }

    private void showEditFormForEmployee(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        Employee existingEmployee = employeeDao.selectEmployee(id);
        req.setAttribute("employee", existingEmployee);
        req.getRequestDispatcher("/employee_form.jsp").forward(req, resp);

    }

    private void updateEmployee(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, SQLException, ParseException {
        int id = Integer.parseInt(req.getParameter("id"));
        String email = req.getParameter("email");
        int department_id = Integer.parseInt(req.getParameter("department_id"));
        int salary = Integer.parseInt(req.getParameter("salary"));
        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("hire_date"));
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Employee updatedEmployee = new Employee(id, email, department_id, salary, sqlDate);
        employeeDao.updateEmployee(updatedEmployee);
        resp.sendRedirect(req.getContextPath() + "/");
    }

}
