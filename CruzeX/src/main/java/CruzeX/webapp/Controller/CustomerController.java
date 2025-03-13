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

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CustomerService customerService = CustomerService.getCustomerServiceInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        

        if (type != null && type.equals("specific")) {
            getSpecificCustomer(request, response, customerService);
        } else {
            getAllCustomers(request, response, customerService);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        CustomerService customerService = CustomerService.getCustomerServiceInstance();

        if (type != null) {
            switch (type) {
                case "update":
                    updateCustomer(request, response, customerService);
                    break;
                case "add":
                    addCustomer(request, response, customerService);
                    break;
                case "delete":
                    deleteCustomer(request, response, customerService);
                    break;
                case "login":
                    loginCustomer(request, response, customerService);
                    break;
                case "logout":
                    logoutCustomer(request, response);
                    break;
            }
        }
    }

    private void getAllCustomers(HttpServletRequest request, HttpServletResponse response, CustomerService service) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String message = "";
        List<Customer> customerList;

        try {
            customerList = service.getAllCustomers();
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
            customerList = new ArrayList<>();
        }

        session.setAttribute("customerList", customerList);
        session.setAttribute("message", message);
        response.sendRedirect("CustomerDashboard.jsp");
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

        RequestDispatcher rd = request.getRequestDispatcher("SearchCustomer.jsp");
        rd.forward(request, response);
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response, CustomerService service) throws ServletException, IOException {
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        String customerFullName = request.getParameter("customerFullName");
        String customerPhoneNumber = request.getParameter("customerPhoneNumber");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String customerAddress = request.getParameter("customerAddress");
        String gender = request.getParameter("gender");
        String customerEmail = request.getParameter("customerEmail");
        String customerUsername = request.getParameter("customerUsername");
        String customerPassword = request.getParameter("customerPassword");

        Customer customer = new Customer(customerFullName, customerPhoneNumber, dateOfBirth, customerAddress, gender, customerEmail, customerUsername, customerPassword);
       String message = "";
        try {
            boolean result = customerService.editCustomer(customer);
            message = result ? "Customer " + customerID + " has been successfully updated!" : "Failed to update the customer! Customer ID: " + customerID;
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }
       
        

//customer.setCustomerID(customerID);

//        boolean result;
//        String message = "";
//        try {
//            result = service.editCustomer(customer);
//            if (result) {
//                message = "Customer " + customerID + " has been successfully updated!";
//            } else {
//                message = "Failed to update customer! Customer ID: " + customerID;
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            message = e.getMessage();
//        }


        request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("SearchCustomer.jsp");
        rd.forward(request, response);
    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response, CustomerService service) throws ServletException, IOException {
        String customerFullName = request.getParameter("customerFullName");
        String customerPhoneNumber = request.getParameter("customerPhoneNumber");
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
                message = "Customer " + customerFullName + " has been successfully registered!";
            } else {
                message = "Failed to register customer!";
            }
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }

        request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
        rd.forward(request, response);
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response, CustomerService service) throws ServletException, IOException {
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        boolean result;
        String message = "";

        try {
            result = service.deleteCustomer(customerID);
            if (result) {
                message = "Customer with ID " + customerID + " has been successfully deleted!";
            } else {
                message = "Failed to delete customer with ID: " + customerID;
            }
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }

        HttpSession session = request.getSession();
        session.setAttribute("message", message);
        response.sendRedirect("CustomerDashboard.jsp");
    }

    private void loginCustomer(HttpServletRequest request, HttpServletResponse response, CustomerService service) throws ServletException, IOException {
        String username = request.getParameter("customerUsername");
        String password = request.getParameter("customerPassword");

        try {
            Customer customer = service.validateCustomerCredentials(username, password);
            if (customer.getCustomerID() > 0) {
                HttpSession session = request.getSession();
                session.setAttribute("loggedInCustomer", customer);
                response.sendRedirect("CustomerDashboard.jsp");
            } else {
                request.setAttribute("message", "Invalid Username or Password");
                RequestDispatcher rd = request.getRequestDispatcher("CustomerLogReg.jsp");
                rd.forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            request.setAttribute("message", e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("CustomerLogReg.jsp");
            rd.forward(request, response);
        }
    }

    private void logoutCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("CustomerLogReg.jsp");
    }
}