<%@ page import="model.Offerta" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Prodotto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>GlobalTech</title>
        <%@include file="/WEB-INF/includes/links.html"%>
        <meta charset="UTF-8">
    </head>
    <body>
        <%@include file="/WEB-INF/includes/navbar.jsp"%>
        <main id="home">
            <div id="prodotti-top">
                <div id="prodotti-home-page">
                    <h1>I Nostri Prodotti</h1>
                    <%
                        List<Prodotto> prodotti = (List<Prodotto>) request.getAttribute("prodotti");
                    %>
                    <%
                        for(int i  =  0 ; i < Math.min(8, prodotti.size()); i++){
                    %>     <%if(prodotti.get(i).getImmagini().size() == 0){%>
                    <img onclick="location.href = 'dettaglio-prodotto?id=' + <%=prodotti.get(i).getId()%>" class="img-home" src="asset/default.png">
                    <%}else{%>
                    <img onclick="location.href = 'dettaglio-prodotto?id=' + <%=prodotti.get(i).getId()%>" src="<%=prodotti.get(i).getImmagini().get(0).getDirectory()%>" class="img-home">
                    <%}%>
                    <%}%>
                    <a href="catalogo"><h1 id="sfoglia-catalogo" >Continua a sfogliare il nostro catalogo <i class="fa fa-arrow-right"></i></h1></a>
                </div>

            </div>
            <section id="categorie">
                <div id="categorie-container">
                    <div class="categoria" id="categoria-informatica" onclick="location.href = 'prodotti-per-categoria?id=1'">
                        <h1>
                            Informatica
                        </h1>
                    </div>
                    <div class="categoria" id="categoria-telefonia" onclick="location.href = 'prodotti-per-categoria?id=2'">
                        <h1>
                            Telefonia
                        </h1>
                    </div>
                    <div class="categoria" id="categoria-accessori" onclick="location.href = 'prodotti-per-categoria?id=3'">
                        <h1>
                            Accessori
                        </h1>
                    </div>
                </div>
                <a href="categoriePage.jsp">
                    <h1 id="sfoglia-categorie">
                        Tutte le categorie <i class="fa fa-arrow-right"></i>
                    </h1>
                </a>
            </section>
        </main>

        <%@include file="/WEB-INF/includes/footer.jsp"%>
    </body>
</html>