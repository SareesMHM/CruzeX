package CruzeX.webapp.Model;

public class Vehicle {
    private int vehicleID;
    private String vehicleName;
    private String image;
    private String category;
    private int monthFee; // Fixed variable name

    // Constructor (Updated to include monthFee)
    public Vehicle(int vehicleID, String vehicleName, String image, String category) {
        this.vehicleID = vehicleID;
        this.vehicleName = vehicleName;
        this.image = image;
        this.category = category;
        this.monthFee = monthFee;
    }

    // Getters and Setters
    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
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

    public int getMonthFee() { // Added getter
        return monthFee;
    }

    public void setMonthFee(int monthFee) { // Added setter
        this.monthFee = monthFee;
    }
}
