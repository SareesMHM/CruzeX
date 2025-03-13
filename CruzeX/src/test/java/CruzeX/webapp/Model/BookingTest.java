package CruzeX.webapp.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BookingTest {

    @Test
    void testGettersAndSetters() {
        Booking booking = new Booking();

        booking.setBookingID(1);
        assertEquals(1, booking.getBookingID());

        booking.setCustomerID(123);
        assertEquals(123, booking.getCustomerID());

        booking.setVehicleID("456");
        assertEquals("456", booking.getVehicleID());

        booking.setDriverID(789);
        assertEquals(789, booking.getDriverID());

        booking.setBookingDate("2024-03-25");
        assertEquals("2024-03-25", booking.getBookingDate());

        booking.setBookingTime("10:00 AM");
        assertEquals("10:00 AM", booking.getBookingTime());

        booking.setPickupLocation("123 Main St");
        assertEquals("123 Main St", booking.getPickupLocation());

        booking.setDropLocation("456 Elm St");
        assertEquals("456 Elm St", booking.getDropLocation());

        //   Test Distance and Fare
        booking.setDistance(10.5);
        assertEquals(10.5, booking.getDistance(), 0.01); // Delta for floating-point comparison

        booking.setFare(1000.75);
        assertEquals(1000.75, booking.getFare(), 0.01);

        //  Test Tax and Discount (7% each)
        double expectedTax = 1000.75 * 0.07;
        double expectedDiscount = 1000.75 * 0.07;
        double expectedTotalFare = 1000.75 + expectedTax - expectedDiscount;

        booking.setTax(expectedTax);
        assertEquals(expectedTax, booking.getTax(), 0.01);

        booking.setDiscount(expectedDiscount);
        assertEquals(expectedDiscount, booking.getDiscount(), 0.01);

        booking.setTotalFare(expectedTotalFare);
        assertEquals(expectedTotalFare, booking.getTotalFare(), 0.01);
    }

    @Test
    void testConstructors() {
        double fare = 1000.75;
        double tax = fare * 0.07;
        double discount = fare * 0.07;
        double totalFare = fare + tax - discount;

        Booking booking = new Booking(1, 123, "456", 789, "2024-03-25", "10:00 AM", "123 Main St", "456 Elm St", 10.5, fare, tax, discount, totalFare);

        assertEquals(1, booking.getBookingID());
        assertEquals(123, booking.getCustomerID());
        assertEquals("456", booking.getVehicleID());
        assertEquals(789, booking.getDriverID());
        assertEquals("2024-03-25", booking.getBookingDate());
        assertEquals("10:00 AM", booking.getBookingTime());
        assertEquals("123 Main St", booking.getPickupLocation());
        assertEquals("456 Elm St", booking.getDropLocation());

        //  Check Distance and Fare
        assertEquals(10.5, booking.getDistance(), 0.01);
        assertEquals(fare, booking.getFare(), 0.01);

        //  Check Tax, Discount, and Total Fare
        assertEquals(tax, booking.getTax(), 0.01);
        assertEquals(discount, booking.getDiscount(), 0.01);
        assertEquals(totalFare, booking.getTotalFare(), 0.01);
    }
}