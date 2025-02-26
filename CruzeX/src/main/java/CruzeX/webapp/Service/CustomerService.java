package CruzeX.webapp.Service;

import CruzeX.webapp.Model.Customer;
import CruzeX.webapp.Dao.CustomerManager;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {

    private static CustomerService customerServiceObj;

    private CustomerService() {
    }

    public static synchronized CustomerService getCustomerServiceInstance() {
        if (customerServiceObj == null) {
            customerServiceObj = new CustomerService();
        }
        return customerServiceObj;
    }

    private CustomerManager getCustomerManager() {
        return new CustomerManager();
    }

    public boolean registerCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        return getCustomerManager().addCustomer(customer);
    }

    public Customer getSpecificCustomer(int customerID) throws ClassNotFoundException, SQLException {
        return getCustomerManager().getSpecificCustomer(customerID);
    }

    public List<Customer> getAllCustomers() throws ClassNotFoundException, SQLException {
        return getCustomerManager().getAllCustomers();
    }

    public boolean editCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        return getCustomerManager().updateCustomer(customer);
    }

    public boolean deleteCustomer(int customerID) throws ClassNotFoundException, SQLException {
        return getCustomerManager().deleteCustomer(customerID);
    }

    public boolean validateCustomerCredentials(String username, String password) throws ClassNotFoundException, SQLException {
        return getCustomerManager().validateCustomerCredentials(username, password);
    }

    public boolean deleteTheCustomer(int customerID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean editTheCustomer(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
