package CruzeX.webapp.Controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import CruzeX.webapp.Model.Payment;
import CruzeX.webapp.Service.PaymentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/PaymentController")
public class PaymentController extends HttpServlet {

    private final PaymentService paymentService = PaymentService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        if ("update".equals(type)) {
            updatePayment(request, response);
        } else if ("delete".equals(type)) {
            deletePayment(request, response);
        } else if ("getAllPayments".equals(type)) {
            getAllPayments(request, response);
        } else {
            // Default: Assume Stripe Payment
            processStripePayment(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");

        if ("getAllPayments".equals(type)) {
            getAllPayments(request, response);
        } else {
            response.sendRedirect("PaymentDashboard.jsp");
        }
    }

//    private void getAllPayments(HttpServletRequest request, HttpServletResponse response) throws IOException {
//       String message ="";
//        try {
//            List<Payment> paymentList = paymentService.getAllPayments();
//            HttpSession session = request.getSession();
//            session.setAttribute("paymentList", paymentList);
//            response.sendRedirect("PaymentDashboard.jsp");
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendRedirect("PaymentDashboard.jsp?message=Failed to load payments");
//        }
//    }
    
    private void getAllPayments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    List<Payment> paymentList;
    String message = "";

    try {
        paymentList = PaymentService.getInstance().getAllPayments();
        session.setAttribute("paymentList", paymentList);
    } catch (Exception e) {
        message = "Error fetching payments: " + e.getMessage();
        session.setAttribute("message", message);
        response.sendRedirect("error.jsp?message=" + message);
        return;
    }

    response.sendRedirect("PaymentDashboard.jsp");
}

    private void updatePayment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int paymentId = Integer.parseInt(request.getParameter("paymentId"));
            int bookingId = Integer.parseInt(request.getParameter("bookingId"));
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            double fare = Double.parseDouble(request.getParameter("fare"));
            double tax = Double.parseDouble(request.getParameter("tax"));
            double discount = Double.parseDouble(request.getParameter("discount"));
            double totalFare = Double.parseDouble(request.getParameter("totalFare"));
            String cardholderName = request.getParameter("cardholderName");
            String last4Digits = request.getParameter("last4Digits");

            Payment payment = new Payment(paymentId, bookingId, customerId, fare, tax, discount, totalFare, cardholderName, last4Digits, null);

            boolean success = paymentService.updatePayment(payment);

            if (success) {
                response.sendRedirect("PaymentDashboard.jsp?message=Payment Updated Successfully");
            } else {
                response.sendRedirect("PaymentDashboard.jsp?message=Payment Update Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("PaymentDashboard.jsp?message=Error Updating Payment");
        }
    }

    private void deletePayment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int paymentId = Integer.parseInt(request.getParameter("paymentId"));
            boolean success = paymentService.deletePayment(paymentId);

            if (success) {
                response.sendRedirect("PaymentDashboard.jsp?message=Payment Deleted Successfully");
            } else {
                response.sendRedirect("PaymentDashboard.jsp?message=Payment Deletion Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("PaymentDashboard.jsp?message=Error Deleting Payment");
        }
    }

    private void processStripePayment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Stripe.apiKey = System.getenv("STRIPE_SECRET_KEY");

        String token = request.getParameter("stripeToken");
        String bookingID = request.getParameter("bookingID");
        String customerID = request.getParameter("customerID");
        String amountStr = request.getParameter("amount");
        String cardholderName = request.getParameter("cardholderName");
        String taxStr = request.getParameter("tax");
        String discountStr = request.getParameter("discount");
        String totalFareStr = request.getParameter("totalFare");

        if (token == null || bookingID == null || amountStr == null || customerID == null ||
            cardholderName == null || taxStr == null || discountStr == null || totalFareStr == null) {
            response.sendRedirect("error.jsp?message=Missing Payment Parameters");
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr) * 100;

            Map<String, Object> params = new HashMap<>();
            params.put("amount", (int) amount);
            params.put("currency", "LKR");
            params.put("description", "Payment for Booking ID: " + bookingID);
            params.put("source", token);

            Charge charge = Charge.create(params);

            Payment payment = new Payment(
                    Integer.parseInt(bookingID),
                    Integer.parseInt(customerID),
                    Double.parseDouble(amountStr),
                    Double.parseDouble(taxStr),
                    Double.parseDouble(discountStr),
                    Double.parseDouble(totalFareStr),
                    cardholderName,
                    token.substring(token.length() - 4)
            );

            boolean paymentSuccess = paymentService.processPayment(payment);

            if (paymentSuccess) {
                response.sendRedirect("Billing.jsp?bookingID=" + bookingID + "&customerID=" + customerID + "&fare=" + amountStr + "&tax=" + taxStr + "&discount=" + discountStr + "&totalFare=" + totalFareStr + "&paymentStatus=Successful");
            } else {
                response.sendRedirect("error.jsp?message=Failed to store payment in DB");
            }

        } catch (StripeException | NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Payment Failed: " + e.getMessage());
        }
    }
}