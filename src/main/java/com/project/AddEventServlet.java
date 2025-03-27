package com.project;

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

import com.google.gson.JsonObject;

@WebServlet("/addEvent")
public class AddEventServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        JsonObject jsonResponse = new JsonObject();

        // Retrieve form data
        String name = request.getParameter("name");
        String eventType = request.getParameter("eventType");
        String date = request.getParameter("date");
        String location = request.getParameter("location");
        String description = request.getParameter("description");

        if (name == null || name.trim().isEmpty()) {
            jsonResponse.addProperty("status", "error");
            jsonResponse.addProperty("message", "Event name is required");
            response.getWriter().write(jsonResponse.toString());
            return;
        }

        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/event_management";
        String jdbcUsername = "root";
        String jdbcPassword = "root";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            // SQL query
            String sql = "INSERT INTO events (name, event_type, date, location, description) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            // Set parameters
            statement.setString(1, name);
            statement.setString(2, eventType);
            statement.setString(3, date);
            statement.setString(4, location);
            statement.setString(5, description);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                jsonResponse.addProperty("status", "success");
                jsonResponse.addProperty("message", "Event created successfully!");
            } else {
                jsonResponse.addProperty("status", "error");
                jsonResponse.addProperty("message", "Failed to create event. Please try again.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            jsonResponse.addProperty("status", "error");
            jsonResponse.addProperty("message", "Database error: -- " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            jsonResponse.addProperty("status", "error");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        response.getWriter().write(jsonResponse.toString());
    }
}
