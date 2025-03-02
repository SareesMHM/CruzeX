package CruzeX.webapp.Controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CruzeX.webapp.Model.Payment;
import CruzeX.webapp.Service.PaymentService;

@WebServlet("/PaymentController")
public class PaymentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PaymentService paymentService = PaymentService.getPaymentServiceInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        if ("add".equals(type)) {
            addPayment(request, response);
        } else if ("delete".equals(type)) {
            deletePayment(request, response);
        }
    }

    private void addPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // ✅ Parse and validate required parameters
            int bookingID = Integer.parseInt(request.getParameter("bookingID"));
            int customerID = Integer.parseInt(request.getParameter("customerID"));

            String amountStr = request.getParameter("amount");
            double amount = (amountStr != null && !amountStr.trim().isEmpty()) ? Double.parseDouble(amountStr) : 0.0;

            String cardNumber = request.getParameter("cardNumber");
            String expiryDate = request.getParameter("expiryDate");
            String cvcNumber = request.getParameter("cvcNumber");
            String paymentDate =request.getParameter("paymentDate");

            // ✅ Check for missing payment details
            if (cardNumber == null || expiryDate == null || cvcNumber == null || amount <= 0) {
                request.setAttribute("message", "Error: Please fill all payment details correctly.");
                request.getRequestDispatcher("Add-Payment.jsp").forward(request, response);
                return;
            }

            // ✅ Create a new Payment object
            Payment payment = new Payment(bookingID, amount, cardNumber, expiryDate, cvcNumber,paymentDate, customerID);

            // ✅ Process the payment
            boolean result = paymentService.processPayment(payment);
            String message = result ? "Payment successful!" : "Payment failed! Please try again.";

            // ✅ Redirect to PaymentConfirmation.jsp
            request.setAttribute("message", message);
            request.getRequestDispatcher("PaymentConfirmation.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("message", "Error: Invalid input. Please enter a valid number.");
            request.getRequestDispatcher("Add-Payment.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("message", "Error processing payment: " + e.getMessage());
            request.getRequestDispatcher("Add-Payment.jsp").forward(request, response);
        }
    }

    private void deletePayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int paymentId = Integer.parseInt(request.getParameter("paymentId"));
            boolean result = paymentService.deletePayment(paymentId);
            String message = result ? "Payment deleted successfully!" : "Failed to delete payment.";

            request.setAttribute("message", message);
            response.sendRedirect("PaymentDashboard.jsp");

        } catch (NumberFormatException e) {
            request.setAttribute("message", "Error: Invalid Payment ID.");
            request.getRequestDispatcher("PaymentDashboard.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("message", "Error deleting payment: " + e.getMessage());
            request.getRequestDispatcher("PaymentDashboard.jsp").forward(request, response);
        }
    }
}