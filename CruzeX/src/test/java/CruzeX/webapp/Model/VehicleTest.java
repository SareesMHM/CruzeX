package CruzeX.webapp.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VehicleTest {

    @Test
    void testGettersAndSetters() {
        Vehicle vehicle = new Vehicle("1", "Car", "image.jpg", "SUV",2000,1);

        vehicle.setVehicleID("2");
        assertEquals("2", vehicle.getVehicleID());

        vehicle.setVehicleName("Truck");
        assertEquals("Truck", vehicle.getVehicleName());

        vehicle.setImage("truck_image.jpg");
        assertEquals("truck_image.jpg", vehicle.getImage());

        vehicle.setCategory("Pickup");
        assertEquals("Pickup", vehicle.getCategory());
    }

    @Test
    void testConstructor() {
        Vehicle vehicle = new Vehicle("1", "Car", "image.jpg", "SUV",2000,1);

        assertEquals("1", vehicle.getVehicleID());
        assertEquals("Car", vehicle.getVehicleName());
        assertEquals("image.jpg", vehicle.getImage());
        assertEquals("SUV", vehicle.getCategory());
    }
}
