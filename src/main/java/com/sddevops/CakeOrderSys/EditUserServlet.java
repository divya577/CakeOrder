package com.sddevops.CakeOrderSys;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

  //Prepare list of variables used for database connections
    private String jdbcURL = "jdbc:mysql://localhost:3306/cake_db";
    private String jdbcUsername = "root";
    private String jdbcPassword = "RootL1";

    static final String UPDATE_USER_SQL = "UPDATE userdetails SET password = ? WHERE name = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String newPassword = request.getParameter("newPassword");

     // Password needs to 8 characters long 

        if (newPassword.length() < 8) { 

            request.setAttribute("errorMessage", "Password must be at least 8 characters long."); 

            request.getRequestDispatcher("/editUser.jsp").forward(request, response); 

            return; 

        } 

        try (Connection connection = getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("login.jsp");
    }
}
