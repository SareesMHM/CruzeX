package CruzeX.webapp.Dao;

import CruzeX.webapp.Model.Billing;
import java.sql.*;

public class BillingManager {

    public DbConnector getDbConnector() {
        DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl();
        return factory.getDbConnector();
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        DbConnector connector = getDbConnector();
        return connector.getDbConnection();
    }

    // Generate a new Bill
    public boolean addBilling(Billing billing) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String query = "INSERT INTO billing (bookingID, customerID, vehicleID, totalAmount) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, billing.getBookingID());
        ps.setInt(2, billing.getCustomerID());
        ps.setString(3, billing.getVehicleID());
        ps.setDouble(4, billing.getTotalAmount());

        int result = ps.executeUpdate();
        ps.close();
        connection.close();
        return result > 0;
    }

    // Retrieve Bill by Booking ID
    public Billing getBillingByBookingID(int bookingID) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String query = "SELECT * FROM billing WHERE bookingID = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, bookingID);

        ResultSet rs = ps.executeQuery();
        Billing billing = null;

        if (rs.next()) {
            billing = new Billing(
                rs.getInt("billingID"),
                rs.getInt("bookingID"),
                rs.getInt("customerID"),
                rs.getString("vehicleID"),
                rs.getDouble("totalAmount"),
                rs.getTimestamp("billingDate").toString()
            );
        }

        ps.close();
        connection.close();
        return billing;
    }
}