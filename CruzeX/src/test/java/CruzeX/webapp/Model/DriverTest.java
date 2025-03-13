package CruzeX.webapp.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DriverTest {

    @Test
    void testGettersAndSetters() {
        Driver driver = new Driver();

        driver.setDriverID(101);
        assertEquals(101, driver.getDriverID());

        driver.setFirstName("John");
        assertEquals("John", driver.getFirstName());

        driver.setLastName("Doe");
        assertEquals("Doe", driver.getLastName());

        driver.setLicenseNumber("LIC123456");
        assertEquals("LIC123456", driver.getLicenseNumber());

        driver.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", driver.getEmail());
    }

    @Test
    void testConstructorWithID() {
        Driver driver = new Driver(101, "John", "Doe", "LIC123456", "john.doe@example.com");

        assertEquals(101, driver.getDriverID());
        assertEquals("John", driver.getFirstName());
        assertEquals("Doe", driver.getLastName());
        assertEquals("LIC123456", driver.getLicenseNumber());
        assertEquals("john.doe@example.com", driver.getEmail());
    }

    @Test
    void testConstructorWithoutID() {
        Driver driver = new Driver("Jane", "Smith", "LIC654321", "jane.smith@example.com");

        assertEquals("Jane", driver.getFirstName());
        assertEquals("Smith", driver.getLastName());
        assertEquals("LIC654321", driver.getLicenseNumber());
        assertEquals("jane.smith@example.com", driver.getEmail());
    }
}