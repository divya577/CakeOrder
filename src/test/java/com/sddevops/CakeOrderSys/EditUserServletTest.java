package com.sddevops.CakeOrderSys;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.lang.reflect.Field;
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

class EditUserServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private RequestDispatcher dispatcher;

    @InjectMocks
    private EditUserServlet editUserServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        // Cleanup after each test
    }

    private String getUpdateUserSql() throws Exception {
        Field field = EditUserServlet.class.getDeclaredField("UPDATE_USER_SQL");
        field.setAccessible(true);
        return (String) field.get(null); // Static field, so null for instance
    }

    @Test
    void testDoPost_Success() throws Exception {
        when(request.getParameter("name")).thenReturn("testUser");
        when(request.getParameter("newPassword")).thenReturn("newSecurePassword");

        // Mock the getConnection method
        EditUserServlet spyServlet = spy(editUserServlet);
        doReturn(connection).when(spyServlet).getConnection();
        when(connection.prepareStatement(getUpdateUserSql())).thenReturn(preparedStatement);

        // Execute the doPost method
        spyServlet.doPost(request, response);

        // Verify that the preparedStatement was set correctly and executed
        verify(preparedStatement).setString(1, "newSecurePassword");
        verify(preparedStatement).setString(2, "testUser");
        verify(preparedStatement).executeUpdate();

        // Verify that response.sendRedirect is called
        verify(response).sendRedirect("login.jsp");
    }

    @Test
    void testDoPost_PasswordTooShort() throws Exception {
        when(request.getParameter("name")).thenReturn("testUser");
        when(request.getParameter("newPassword")).thenReturn("short");

        // Mock RequestDispatcher
        when(request.getRequestDispatcher("/editUser.jsp")).thenReturn(dispatcher);

        // Execute the doPost method
        editUserServlet.doPost(request, response);

        // Verify that the request was forwarded to editUser.jsp
        verify(request).setAttribute("errorMessage", "Password must be at least 8 characters long.");
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoPost_DatabaseError() throws Exception {
        when(request.getParameter("name")).thenReturn("testUser");
        when(request.getParameter("newPassword")).thenReturn("validPassword");

        // Mock the getConnection method to throw SQLException
        EditUserServlet spyServlet = spy(editUserServlet);
        doReturn(connection).when(spyServlet).getConnection();
        when(connection.prepareStatement(getUpdateUserSql())).thenReturn(preparedStatement);
        doThrow(new SQLException("Database error")).when(preparedStatement).executeUpdate();

        // Execute the doPost method
        spyServlet.doPost(request, response);

        // Verify that response.sendRedirect is called even when there's an error (if applicable)
        verify(response).sendRedirect("login.jsp");
    }
    @Test
    void testDoPost_ConnectionAndStatementClosed() throws Exception {
        when(request.getParameter("name")).thenReturn("testUser");
        when(request.getParameter("newPassword")).thenReturn("newSecurePassword");

        EditUserServlet spyServlet = spy(editUserServlet);
        doReturn(connection).when(spyServlet).getConnection();
        when(connection.prepareStatement(getUpdateUserSql())).thenReturn(preparedStatement);

        // Execute the doPost method
        spyServlet.doPost(request, response);

        // Verify that resources were closed
        verify(preparedStatement).close();
        verify(connection).close();
    }

}
