package CruzeX.webapp.Model;

public class Driver {
    private int driverID;
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private String email;

    // Constructor for creating a new driver (with driverID)
    public Driver(int driverID, String firstName, String lastName, String licenseNumber, String email) {
        this.driverID = driverID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenseNumber = licenseNumber;
        this.email = email;
    }

    // Constructor for creating a new driver without driverID (for adding new drivers)
    public Driver(String firstName, String lastName, String licenseNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenseNumber = licenseNumber;
        this.email = email;
    }
    
    public Driver() {
    }

    // Getters and Setters for the fields

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLicenseNumber() {
        return licenseNumber;  // Make sure this method is implemented
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
