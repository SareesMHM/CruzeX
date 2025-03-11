package CruzeX.webapp.Model;

public class Customer {
    private int customerID;
    private String customerFullName;
    private String customerPhoneNumber;
    private String dateOfBirth;
    private String customerAddress;
    private String gender;
    private String customerEmail;
    private String customerUsername;
    private String customerPassword;

    //  Default Constructor
    public Customer() {}

    //  Constructor with all details (excluding confirmPassword)
    public Customer(String customerFullName, String customerPhoneNumber, String dateOfBirth,
                    String customerAddress, String gender, String customerEmail,
                    String customerUsername, String customerPassword) {
        this.customerFullName = customerFullName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.customerAddress = customerAddress;
        this.gender = gender;
        this.customerEmail = customerEmail;
        this.customerUsername = customerUsername;
        this.customerPassword = customerPassword;
    }
    
    public Customer(int customerID,String customerFullName, String customerPhoneNumber, String dateOfBirth,
                    String customerAddress, String gender, String customerEmail,
                    String customerUsername, String customerPassword) {
        this.customerID=customerID;
        this.customerFullName = customerFullName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.customerAddress = customerAddress;
        this.gender = gender;
        this.customerEmail = customerEmail;
        this.customerUsername = customerUsername;
        this.customerPassword = customerPassword;
    }

   

    //  Getters & Setters


    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

  

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }
}