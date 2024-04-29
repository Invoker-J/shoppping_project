<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/1/2024
  Time: 9:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>

<body>

<nav class="navbar bg-body-tertiary bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Kaknus market</a>
        <div class="d-flex">

            <a class="btn btn-success text-white me-2 mx-1" href="http://localhost:8080/admin/orders.jsp">Orders</a>
            <a class="btn btn-success text-white me-2 mx-1" href="/auth/logout">Log out</a>
        </div>
    </div>
</nav>


    <div class="row">
        <div class="col-3 border-right p-4">
            <ul class="list-group">
                <a href="category.jsp"><li class="list-group-item"> Category</li></a>
                <a href="product.jsp"> <li class="list-group-item">Product</li></a>

            </ul>
        </div>
        <div class="col-9">

        </div>
    </div>


</body>
</html>
