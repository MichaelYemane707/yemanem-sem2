<%@ page import="java.sql.*" %>
<html>
  <body>
    <% String path = "C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/mydb.db"; String url
    = "jdbc:sqlite:"+path; String sql = "select * from events where id=2";
    Class.forName("org.sqlite.JDBC"); Connection con =
    DriverManager.getConnection(url,"",""); Statement st =
    con.createStatement(); ResultSet rs = st.executeQuery(sql); rs.next(); %>
    <p>ID: <%= rs.getString(1) %></p>
    <p>Name: <%= rs.getString("name") %></p>
    <p>Type: <%= rs.getString("type") %></p>
    <p>Date: <%= rs.getString("date") %></p>
    <p>Location: <%= rs.getString("location") %></p>
    <% con.close(); %>
  </body>
</html>
