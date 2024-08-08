<!DOCTYPE html>
<html>
<head>
    <title>Purchase Items</title>
    <link rel="stylesheet" type="text/css" href="styles_2.css">

</head>
<body>
    <h1>Purchase Items</h1>
    <form action="purchase" method="post">
        <label for="itemId">Item ID:</label>
        <input type="text" id="itemId" name="itemId" required><br>
        <label for="quantity">Quantity:</label>
        <input type="text" id="quantity" name="quantity" required><br>
        <input type="submit" value="Purchase">
    </form>
    <h2>Items Available</h2>
    <ul>
        <c:forEach var="item" items="${items}">
            <li>${item.id} - ${item.name} - $${item.price}</li>
        </c:forEach>
    </ul>
    <h2>${message}</h2>
</body>
</html>
