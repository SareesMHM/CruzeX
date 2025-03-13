package CruzeX.webapp.Controller;

import CruzeX.webapp.Model.Customer;
import CruzeX.webapp.Service.CustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/CustomerLoginController")  // You can rename this to /UserLoginController if desired
public class CustomerLoginController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        try {
            // Admin login
            if ("Admin".equalsIgnoreCase(username) && "Admin".equals(password)) {
                session.setAttribute("Admin", username);
                request.setAttribute("successMessage", "Login successfully");

//                response.sendRedirect("AdminHomePage.jsp");
                request.getRequestDispatcher("AdminHomePage.jsp").forward(request, response);
                return;
            }

            // Driver login
            if ("driver".equalsIgnoreCase(username) && "123456".equals(password)) {
                session.setAttribute("driver", username);
                request.setAttribute("successMessage", "Login successfully");

//                response.sendRedirect("DriverHomePage.jsp");
                 request.getRequestDispatcher("DriverHomePage.jsp").forward(request, response);
                return;
            }

            // Try customer login (via DB)
            Customer customer = CustomerService.getCustomerServiceInstance()
                    .validateCustomerCredentials(username, password);
            String message = "";

            if (customer != null && customer.getCustomerID() > 0) {
                session.setAttribute("customerId", customer.getCustomerID());
                session.setAttribute("customerName", customer.getCustomerFullName());
                session.setAttribute("customerEmail", customer.getCustomerEmail());
                
                 message = "Customer " + " has been successfully registered!";
                request.setAttribute("message", message);

//                response.sendRedirect("CustomerHomePage.jsp");
                request.getRequestDispatcher("CustomerHomePage.jsp").forward(request, response);
            } else {
                // Invalid customer credentials
                request.setAttribute("errorMessage", "Invalid username or password.");
                request.getRequestDispatcher("Login-Customer.jsp").forward(request, response);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An internal error occurred. Please try again.");
            request.getRequestDispatcher("Login-Customer.jsp").forward(request, response);
        }
    }
}