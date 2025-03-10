package CruzeX.webapp.Dao;

import CruzeX.webapp.Model.Billing;
import java.sql.*;
import java.time.LocalDateTime;

public class BillingManager {
    public DbConnector getDbConnector() {
        DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl();
        return factory.getDbConnector();
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        return getDbConnector().getDbConnection();
    }

    public boolean addBilling(Billing billing) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO billing (bookingID, customerID, baseAmount, discount, tax, totalAmount, billingDate) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, billing.getBookingID());
            ps.setInt(2, billing.getCustomerID());
            ps.setDouble(3, billing.getBaseAmount());
            ps.setDouble(4, billing.getDiscount());
            ps.setDouble(5, billing.getTax());
            ps.setDouble(6, billing.getTotalAmount());

            return ps.executeUpdate() > 0;
        }
    }

    public Billing getBillingByBookingID(int bookingID) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM billing WHERE bookingID = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, bookingID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Billing(
                        rs.getInt("billingID"),
                        rs.getInt("bookingID"),
                        rs.getInt("customerID"),
                        rs.getDouble("baseAmount"),
                        rs.getDouble("discount"),
                        rs.getDouble("tax"),
                        rs.getDouble("totalAmount"),
                        rs.getTimestamp("billingDate").toLocalDateTime()
                );
            }
        }
        return null;
    }
}