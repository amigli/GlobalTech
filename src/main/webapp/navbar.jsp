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
    <%@ include file="links.html"%>
</head>
<body>
    <nav>
        <!--
        Aggiungere collegamento alla Home nel logo
        -->
       <div id="logo">
           <h2>
               <a href="#" style="text-decoration: none; color:white;">
               GlobalTech
               </a>
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
                            <a href="loginPage.jsp">
                                Login
                            </a>
                            <div id="login-box">
                                <%@include file="login.jsp"%>
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

        window.addEventListener("resize", function (){
            let  el  = document.getElementById("menu-items");

            if(window.innerWidth >= 769){
                el.style.display = "block"
            }else{
                el.style.display = "none"
            }
        })
    </script>
    <script src="script/SelectResultJquery.js" defer>
    </script>

</body>
</html>