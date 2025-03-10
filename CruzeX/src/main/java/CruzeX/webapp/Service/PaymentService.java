package CruzeX.webapp.Service;

import CruzeX.webapp.Dao.PaymentManager;
import CruzeX.webapp.Model.Payment;
import java.sql.SQLException;

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

    public boolean processPayment(Payment payment) {
        try {
            return new PaymentManager().addPayment(payment);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}