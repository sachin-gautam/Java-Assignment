<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class = "container">
   <h1>Your wishlist</h1>
    <table class = "table">
            <thead>
            <tr>
                <th>Description</th>
                <th>Completion Date</th>
                <th>Is Done ?</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${wishlists}" var="wishlist">
                <tr>
                    <td>${wishlist.description}</td>
                    <td>${wishlist.targetDate}</td>
                    <td>${wishlist.done}</td>
                    <td><a href="delete-wishlist?id=${wishlist.id}" class = "btn btn-warning" >Delete</a></td>
                    <td><a href="update-wishlist?id=${wishlist.id}" class = "btn btn-success" >Update </a></td>
                </tr>
            </c:forEach>
            </tbody>
    </table>
    <a href="add-wishlist" class="btn btn-success">Add new</a>
</div>
<%@ include file="common/footer.jspf" %>