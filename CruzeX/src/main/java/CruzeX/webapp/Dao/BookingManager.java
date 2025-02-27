package CruzeX.webapp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import CruzeX.webapp.Model.Booking;  // Change from Appointment to Booking

public class BookingManager {

    public DbConnector getDbConnector() {
        DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl(); 
        return factory.getDbConnector();
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        DbConnector connector = getDbConnector();
        return connector.getDbConnection();
    }

    public boolean addBooking(Booking booking) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection(); 
        String query = "INSERT INTO booking (CustomerID, BookingDate, BookingTime, DriverName, Address, Destination) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, booking.getCustomerID());
        ps.setString(2, booking.getBookingDate());
        ps.setString(3, booking.getBookingTime());
        ps.setString(4, booking.getDriverName());
        ps.setString(5, booking.getAddress());
        ps.setString(6, booking.getDestination());

        int result = ps.executeUpdate();
        
        ps.close();
        connection.close();
        return result > 0;
    }

    public Booking getSpecificBooking(int bookingID) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String query = "SELECT * FROM booking WHERE BookingID = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, bookingID);

        ResultSet rs = ps.executeQuery();
        Booking booking = new Booking();

        while (rs.next()) {
            booking.setBookingID(rs.getInt("BookingID"));
            booking.setCustomerID(rs.getInt("CustomerID"));
            booking.setBookingDate(rs.getString("BookingDate"));
            booking.setBookingTime(rs.getString("BookingTime"));
            booking.setDriverName(rs.getString("DriverName"));
            booking.setAddress(rs.getString("Address"));
            booking.setDestination(rs.getString("Destination"));
        }

        ps.close();
        connection.close();
        return booking;
    }

    public List<Booking> getAllBookings() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();    
        List<Booking> bookingList = new ArrayList<>();
        
        String query = "SELECT * FROM booking";
        
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()) {
            Booking booking = new Booking();
            booking.setBookingID(rs.getInt("BookingID"));
            booking.setCustomerID(rs.getInt("CustomerID"));
            booking.setBookingDate(rs.getString("BookingDate"));
            booking.setBookingTime(rs.getString("BookingTime"));
            booking.setDriverName(rs.getString("DriverName"));
            booking.setAddress(rs.getString("Address"));
            booking.setDestination(rs.getString("Destination"));
            
            bookingList.add(booking);
        }
        
        st.close();
        connection.close();
        
        return bookingList;        
    }

    public boolean updateBooking(Booking booking) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        String query = "UPDATE booking SET CustomerID = ?, BookingDate = ?, BookingTime = ?, DriverName = ?, Address = ?, Destination = ? WHERE BookingID = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, booking.getCustomerID());
        ps.setString(2, booking.getBookingDate());
        ps.setString(3, booking.getBookingTime());
        ps.setString(4, booking.getDriverName());
        ps.setString(5, booking.getAddress());
        ps.setString(6, booking.getDestination());
        ps.setInt(7, booking.getBookingID());

        int result = ps.executeUpdate();
        
        ps.close();
        connection.close();
        return result > 0;
    }

    public boolean deleteBooking(int bookingID) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();        
        String query = "DELETE FROM booking WHERE BookingID = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, bookingID);
        int result = ps.executeUpdate();

        ps.close();
        connection.close();        
        return result > 0;
    }
}
