<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/2/2024
  Time: 1:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        .container {
            display: flex;
            justify-content: space-around;
            align-items: center;
            height: 100vh;
        }

        .left-side, .right-side {
            width: 45%;
            border: 1px solid #ccc;
            padding: 20px;
        }

        .left-side {
            order: 2;
        }

        .right-side {
            order: 1;
        }

        .in-progress, .progress {
            text-align: center;
            margin: 0;
            padding: 10px;
        }

        .in-progress {
            background-color: #f44336;
            color: #fff;
        }

        .progress {
            background-color: #4caf50;
            color: #fff;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="left-side">
        <h2 class="in-progress">IN Progress</h2>
    </div>
    <div class="right-side">
        <h2 class="progress">Progress</h2>
    </div>
</div>


</body>
</html>
