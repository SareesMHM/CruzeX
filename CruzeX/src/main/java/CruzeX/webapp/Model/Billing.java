package CruzeX.webapp.Model;

import java.time.LocalDateTime;

public class Billing {
    private int billingID;
    private int bookingID;
    private int customerID;
    private double baseAmount;
    private double discount;
    private double tax;
    private double totalAmount;
    private LocalDateTime billingDate;

    public Billing(int billingID, int bookingID, int customerID, double baseAmount, double discount, double tax, double totalAmount, LocalDateTime billingDate) {
        this.billingID = billingID;
        this.bookingID = bookingID;
        this.customerID = customerID;
        this.baseAmount = baseAmount;
        this.discount = discount;
        this.tax = tax;
        this.totalAmount = totalAmount;
        this.billingDate = billingDate;
    }

    public Billing(int bookingID, int customerID, double baseAmount, double discount, double tax) {
        this.bookingID = bookingID;
        this.customerID = customerID;
        this.baseAmount = baseAmount;
        this.discount = discount;
        this.tax = tax;
        this.totalAmount = calculateTotalAmount();
    }

    public int getBillingID() { return billingID; }
    public int getBookingID() { return bookingID; }
    public int getCustomerID() { return customerID; }
    public double getBaseAmount() { return baseAmount; }
    public double getDiscount() { return discount; }
    public double getTax() { return tax; }
    public double getTotalAmount() { return totalAmount; }
    public LocalDateTime getBillingDate() { return billingDate; }

    public double calculateTotalAmount() {
        double discountAmount = (baseAmount * discount) / 100;
        double taxAmount = ((baseAmount - discountAmount) * tax) / 100;
        return baseAmount - discountAmount + taxAmount;
    }
}