package com.sddevops.CakeOrderSys;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertTrue;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class HomeServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private HomeServlet homeServlet;

    private StringWriter responseWriter;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        responseWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(responseWriter);
        when(response.getWriter()).thenReturn(writer);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testDoGet() throws Exception {
        homeServlet.doGet(request, response);

        String responseContent = responseWriter.toString();

        assertTrue(responseContent.contains("Promotional Cakes"));
        assertTrue(responseContent.contains("Customer Reviews"));
        assertTrue(responseContent.contains("Chocolate Truffle Cake - 20% off"));
        assertTrue(responseContent.contains("Vanilla Sponge Cake - Buy 1 Get 1 Free"));
        assertTrue(responseContent.contains("Red Velvet Cake - 15% off"));
        assertTrue(responseContent.contains("John12: The Chocolate Truffle Cake was amazing!"));
        assertTrue(responseContent.contains("Jady Smith: Loved the Red Velvet Cake, highly recommend!"));
        assertTrue(responseContent.contains("Alice: The Vanilla Sponge Cake was good, but a bit too sweet for my taste. Love it anyways!"));
    }
}
