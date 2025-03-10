package CruzeX.webapp.Model;

import java.time.LocalDateTime;

public class Payment {
    private int paymentId;
    private int bookingId;
    private int customerId;
    private double distance;
    private double fare;
    private double tax;
    private double discount;
    private double totalFare;
    private String cardholderName;
    private String last4Digits;
    private LocalDateTime paymentDate;

    // No-Argument Constructor
    public Payment() {}

    // Constructor for existing payment (from database)
    public Payment(int paymentId, int bookingId, int customerId, double fare, double tax, 
                   double discount, double totalFare, 
                   String cardholderName, String last4Digits, LocalDateTime paymentDate) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.fare = fare;
        this.tax = tax;
        this.discount = discount;
        this.totalFare = totalFare;
        this.cardholderName = cardholderName;
        this.last4Digits = last4Digits;
        this.paymentDate = paymentDate;
    }

    // Constructor for new payment (before storing in DB)
    public Payment(int bookingId, int customerId, double fare, double tax, 
                   double discount, double totalFare, 
                   String cardholderName, String last4Digits) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.fare = fare;
        this.tax = tax;
        this.discount = discount;
        this.totalFare = totalFare;
        this.cardholderName = cardholderName;
        this.last4Digits = last4Digits;
        this.paymentDate = LocalDateTime.now();
    }

    // Getters and Setters
    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }

    public double getFare() { return fare; }
    public void setFare(double fare) { this.fare = fare; }

    public double getTax() { return tax; }
    public void setTax(double tax) { this.tax = tax; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

    public double getTotalFare() { return totalFare; }
    public void setTotalFare(double totalFare) { this.totalFare = totalFare; }

    public String getCardholderName() { return cardholderName; }
    public void setCardholderName(String cardholderName) { this.cardholderName = cardholderName; }

    public String getLast4Digits() { return last4Digits; }
    public void setLast4Digits(String last4Digits) { this.last4Digits = last4Digits; }

    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }

    // toString() Method
    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", bookingId=" + bookingId +
                ", customerId=" + customerId +
                ", distance=" + distance +
                ", fare=" + fare +
                ", tax=" + tax +
                ", discount=" + discount +
                ", totalFare=" + totalFare +
                ", cardholderName='" + cardholderName + '\'' +
                ", last4Digits='**** " + last4Digits + '\'' +
                ", paymentDate=" + paymentDate +
                 
                '}';
    }
}