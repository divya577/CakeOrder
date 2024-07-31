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

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ConnectionProvider connectionProvider;

    public class DefaultConnectionProvider implements ConnectionProvider {
        private String jdbcURL = "jdbc:mysql://localhost:3306/cake_db";
        private String jdbcUsername = "root";
        private String jdbcPassword = "RootL1";

        @Override
        public Connection getConnection() throws SQLException {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Use the updated driver class name
                return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            } catch (ClassNotFoundException e) {
                throw new SQLException("JDBC Driver not found", e);
            }
        }
    }

    public interface ConnectionProvider {
        Connection getConnection() throws SQLException;
    }

    // Constructor for dependency injection
    public DeleteUserServlet(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    // Default constructor for regular use
    public DeleteUserServlet() {
        this.connectionProvider = new DefaultConnectionProvider();
    }

    static final String DELETE_USER_SQL = "DELETE FROM userdetails WHERE name = ?;";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        if (name == null || name.trim().isEmpty()) {
            // Handle invalid name (empty or null)
            response.sendRedirect("login.jsp");
            return;
        }

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Log error and set an error message for the user if needed
        }

        response.sendRedirect("login.jsp");
    }
}
