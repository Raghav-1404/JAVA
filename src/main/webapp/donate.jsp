<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Donate Surplus Food</title>
    <link rel="stylesheet" type="text/css" href="css/donation.css">
</head>
<body>
    <div class="container">
        <h1>Donate Surplus Food</h1>
        <form action="donate" method="post">
            <label for="email">Your Email:</label>
            <input type="email" name="email" id="email" required><br><br>

            <label for="foodType">Food Type:</label>
            <input type="text" name="foodType" id="foodType" required><br><br>

            <label for="quantity">Quantity:</label>
            <input type="number" name="quantity" id="quantity" required><br><br>

            <label for="charityName">Charity Name:</label>
            <input type="text" name="charityName" id="charityName" required><br><br>

            <input type="submit" value="Donate">
        </form>
    </div>
</body>
</html>