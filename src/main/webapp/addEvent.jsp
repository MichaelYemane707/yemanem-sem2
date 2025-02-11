<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Add New Event</title>
  </head>
  <body>
    <h1>Create a New Event</h1>

    <form action="addEvent" method="POST">
      <label for="name">Event Name:</label>
      <input type="text" id="name" name="name" required /><br /><br />

      <label for="eventType">Event Type:</label>
      <select id="eventType" name="eventType" required>
        <option value="Conference">Conference</option>
        <option value="Wedding">Wedding</option>
        <option value="Workshop">Workshop</option>
        <option value="Party">Party</option></select
      ><br /><br />

      <label for="date">Event Date:</label>
      <input type="date" id="date" name="date" required /><br /><br />

      <label for="location">Location:</label>
      <input type="text" id="location" name="location" required /><br /><br />

      <label for="description">Description:</label>
      <textarea id="description" name="description" required></textarea
      ><br /><br />

      <input type="submit" value="Create Event" />
    </form>
  </body>
</html>
