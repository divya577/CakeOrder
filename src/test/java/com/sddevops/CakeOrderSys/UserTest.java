package com.sddevops.CakeOrderSys;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void testUserConstructorAndGetters() {
        // Create a new User object
        User user = new User("JohnDoe", "password123", "john.doe@example.com", "English");

        // Verify that the constructor sets the values correctly
        assertEquals("JohnDoe", user.getName());
        assertEquals("password123", user.getPassword());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("English", user.getLanguage());
    }

    @Test
    void testSetters() {
        // Create a new User object
        User user = new User("JohnDoe", "password123", "john.doe@example.com", "English");

        // Update the values using setters
        user.setName("JaneDoe");
        user.setPassword("newpassword456");
        user.setEmail("jane.doe@example.com");
        user.setLanguage("French");

        // Verify that the setters update the values correctly
        assertEquals("JaneDoe", user.getName());
        assertEquals("newpassword456", user.getPassword());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals("French", user.getLanguage());
    }
}
