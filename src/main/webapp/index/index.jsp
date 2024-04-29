<%@ page import="uz.pdp.shopping_project.repo.CategoryRepo" %>
<%@ page import="uz.pdp.shopping_project.entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.shopping_project.entity.Product" %>
<%@ page import="uz.pdp.shopping_project.repo.ProductRepo" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.nio.file.Files" %>
<%@ page import="java.nio.file.Path" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/1/2024
  Time: 12:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>


<head>
    <title>Kaknus market</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>
<body>



<%
    List<Category> categories = CategoryRepo.findAll();
%>
<%
    List<Product> products = ProductRepo.findAll();
%>


<nav class="navbar bg-body-tertiary bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Kaknus market</a>
        <div class="d-flex">
            <a class="btn btn-success text-white me-2 mx-3" href="admin/basket.jsp">🛒</a>
            <a class="btn btn-success text-white" href="admin/admin.jsp">Admin</a>
        </div>
    </div>
</nav>

<div class="row">
    <div class="col-3 border-right p-4">
        <ul class="list-group">
            <%for (Category category : categories) {%>
            <a href="admin/pageByCategory.jsp?id=<%=category.getId()%>">
                <li class="list-group-item"><%=category.getName()%>
                </li>
            </a>
            <% } %>
        </ul>
    </div>

    <div class="col-9">
        <div class="row">
            <% for (Product product : products) { %>
                    <div class="col-3">
                        <div class="card p-3">
                            <img src="data:image/jpeg;base64,<%=Base64.getEncoder().encodeToString(product.getPhoto())%>">
                            <h4 margin="0"><%=product.getName()%></h4>
                            <h5 margin="0">Price: <%=product.getPrice() %>$</h5>
                            <a href="http://localhost:8080/add/category?id=<%=product.getId()%>" class="btn btn-outline-dark text-center align-text-bottom bg-info">
                                Choose
                            </a>
                        </div>
                    </div>
                <% } %>
        </div>
    </div>


</div>

</body>
</html>
