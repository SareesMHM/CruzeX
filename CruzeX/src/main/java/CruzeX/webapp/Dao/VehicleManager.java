package CruzeX.webapp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import CruzeX.webapp.Model.Vehicle;

public class VehicleManager {

    public DbConnector getDbConnector() {
        DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl();
        return factory.getDbConnector();
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        DbConnector connector = getDbConnector();
        return connector.getDbConnection();
    }

    // Add a new Vehicle
    public boolean addVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        String query = "INSERT INTO vehicle (VehicleID, VehicleName, Image, Category, MonthFee) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,vehicle.getVehicleID());
        ps.setString(2, vehicle.getVehicleName());
        ps.setString(3, vehicle.getImage());
        ps.setString(4, vehicle.getCategory());
        ps.setInt(5, vehicle.getMonthFee());

        int result = ps.executeUpdate();
        ps.close();
        connection.close();
        return result > 0;
    }

    // Get a specific vehicle by ID
    public Vehicle getSpecificVehicle(String vehicleID) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String query = "SELECT * FROM vehicle WHERE VehicleID = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, vehicleID);

        ResultSet rs = ps.executeQuery();
        Vehicle vehicle = null;

        if (rs.next()) {
            vehicle = new Vehicle(
                rs.getString("VehicleID"),
                rs.getString("VehicleName"),
                rs.getString("Image"),
                rs.getString("Category"),
                rs.getInt("MonthFee"));
        }

        ps.close();
        connection.close();
        return vehicle;
    }

    // Get all vehicles
    public List<Vehicle> getAllVehicles() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        List<Vehicle> vehicleList = new ArrayList<>();

        String query = "SELECT * FROM `vehicle`";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Vehicle vehicle = new Vehicle(
                rs.getString("VehicleID"),
                rs.getString("VehicleName"),
                rs.getString("Image"),
                rs.getString("Category"),
                rs.getInt("MonthFee"));
            vehicleList.add(vehicle);
        }

        st.close();
        connection.close();
        return vehicleList;
    }

    // Update a vehicle
    public boolean updateVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        String query = "UPDATE vehicle SET VehicleName = ?, Image = ?, Category = ?, MonthFee = ? WHERE VehicleID = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, vehicle.getVehicleName());
        ps.setString(2, vehicle.getImage());
        ps.setString(3, vehicle.getCategory());
        ps.setInt(4, vehicle.getMonthFee());
        ps.setString(5, vehicle.getVehicleID());

        int result = ps.executeUpdate();
        ps.close();
        connection.close();
        return result > 0;
    }

    // Delete a vehicle by ID
    public boolean deleteVehicle(String vehicleID) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        String query = "DELETE FROM vehicle WHERE VehicleID = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, vehicleID);
        int result = ps.executeUpdate();

        ps.close();
        connection.close();
        return result > 0;
    }
}
