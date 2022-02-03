package com.example.departmentsystem.dao;

import com.example.departmentsystem.model.Employee;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmployeeDao {


    Properties properties = loadProperties();
    String url = properties.getProperty("url");

    private static final String SELECT_EMPLOYEES_BY_DEPARTMENT_ID = "select e.id, e.email, e.salary, e.hire_date " +
            "from employees e, departments d " +
            "where d.id = ? " +
            "and e.department_id = d.id";

    private static final String INSERT_EMPLOYEES = "INSERT INTO employees" + "  (email,department_id,salary,hire_date) VALUES "
            + " (?,?,?,?);";
    private static final String DELETE_EMPLOYEES = "delete from employees where id = ?;";
    private static final String SELECT_EMPLOYEE_BY_ID = "select id,email,salary,hire_date,department_id from employees where id =?";
    private static final String UPDATE_EMPLOYEES = "update employees set email = ?, department_id = ?, salary =?, hire_date = ? " +
            "where id = ?;";


    public EmployeeDao() {
    }

    public List<Employee> selectEmployeesByDepartmentId(int department_id) {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEES_BY_DEPARTMENT_ID)) {
            preparedStatement.setInt(1, department_id);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String email = rs.getString("email");
                int employee_id = rs.getInt("id");
                int salary = rs.getInt("salary");
                Date hireDate = rs.getDate("hire_date");

                Employee employee = new Employee(employee_id, email, department_id, salary, hireDate);
                employees.add(employee);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(employees);
        return employees;
    }

    public void insertEmployee(Employee employee) throws SQLException {
        System.out.println();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEES)) {
            preparedStatement.setString(1, employee.getEmail());
            preparedStatement.setInt(2, employee.getDepartment_id());
            preparedStatement.setInt(3, employee.getSalary());
            preparedStatement.setDate(4, (Date) employee.getHireDate());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteEmployee(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEES)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }

        return rowDeleted;
    }

    public Employee selectEmployee(int id) {
        Employee employee = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String email = rs.getString("email");
                int department_id = rs.getInt("department_id");
                int salary = rs.getInt("salary");
                Date hireDate = rs.getDate("hire_date");

                employee = new Employee(id, email, department_id, salary, hireDate);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employee;
    }

    public boolean updateEmployee(Employee employee) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEES)) {


            preparedStatement.setString(1, employee.getEmail());
            preparedStatement.setInt(2, employee.getDepartment_id());
            preparedStatement.setInt(3, employee.getSalary());
            preparedStatement.setDate(4, (Date) employee.getHireDate());
            preparedStatement.setInt(5, employee.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }


    protected static Properties loadProperties() {

        Properties props = new Properties();

        try (InputStream input = EmployeeDao.class.getResourceAsStream("/jdbc.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return props;
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
}
