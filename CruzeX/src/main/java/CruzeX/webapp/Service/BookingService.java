package CruzeX.webapp.Service;

import CruzeX.webapp.Dao.BookingManager;
import CruzeX.webapp.Model.Booking;

import java.sql.SQLException;
import java.util.List;

public class BookingService {

    private static BookingService bookingServiceObj;

    private BookingService() {

    }

    public static synchronized BookingService getBookingServiceInstance() {
        if (bookingServiceObj == null) {
            bookingServiceObj = new BookingService();
        }
        return bookingServiceObj;
    }

    private BookingManager getBookingManager() {
        return new BookingManager();  // Use BookingManager instead of AppointmentManager
    }

    public boolean registerBooking(Booking booking) throws ClassNotFoundException, SQLException {
        return getBookingManager().addBooking(booking);  // Changed method to addBooking
    }

    public Booking getSpecificBooking(int bookingID) throws ClassNotFoundException, SQLException {
        return getBookingManager().getSpecificBooking(bookingID);  // Changed method to getSpecificBooking
    }

    public List<Booking> getAllBookings() throws ClassNotFoundException, SQLException {
        return getBookingManager().getAllBookings();  // Changed method to getAllBookings
    }

    public boolean editTheBooking(Booking booking) throws ClassNotFoundException, SQLException {
        return getBookingManager().updateBooking(booking);  // Changed method to updateBooking
    }

    public boolean deleteTheBooking(int bookingID) throws ClassNotFoundException, SQLException {
        return getBookingManager().deleteBooking(bookingID);  // Changed method to deleteBooking
    }
}
