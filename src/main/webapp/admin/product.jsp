<%@ page import="uz.pdp.shopping_project.repo.ProductRepo" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.shopping_project.entity.Product" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/1/2024
  Time: 10:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">

</head>
<body>

<%
    List<Product> products = ProductRepo.findAll();
%>

<div class="row">
    <div class="col-3 border-right p-4">
        <ul class="list-group">
            <a href="category.jsp">
                <li class="list-group-item "> Category</li>
            </a>
            <a href="product.jsp">
                <li class="list-group-item bg-dark text-white">Product</li>
            </a>
        </ul>
    </div>
    <div class="col-9">


        <div class="row">
            <div class="col-2 offset-10 p-3">
                <a class="btn btn-dark text-white" href="addProduct.jsp">Add product</a>
            </div>
        </div>

        <div class="p-4">
            <hr>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Category</th>
                    <th>#</th>
                </tr>
                </thead>
                <tbody>
                <%for (Product product: products) {%>
                <tr>
                    <td><%= product.getId()%></td>
                    <td><%= product.getName()%></td>
                    <td><%= product.getPrice()%></td>
                    <td><%= product.getCategoryName()%></td>
                    <td>
                        <a href="editProduct.jsp?id=<%=product.getId()%>" class="btn btn-info text-white">edit</a>
                        <a href="http://localhost:8080/product/delete?id=<%=product.getId()   %>" class="btn btn-dark text-white">delete</a>
                    </td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>





    </div>
</div>


</body>
</html>
