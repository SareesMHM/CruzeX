package CruzeX.webapp.Service;

import CruzeX.webapp.Dao.CustomerManager;
import CruzeX.webapp.Model.Customer;
import java.sql.SQLException;
import java.util.List;

public class CustomerService {
    
    private static CustomerService customerServiceObj;

    //  Private Constructor to Prevent Direct Instantiation
    private CustomerService() {}

    //  Singleton Pattern: Get Single Instance of Service
    public static synchronized CustomerService getCustomerServiceInstance() {
        if (customerServiceObj == null) {
            customerServiceObj = new CustomerService();
        }
        return customerServiceObj;
    }

    //  Get CustomerManager Instance
    private CustomerManager getCustomerManager() {
        return new CustomerManager();
    }

    //  Register New Customer (With Email & Username Validation)
    public boolean registerCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        CustomerManager manager = getCustomerManager();

        // Check if Email or Username Already Exists
        if (manager.isEmailExists(customer.getCustomerEmail())) {
            throw new SQLException("Email is already registered!");
        }
        if (manager.isUsernameExists(customer.getCustomerUsername())) {
            throw new SQLException("Username is already taken!");
        }
        return getCustomerManager().addCustomer(customer);

        // Add New Customer
//        return manager.addCustomer(customer);

    }

    //  Get a Specific Customer by ID
    public Customer getSpecificCustomer(int customerID) throws ClassNotFoundException, SQLException {
        return getCustomerManager().getSpecificCustomer(customerID);
    }

    //  Get All Customers
    public List<Customer> getAllCustomers() throws ClassNotFoundException, SQLException {
        return getCustomerManager().getAllCustomers();
    }

    //  Update Customer Information
    public boolean editCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        return getCustomerManager().updateCustomer(customer);
    }

    //  Delete Customer by ID
    public boolean deleteCustomer(int customerID) throws ClassNotFoundException, SQLException {
        return getCustomerManager().deleteCustomer(customerID);
    }

    //  Validate Customer Login Credentials
    public Customer validateCustomerCredentials(String username, String password) throws ClassNotFoundException, SQLException {
        return getCustomerManager().validateCustomerCredentials(username, password);
    }
     // Email exists (used for forgot password)
    public boolean doesEmailExist(String email) throws SQLException, ClassNotFoundException {
        return getCustomerManager().isEmailExists(email);
    }
}