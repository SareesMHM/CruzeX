package CruzeX.webapp.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import CruzeX.webapp.Model.Booking;

public class BookingManager {

    // Get a database connector instance
    public DbConnector getDbConnector() {
        DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl();
        return factory.getDbConnector();
    }

    // Establish a database connection
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        DbConnector connector = getDbConnector();
        return connector.getDbConnection();
    }

    // Add a new booking and return the generated BookingID
    public int addBooking(Booking booking) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO booking (CustomerID, VehicleID, DriverID, BookingDate, BookingTime, PickupLocation, DropLocation, Distance, Fare) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

            int result = ps.executeUpdate();
            int bookingID = 0;

            // Retrieve generated booking ID
            if (result > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    bookingID = rs.getInt(1);
                }
            }
            return bookingID;
        } catch (SQLException e) {
            System.err.println("Error adding booking: " + e.getMessage());
            throw e;
        }
    }

    // Retrieve a specific booking by ID
    public Booking getSpecificBooking(int bookingID) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM booking WHERE BookingID = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, bookingID);
            ResultSet rs = ps.executeQuery();
            Booking booking = null;

            if (rs.next()) {
                booking = new Booking(
                    rs.getInt("BookingID"),
                    rs.getInt("CustomerID"),
                    rs.getString("VehicleID"),
                    rs.getInt("DriverID"),
                    rs.getString("BookingDate"),
                    rs.getString("BookingTime"),
                    rs.getString("PickupLocation"),
                    rs.getString("DropLocation"),
                    rs.getDouble("Distance"),
                    rs.getDouble("Fare")
                );
            }
            return booking;
        } catch (SQLException e) {
            System.err.println("Error fetching booking ID " + bookingID + ": " + e.getMessage());
            throw e;
        }
    }

    // Retrieve all bookings
    public List<Booking> getAllBookings() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM booking";
        List<Booking> bookingList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {

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
                    rs.getDouble("Fare")
                );
                bookingList.add(booking);
            }
            return bookingList;
        } catch (SQLException e) {
            System.err.println("Error fetching all bookings: " + e.getMessage());
            throw e;
        }
    }

    // Update an existing booking
    public boolean updateBooking(Booking booking) throws ClassNotFoundException, SQLException {
        String query = "UPDATE booking SET CustomerID = ?, VehicleID = ?, DriverID = ?, BookingDate = ?, BookingTime = ?, PickupLocation = ?, DropLocation = ?, Distance = ?, Fare = ? WHERE BookingID = ?";

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
            ps.setInt(10, booking.getBookingID());

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("Error updating booking ID " + booking.getBookingID() + ": " + e.getMessage());
            throw e;
        }
    }

    // Delete a booking by ID
    public boolean deleteBooking(int bookingID) throws ClassNotFoundException, SQLException {
        String query = "DELETE FROM booking WHERE BookingID = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, bookingID);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting booking ID " + bookingID + ": " + e.getMessage());
            throw e;
        }
    }
}