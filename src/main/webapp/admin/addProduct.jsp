<%@ page import="uz.pdp.shopping_project.repo.CategoryRepo" %>
<%@ page import="uz.pdp.shopping_project.entity.Category" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/1/2024
  Time: 11:30 PM
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
    List<Category> categories = CategoryRepo.findAll();
%>
<div class="row mt-4">
    <div class="col-4 offset-4">
        <div class="card p-2">
            <form enctype="multipart/form-data" action="http://localhost:8080/product/add" method="post">
                <h1>Add product</h1>
                    <input type="file" name="photo" autofocus>
                <input name="name" autofocus class="form-control" type="text" placeholder="Name">
                <input name="price" autofocus class="form-control" type="number" placeholder="Price">
                <select name="categoryId" class="form-control">
                    <option value="" selected disabled>Select category</option>
                    <%for (Category category : categories) {%>
                    <option value="<%=category.getId()%>">
                        <%=category.getName()%></option>
                    <% } %>
                </select>
                <div class="text-center">
                    <button class="btn-dark my-4 btn-lg w-100 ">save</button>
                </div>
            </form>
        </div>
    </div>
</div>


</body>
</html>
