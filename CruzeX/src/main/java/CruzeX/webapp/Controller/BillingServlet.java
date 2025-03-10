package CruzeX.webapp.Controller;

import CruzeX.webapp.Model.Billing;
import CruzeX.webapp.Service.BillingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BillingServlet")
public class BillingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Validate and parse input parameters
            String bookingIDStr = request.getParameter("bookingID");
            String customerIDStr = request.getParameter("customerID");
            String baseAmountStr = request.getParameter("baseAmount");
            String discountStr = request.getParameter("discount");
            String taxStr = request.getParameter("tax");

            if (bookingIDStr == null || customerIDStr == null || baseAmountStr == null || discountStr == null || taxStr == null) {
                response.sendRedirect("error.jsp?message=Invalid Input Parameters");
                return;
            }

            int bookingID = Integer.parseInt(bookingIDStr);
            int customerID = Integer.parseInt(customerIDStr);
            double baseAmount = Double.parseDouble(baseAmountStr);
            double discount = Double.parseDouble(discountStr);
            double tax = Double.parseDouble(taxStr);

            // Create a Billing object
            Billing billing = new Billing(bookingID, customerID, baseAmount, discount, tax);

            // Process billing
            boolean success = BillingService.getInstance().generateBill(billing);

            if (success) {
                response.sendRedirect("Billing.jsp?bookingID=" + bookingID);
            } else {
                response.sendRedirect("error.jsp?message=Billing Failed");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp?message=Invalid number format in input");
        } catch (Exception e) {
            response.sendRedirect("error.jsp?message=Unexpected error occurred");
        }
    }
}