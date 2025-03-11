package CruzeX.webapp.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerTest {

    @Test
    void testGettersAndSetters() {
        Customer customer = new Customer();
        
        customer.setCustomerID(1);
        assertEquals(1, customer.getCustomerID());
        
        customer.setCustomerFullName("Vishal Nirosh");
        assertEquals("Vishal Nirosh", customer.getCustomerFullName());
        
        customer.setCustomerPhoneNumber("1234567890");
        assertEquals("1234567890", customer.getCustomerPhoneNumber());
        
        customer.setDateOfBirth("2000-01-01");
        assertEquals("2000-01-01", customer.getDateOfBirth());
        
        customer.setCustomerAddress("123 Main St");
        assertEquals("123 Main St", customer.getCustomerAddress());
        
        customer.setGender("Male");
        assertEquals("Male", customer.getGender());
        
        customer.setCustomerEmail("vishal@example.com");
        assertEquals("vishal@example.com", customer.getCustomerEmail());
        
        customer.setCustomerUsername("vishal123");
        assertEquals("vishal123", customer.getCustomerUsername());
        
        customer.setCustomerPassword("password");
        assertEquals("password", customer.getCustomerPassword());
    }
    
    @Test
    void testConstructors() {
        Customer customer = new Customer("Vishal Nirosh", "1234567890", "2000-01-01", "123 Main St",
                                      "Male", "vishal@example.com", "vishal123", "password");
        
        assertEquals("Vishal Nirosh", customer.getCustomerFullName());
        assertEquals("1234567890", customer.getCustomerPhoneNumber());
        assertEquals("2000-01-01", customer.getDateOfBirth());
        assertEquals("123 Main St", customer.getCustomerAddress());
        assertEquals("Male", customer.getGender());
        assertEquals("vishal@example.com", customer.getCustomerEmail());
        assertEquals("vishal123", customer.getCustomerUsername());
        assertEquals("password", customer.getCustomerPassword());
    }
}
