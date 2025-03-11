package CruzeX.webapp.Controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CruzeX.webapp.Model.Customer;
import CruzeX.webapp.Service.CustomerService;

@WebServlet("/RegisterCustomerController")
public class RegisterCustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        CustomerService customerService = CustomerService.getCustomerServiceInstance();

        if (type != null && type.equals("add")) {
            addCustomer(request, response, customerService);
        }
    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response,CustomerService service) throws ServletException, IOException {
        String customerFullName = request.getParameter("customerFullName");
        String customerPhoneNumber = request.getParameter("customerPhoneNumber");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String customerAddress = request.getParameter("customerAddress");
        String gender = request.getParameter("gender");
        String customerEmail = request.getParameter("customerEmail");
        String customerUsername = request.getParameter("customerUsername");
        String customerPassword = request.getParameter("customerPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        //  Validate Password Match
//        if (!customerPassword.equals(confirmPassword)) {
//            request.setAttribute("message", "Passwords do not match!");
//            RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
//            rd.forward(request, response);
//            return;
//        }

        Customer customer = new Customer(customerFullName, customerPhoneNumber, dateOfBirth, 
                                         customerAddress, gender, customerEmail, 
                                         customerUsername, customerPassword);

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
        RequestDispatcher rd = request.getRequestDispatcher("Login-Customer.jsp");
        rd.forward(request, response);
    }
}