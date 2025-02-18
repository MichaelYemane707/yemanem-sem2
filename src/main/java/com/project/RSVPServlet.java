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

// @WebServlet("/rsvpEvent")
// public class RSVPServlet extends HttpServlet {
//     @Override
//     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         // Retrieve form data
//         int eventId = Integer.parseInt(request.getParameter("event_id"));
//         String rsvpStatus = request.getParameter("rsvp_status");
//         // Database connection details
//         String jdbcURL = "jdbc:mysql://localhost:3306/event_management";
//         String jdbcUsername = "root";
//         String jdbcPassword = "admin"; // Update with your database password
//         Connection connection = null;
//         PreparedStatement statement = null;
//         try {
//             // Establish a database connection
//             Class.forName("com.mysql.cj.jdbc.Driver");
//             connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
//             System.out.println(connection);
//             // Check if the user has already RSVPed for this event
//             String checkRSVP = "SELECT * FROM rsvps WHERE event_id = ?";
//             statement = connection.prepareStatement(checkRSVP);
//             statement.setInt(1, eventId);
//             ResultSet rs = statement.executeQuery();
//             if (rs.next()) {
//                 // Update the existing RSVP
//                 String updateRSVP = "UPDATE rsvps SET rsvp_status = ? WHERE event_id = ?";
//                 statement = connection.prepareStatement(updateRSVP);
//                 statement.setString(1, rsvpStatus);
//                 statement.setInt(2, eventId);
//                 int rowsAffected = statement.executeUpdate();
//                 if (rowsAffected > 0) {
//                     // Update attendees count if the RSVP status is "Yes"
//                     if (rsvpStatus.equals("Yes")) {
//                         String updateAttendees = "UPDATE events SET attendees_count = (SELECT COUNT(*) FROM rsvps WHERE event_id = ? AND rsvp_status = 'Yes') WHERE event_id = ?";
//                         statement = connection.prepareStatement(updateAttendees);
//                         statement.setInt(1, eventId);
//                         statement.setInt(2, eventId);
//                         statement.executeUpdate();
//                     }
//                     response.sendRedirect("rsvpSuccess.jsp");
//                 } else {
//                     response.sendRedirect("error.jsp");
//                 }
//             } else {
//                 // Insert a new RSVP entry
//                 String insertRSVP = "INSERT INTO rsvps (event_id, rsvp_status) VALUES (?, ?)";
//                 statement = connection.prepareStatement(insertRSVP);
//                 statement.setInt(1, eventId);
//                 statement.setString(2, rsvpStatus);
//                 int rowsAffected = statement.executeUpdate();
//                 if (rowsAffected > 0) {
//                     // Update attendees count if the RSVP status is "Yes"
//                     if (rsvpStatus.equals("Yes")) {
//                         String updateAttendees = "UPDATE events SET attendees_count = (SELECT COUNT(*) FROM rsvps WHERE event_id = ? AND rsvp_status = 'Yes') WHERE event_id = ?";
//                         statement = connection.prepareStatement(updateAttendees);
//                         statement.setInt(1, eventId);
//                         statement.setInt(2, eventId);
//                         statement.executeUpdate();
//                     }
//                     response.sendRedirect("rsvpSuccess.jsp");
//                 } else {
//                     response.sendRedirect("error.jsp");
//                 }
//             }
//         } catch (SQLException | ClassNotFoundException e) {
//             e.printStackTrace();
//             response.sendRedirect("error.jsp");
//         } finally {
//             try {
//                 if (statement != null) {
//                     statement.close();
//                 }
//                 if (connection != null) {
//                     connection.close();
//                 }
//             } catch (SQLException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
// }
@WebServlet("/rsvpEvent")
public class RSVPServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        int eventId = Integer.parseInt(request.getParameter("event_id"));
        String rsvpStatus = request.getParameter("rsvp_status");

        System.out.println("Event ID: " + eventId);
        System.out.println("Rsvp: " + rsvpStatus);

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
            System.out.println(connection);

            // Check if the user has already RSVPed for this event
            // String checkRSVP = "SELECT * FROM rsvps WHERE event_id = ?";
            // statement = connection.prepareStatement(checkRSVP);
            // statement.setInt(1, eventId);
            // ResultSet rs = statement.executeQuery();

            // if (rs.next()) {
            //     // Update the existing RSVP
            //     System.out.println("Rsvp found: " + rs.getInt(1) + rs.getInt(2) + rs.getString(3));

            //     String updateRSVP = "UPDATE rsvps SET rsvp_status = ? WHERE event_id = ?";
            //     statement = connection.prepareStatement(updateRSVP);
            //     statement.setString(1, rsvpStatus);
            //     statement.setInt(2, eventId);
            //     int rowsAffected = statement.executeUpdate();

            //     if (rowsAffected > 0) {
            //         // Update attendees count if the RSVP status is "Yes"
            //         if (rsvpStatus.equals("Yes")) {
            //             updateAttendeesCount(connection, eventId);
            //         }
            //         response.sendRedirect("rsvpSuccess.jsp");
            //     } else {
            //         response.sendRedirect("error.jsp");
            //     }
            // } else {
                // Insert a new RSVP entry
                String insertRSVP = "INSERT INTO rsvps (event_id, rsvp_status) VALUES (?, ?)";
                System.out.println("statement " + insertRSVP);
                statement = connection.prepareStatement(insertRSVP);
                statement.setInt(1, eventId);
                statement.setString(2, rsvpStatus);
                int rowsAffected = statement.executeUpdate();

                System.out.println("rows affected: " + rowsAffected);
                if (rowsAffected > 0) {
                    // Update attendees count if the RSVP status is "Yes"
                    if (rsvpStatus.equals("Yes")) {
                        updateAttendeesCount(connection, eventId);
                    }
                    response.sendRedirect("rsvpSuccess.jsp");
                } else {
                    response.sendRedirect("error.jsp");
                }
            // }
        } catch (SQLException | ClassNotFoundException e) {
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

    // Helper method to update the attendees count
    private void updateAttendeesCount(Connection connection, int eventId) throws SQLException {
        // Update the attendees_count for the event based on the "Yes" RSVPs
        String updateAttendees = "UPDATE events SET attendees_count = (SELECT COUNT(*) FROM rsvps WHERE event_id = ? AND rsvp_status = 'Yes') WHERE event_id = ?";
        PreparedStatement statement = connection.prepareStatement(updateAttendees);
        statement.setInt(1, eventId);
        statement.setInt(2, eventId);
        statement.executeUpdate();
    }
}
