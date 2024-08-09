<html>
<head>
    <title>Subscribe to Surplus Food Alerts</title>
</head>
<body>
    <h1>Subscribe to Surplus Food Alerts</h1>
    <form action="subscribe" method="post">
        <label for="email">Email:</label>
        <input type="email" name="email" id="email" required><br><br>

        <label for="phone">Phone:</label>
        <input type="text" name="phone" id="phone"><br><br>

        <label for="location">Location:</label>
        <input type="text" name="location" id="location" required><br><br>

        <label for="foodPreferences">Food Preferences:</label>
        <input type="text" name="foodPreferences" id="foodPreferences" required><br><br>

        <label for="communicationMethod">Communication Method:</label>
        <select name="communicationMethod" id="communicationMethod" required>
            <option value="email">Email</option>
            <option value="phone">Phone</option>
        </select><br><br>

        <input type="submit" value="Subscribe">
    </form>
</body>
</html>