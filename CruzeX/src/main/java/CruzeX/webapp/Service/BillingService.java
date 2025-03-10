package CruzeX.webapp.Service;

import CruzeX.webapp.Dao.BillingManager;
import CruzeX.webapp.Model.Billing;
import java.sql.SQLException;

public class BillingService {
    private static volatile BillingService billingServiceInstance;

    private BillingService() {}

    public static BillingService getInstance() {
        if (billingServiceInstance == null) {
            synchronized (BillingService.class) {
                if (billingServiceInstance == null) {
                    billingServiceInstance = new BillingService();
                }
            }
        }
        return billingServiceInstance;
    }

    public boolean generateBill(Billing billing) {
        try {
            return new BillingManager().addBilling(billing);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Billing getBillByBookingID(int bookingID) {
        try {
            return new BillingManager().getBillingByBookingID(bookingID);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}