package CruzeX.webapp.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PaymentTest {

    @Test
    void testGettersAndSetters() {
        Payment payment = new Payment();

        payment.setPaymentId(1);
        assertEquals(1, payment.getPaymentId());

        payment.setPrice(100.0);
        assertEquals(100.0, payment.getPrice());

        payment.setCardholderName("Vishal Nirosh");
        assertEquals("Vishal Nirosh", payment.getCardholderName());

        // ✅ Store Card Number as a String
        payment.setCardNumber("1234567890123456");
        assertEquals("1234567890123456", payment.getCardNumber());

        payment.setExpiryDate("12/25");
        assertEquals("12/25", payment.getExpiryDate());

        // ✅ Store CVC as a String
        payment.setCvcNumber("123");
        assertEquals("123", payment.getCvcNumber());

        payment.setPaymentDate("2024-03-30");
        assertEquals("2024-03-30", payment.getPaymentDate());

        // ✅ Fixed method name (CustomerId, not PatientId)
        payment.setCustomerId(2);
        assertEquals(2, payment.getCustomerId());
    }

    @Test
    void testConstructors() {
        // ✅ Updated constructor with correct data types
        Payment payment = new Payment(1,1, 100.0, "John Doe", "1234567890123456", "12/25", "123", "2024-03-30", 2);

        assertEquals(1, payment.getPaymentId());
        assertEquals(1, payment.getBookingId());
        assertEquals(100.0, payment.getPrice());
        assertEquals("John Doe", payment.getCardholderName());
        assertEquals("1234567890123456", payment.getCardNumber());
        assertEquals("12/25", payment.getExpiryDate());
        assertEquals("123", payment.getCvcNumber());
        assertEquals("2024-03-30", payment.getPaymentDate());
        assertEquals(2, payment.getCustomerId());
    }
}