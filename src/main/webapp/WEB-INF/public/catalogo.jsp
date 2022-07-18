<%@ page import="model.Item" %>
<%@ page import="model.Prodotto"%>
<%@ page import="java.util.*"%>
<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 17/07/2022
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Catalogo</title>
        <%@include file="/WEB-INF/includes/links.html" %>
    </head>
    <body>
        <%@include file="/WEB-INF/includes/navbar.jsp" %>
            <%
                List<Item> catalogo = (List<Item>) request.getAttribute("catalogo");

                for(Item item : catalogo){
            %>
                <figure>
                    <figcaption>
                        <%if(item.getProdotto().getImmagini().size() > 0){%>
                            <img src="<%=item.getProdotto().getImmagini().get(0).getDirectory()%>"
                        <%}else{%>
                            <img src="./asset/default.png">
                        <%}%>
                        <h1 id="marca-nome">
                            <%=item.getProdotto().getMarca()%>-<%=item.getProdotto().getNome()%>
                        </h1>
                        <div id="descrizione">
                            <%=item.getProdotto().getDescrizione().substring(0, 50)%>
                        </div>
                        <div id="prezzo"><%=item.getPrezzo()%></div>
                        <form action="aggiungi-carrello" method="post">
                            <input type="hidden" name="quantita" value="1">
                            <input type="hidden" name="prodotto"  value="<%=item.getProdotto().getId()%>">
                            <input type="submit" value="Aggiungi al carrello">
                        </form>
                    </figcaption>
                </figure>


            <%}%>
        <%@include file="/WEB-INF/includes/footer.jsp" %>
    </body>
</html>
