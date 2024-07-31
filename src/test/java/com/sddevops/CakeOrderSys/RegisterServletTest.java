package com.sddevops.CakeOrderSys;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RegisterServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @InjectMocks
    private RegisterServlet registerServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoPost_Success() throws Exception {
        when(request.getParameter("name")).thenReturn("testUser");
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("phoneNum")).thenReturn("1234567890");
        when(request.getParameter("password")).thenReturn("securePassword");

        // Mock the getConnection method
        RegisterServlet spyServlet = spy(registerServlet);
        doReturn(connection).when(spyServlet).getConnection();
        when(connection.prepareStatement(RegisterServlet.INSERT_USERS_SQL)).thenReturn(preparedStatement);

        // Execute the doPost method
        spyServlet.doPost(request, response);

        // Verify the preparedStatement was set correctly and executed
        verify(preparedStatement).setString(1, "testUser");
        verify(preparedStatement).setString(2, "test@example.com");
        verify(preparedStatement).setString(3, "1234567890");
        verify(preparedStatement).setString(4, "securePassword");
        verify(preparedStatement).executeUpdate();

        // Verify response redirect
        verify(response).sendRedirect("login.jsp");
    }
    
    @Test
    void testDoPost_DatabaseError() throws Exception {
        when(request.getParameter("name")).thenReturn("testUser");
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("phoneNum")).thenReturn("1234567890");
        when(request.getParameter("password")).thenReturn("securePassword");

        // Mock the getConnection method
        RegisterServlet spyServlet = spy(registerServlet);
        doReturn(connection).when(spyServlet).getConnection();
        when(connection.prepareStatement(RegisterServlet.INSERT_USERS_SQL)).thenReturn(preparedStatement);
        doThrow(new SQLException("Database error")).when(preparedStatement).executeUpdate();

        // Execute the doPost method
        spyServlet.doPost(request, response);

        // Verify that response.sendRedirect is still called
        verify(response).sendRedirect("login.jsp");
    }
}
