package CruzeX.webapp.Model;

public class Vehicle {
    private String vehicleID;
    private String vehicleName;
    private String image;
    private String category;
    private String status;
    private int driverID;
    private int monthFee; // Fixed variable name

    

   
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Constructor (Updated to include monthFee)
    public Vehicle(String vehicleID, String vehicleName, String image, String category,int monthFee,int driverID) {
        this.vehicleID = vehicleID;
        this.vehicleName = vehicleName;
        this.image = image;
        this.category = category;
        this.monthFee = monthFee;
        this.driverID = driverID;
        this.status = status;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    // Getters and Setters
    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMonthFee() { 
        return monthFee;
    }

    public void setMonthFee(int monthFee) { 
        this.monthFee = monthFee;
    }
}
