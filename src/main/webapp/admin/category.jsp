<%@ page import="uz.pdp.shopping_project.repo.CategoryRepo" %>
<%@ page import="uz.pdp.shopping_project.entity.Category" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/1/2024
  Time: 10:16 AM
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


<div class="row">
    <div class="col-3 border-right p-4">
        <ul class="list-group">
            <a href="category.jsp">
                <li class="list-group-item bg-dark text-white"> Category</li>
            </a>
            <a href="product.jsp">
                <li class="list-group-item ">Product</li>
            </a>
        </ul>
    </div>
    <div class="col-9">

        <div class="row">
            <div class="col-2 offset-10 p-3">
                <a class="btn btn-dark text-white" href="addCategory.jsp">Add category</a>
            </div>
        </div>

        <div class="p-4">
            <hr>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>#</th>
                </tr>
                </thead>
                <tbody>
                    <%for (Category category: categories) {%>
                            <tr>
                                <td><%= category.getId()%></td>
                                <td><%= category.getName()%></td>
                                <td>
                                    <a href="editCategory.jsp?id=<%=category.getId()%>" class="btn btn-info text-white">edit</a>
                                    <a href="http://localhost:8080/category/delete?id=<%=category.getId()   %>" class="btn btn-dark text-white">delete</a>
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
