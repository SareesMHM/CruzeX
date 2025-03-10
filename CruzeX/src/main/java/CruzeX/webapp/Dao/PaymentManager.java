package CruzeX.webapp.Dao;

import CruzeX.webapp.Model.Payment;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentManager {

    public DbConnector getDbConnector() {
        DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl();
        return factory.getDbConnector();
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        return getDbConnector().getDbConnection();
    }

    public boolean addPayment(Payment payment) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO payment (bookingId, customerId,  distance, fare, tax, discount, totalFare, cardholderName, last4Digits, paymentDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

           ps.setInt(1, payment.getBookingId());
            ps.setInt(2, payment.getCustomerId());
            ps.setDouble(3, payment.getDistance());
            ps.setDouble(4, payment.getFare());
            ps.setDouble(5, payment.getTax());
            ps.setDouble(6, payment.getDiscount());
            ps.setDouble(7, payment.getTotalFare());
            ps.setString(8, payment.getCardholderName());
            ps.setString(9, payment.getLast4Digits());

            return ps.executeUpdate() > 0;
        }
    }

    public Payment getPaymentByBookingId(int bookingId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM payment WHERE bookingId = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Payment(
                        rs.getInt("paymentId"),
                        rs.getInt("bookingId"),
                        rs.getInt("customerId"),
                        rs.getDouble("fare"),
                        rs.getDouble("tax"),
                        rs.getDouble("discount"),
                        rs.getDouble("totalFare"),
                        rs.getString("cardholderName"),
                        rs.getString("last4Digits"),
                        rs.getTimestamp("paymentDate").toLocalDateTime()
                );
            }
        }
        return null;
    }
}