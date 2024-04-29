<%@ page import="uz.pdp.shopping_project.repo.CategoryRepo" %>
<%@ page import="uz.pdp.shopping_project.entity.Category" %>
<%@ page import="uz.pdp.shopping_project.repo.ProductRepo" %>
<%@ page import="uz.pdp.shopping_project.entity.Product" %>
<%@ page import="uz.pdp.shopping_project.repo.BasketRepo" %>
<%@ page import="java.util.*" %>
<%@ page import="uz.pdp.shopping_project.repo.UserRepo" %>
<%@ page import="uz.pdp.shopping_project.entity.User" %>
<%@ page import="uz.pdp.shopping_project.servlets.BasketServlet" %>
<%@ page import="uz.pdp.shopping_project.entity.Basket" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/2/2024
  Time: 3:07 AM
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
//    if (request.getParameter("id") == null) {
//        System.out.println("NUll keldi");
//        return;
//    }
//    User user = UserRepo.getUser(session);

    User user = null;

    Object obj = session.getAttribute("currentUser");
    if (obj != null) {
        user = (User) obj;
    }


    Optional<User> optionalUser = UserRepo.getUserByCookie(request);
    if (optionalUser.isPresent()) {
        user = optionalUser.get();
    }


    Object object = session.getAttribute("basket");
    Basket basket = new Basket();
    if (object != null) {
        basket = (Basket) object;
    }



    UUID id = UUID.fromString(request.getParameter("id"));
    List<Product> products = ProductRepo.getAllProductsByCategory(id);
    List<Category> categories = CategoryRepo.findAll();

%>
<nav class="navbar bg-body-tertiary bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Kaknus market</a>
        <div class="d-flex">
            <a class="nav-link btn btn-success text-white me-2 mx-1" href="basket.jsp">
                Basket
                <span class="badge badge-danger"><%=basket.getBasketProducts().size()%></span>
            </a>
            <%System.out.println("In pageByCate: "+user);%>
            <% if (user != null) { %>
            <a class="btn btn-success text-white me-2 mx-1" href="../auth/logout">Log out</a>
            <% } else { %>
            <a class="btn btn-success text-white me-2 mx-1" href="../login.jsp">Login</a>
            <a class="btn btn-success text-white me-2 mx-1" href="../signup.jsp">Sign up</a>
            <% } %>
        </div>
    </div>
</nav>

<div class="row">
    <div class="col-3 border-right p-4">
        <ul class="list">
            <%for (Category category : categories) {%>
            <%if (category.getId().equals(id)) {%>

            <a href="pageByCategory.jsp?id=<%=category.getId()%>">
                <li class="list-group-item bg-dark text-white"><%=category.getName()%>
                </li>
            </a>

            <% continue;
            } %>
            <a href="pageByCategory.jsp?id=<%=category.getId()%>">
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
                <div class="card p-3" >
                    <%if(Objects.equals(product.getPhoto(),null)){%>
                        <% System.out.println(Arrays.toString(product.getPhoto()));
                                continue;}%>
                    <img src="data:image/jpeg;base64,<%=Base64.getEncoder().encodeToString(product.getPhoto())%>" alt="topilmadi">
                    <h4 margin="0"><%=product.getName()%></h4>
                    <h5 margin="0">Price: <%=product.getPrice() %>$</h5>


                    <%if (basket.getBasketProducts().containsKey(product)) { %>
                    <a href="#"
                       class="btn btn-outline-dark text-center align-text-bottom bg-secondary">
                        Chosen
                    </a>
                    <% } else {%>
                    <a href="http://localhost:8080/add/category?id=<%=product.getId()%>"
                       class="btn btn-outline-dark text-center align-text-bottom bg-info">
                        Choose
                    </a>
                    <% } %>
                </div>
            </div>
            <% } %>
        </div>
    </div>

</div>

</body>
</html>