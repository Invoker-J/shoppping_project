<%@ page import="java.util.UUID" %>
<%@ page import="uz.pdp.shopping_project.repo.ProductRepo" %>
<%@ page import="uz.pdp.shopping_project.entity.Product" %>
<%@ page import="uz.pdp.shopping_project.repo.CategoryRepo" %>
<%@ page import="uz.pdp.shopping_project.entity.Category" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/1/2024
  Time: 11:44 PM
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
    UUID id = UUID.fromString(request.getParameter("id"));
    Product product = ProductRepo.findById(id);
%>

<%
    List<Category> categories = CategoryRepo.findAll();
%>


<div class="row mt-4">
    <div class="col-4 offset-4">
        <div class="card p-2">
            <form action="http://localhost:8080/product/edit" method="post">
                <h1>Edit product</h1>
                <input name="id" type="hidden" value="<%=product.getId()%>">
                <input value="<%=product.getName()%>" name="name" autofocus class="form-control" type="text" placeholder="Name">
                <input value="<%=product.getPrice()%>" name="price" autofocus class="form-control" type="number" placeholder="Price">
                <select name="categoryId" class="form-control">
                    <option value="" selected disabled>Select category</option>
                    <%for (Category category : categories) {%>
                    <option value="<%=category.getId()%>">
                        <%=category.getName()%></option>
                    <% } %>
                </select>
                <div class="text-center">
                    <button class="btn-dark my-4 btn-lg w-100 ">edit</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
