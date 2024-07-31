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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DeleteUserServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private DeleteUserServlet.ConnectionProvider connectionProvider;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @InjectMocks
    private DeleteUserServlet deleteUserServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        // Cleanup after each test
    }

    @Test
    void testDoPost_Success() throws Exception {
        when(request.getParameter("name")).thenReturn("testUser");

        // Mock the connection and preparedStatement
        when(connectionProvider.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(DeleteUserServlet.DELETE_USER_SQL)).thenReturn(preparedStatement);

        // Execute the doPost method
        deleteUserServlet.doPost(request, response);

        // Verify that preparedStatement was set and executed
        verify(preparedStatement).setString(1, "testUser");
        verify(preparedStatement).executeUpdate();

        // Verify that response.sendRedirect is called
        verify(response).sendRedirect("login.jsp");
    }

    @Test
    void testDoPost_NoNameParameter() throws Exception {
        when(request.getParameter("name")).thenReturn(null);

        // Execute the doPost method
        deleteUserServlet.doPost(request, response);

        // Verify that no preparedStatement is created
        verify(connectionProvider, never()).getConnection();
        verify(response).sendRedirect("login.jsp");
    }

    @Test
    void testDoPost_EmptyNameParameter() throws Exception {
        when(request.getParameter("name")).thenReturn("");

        // Execute the doPost method
        deleteUserServlet.doPost(request, response);

        // Verify that no preparedStatement is created
        verify(connectionProvider, never()).getConnection();
        verify(response).sendRedirect("login.jsp");
    }

    @Test
    void testDoPost_ConnectionError() throws Exception {
        when(request.getParameter("name")).thenReturn("testUser");

        // Mock the connectionProvider to throw SQLException
        when(connectionProvider.getConnection()).thenThrow(new SQLException("Connection error"));

        // Execute the doPost method
        deleteUserServlet.doPost(request, response);

        // Verify that response.sendRedirect is called even when there's a connection error
        verify(response).sendRedirect("login.jsp");
    }

    @Test
    void testDoPost_StatementExecutionError() throws Exception {
        when(request.getParameter("name")).thenReturn("testUser");

        // Mock the connection and preparedStatement
        when(connectionProvider.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(DeleteUserServlet.DELETE_USER_SQL)).thenReturn(preparedStatement);
        doThrow(new SQLException("Execution error")).when(preparedStatement).executeUpdate();

        // Execute the doPost method
        deleteUserServlet.doPost(request, response);

        // Verify that response.sendRedirect is called even when there's an execution error
        verify(response).sendRedirect("login.jsp");
    }
}
