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

@WebServlet("/StripePaymentServlet")
public class StripePaymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");  // Ensure JSON response

        // Set Stripe API Key (Move to environment variable in production)
        Stripe.apiKey = "sk_test_51R03Ce07fEoJmqEFoNXWmSoKuHsHkbD1XfJYohp18gg8tM4FIkdLqOxZlHYMghUhhCbWIVZIuhKXkt9EJnQkfyJx00H57Y8f5N";

        // Retrieve parameters
        String token = request.getParameter("stripeToken");
        String bookingID = request.getParameter("bookingID");
        String customerID = request.getParameter("customerID");
       String fareStr = request.getParameter("amount"); // Original fare
        String taxStr = request.getParameter("tax");
        String discountStr = request.getParameter("discount");
        String totalFareStr = request.getParameter("totalFare");
        String cardholderName = request.getParameter("cardholderName");
        // Validate inputs
       if (token == null || bookingID == null || customerID == null || fareStr == null ||
            taxStr == null || discountStr == null || totalFareStr == null || cardholderName == null) {
            response.getWriter().write("{\"success\": false, \"message\": \"Missing required parameters.\"}");
            return;
        }

        try {
            // Convert amount to cents (Stripe requires smallest currency unit)
            double fare = Double.parseDouble(fareStr);
            double tax = Double.parseDouble(taxStr);
            double discount = Double.parseDouble(discountStr);
            double totalFare = Double.parseDouble(totalFareStr);
            int amountInCents = (int) (totalFare * 100);

            // Create a charge request
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", amountInCents);
            chargeParams.put("currency", "LKR");
            chargeParams.put("description", "Payment for Booking ID: " + bookingID);
            chargeParams.put("source", token);

            // Process Stripe charge
            Charge charge = Charge.create(chargeParams);

            // Store payment in the database
            Payment payment = new Payment(
                Integer.parseInt(bookingID),
                Integer.parseInt(customerID),
                fare,
                tax,
                discount,
                totalFare,
                cardholderName,
                  
                token.substring(token.length() - 4) // Store only last 4 digits
            );

            boolean paymentSuccess = PaymentService.getInstance().processPayment(payment);

            // Return JSON response
            if (paymentSuccess) {
               response.getWriter().write("{\"success\": true, \"bookingID\": \"" + bookingID + "\", \"customerID\": \"" + customerID + "\"}");
            } else {
                response.getWriter().write("{\"success\": false, \"message\": \"Failed to store payment in database.\"}");
            }

        } catch (StripeException e) {
            response.getWriter().write("{\"success\": false, \"message\": \"Payment Failed: " + e.getMessage() + "\"}");
        } catch (NumberFormatException e) {
            response.getWriter().write("{\"success\": false, \"message\": \"Invalid amount format.\"}");
        }
    }
}
