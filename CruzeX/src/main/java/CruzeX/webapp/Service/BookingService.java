package CruzeX.webapp.Service;

import CruzeX.webapp.Dao.BookingManager;
import CruzeX.webapp.Model.Booking;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingService {

    private static BookingService bookingServiceInstance;

    private BookingService() {
        // Private constructor to enforce Singleton pattern
    }

    public static synchronized BookingService getBookingServiceInstance() {
        if (bookingServiceInstance == null) {
            bookingServiceInstance = new BookingService();
        }
        return bookingServiceInstance;
    }

    private BookingManager getBookingManager() {
        return new BookingManager(); // Retrieves an instance of BookingManager (DAO Layer)
    }

    /**
     * Registers a new booking in the system.
     * 
     * @param booking Booking object containing booking details.
     * @return booking ID if successful, otherwise -1.
     */
    public int registerBooking(Booking booking) {
        try {
            return getBookingManager().addBooking(booking);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("[ERROR] Failed to add booking: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Fetches a specific booking by its ID.
     * 
     * @param bookingID ID of the booking to retrieve.
     * @return Booking object containing booking details, or a new empty object if not found.
     */
    public Booking getSpecificBooking(int bookingID) {
        try {
            Booking booking = getBookingManager().getSpecificBooking(bookingID);
            return booking != null ? booking : new Booking();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("[ERROR] Unable to fetch booking ID " + bookingID + ": " + e.getMessage());
            return new Booking();
        }
    }

    /**
     * Retrieves all bookings from the database.
     * 
     * @return List of Booking objects, or an empty list if an error occurs.
     */
    public List<Booking> getAllBookings() {
        try {
            return getBookingManager().getAllBookings();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("[ERROR] Unable to fetch all bookings: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Updates an existing booking in the system.
     * 
     * @param booking Booking object containing updated details.
     * @return true if update is successful, false otherwise.
     */
    public boolean editTheBooking(Booking booking) {
        try {
            return getBookingManager().updateBooking(booking);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("[ERROR] Failed to update booking ID " + booking.getBookingID() + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a booking by its ID.
     * 
     * @param bookingID ID of the booking to be deleted.
     * @return true if deletion is successful, false otherwise.
     */
    public boolean deleteTheBooking(int bookingID) {
        try {
            return getBookingManager().deleteBooking(bookingID);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("[ERROR] Failed to delete booking ID " + bookingID + ": " + e.getMessage());
            return false;
        }
    }
}