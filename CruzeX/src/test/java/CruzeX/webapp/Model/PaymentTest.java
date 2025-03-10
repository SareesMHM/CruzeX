package CruzeX.webapp.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

class PaymentTest {

    @Test
    void testGettersAndSetters() {
        Payment payment = new Payment();

        payment.setPaymentId(1);
        assertEquals(1, payment.getPaymentId());

        payment.setBookingId(101);
        assertEquals(101, payment.getBookingId());

        payment.setCustomerId(2);
        assertEquals(2, payment.getCustomerId());

        payment.setDistance(12.5);
        assertEquals(12.5, payment.getDistance());

        payment.setFare(100.0);
        assertEquals(100.0, payment.getFare());

        payment.setTax(10.0);
        assertEquals(10.0, payment.getTax());

        payment.setDiscount(5.0);
        assertEquals(5.0, payment.getDiscount());

        payment.setTotalFare(105.0);
        assertEquals(105.0, payment.getTotalFare());

        payment.setCardholderName("Vishal Nirosh");
        assertEquals("Vishal Nirosh", payment.getCardholderName());

        payment.setLast4Digits("3456");
        assertEquals("3456", payment.getLast4Digits());

        LocalDateTime paymentDate = LocalDateTime.now();
        payment.setPaymentDate(paymentDate);
        assertEquals(paymentDate, payment.getPaymentDate());
    }

    @Test
    void testConstructors() {
        LocalDateTime paymentDate = LocalDateTime.of(2024, 3, 30, 14, 0);

        Payment payment1 = new Payment(
                1, 101, 2, 100.0, 10.0, 5.0, 105.0,
                "John Doe", "3456", paymentDate
        );

        assertEquals(1, payment1.getPaymentId());
        assertEquals(101, payment1.getBookingId());
        assertEquals(2, payment1.getCustomerId());
        assertEquals(100.0, payment1.getFare());
        assertEquals(10.0, payment1.getTax());
        assertEquals(5.0, payment1.getDiscount());
        assertEquals(105.0, payment1.getTotalFare());
        assertEquals("John Doe", payment1.getCardholderName());
        assertEquals("3456", payment1.getLast4Digits());
        assertEquals(paymentDate, payment1.getPaymentDate());

        Payment payment2 = new Payment(
                101, 2, 100.0, 10.0, 5.0, 105.0,
                "John Doe", "3456"
        );

        assertEquals(101, payment2.getBookingId());
        assertEquals(2, payment2.getCustomerId());
        assertEquals(100.0, payment2.getFare());
        assertEquals(10.0, payment2.getTax());
        assertEquals(5.0, payment2.getDiscount());
        assertEquals(105.0, payment2.getTotalFare());
        assertEquals("John Doe", payment2.getCardholderName());
        assertEquals("3456", payment2.getLast4Digits());
        assertNotNull(payment2.getPaymentDate());
    }

    @Test
    void testToString() {
        LocalDateTime paymentDate = LocalDateTime.of(2024, 3, 30, 14, 0);

        Payment payment = new Payment(
                1, 101, 2, 100.0, 10.0, 5.0, 105.0,
                "John Doe", "3456", paymentDate
        );

        String expectedToString = "Payment{" +
                "paymentId=1, bookingId=101, customerId=2, distance=0.0, fare=100.0, tax=10.0, discount=5.0, totalFare=105.0, " +
                "cardholderName='John Doe', last4Digits='**** 3456', paymentDate=2024-03-30T14:00}";

        assertEquals(expectedToString, payment.toString());
    }
}