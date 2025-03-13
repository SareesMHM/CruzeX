package CruzeX.webapp.Controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import CruzeX.webapp.Model.Payment;
import CruzeX.webapp.Service.PaymentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/PaymentController")
public class PaymentController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Stripe.apiKey = System.getenv("STRIPE_SECRET_KEY"); // Securely store API key in environment variable

        String token = request.getParameter("stripeToken");
        String bookingID = request.getParameter("bookingID");
        String customerID = request.getParameter("customerID");
        String amountStr = request.getParameter("amount");
        String cardholderName = request.getParameter("cardholderName");
        String taxStr = request.getParameter("tax");
        String discountStr = request.getParameter("discount");
        String totalFareStr = request.getParameter("totalFare");

        response.setContentType("application/json");

        if (token == null || bookingID == null || amountStr == null || customerID == null || cardholderName == null ||taxStr == null || discountStr == null || totalFareStr == null) {
            response.getWriter().write("{\"success\": false, \"message\": \"Invalid request parameters.\"}");
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr) * 100; // Convert amount to cents
            
            // Create charge parameters
            Map<String, Object> params = new HashMap<>();
            params.put("amount", (int) amount);
            params.put("currency", "LKR");
            params.put("description", "Payment for Booking ID: " + bookingID);
            params.put("source", token);

            Charge charge = Charge.create(params); // Process payment via Stripe

            // Store payment details in the database
            Payment payment = new Payment(
                    Integer.parseInt(bookingID),
                    Integer.parseInt(customerID),
                    Double.parseDouble(taxStr),
                    Double.parseDouble(discountStr),
                    Double.parseDouble(amountStr),
                    Double.parseDouble(totalFareStr),
                    
                    
                    cardholderName,
                    token.substring(token.length() - 4) // Store only last 4 digits of the token
            );

            boolean paymentSuccess = PaymentService.getInstance().processPayment(payment);

            if (paymentSuccess) {
                response.sendRedirect("Billing.jsp?bookingID=" + bookingID + "&customerID=" + customerID + "&fare="+amountStr +"&tax="+taxStr+"&discount="+discountStr+"&totalFare=" + totalFareStr + "&paymentStatus=Successful");
            } else {
                response.getWriter().write("{\"success\": false, \"message\": \"Failed to store payment in database.\"}");
            }

        } catch (StripeException e) {
            response.getWriter().write("{\"success\": false, \"message\": \"" + e.getMessage() + "\"}");
        } catch (NumberFormatException e) {
            response.getWriter().write("{\"success\": false, \"message\": \"Invalid amount format.\"}");
        }
    }
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.sendRedirect("PaymentDashboard.jsp"); // Or a custom "Invalid Access" page
}
}