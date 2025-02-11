package com.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rsvpEvent")
public class RSVPServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        int eventId = Integer.parseInt(request.getParameter("event_id"));
        String rsvpStatus = request.getParameter("rsvp_status");
        int userId = 123; // Assuming the user ID is retrieved from the session or logged-in user (replace with actual user ID)

        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/event_management";
        String jdbcUsername = "root";
        String jdbcPassword = "admin"; // Update with your database password

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Establish a database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            // Check if the user has already RSVPed for this event
            String checkRSVP = "SELECT * FROM rsvps WHERE user_id = ? AND event_id = ?";
            statement = connection.prepareStatement(checkRSVP);
            statement.setInt(1, userId);
            statement.setInt(2, eventId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                // Update the existing RSVP
                String updateRSVP = "UPDATE rsvps SET rsvp_status = ? WHERE user_id = ? AND event_id = ?";
                statement = connection.prepareStatement(updateRSVP);
                statement.setString(1, rsvpStatus);
                statement.setInt(2, userId);
                statement.setInt(3, eventId);
            } else {
                // Insert a new RSVP entry
                String insertRSVP = "INSERT INTO rsvps (user_id, event_id, rsvp_status) VALUES (?, ?, ?)";
                statement = connection.prepareStatement(insertRSVP);
                statement.setInt(1, userId);
                statement.setInt(2, eventId);
                statement.setString(3, rsvpStatus);
            }

            // Execute the update/insert query
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Redirect to a page showing the successful RSVP submission
                response.sendRedirect("rsvpSuccess.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

