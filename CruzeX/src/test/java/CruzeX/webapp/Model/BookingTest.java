package CruzeX.webapp.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BookingTest {

    @Test
    void testGettersAndSetters() {
        Booking booking = new Booking();

        booking.setBookingID(1);
        assertEquals(1, booking.getBookingID());

        booking.setBookingDate("2024-03-25");
        assertEquals("2024-03-25", booking.getBookingDate());

        booking.setDriverName("John Doe");
        assertEquals("John Doe", booking.getDriverName());

        booking.setAddress("123 Main St");
        assertEquals("123 Main St", booking.getAddress());

        booking.setDestination("456 Elm St");
        assertEquals("456 Elm St", booking.getDestination());

        booking.setCustomerID(123);
        assertEquals(123, booking.getCustomerID());

       
    }

    @Test
    void testConstructors() {
        Booking booking = new Booking(1, 123, "2024-03-25", "10:00 AM", "John Doe", "123 Main St", "456 Elm St" );

        assertEquals(1, booking.getBookingID());
        assertEquals(123, booking.getCustomerID());
        assertEquals("2024-03-25", booking.getBookingDate());
        assertEquals("10:00 AM", booking.getBookingTime());
        assertEquals("John Doe", booking.getDriverName());
        assertEquals("123 Main St", booking.getAddress());
        assertEquals("456 Elm St", booking.getDestination());
        
    }
}
