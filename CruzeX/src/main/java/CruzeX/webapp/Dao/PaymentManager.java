package CruzeX.webapp.Dao;

import CruzeX.webapp.Model.Payment;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentManager {

    private DbConnector getDbConnector() {
        DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl();
        return factory.getDbConnector();
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        return getDbConnector().getDbConnection();
    }

    // 1. Add new payment
    public boolean addPayment(Payment payment) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO payment (bookingId, customerId, distance, fare, tax, discount, totalFare, cardholderName, last4Digits, paymentDate) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
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

    // 2. Get payment by booking ID
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

    // 3. Update payment
    public boolean updatePayment(Payment payment) throws SQLException, ClassNotFoundException {
        String query = "UPDATE payment SET fare=?, tax=?, discount=?, totalFare=?, cardholderName=?, last4Digits=? WHERE paymentId=?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setDouble(1, payment.getFare());
            ps.setDouble(2, payment.getTax());
            ps.setDouble(3, payment.getDiscount());
            ps.setDouble(4, payment.getTotalFare());
            ps.setString(5, payment.getCardholderName());
            ps.setString(6, payment.getLast4Digits());
            ps.setInt(7, payment.getPaymentId());

            return ps.executeUpdate() > 0;
        }
    }

    // 4. Delete payment
public boolean deletePayment(int paymentID) {
    String sql = "DELETE FROM payment WHERE paymentId = ?";

    try (Connection conn = getConnection(); 
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        System.out.println("Deleting Payment ID: " + paymentID);
        stmt.setInt(1, paymentID);

        int affectedRows = stmt.executeUpdate();
        System.out.println("Affected Rows: " + affectedRows);

        return affectedRows > 0;

    } catch (SQLException | ClassNotFoundException e) {
        System.err.println("SQL Error: " + e.getMessage());
        return false;
    }
}

    // 5. Get all payments
    public List<Payment> getAllPayments() throws SQLException, ClassNotFoundException {
        List<Payment> paymentList = new ArrayList<>();
        String query = "SELECT * FROM payment ORDER BY paymentDate DESC";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Payment payment = new Payment(
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
                paymentList.add(payment);
            }
        }
        return paymentList;
    }
}