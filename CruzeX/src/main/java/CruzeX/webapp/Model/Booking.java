package CruzeX.webapp.Model;

public class Booking {
    private int bookingID;
    private int customerID;
    private String vehicleID;
    private int driverID;
    private String bookingDate;
    private String bookingTime;
    private String pickupLocation;
    private String dropLocation;
    private double distance;
    private double fare;
    private double tax;
    private double discount;
    private double totalFare;

    //  No-Argument Constructor (Fixes Compilation Error)
    public Booking() {
    }

    //  Constructor with all fields (for existing bookings)
    public Booking(int bookingID, int customerID, String vehicleID, int driverID, 
                   String bookingDate, String bookingTime, String pickupLocation, 
                   String dropLocation, double distance, double fare, double tax, 
                   double discount, double totalFare) {
        this.bookingID = bookingID;
        this.customerID = customerID;
        this.vehicleID = vehicleID;
        this.driverID = driverID;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.distance = distance;
        this.fare = fare;
        this.tax = tax;
        this.discount = discount;
        this.totalFare = totalFare;
    }

    // Constructor without bookingID (For Adding New Bookings)
    public Booking(int customerID, String vehicleID, int driverID, 
                   String bookingDate, String bookingTime, String pickupLocation, 
                   String dropLocation, double distance, double fare, double tax, 
                   double discount, double totalFare) {
        this.customerID = customerID;
        this.vehicleID = vehicleID;
        this.driverID = driverID;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.distance = distance;
        this.fare = fare;
        this.tax = tax;
        this.discount = discount;
        this.totalFare = totalFare;
    }

    //  Getters & Setters (Ensure all fields have getters and setters)

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }
}