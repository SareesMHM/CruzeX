package CruzeX.webapp.Controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import CruzeX.webapp.Service.CustomerService;

@WebServlet("/ForgotPasswordController")
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        CustomerService service = CustomerService.getCustomerServiceInstance();
        String message;

        try {
            boolean exists = service.doesEmailExist(email);

            if (exists) {
                // This is where you would generate and send a reset link or code.
                // For now, just return a success message.
                message = "Password reset instructions have been sent to your email.";
            } else {
                message = "No account found with that email.";
            }

        } catch (ClassNotFoundException | SQLException e) {
            message = "Error: " + e.getMessage();
        }

        request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("ForgotPassword.jsp");
        rd.forward(request, response);
    }
}