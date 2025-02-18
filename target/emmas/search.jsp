<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search and Filter Events</title>
</head>
<body>
    <h1>Search and Filter Events</h1>
    
    <form action="filterEvents" method="get">
        <label for="event_type">Event Type:</label>
        <select name="event_type" id="event_type">
            <option value="">All</option>
            <option value="Concert">Wedding</option>
            <option value="Conference">Conference</option>
            <option value="Workshop">Workshop</option>
            <option value="Seminar">Party</option>
        </select><br>

        <label for="event_date">Event Date:</label>
        <input type="date" id="event_date" name="event_date"><br>

        <label for="location">Location:</label>
        <input type="text" id="location" name="location" placeholder="Enter location"><br>

        <button type="submit">Search</button>
    </form>

    <hr>

    <h2>Event List</h2>
    <div id="event-list">
        <!-- Event results will be displayed here -->
    </div>
</body>
</html>
