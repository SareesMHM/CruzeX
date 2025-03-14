package CruzeX.webapp.Service;

import CruzeX.webapp.Dao.PaymentManager;
import CruzeX.webapp.Model.Payment;

import java.sql.SQLException;
import java.util.List;

public class PaymentService {

    private static volatile PaymentService paymentServiceInstance;

    private PaymentService() {}

    public static PaymentService getInstance() {
        if (paymentServiceInstance == null) {
            synchronized (PaymentService.class) {
                if (paymentServiceInstance == null) {
                    paymentServiceInstance = new PaymentService();
                }
            }
        }
        return paymentServiceInstance;
    }
   private PaymentManager getPaymentManager() {
        return new PaymentManager();
    }
    // Add new payment
    public boolean processPayment(Payment payment) {
        try {
            return getPaymentManager().addPayment(payment);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update existing payment
    public boolean updatePayment(Payment payment) {
        try {
            return getPaymentManager().updatePayment(payment);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete payment by ID
  public boolean deletePayment(int paymentID) {
    try {
        return getPaymentManager().deletePayment(paymentID);
    } catch (Exception e) {
        System.err.println("Error deleting payment: " + e.getMessage());
        return false;
    }
}

    // Get all payments
    public List<Payment> getAllPayments() {
        try {
            return getPaymentManager().getAllPayments();
       } catch (Exception e) {
            System.err.println("Error fetching Payments: " + e.getMessage());
            return null;
        }
    }

    // Optional: Get payment by booking ID
    public Payment getPaymentByBookingId(int bookingId) {
        try {
            return getPaymentManager().getPaymentByBookingId(bookingId);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}