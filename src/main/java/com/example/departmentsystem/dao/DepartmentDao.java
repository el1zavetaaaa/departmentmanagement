package com.example.departmentsystem.dao;

import com.example.departmentsystem.model.Department;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;


public class DepartmentDao {

    Properties properties = loadProperties();
    String url = properties.getProperty("url");


    public DepartmentDao() {
    }


    private static final String INSERT_DEPARTMENTS = "INSERT INTO departments" + "  (name) VALUES "
            + " (?);";

    private static final String SELECT_DEPARTMENT_BY_ID = "select id,name from departments where id =?";
    private static final String SELECT_ALL_DEPARTMENTS = "select * from departments";
    private static final String DELETE_DEPARTMENTS = "delete from departments where id = ?;";
    private static final String UPDATE_DEPARTMENTS = "update departments set name = ? where id = ?;";

    public Set<Department> selectAllDepartments() {

        Set<Department> departments = new HashSet<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DEPARTMENTS);) {

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                departments.add(new Department(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public void insertDepartment(Department department) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DEPARTMENTS);) {

            preparedStatement.setString(1, department.getName());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteDepartment(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DEPARTMENTS);) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }

        return rowDeleted;
    }

    public Department selectDepartment(int id) {
        Department department = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEPARTMENT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");

                department = new Department(id, name);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return department;
    }

    public boolean updateDepartment(Department department) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEPARTMENTS);) {


            preparedStatement.setString(1, department.getName());
            preparedStatement.setInt(2, department.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }


    protected static Properties loadProperties() {

        Properties props = new Properties();

        try (InputStream input = DepartmentDao.class.getResourceAsStream("/jdbc.properties")) {
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
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
