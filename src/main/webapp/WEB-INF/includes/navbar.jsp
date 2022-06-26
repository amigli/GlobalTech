<%--
  Created by IntelliJ IDEA.
  User: migli
  Date: 22/05/2022
  Time: 19:00
  To change this template use File | Settings | File Templates.

--%>
<%@page import="model.Utente" %>
<%
    Utente utente = (Utente) session.getAttribute("utente");
%>
    <nav>
        <!--
        Aggiungere collegamento alla Home nel logo
        -->
       <div id="logo">
           <h2>
               <a href="index.html" style="text-decoration: none; color:white;">
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
                            <%if(utente==null){ %>
                                <a href="login-page">
                                    Login/Registrazione
                                </a>
                            <%}else{%>
                                <a href="#">
                                    <%=utente.getNome()%>
                                </a>
                                <div id="account-box">
                                    <ul id="account-box-list">
                                        <%if(!utente.isAdmin()){%>
                                            <li>
                                                <a href="#">I miei Ordini</a>
                                            </li>
                                            <li>
                                                <a href="#">Il mio account</a>
                                            </li>
                                        <%}else{%>
                                            <li>
                                                <a href="#">Gestione Prodotti</a>
                                            </li>
                                            <li>
                                                <a href="#">Gestione Categorie</a>
                                            </li>
                                        <%}%>
                                        <li>
                                            <a href="logout">Logout</a>
                                        </li>
                                    </ul>
                                </div>

                            <%}%>
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
    <script src="/script/SelectResultJquery.js" defer>
    </script>
