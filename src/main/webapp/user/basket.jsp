<%@ page import="uz.pdp.shopping_project.entity.Product" %>
<%@ page import="uz.pdp.shopping_project.servlets.BasketServlet" %>
<%@ page import="uz.pdp.shopping_project.repo.ProductRepo" %>
<%@ page import="uz.pdp.shopping_project.repo.BasketRepo" %>
<%@ page import="java.util.Map" %>
<%@ page import="uz.pdp.shopping_project.repo.CategoryRepo" %>
<%@ page import="uz.pdp.shopping_project.entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.shopping_project.repo.UserRepo" %>
<%@ page import="uz.pdp.shopping_project.entity.User" %>
<%@ page import="uz.pdp.shopping_project.entity.Basket" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/2/2024
  Time: 4:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>
<style>
    .customButton {
        border:5.7px solid #02FFA1;
        border-radius:41.4px;
        padding:16.5px;
        margin:73.8px;
        background-color:#0ED80E;
        color:#000000;
        font-weight:normal;
        opacity:1;
        transition:4.8s;
    }
    .customButton:hover {
        cursor:pointer;
        background-color:#0FF90E;
        opacity:0.6;
        transition:2.3s;
        color:#000000;
    }

</style>
<body>
<%
    Object object = session.getAttribute("basket");
    Basket basket = new Basket();
    if (object != null) {
        basket = (Basket) object;
    }

    List<Category> categories = CategoryRepo.findAll();
%>
<nav class="navbar bg-body-tertiary bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Kaknus market</a>
        <div class="d-flex">
            <a class="btn btn-success text-white" href="../index.jsp">Home</a>
        </div>
    </div>
</nav>


<div class="row">
    <div class="col-3 border-right p-4">
        <ul class="list-group">
            <%for (Category category : CategoryRepo.findAll()) { %>
            <a href="basket.jsp"><li class="list-group-item"><%=category.getName()%></li></a>

            <% }%>
        </ul>
    </div>


    <div class="col-9">
        <div class="p-4">
            <hr>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Category</th>
                    <th>Total amount</th>
                    <th>#</th>
                </tr>
                </thead>
                <tbody>


                <%--            <%for (Product product: ProductRepo.getAllProductsByCategory()) {%>--%>
                <%--            <tr>--%>
                <%--                <td><%= product.getId()%></td>--%>
                <%--                <td><%= product.getName()%></td>--%>
                <%--                <td><%= product.getPrice()%></td>--%>
                <%--                <td><%= product.getCategoryName()%></td>--%>
                <%--                <td>--%>
                <%--                    <a href="http://localhost:8080/amount/product?id=<%=product.getId()%>" class="btn btn-info text-white">➖</a>--%>
                <%--                    <a href="basket.jsp" class="btn btn-info text-white"><%=ProductRepo.countProducts(product.getId())%></a>--%>
                <%--                    <a href="http://localhost:8080/amount/product?id=<%=product.getId()%>" class="btn btn-info text-white">➕</a>--%>
                <%--                </td>--%>
                <%--            </tr>--%>
                <%--            <%}%>--%>
                <%if(categories.isEmpty()){%>
                <h1>Sizda mahsulotlar  yo'q</h1>
                <% return;}
                int sum=0;
                %>
                <% for (Map.Entry<Product, Integer> productIntegerEntry : basket.getBasketProducts().entrySet()) { %>
                <tr>
                    <td><%= productIntegerEntry.getKey().getId()%>
                    </td>
                    <td><%= productIntegerEntry.getKey().getName()%>
                    </td>
                    <td><%= productIntegerEntry.getKey().getPrice()%>
                    </td>
                    <td><%= productIntegerEntry.getKey().getCategoryName()%>
                    </td>
                    <td><%= productIntegerEntry.getKey().getPrice()*productIntegerEntry.getValue()%>
                    </td>
                    <%sum+=productIntegerEntry.getKey().getPrice()*productIntegerEntry.getValue();%>

                    <td>
                        <a href="http://localhost:8080/amount/product?id=<%=productIntegerEntry.getKey().getId()+"minus"%>"
                           class="btn btn-info text-white">➖</a>
                        <a href="basket.jsp" class="btn btn-info text-white"><%=productIntegerEntry.getValue()%>
                        </a>
                        <a href="http://localhost:8080/amount/product?id=<%=productIntegerEntry.getKey().getId() + "plus"%>"
                           class="btn btn-info text-white">➕</a>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>

        <div style="text-align: center;">
            <div class="html_button btn-left">
                <a href="http://localhost:8080/save/db" class="btn customButton large">
                    Buyurtma berish: <%=sum%> $
                </a>
            </div>
        </div>
    </div>



</div>
</body>
</html>
