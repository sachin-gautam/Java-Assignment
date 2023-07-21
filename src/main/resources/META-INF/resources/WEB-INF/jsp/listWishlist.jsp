<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
            <link href="webjars\bootstrap\5.1.3\css\bootstrap.min.css" rel="stylesheet">
            <title>List Wishlist Page</title>
    </head>
    <body>
        <div class = "container">
                <h1>Your wishlist</h1>
                <table class = "table">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Description</th>
                        <th>Completion Date</th>
                        <th>Is Done ?</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${wishlists}" var="wishlist">
                        <tr>
                            <td>${wishlist.id}</td>
                            <td>${wishlist.description}</td>
                            <td>${wishlist.targetDate}</td>
                            <td>${wishlist.done}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
            </table>
            <a href="add-wishlist" class="btn btn-success">Add new</a>
        </div>
        <script src = "webjars\bootstrap\5.1.3\js\bootstrap.min.js"></script>
        <script src = "webjars\jquery\3.6.0\jquery.min.js"></script>
    </body>
</html>
