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
    <link rel="stylesheet" href="static/bootstrap.min.css">
</head>
<body>

<div class="row">
    <div class="col-4 offset-4">
        <div class="card">
            <div class="card-header bg-dark text-white text-center">
                Login
            </div>
            <div class="card-body">
                <form action="/auth/login" method="post">
                    <div>
                        <input type="text" placeholder="Username..." name="username" class="form-control mt-2">
                    </div>
                    <div>
                        <input type="password" placeholder="Password..." name="password" class="form-control mt-2">
                    </div>
                    <div>
                        <label class="form-check-label mt-2">
                            <input name="rememberMe" class="form-check" type="checkbox">Remember me
                        </label>
                    </div>
                    <br>
                    <div class="card text-center" >
                        <button class="btn btn-primary btn-primary text-center " >
                            Login
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>



</body>
</html>
