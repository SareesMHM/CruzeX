package CruzeX.webapp.Service;

import CruzeX.webapp.Dao.BookingManager;
import CruzeX.webapp.Dao.VehicleManager;
import CruzeX.webapp.Model.Booking;
import java.sql.SQLException;
import java.util.List;

public class BookingService {

    private static BookingService bookingServiceInstance;

    private BookingService() { }

    public static synchronized BookingService getBookingServiceInstance() {
        if (bookingServiceInstance == null) {
            bookingServiceInstance = new BookingService();
        }
        return bookingServiceInstance;
    }

    public int registerBooking(Booking booking) {
        try {
            double tax = booking.getFare() * 0.07;
            double discount = booking.getFare() * 0.07;
            double totalFare = booking.getFare() + tax - discount;

            booking.setTax(tax);
            booking.setDiscount(discount);
            booking.setTotalFare(totalFare);

            return new BookingManager().addBooking(booking);
        } catch (Exception e) {
            System.err.println("Error adding booking: " + e.getMessage());
            return -1;
        }
    }

     private BookingManager getBookingManager() {
        return new BookingManager();
    }
  // Update existing booking
    public boolean editTheBooking(Booking booking) {
        try {
            return getBookingManager().updateBooking(booking);
        } catch (Exception e) {
            System.err.println("Error updating booking: " + e.getMessage());
            return false;
        }
    }

    // Delete booking by ID
    public boolean deleteTheBooking(int bookingID) {
        try {
            return getBookingManager().deleteBooking(bookingID);
        } catch (Exception e) {
            System.err.println("Error deleting booking: " + e.getMessage());
            return false;
        }
    }

    // Get booking by ID
    public Booking getSpecificBooking(int bookingID) {
        try {
            return getBookingManager().getSpecificBookingById(bookingID);
        } catch (Exception e) {
            System.err.println("Error fetching booking: " + e.getMessage());
            return null;
        }
    }

    // Get all bookings
    public List<Booking> getAllBookings() {
        try {
            return getBookingManager().getAllBookings();
        } catch (Exception e) {
            System.err.println("Error fetching bookings: " + e.getMessage());
            return null;
        }
    }

}