package CruzeX.webapp.Controller;

import CruzeX.webapp.Model.Customer;
import CruzeX.webapp.Service.CustomerService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CustomerLoginController")
public class CustomerLoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        try {
            // Validate customer credentials
            Customer customer = CustomerService.getCustomerServiceInstance().validateCustomerCredentials(username, password);

            if (customer != null && customer.getCustomerID() > 0) {
                // ✅ Store customer info in session
                session.setAttribute("customerId", customer.getCustomerID());
                session.setAttribute("customerName", customer.getCustomerFullName());
                session.setAttribute("customerEmail", customer.getCustomerEmail());
                
                // ✅ Redirect to the customer's homepage after login
                response.sendRedirect("CustomerHomePage.jsp");
            } else {
                // ❌ Invalid credentials, redirect back with an error message
                request.setAttribute("errorMessage", "Invalid username or password");
                RequestDispatcher rd = request.getRequestDispatcher("Login-Customer.jsp");
                rd.forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Logging the exception (Consider logging it to a file in production)
            
            // ❌ Redirect to error page if something goes wrong
            request.setAttribute("errorMessage", "An error occurred while processing your request. Please try again.");
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp"); 
            rd.forward(request, response);
        }
    }
}