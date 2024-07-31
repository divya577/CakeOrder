package com.sddevops.CakeOrderSys;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class CartServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @InjectMocks
    private CartServlet cartServlet;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(request.getSession()).thenReturn(session);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testDoPost_NewItem() throws ServletException, IOException {
        List<String> cart = new ArrayList<>();
        when(session.getAttribute("cart")).thenReturn(cart);
        when(request.getParameter("item")).thenReturn("Cake");

        cartServlet.doPost(request, response);

        verify(session).setAttribute("cart", cart);
        verify(response).sendRedirect("cart.jsp");
        assertTrue(cart.contains("Cake"));
    }

    @Test
    void testDoPost_ExistingCart() throws ServletException, IOException {
        List<String> cart = new ArrayList<>();
        cart.add("Bread");
        when(session.getAttribute("cart")).thenReturn(cart);
        when(request.getParameter("item")).thenReturn("Cake");

        cartServlet.doPost(request, response);

        verify(session).setAttribute("cart", cart);
        verify(response).sendRedirect("cart.jsp");
        assertTrue(cart.contains("Cake"));
        assertTrue(cart.contains("Bread"));
    }

    @Test
    void testDoPost_EmptyItem() throws ServletException, IOException {
        List<String> cart = new ArrayList<>();
        when(session.getAttribute("cart")).thenReturn(cart);
        when(request.getParameter("item")).thenReturn("");

        cartServlet.doPost(request, response);

        verify(session).setAttribute("cart", cart);
        verify(response).sendRedirect("cart.jsp");
        assertFalse(cart.contains(""));
    }

    @Test
    void testDoGet() throws ServletException, IOException {
        cartServlet.doGet(request, response);

        verify(request).getSession();
        verify(response).sendRedirect("cart.jsp");
    }
}
