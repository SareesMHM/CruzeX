package CruzeX.webapp.Model;

import java.time.LocalDateTime;

public class Billing {
    private int billingID;
    private int bookingID;
    private int customerID;
    private double totalAmount;
    private LocalDateTime billingDate;

    public Billing(int billingID, int bookingID, int customerID, double totalAmount, LocalDateTime billingDate) {
        this.billingID = billingID;
        this.bookingID = bookingID;
        this.customerID = customerID;
        this.totalAmount = totalAmount;
        this.billingDate = billingDate;
    }

    public Billing(int bookingID, int customerID, double totalAmount) {
        this.bookingID = bookingID;
        this.customerID = customerID;
        this.totalAmount = totalAmount;
    }

    public int getBillingID() { return billingID; }
    public int getBookingID() { return bookingID; }
    public int getCustomerID() { return customerID; }
    public double getTotalAmount() { return totalAmount; }
    public LocalDateTime getBillingDate() { return billingDate; }
}