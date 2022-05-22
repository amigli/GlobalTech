<%--
  Created by IntelliJ IDEA.
  User: migli
  Date: 22/05/2022
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Navbar</title>
    <link type="text/css" rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <nav>
        <ul class="nav">
            <li>
                <a href="#">Home</a>
            </li>
            <li>
                <a href="#">Catalogo</a>
            </li>
            <li >
                <form class="search-form">
                    <input type="text" placeholder="Inzia a digitare..." name="keyword" id="search-box">
                    <button type="submit" id="search-button"><i class="fa fa-search"></i></button>
                </form>

            </li>
            <li class="login-item">
                <a href="#">
                    Login
                </a>
            </li>

        </ul>
    </nav>

</body>
</html>
