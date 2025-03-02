package CruzeX.webapp.Service;

import CruzeX.webapp.Dao.PaymentManager;
import CruzeX.webapp.Model.Payment;

import java.sql.SQLException;
import java.util.List;

public class PaymentService {
    
    private static PaymentService paymentServiceInstance;

    private PaymentService() {
    }

    public static synchronized PaymentService getPaymentServiceInstance() {
        if (paymentServiceInstance == null) {
            paymentServiceInstance = new PaymentService();
        }
        return paymentServiceInstance;
    }

    private PaymentManager getPaymentManager() {
        return new PaymentManager();
    }

    public boolean processPayment(Payment payment) throws ClassNotFoundException, SQLException {
        return getPaymentManager().addPayment(payment);
    }

    public Payment getPaymentById(int paymentId) throws ClassNotFoundException, SQLException {
        return getPaymentManager().getPaymentById(paymentId);
    }

    public List<Payment> getAllPayments() throws ClassNotFoundException, SQLException {
        return getPaymentManager().getAllPayments();
    }

    public boolean deletePayment(int paymentId) throws ClassNotFoundException, SQLException {
        return getPaymentManager().deletePayment(paymentId);
    }
}