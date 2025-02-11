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

@WebServlet("/addEvent")
public class AddEventServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("name");
        String eventType = request.getParameter("eventType");
        String date = request.getParameter("date");
        String location = request.getParameter("location");
        String description = request.getParameter("description");

        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/event_management";
        String jdbcUsername = "root";
        String jdbcPassword = "admin"; // Change this to your actual database password

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // System.out.print("Date::" + date);
            // // Ensure the date format is correct (replace "T" with a space and add ":00" for seconds)
            // date = date.replace("T", " ") + ":00"; // Converts "2025-05-20T10:00" to "2025-05-20 10:00:00"

            // Convert the date string to a java.sql.Timestamp (MySQL DATETIME format)
            java.sql.Timestamp eventDate;
            try {
                eventDate = java.sql.Timestamp.valueOf(date + " 00:00:00");
            } catch (Exception e) {
                throw new IllegalArgumentException("Date is :" + date);
            }

            // Establish a database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            // SQL query to insert a new event into the database
            String sql = "INSERT INTO events (name, event_type, date, location, description) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            // Set values for the query parameters
            statement.setString(1, name);
            statement.setString(2, eventType);
            statement.setTimestamp(3, eventDate);
            statement.setString(4, location);
            statement.setString(5, description);

            // Execute the insert query
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Redirect to a page showing the new event has been added
                response.sendRedirect("eventAdded.jsp");
            } else {
                // Display an error if the event wasn't added
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException | ClassNotFoundException e) {
            // Log the error (for debugging) and redirect to error page
            e.printStackTrace();
            response.sendRedirect("error.jsp");
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
    }
}
