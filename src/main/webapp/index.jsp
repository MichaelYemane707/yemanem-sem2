<%@ page import="java.sql.*" %>
<html>
  <body>
    <% String url= "jdbc:mysql://localhost:3306/event_management"; String sql =
    "select distinct * from events"; Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection(url,"root","admin"); Statement
    st = con.createStatement(); ResultSet rs = st.executeQuery(sql);
    while(rs.next()){ %>
    <p>ID:<%= rs.getString("event_id") %></p>
    <p>Name: <%= rs.getString("name") %></p>
    <p>Description: <%= rs.getString("description") %></p>
    <p>Type: <%= rs.getString("event_type") %></p>
    <p>Date: <%= rs.getString("date") %></p>
    <p>Location: <%= rs.getString("location") %></p>
    <% } con.close(); %>

    <h2>
      <strong>RSVP Event</strong>
    </h2>
    <div>
      <form action="rsvpEvent" method="POST">
        <input name="event_id" value="eventIdValue" />
        <!-- The event ID -->

        <label for="rsvp_status">RSVP Status:</label>
        <select name="rsvp_status" id="rsvp_status">
          <option value="Yes">Yes</option>
          <option value="No">No</option>
          <option value="Maybe">Maybe</option>
        </select>

        <input type="submit" value="Submit RSVP" />
      </form>
    </div>
  </body>
</html>
