package CruzeX.webapp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CruzeX.webapp.Model.Customer;

public class CustomerManager {

    //  Get Database Connector
    public DbConnector getDbConnector() {
        DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl();
        return factory.getDbConnector();
    }

    //  Get Connection to Database
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        DbConnector connector = getDbConnector();
        return connector.getDbConnection();
    }

    //  Add New Customer to Database
    public boolean addCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        int result;
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO customer (CustomerFullName, CustomerPhoneNumber, DateOfBirth, " +
                           "CustomerAddress, Gender, CustomerEmail, CustomerUsername, CustomerPassword) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customer.getCustomerFullName());
            ps.setString(2, customer.getCustomerPhoneNumber());
            ps.setString(3, customer.getDateOfBirth());
            ps.setString(4, customer.getCustomerAddress());
            ps.setString(5, customer.getGender());
            ps.setString(6, customer.getCustomerEmail());
            ps.setString(7, customer.getCustomerUsername());
            ps.setString(8, customer.getCustomerPassword());
            result = ps.executeUpdate();
            ps.close();
        }
        return result > 0;
    }

    //  Get a Specific Customer by ID
    public Customer getSpecificCustomer(int customerID) throws SQLException, ClassNotFoundException {
        Customer customer = null;
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM customer WHERE CustomerID = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = mapResultSetToCustomer(rs);
            }
            ps.close();
        }
        return customer;
    }

    //  Get All Customers
    public List<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> customerList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM customer");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                customerList.add(mapResultSetToCustomer(rs));
            }
        }
        return customerList;
    }

    //  Update Customer Information
    public boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        int result;
        try (Connection connection = getConnection()) {
            String query = "UPDATE customer SET CustomerFullName = ?, CustomerPhoneNumber = ?, DateOfBirth = ?, " +
                           "CustomerAddress = ?, Gender = ?, CustomerEmail = ?, CustomerUsername = ?, CustomerPassword = ? " +
                           "WHERE CustomerID = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, customer.getCustomerFullName());
                ps.setString(2, customer.getCustomerPhoneNumber());
                ps.setString(3, customer.getDateOfBirth());
                ps.setString(4, customer.getCustomerAddress());
                ps.setString(5, customer.getGender());
                ps.setString(6, customer.getCustomerEmail());
                ps.setString(7, customer.getCustomerUsername());
                ps.setString(8, customer.getCustomerPassword());
                ps.setInt(9, customer.getCustomerID());
                result = ps.executeUpdate();
            }
            connection.close();
        }
        return result > 0;
    }

    //  Delete a Customer
    public boolean deleteCustomer(int customerID) throws ClassNotFoundException, SQLException {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM customer WHERE CustomerID = ?")) {
            ps.setInt(1, customerID);
            return ps.executeUpdate() > 0;
        }
    }

    //  Validate Customer Login Credentials
    public Customer validateCustomerCredentials(String username, String password) throws ClassNotFoundException, SQLException {
        Customer customer = null;
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM customer WHERE CustomerUsername = ? AND CustomerPassword = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = mapResultSetToCustomer(rs);
            }
            ps.close();
        }
        return customer;
    }

    //  Check if Email Already Exists
    public boolean isEmailExists(String email) throws ClassNotFoundException, SQLException {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM customer WHERE CustomerEmail = ?")) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    //  Check if Username Already Exists
    public boolean isUsernameExists(String username) throws ClassNotFoundException, SQLException {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM customer WHERE CustomerUsername = ?")) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    //  Helper Method: Map ResultSet to Customer Object
    private Customer mapResultSetToCustomer(ResultSet rs) throws SQLException {
        return new Customer(
            rs.getInt("CustomerID"), 
            rs.getString("CustomerFullName"), 
            rs.getString("CustomerPhoneNumber"), 
            rs.getString("DateOfBirth"), 
            rs.getString("CustomerAddress"), 
            rs.getString("Gender"), 
            rs.getString("CustomerEmail"), 
            rs.getString("CustomerUsername"), 
            rs.getString("CustomerPassword")
        );
    }
}