<%@ page import="uz.pdp.shopping_project.entity.User" %>
<%@ page import="uz.pdp.shopping_project.repo.OrderRepo" %>
<%@ page import="uz.pdp.shopping_project.entity.Order" %>
<%@ page import="uz.pdp.shopping_project.repo.UserRepo" %>
<%@ page import="uz.pdp.shopping_project.entity.OrderProduct" %>
<%@ page import="uz.pdp.shopping_project.repo.ProductRepo" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Company Table</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <style>
        /* Custom CSS */
        .cur:before { content: "$"; }
        .per:after { content: "%"; }
    </style>
</head>
<body>


<nav class="navbar bg-body-tertiary bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Kaknus market</a>
        <div class="d-flex">

            <a class="btn btn-success text-white me-2 mx-1" href="/admin/admin.jsp">Home</a>
            <a class="btn btn-success text-white me-2 mx-1" href="/auth/logout">Log out</a>
        </div>
    </div>
</nav>


<%for (Order order : OrderRepo.findAll()) { %>


<div class="container pt-2">
    <table class="table fold-table">
        <thead class="thead-dark">
        <tr>
            <th>Id</th>
            <th>Date time</th>
            <th>User name</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <tr class="view">
            <td><%=order.getId()%></td>
            <td><%=order.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))%></td>
            <td><%=UserRepo.findByUserId( order.getUserId()).get().getUsername()%></td>
            <td><%=order.getStatus()%></td>

        </tr>
        <tr class="fold">
            <td colspan="7">
                <div class="collapse fold-content">
                    <div class="card card-body">
                        <h3><%=UserRepo.findByUserId( order.getUserId()).get().getUsername()%></h3>
                        <table class="table">
                            <thead class="thead-light">
                            <tr>

                                <th>Order id</th>
                                <th>Product id</th>
                                <th>Amount</th>

                            </tr>
                            </thead>
                            <tbody>
                            <tr><% for (OrderProduct orderProduct : OrderRepo.findOrdersByOrderId(order.getId())) { %>
                                <td><%=orderProduct.getOrderId()%></td>
                                <td><%=ProductRepo.findById(orderProduct.getProductId()).getName()%></td>
                                <td><%=orderProduct.getAmount()%></td>
                            </tr>
                            <!-- Diğer satırlar buraya eklenecek -->
                            </tbody>
                            <% }%>
                        </table>
                    </div>
                </div>
            </td>
        </tr>
        <!-- Diğer şirketlerin bilgileri buraya eklenecek -->
        </tbody>
    </table>
</div>
<% }%>
<!-- Bootstrap JS ve jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    $(function(){
        $(".fold-table tr.view").on("click", function(){
            // Tıklanan satırın altındaki fold-content'i bul
            var foldContent = $(this).next(".fold").find(".fold-content");

            // Tıklanan satırın açık veya kapalı olup olmadığını kontrol et
            if ($(this).hasClass("open")) {
                // Eğer tıklanan satır zaten açıksa, kapat
                $(this).removeClass("open");
                foldContent.collapse("hide");
            } else {
                // Diğer açık satırları kapat
                $(".fold-table tr.view.open").removeClass("open");
                $(".fold-table tr.fold.open").removeClass("open").find(".fold-content").collapse("hide");

                // Tıklanan satırı aç
                $(this).addClass("open");
                foldContent.collapse("show");
            }
        });
    });
</script>
</body>
</html>
