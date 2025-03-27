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

@WebServlet("/filterEvents")
public class FilterEventsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve filter parameters from the request
        String eventType = request.getParameter("event_type");
        String eventDate = request.getParameter("event_date");
        String location = request.getParameter("location");

        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/event_management";
        String jdbcUsername = "root";
        String jdbcPassword = "root"; // Update with your database password

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        StringBuilder query = new StringBuilder("SELECT * FROM events WHERE 1=1");

        // Building dynamic query based on filter parameters
        if (eventType != null && !eventType.isEmpty()) {
            query.append(" AND event_type = ?");
        }
        if (eventDate != null && !eventDate.isEmpty()) {
            query.append(" AND DATE(date) = ?");
        }
        if (location != null && !location.isEmpty()) {
            query.append(" AND location LIKE ?");
        }

        try {
            // Establish a database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            // Prepare the query statement
            statement = connection.prepareStatement(query.toString());

            // Set parameters dynamically based on the filters
            int paramIndex = 1;
            if (eventType != null && !eventType.isEmpty()) {
                statement.setString(paramIndex++, eventType);
            }
            if (eventDate != null && !eventDate.isEmpty()) {
                statement.setString(paramIndex++, eventDate);
            }
            if (location != null && !location.isEmpty()) {
                statement.setString(paramIndex++, "%" + location + "%"); // LIKE query for location
            }

            // Execute the query
            rs = statement.executeQuery();

            // Prepare the result set for displaying events
            StringBuilder resultHtml = new StringBuilder();
            while (rs.next()) {
                resultHtml.append("<div>");
                resultHtml.append("<p>Name: ").append(rs.getString("name")).append("</p>");
                resultHtml.append("<p>Description: ").append(rs.getString("description")).append("</p>");
                resultHtml.append("<p>Type: ").append(rs.getString("event_type")).append("</p>");
                resultHtml.append("<p>Date: ").append(rs.getString("date")).append("</p>");
                resultHtml.append("<p>Location: ").append(rs.getString("location")).append("</p>");
                resultHtml.append("</div><hr>");
            }

            // Return the results to the user
            if (resultHtml.length() == 0) {
                resultHtml.append("<p>No events found matching your filters.</p>");
            }
            response.setContentType("text/html");
            response.getWriter().write(resultHtml.toString());

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
