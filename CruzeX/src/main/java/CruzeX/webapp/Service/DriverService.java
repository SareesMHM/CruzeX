package CruzeX.webapp.Service;

import CruzeX.webapp.Dao.DriverManager;
import CruzeX.webapp.Model.Driver;

import java.sql.SQLException;
import java.util.List;

public class DriverService {

    private static DriverService driverServiceObj;

    private DriverService() {

    }

    public static synchronized DriverService getDriverServiceInstance() {

        if (driverServiceObj == null) {
            driverServiceObj = new DriverService();
        }

        return driverServiceObj;
    }

    private DriverManager getDriverManager() {
        return new DriverManager();
    }

    public boolean registerDriver(Driver driver) throws ClassNotFoundException, SQLException {
        return getDriverManager().addDriver(driver);
    }

    public Driver getSpecificDriver(int driverID) throws ClassNotFoundException, SQLException {
        return getDriverManager().getSpecificDriver(driverID);
    }

    public List<Driver> getAllDrivers() throws ClassNotFoundException, SQLException {
        return getDriverManager().getAllDrivers();
    }

    public boolean editTheDriver(Driver driver) throws ClassNotFoundException, SQLException {
        return getDriverManager().updateDriver(driver);
    }

    public boolean deleteTheDriver(int driverID) throws ClassNotFoundException, SQLException {
        return getDriverManager().deleteDriver(driverID);
    }

   
}
