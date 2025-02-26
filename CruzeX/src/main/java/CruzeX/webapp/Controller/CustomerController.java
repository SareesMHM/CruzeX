package CruzeX.webapp.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CruzeX.webapp.Model.Customer;
import CruzeX.webapp.Service.CustomerService;

@WebServlet("/customerController")
public class CustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        CustomerService customerService = CustomerService.getCustomerServiceInstance();

        if (type != null && type.equals("specific")) {
            getSpecificCustomer(request, response, customerService);
        } else {
            getAllCustomers(request, response, customerService);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        CustomerService customerService = CustomerService.getCustomerServiceInstance();

        if (type != null && type.equals("update")) {
            updateCustomer(request, response, customerService);
        } else if (type != null && type.equals("add")) {
            addCustomer(request, response, customerService);
        } else if (type != null && type.equals("delete")) {
            deleteCustomer(request, response, customerService);
        }
    }

    private void getAllCustomers(HttpServletRequest request, HttpServletResponse response, CustomerService service) throws ServletException, IOException {
        String message = "";
        List<Customer> customerList;

        try {
            customerList = service.getAllCustomers();
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
            customerList = new ArrayList<>();
        }

        request.setAttribute("message", message);
        request.setAttribute("customerList", customerList);

        RequestDispatcher rd = request.getRequestDispatcher("CustomerDashboard.jsp");
        rd.forward(request, response);
    }

    private void getSpecificCustomer(HttpServletRequest request, HttpServletResponse response, CustomerService service) throws ServletException, IOException {
        String customerIdStr = request.getParameter("customerID");
        int customerID;
        Customer customer;
        String message = "";

        if (customerIdStr != null && !customerIdStr.isEmpty()) {
            try {
                customerID = Integer.parseInt(customerIdStr);
                customer = service.getSpecificCustomer(customerID);
            } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
                message = e.getMessage();
                customer = new Customer();
            }
        } else {
            message = "Customer ID is missing or invalid.";
            customer = new Customer();
        }

        request.setAttribute("customer", customer);
        request.setAttribute("message", message);

        RequestDispatcher rd = request.getRequestDispatcher("Search-Customer-Details.jsp");
        rd.forward(request, response);
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response, CustomerService service) throws ServletException, IOException {
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        String customerFullName = request.getParameter("customerFullName");
        int customerPhoneNumber = Integer.parseInt(request.getParameter("customerPhoneNumber"));
        String dateOfBirth = request.getParameter("dateOfBirth");
        String customerAddress = request.getParameter("customerAddress");
        String gender = request.getParameter("gender");
        String customerEmail = request.getParameter("customerEmail");
        String customerUsername = request.getParameter("customerUsername");
        String customerPassword = request.getParameter("customerPassword");

        Customer customer = new Customer(customerFullName, customerPhoneNumber, dateOfBirth, customerAddress, gender, customerEmail, customerUsername, customerPassword);
        customer.setCustomerID(customerID);

        boolean result;
        String message = "";
        result = service.editTheCustomer(customer);
        if (result) {
            message = "Customer " + customerID + " has been successfully updated!";
        } else {
            message = "Failed to update the customer! Customer ID: " + customerID;
        }

        request.setAttribute("message", message);

        RequestDispatcher rd = request.getRequestDispatcher("Search-Customer-Details.jsp");
        rd.forward(request, response);
    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response, CustomerService service) throws ServletException, IOException {
        String customerFullName = request.getParameter("customerFullName");
        int customerPhoneNumber = Integer.parseInt(request.getParameter("customerPhoneNumber"));
        String dateOfBirth = request.getParameter("dateOfBirth");
        String customerAddress = request.getParameter("customerAddress");
        String gender = request.getParameter("gender");
        String customerEmail = request.getParameter("customerEmail");
        String customerUsername = request.getParameter("customerUsername");
        String customerPassword = request.getParameter("customerPassword");

        Customer customer = new Customer(customerFullName, customerPhoneNumber, dateOfBirth, customerAddress, gender, customerEmail, customerUsername, customerPassword);

        boolean result;
        String message = "";
        try {
            result = service.registerCustomer(customer);
            if (result) {
                message = "Customer " + customerFullName + " has been successfully added!";
            } else {
                message = "Failed to add the customer!";
            }
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }

        request.setAttribute("message", message);

        RequestDispatcher rd = request.getRequestDispatcher("Add-Customer-Details.jsp");
        rd.forward(request, response);
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response, CustomerService service) throws ServletException, IOException {
        int customerID = Integer.parseInt(request.getParameter("customerID"));

        boolean result;
        String message = "";
        result = service.deleteTheCustomer(customerID);
        if (result) {
            message = "Customer with ID " + customerID + " has been successfully deleted!";
        } else {
            message = "Failed to delete the customer with ID: " + customerID;
        }

        HttpSession session = request.getSession();

        try {
            List<Customer> customerList = service.getAllCustomers();
            session.setAttribute("customerList", customerList);
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }

        session.setAttribute("message", message);
        response.sendRedirect("CustomerDashboard.jsp");
    }
}
