package CruzeX.webapp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import CruzeX.webapp.Model.Driver;

public class DriverManager {

    public DbConnector getDbConnector() {
        DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl();
        return factory.getDbConnector();
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        DbConnector connector = getDbConnector();
        return connector.getDbConnection();
    }

    public boolean addDriver(Driver driver) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String query = "INSERT INTO driver (FirstName, LastName, LicenseNumber, Email) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, driver.getFirstName());
        ps.setString(2, driver.getLastName());
        ps.setString(3, driver.getLicenseNumber());
        ps.setString(4, driver.getEmail());

        int result = ps.executeUpdate();

        ps.close();
        connection.close();
        return result > 0;
    }

    public Driver getSpecificDriver(int driverID) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String query = "SELECT * FROM driver WHERE DriverID = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, driverID);

        ResultSet rs = ps.executeQuery();
        Driver driver = new Driver(0, "", "", "", "");  // Initializing with default values

        while (rs.next()) {
            driver.setDriverID(rs.getInt("DriverID"));
            driver.setFirstName(rs.getString("FirstName"));
            driver.setLastName(rs.getString("LastName"));
            driver.setLicenseNumber(rs.getString("LicenseNumber"));
            driver.setEmail(rs.getString("Email"));
        }

        ps.close();
        connection.close();
        return driver;
    }

    public List<Driver> getAllDrivers() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        List<Driver> driverList = new ArrayList<>();

        String query = "SELECT * FROM driver";
        Statement st = connection.createStatement();

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Driver driver = new Driver(
                rs.getInt("DriverID"),
                rs.getString("FirstName"),
                rs.getString("LastName"),
                rs.getString("LicenseNumber"),
                rs.getString("Email")
            );
            driverList.add(driver);
        }

        st.close();
        connection.close();

        return driverList;
    }

    public boolean updateDriver(Driver driver) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String query = "UPDATE driver SET FirstName = ?, LastName = ?, LicenseNumber = ?, Email = ? WHERE DriverID = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, driver.getFirstName());
        ps.setString(2, driver.getLastName());
        ps.setString(3, driver.getLicenseNumber());
        ps.setString(4, driver.getEmail());
        ps.setInt(5, driver.getDriverID());

        int result = ps.executeUpdate();

        ps.close();
        connection.close();
        return result > 0;
    }

    public boolean deleteDriver(int driverID) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String query = "DELETE FROM driver WHERE DriverID = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, driverID);
        int result = ps.executeUpdate();

        ps.close();
        connection.close();
        return result > 0;
    }
}
