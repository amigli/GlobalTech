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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

</head>
<body>
    <nav>
       <div id="logo">
           <h2 >
               GlobalTech
           </h2>
       </div>
        <ul class="nav">
                <li id="search-item">
                    <form id="search-form" action=#>
                        <input type="text" placeholder="Inizia a digitare..." name="keyword" id="search-box">
                        <button type="submit" id="search-button"><i class="fa fa-search"></i></button>
                        <div id="suggest">

                        </div>
                    </form>
                    <div id="burger" >
                        <span class="bar"></span>
                        <span class="bar"></span>
                        <span class="bar"></span>
                    </div>
                </li>

                <li id="menu-items">
                    <ul id="menu-items-list">
                        <li>
                            <a href="#">Catalogo</a>
                        </li>
                        <li>
                            <a href="#">Offerte</a>
                        </li>

                        <li id="login-item">
                            <a href="#">
                                Login
                            </a>
                            <div id="login-box">
                                <-!%@include file="FormAccesso.jsp"%->
                            </div>
                        </li>
                    </ul>
                </li>
        </ul>
    </nav>
    <script type="text/javascript">
        function mostraMenu(){
            //const menu =  document.querySelector("#menu-items");
            const burger = document.getElementById("burger");
            //menu.classList.toggle("active");
            burger.classList.toggle("active");


        }
        $(document).ready(
            function (){
                $("#burger").click(
                    function () {
                        $("#menu-items").toggle();
                    }
                )
            }
        );


    </script>
    <script src="script/SelectResultJquery.js" defer>
    </script>

</body>
</html>