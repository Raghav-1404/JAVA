<html>
<head>
    <title>Subscriptions for Location</title>
</head>
<body>
    <h1>Subscriptions for <%= request.getParameter("location") %></h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Location</th>
            <th>Food Preferences</th>
            <th>Communication Method</th>
        </tr>
        <%
            List<UserSubscriptionDTO> subscriptions = (List<UserSubscriptionDTO>) request.getAttribute("subscriptions");
            for (UserSubscriptionDTO subscription : subscriptions) {
        %>
        <tr>
            <td><%= subscription.getId() %></td>
            <td><%= subscription.getUserEmail() %></td>
            <td><%= subscription.getUserPhone() %></td>
            <td><%= subscription.getLocation() %></td>
            <td><%= subscription.getFoodPreferences() %></td>
            <td><%= subscription.getCommunicationMethod() %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>