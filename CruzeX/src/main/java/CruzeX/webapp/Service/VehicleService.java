package CruzeX.webapp.Service;

import CruzeX.webapp.Dao.VehicleManager;
import CruzeX.webapp.Model.Vehicle;

import java.sql.SQLException;
import java.util.List;

public class VehicleService {

    private static VehicleService vehicleServiceObj;

    private VehicleService() {
    }

    public static synchronized VehicleService getVehicleServiceObj() {
        if (vehicleServiceObj == null) {
            vehicleServiceObj = new VehicleService();
        }
        return vehicleServiceObj;
    }

    private VehicleManager getVehicleManager() {
        return new VehicleManager();
    }

    public boolean registerVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException {
        return getVehicleManager().addVehicle(vehicle);
    }

    public Vehicle getSpecificVehicle(String vehicleID) throws ClassNotFoundException, SQLException {
        return getVehicleManager().getSpecificVehicle(vehicleID);
    }

    public List<Vehicle> getAllVehicles() throws ClassNotFoundException, SQLException {
        return getVehicleManager().getAllVehicles();
    }

    public boolean editVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException {
        return getVehicleManager().updateVehicle(vehicle);
    }

    public boolean deleteVehicle(String vehicleID) throws ClassNotFoundException, SQLException {
        return getVehicleManager().deleteVehicle(vehicleID);
    }
}
