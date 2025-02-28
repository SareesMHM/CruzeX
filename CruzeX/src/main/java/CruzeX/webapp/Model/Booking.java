package CruzeX.webapp.Model;

public class Booking {
    private int bookingID;
    private int customerID;
    private String bookingDate;
    private String bookingTime;
    private String driverName;
    private String address;
    private String destination;

    // Constructor with all fields
    public Booking(int bookingID, int customerID, String bookingDate, String bookingTime, String driverName, String address, String destination) {
        this.bookingID = bookingID;
        this.customerID = customerID;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.driverName = driverName;
        this.address = address;
        this.destination = destination;
    }
    
    public Booking( int customerID, String bookingDate, String bookingTime, String driverName, String address, String destination) {

        this.customerID = customerID;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.driverName = driverName;
        this.address = address;
        this.destination = destination;
    }

    public Booking() {
        
    }

//    public Booking(int customerID, String bookingDate, String bookingTime, String driverName, String address, String destination) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    // Getters and setters for each field
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

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
