package CruzeX.webapp.Dao;

import CruzeX.webapp.Model.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingManager {

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl();
        return factory.getDbConnector().getDbConnection();
    }

    // Add a new booking
    public int addBooking(Booking booking) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO booking (CustomerID, VehicleID, DriverID, BookingDate, BookingTime, PickupLocation, DropLocation, Distance, Fare, Tax, Discount, TotalFare) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, booking.getCustomerID());
            ps.setString(2, booking.getVehicleID());
            ps.setInt(3, booking.getDriverID());
            ps.setString(4, booking.getBookingDate());
            ps.setString(5, booking.getBookingTime());
            ps.setString(6, booking.getPickupLocation());
            ps.setString(7, booking.getDropLocation());
            ps.setDouble(8, booking.getDistance());
            ps.setDouble(9, booking.getFare());
            ps.setDouble(10, booking.getTax());
            ps.setDouble(11, booking.getDiscount());
            ps.setDouble(12, booking.getTotalFare());

            int result = ps.executeUpdate();
            if (result > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            return -1;
        }
    }

    // Update a booking
public boolean updateBooking(Booking booking) throws ClassNotFoundException, SQLException {
    String query = "UPDATE booking SET CustomerID=?, VehicleID=?, DriverID=?, BookingDate=?, BookingTime=?, PickupLocation=?, DropLocation=?, Distance=?, Fare=?, Tax=?, Discount=?, TotalFare=? WHERE BookingID=?";

    try (Connection connection = getConnection();
         PreparedStatement ps = connection.prepareStatement(query)) {

        ps.setInt(1, booking.getCustomerID());
        ps.setString(2, booking.getVehicleID());
        ps.setInt(3, booking.getDriverID());
        ps.setString(4, booking.getBookingDate());
        ps.setString(5, booking.getBookingTime());
        ps.setString(6, booking.getPickupLocation());
        ps.setString(7, booking.getDropLocation());
        ps.setDouble(8, booking.getDistance());
        ps.setDouble(9, booking.getFare());
        ps.setDouble(10, booking.getTax());
        ps.setDouble(11, booking.getDiscount());
        ps.setDouble(12, booking.getTotalFare());
        ps.setInt(13, booking.getBookingID());

        int result = ps.executeUpdate();
        return result > 0;
    }
}
    // Delete booking by ID
 public boolean deleteBooking(int bookingID) {
    String sql = "DELETE FROM booking WHERE BookingID = ?";

    try (Connection conn = getConnection(); 
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        System.out.println("Deleting Booking ID: " + bookingID);
        stmt.setInt(1, bookingID);

        int affectedRows = stmt.executeUpdate();
        System.out.println("Affected Rows: " + affectedRows);

        return affectedRows > 0;

    } catch (SQLException | ClassNotFoundException e) {
        System.err.println("SQL Error: " + e.getMessage());
        return false;
    }
}
    // Get specific booking by ID
    public Booking getSpecificBookingById(int bookingID) throws ClassNotFoundException, SQLException {
        String query = "SELECT * FROM booking WHERE BookingID = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, bookingID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Booking(
                        rs.getInt("BookingID"),
                        rs.getInt("CustomerID"),
                        rs.getString("VehicleID"),
                        rs.getInt("DriverID"),
                        rs.getString("BookingDate"),
                        rs.getString("BookingTime"),
                        rs.getString("PickupLocation"),
                        rs.getString("DropLocation"),
                        rs.getDouble("Distance"),
                        rs.getDouble("Fare"),
                        rs.getDouble("Tax"),
                        rs.getDouble("Discount"),
                        rs.getDouble("TotalFare")
                );
            }
        }

        
        return null;
    }

    // Get all bookings
    public List<Booking> getAllBookings() throws ClassNotFoundException, SQLException {
        List<Booking> bookingList = new ArrayList<>();
        String query = "SELECT * FROM booking";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getInt("BookingID"),
                        rs.getInt("CustomerID"),
                        rs.getString("VehicleID"),
                        rs.getInt("DriverID"),
                        rs.getString("BookingDate"),
                        rs.getString("BookingTime"),
                        rs.getString("PickupLocation"),
                        rs.getString("DropLocation"),
                        rs.getDouble("Distance"),
                        rs.getDouble("Fare"),
                        rs.getDouble("Tax"),
                        rs.getDouble("Discount"),
                        rs.getDouble("TotalFare")
                );
                bookingList.add(booking);
            }
        }catch (SQLException e) {
        e.printStackTrace(); // Replace with logging in production
        throw e;
        }

        return bookingList;
    }
}