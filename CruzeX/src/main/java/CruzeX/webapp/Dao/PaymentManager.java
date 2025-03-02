package CruzeX.webapp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CruzeX.webapp.Model.Payment;

public class PaymentManager {
    public DbConnector getDbConnector() {
        DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl();
        return factory.getDbConnector();
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        DbConnector connector = getDbConnector();
        return connector.getDbConnection();
    }

    // ✅ Add a new payment
    public boolean addPayment(Payment payment) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO payment (BookingID, CustomerID, Amount, CardNumber, ExpiryDate, CVCNumber) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, payment.getBookingId());
            ps.setInt(2, payment.getCustomerId());
            ps.setDouble(3, payment.getPrice());
            ps.setString(4, payment.getCardNumber());
            ps.setString(5, payment.getExpiryDate());
            ps.setString(6, payment.getCvcNumber());

            return ps.executeUpdate() > 0;
        }
    }

    // ✅ Retrieve a specific payment by ID
    public Payment getPaymentById(int paymentId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM payment WHERE PaymentID = ?";
        Payment payment = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, paymentId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                payment = new Payment(
                    rs.getInt("PaymentID"),
                         rs.getInt("CustomerId"),
                    rs.getDouble("Amount"),
                    rs.getString("CardholderName"),
                    rs.getString("CardNumber"),
                    rs.getString("ExpiryDate"),
                    rs.getString("CVCNumber"),
                    rs.getString("PaymentDate"),
                    rs.getInt("BookingID")
                       
                );
            }
        }
        return payment;
    }

    // ✅ Retrieve all payments
    public List<Payment> getAllPayments() throws SQLException, ClassNotFoundException {
        List<Payment> paymentList = new ArrayList<>();
        String query = "SELECT * FROM payment";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Payment payment = new Payment(
                    rs.getInt("PaymentID"),
                        rs.getInt("CustomerId"),
                    rs.getDouble("Amount"),
                    rs.getString("CardholderName"),
                    rs.getString("CardNumber"),
                    rs.getString("ExpiryDate"),
                    rs.getString("CVCNumber"),
                    rs.getString("PaymentDate"),
                    rs.getInt("BookingID")
                        
                        
                        
                );
                paymentList.add(payment);
            }
        }
        return paymentList;
    }

    // ✅ Delete a payment by ID
    public boolean deletePayment(int paymentId) throws ClassNotFoundException, SQLException {
        String query = "DELETE FROM payment WHERE PaymentID = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, paymentId);
            return ps.executeUpdate() > 0;
        }
    }
}