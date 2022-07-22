<%@ page import="model.Item" %>
<%@ page import="model.Foto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 22/07/2022
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Title</title>
        <%@include file="/WEB-INF/includes/links.html" %>
    </head>
    <body>
        <%@include file="/WEB-INF/includes/navbar.jsp" %>
        <%
            Item item = (Item) request.getAttribute("item");
        %>
        <main>
            <h1><%=item.getProdotto().getMarca()%>-<%=item.getProdotto().getNome()%></h1>
            <section id="dettagli-prodotto">
                <div id="gallery-product">
                    <%
                        if(item.getProdotto().getImmagini().isEmpty()){
                    %>
                        <img src="asset/default.png">
                    <%}else{
                        List<Foto> immagini = item.getProdotto().getImmagini();
                        %>
                        <img src="<%=immagini.get(0).getDirectory()%>">

                        <%
                        for (int i = 1; i < immagini.size(); i++){
                        %>
                            <img src="<%=immagini.get(i).getDirectory()%>" style="display:none">
                        <%}%>

                        <input type="hidden" id="numero-foto" value="<%=immagini.size()%>">
                        <a><i class="fa fa-angle-left"></i></a>
                        <a><i class="fa fa-angle-right"></i></a>
                    <%}%>
                </div>
                <div id="description">
                    <%=item.getProdotto().getDescrizione()%>
                </div>
                <div >
                    <h1>Prezzo
                        <%if(item.getPrezzo() != item.getProdotto().getPrezzoListino()){
                    %>  <s><%=item.getProdotto().getPrezzoListino()%></s> <%=item.getPrezzo()%>
                        <%}else{%>
                        <%}%>
                    </h1>
                    <form method="post" action="aggiungi-carrello">
                        <input type="hidden" name="prodotto" value="<%=item.getProdotto().getId()%>">
                        <label>Quantita</label>
                        <input type="number" max="<%=item.getProdotto().getDisponibilita()%>" min="1" value="1">
                        <input type="submit" value="Aggiungi Al Carrello">
                    </form>
                </div>
            </section>
            <section id="scheda-tecnica">
                <div id="dettagli-caricamento">
                    <p>Nome:&emsp;<%=item.getProdotto().getNome()%></p>
                    <p>Marca:&emsp;<%=item.getProdotto().getMarca()%></p>
                    <p>Colore:&emsp;<%=item.getProdotto().getColore()%></p>
                    <p>Sistema Operativo:&emsp;<%=item.getProdotto().getSistemaOperativo()%></p>
                    <p>Tipo RAM:&emsp;<%=item.getProdotto().getTipoRam() == null ? "-" : item.getProdotto().getTipoRam()%> </p>
                    <p>Quantita RAM&emsp;<%=item.getProdotto().getTipoRam() == null ? "-" : item.getProdotto().getQuantitaRam()%> </p>
                    <p>CPU:&emsp;<%=item.getProdotto().getCpuNome()%></p>
                    <p>Batteria :&emsp;<%=item.getProdotto().isBatteria() ? "Si" : "No"%> </p>
                </div>
            </section>

        </main>

        <%@include file="/WEB-INF/includes/footer.jsp" %>
    </body>
</html>
