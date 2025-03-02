package CruzeX.webapp.Service;

import CruzeX.webapp.Dao.BillingManager;
import CruzeX.webapp.Model.Billing;
import java.sql.SQLException;

public class BillingService {
    private static BillingService billingServiceInstance;

    private BillingService() {}

    public static synchronized BillingService getInstance() {
        if (billingServiceInstance == null) {
            billingServiceInstance = new BillingService();
        }
        return billingServiceInstance;
    }

    private BillingManager getBillingManager() {
        return new BillingManager();
    }

    public boolean generateBill(Billing billing) throws ClassNotFoundException, SQLException {
        return getBillingManager().addBilling(billing);
    }

    public Billing getBillByBookingID(int bookingID) throws ClassNotFoundException, SQLException {
        return getBillingManager().getBillingByBookingID(bookingID);
    }
}