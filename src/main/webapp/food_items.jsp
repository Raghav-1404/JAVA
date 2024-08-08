<!-- food_items.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Food Items</title>
    <link rel="stylesheet" href="./css/styles_1.css">
<a href="css/purchasejsp.jsp"></a>
</head>
<body>
<h2>Available Food Items</h2>
<c:if test="${param.success != null}">
    <p>Food item claimed successfully!</p>
</c:if>
<c:if test="${param.error != null}">
    <p>Error: Insufficient quantity to claim!</p>
</c:if>
<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Quantity</th>
            <th>Retailer</th>
            <th>Claim</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="foodItem" items="${foodItems}">
            <tr>
                <td>${foodItem.id}</td>
                <td>${foodItem.name}</td>
                <td>${foodItem.quantity}</td>
                <td>${foodItem.retailer}</td>
                <td>
                    <form action="claim-food" method="post">
                        <input type="hidden" name="id" value="${foodItem.id}"/>
                        <input type="number" name="quantity" min="1" max="${foodItem.quantity}"/>
                        <input type="submit" value="Claim"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
