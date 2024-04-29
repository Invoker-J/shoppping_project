<%@ page import="java.util.UUID" %>
<%@ page import="uz.pdp.shopping_project.repo.CategoryRepo" %>
<%@ page import="uz.pdp.shopping_project.entity.Category" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/1/2024
  Time: 9:38 PM
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
    Category category = CategoryRepo.findById(id);
%>

<div class="row mt-4">
    <div class="col-4 offset-4">
        <div class="card p-2">
            <form action="http://localhost:8080/category/edit" method="post">
                <h1>Edit category</h1>
                <input name="id" type="hidden" value="<%=category.getId()%>">
                <input value="<%=category.getName()%>" name="name" autofocus class="form-control" type="text" placeholder="Name">
                <div class="text-center">
                    <button class="btn-dark my-4 btn-lg w-100 ">edit</button>
                </div>
            </form>
        </div>
    </div>
</div>


</body>
</html>
