package com.sddevops.CakeOrderSys;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

class RemoveCartItemServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @InjectMocks
    private RemoveCartItemServlet removeCartItemServlet;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(request.getSession()).thenReturn(session);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testDoPost_ItemExists() throws ServletException, IOException {
        List<String> cart = new ArrayList<>();
        cart.add("Cake");
        cart.add("Bread");
        when(session.getAttribute("cart")).thenReturn(cart);
        when(request.getParameter("item")).thenReturn("Cake");

        removeCartItemServlet.doPost(request, response);

        verify(session).setAttribute("cart", cart);
        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertFalse(cart.contains("Cake"));
        assertTrue(cart.contains("Bread"));
    }

    @Test
    void testDoPost_ItemNotInCart() throws ServletException, IOException {
        List<String> cart = new ArrayList<>();
        cart.add("Bread");
        when(session.getAttribute("cart")).thenReturn(cart);
        when(request.getParameter("item")).thenReturn("Cake");

        removeCartItemServlet.doPost(request, response);

        verify(session).setAttribute("cart", cart);
        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertFalse(cart.contains("Cake"));
        assertTrue(cart.contains("Bread"));
    }

    @Test
    void testDoPost_EmptyCart() throws ServletException, IOException {
        List<String> cart = new ArrayList<>();
        cart.add("Cake");
        when(session.getAttribute("cart")).thenReturn(cart);
        when(request.getParameter("item")).thenReturn("Cake");

        removeCartItemServlet.doPost(request, response);

        verify(session).setAttribute("cart", cart);
        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertFalse(cart.contains("Cake"));
    }

    @Test
    void testDoPost_NullCart() throws ServletException, IOException {
        when(session.getAttribute("cart")).thenReturn(null);
        when(request.getParameter("item")).thenReturn("Cake");

        removeCartItemServlet.doPost(request, response);

        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
