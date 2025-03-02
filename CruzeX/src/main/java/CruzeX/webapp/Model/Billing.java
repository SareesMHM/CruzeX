package CruzeX.webapp.Model;

public class Billing {
    private int billingID;
    private int bookingID;
    private int customerID;
    private String vehicleID;
    private double totalAmount;
    private String billingDate;

    // Constructor
    public Billing(int billingID, int bookingID, int customerID, String vehicleID, double totalAmount, String billingDate) {
        this.billingID = billingID;
        this.bookingID = bookingID;
        this.customerID = customerID;
        this.vehicleID = vehicleID;
        this.totalAmount = totalAmount;
        this.billingDate = billingDate;
    }

    // Constructor without billingID
    public Billing(int bookingID, int customerID, String vehicleID, double totalAmount) {
        this.bookingID = bookingID;
        this.customerID = customerID;
        this.vehicleID = vehicleID;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters
    public int getBillingID() { return billingID; }
    public void setBillingID(int billingID) { this.billingID = billingID; }

    public int getBookingID() { return bookingID; }
    public void setBookingID(int bookingID) { this.bookingID = bookingID; }

    public int getCustomerID() { return customerID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }

    public String getVehicleID() { return vehicleID; }
    public void setVehicleID(String vehicleID) { this.vehicleID = vehicleID; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getBillingDate() { return billingDate; }
    public void setBillingDate(String billingDate) { this.billingDate = billingDate; }
}