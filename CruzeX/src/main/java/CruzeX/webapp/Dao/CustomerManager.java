package CruzeX.webapp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import CruzeX.webapp.Model.Customer;
import CruzeX.webapp.Model.Customer;
import javax.servlet.http.HttpSession;

public class CustomerManager {

    public DbConnector getDbConnector() {
        DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl();
        return factory.getDbConnector();
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        DbConnector connector = getDbConnector();
        return connector.getDbConnection();
    }

    public boolean addCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        int result;
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO customer (CustomerFullName, CustomerPhoneNumber, DateOfBirth, CustomerAddress, Gender, CustomerEmail, CustomerUsername, CustomerPassword) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customer.getCustomerFullName());
            ps.setInt(2, customer.getCustomerPhoneNumber());
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

    public Customer getSpecificCustomer(int customerID) throws SQLException, ClassNotFoundException {
        Customer customer;
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM customer WHERE CustomerID = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            customer = new Customer();
            if (rs.next()) {
                customer.setCustomerID(rs.getInt("CustomerID"));
                customer.setCustomerFullName(rs.getString("CustomerFullName"));
                customer.setCustomerPhoneNumber(rs.getInt("CustomerPhoneNumber"));
                customer.setDateOfBirth(rs.getString("DateOfBirth"));
                customer.setCustomerAddress(rs.getString("CustomerAddress"));
                customer.setGender(rs.getString("Gender"));
                customer.setCustomerEmail(rs.getString("CustomerEmail"));
                customer.setCustomerUsername(rs.getString("CustomerUsername"));
                customer.setCustomerPassword(rs.getString("CustomerPassword"));
            }   ps.close();
        }
        return customer;
    }

    public List<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        List<Customer> customerList = new ArrayList<>();

        String query = "SELECT * FROM customer";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Customer customer = new Customer();
            customer.setCustomerID(rs.getInt("CustomerID"));
            customer.setCustomerFullName(rs.getString("CustomerFullName"));
            customer.setCustomerPhoneNumber(rs.getInt("CustomerPhoneNumber"));
            customer.setDateOfBirth(rs.getString("DateOfBirth"));
            customer.setCustomerAddress(rs.getString("CustomerAddress"));
            customer.setGender(rs.getString("Gender"));
            customer.setCustomerEmail(rs.getString("CustomerEmail"));
            customer.setCustomerUsername(rs.getString("CustomerUsername"));
            customer.setCustomerPassword(rs.getString("CustomerPassword"));
            customerList.add(customer);
        }

        st.close();
        connection.close();
        return customerList;
    }

    public boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        String query = "UPDATE customer SET CustomerFullName = ?, CustomerPhoneNumber = ?, DateOfBirth = ?, " +
                "CustomerAddress = ?, Gender = ?, CustomerEmail = ?, CustomerUsername = ?, CustomerPassword = ? " +
                "WHERE CustomerID = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, customer.getCustomerFullName());
        ps.setInt(2, customer.getCustomerPhoneNumber());
        ps.setString(3, customer.getDateOfBirth());
        ps.setString(4, customer.getCustomerAddress());
        ps.setString(5, customer.getGender());
        ps.setString(6, customer.getCustomerEmail());
        ps.setString(7, customer.getCustomerUsername());
        ps.setString(8, customer.getCustomerPassword());
        ps.setInt(9, customer.getCustomerID());

        int result = ps.executeUpdate();
        ps.close();
        connection.close();
        return result > 0;
    }

    public boolean deleteCustomer(int customerID) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        String query = "DELETE FROM customer WHERE CustomerID = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, customerID);

        int result = ps.executeUpdate();
        ps.close();
        connection.close();
        return result > 0;
    }
    
    public Customer validateCustomerCredentials(String username, String password) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        Customer customer = new Customer();
        String query = "SELECT * FROM customer WHERE CustomerUsername = ? AND CustomerPassword = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
//        boolean isValid = rs.next(); // If result set has next, it means credentials are valid
        while (rs.next()) {
            customer.setCustomerID(rs.getInt("CustomerID"));
            customer.setCustomerFullName(rs.getString("CustomerFullName"));
            customer.setCustomerPhoneNumber(rs.getInt("CustomerPhoneNumber"));
            customer.setDateOfBirth(rs.getString("DateOfBirth"));
            customer.setCustomerAddress(rs.getString("CustomerAddress"));
            customer.setGender(rs.getString("Gender"));
            customer.setCustomerEmail(rs.getString("CustomerEmail"));
            customer.setCustomerUsername(rs.getString("CustomerUsername"));
            customer.setCustomerPassword(rs.getString("CustomerPassword"));
                }
        ps.close();
        connection.close();
        return customer;
    }
}
