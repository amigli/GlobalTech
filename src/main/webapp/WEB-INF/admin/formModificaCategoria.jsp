<%@ page import="model.Categoria" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Prodotto" %>
<%@ page import="java.util.stream.Collectors" %><%--
  Created by IntelliJ IDEA.
  User: Annalaura
  Date: 20/06/2022
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Modifica categoria</title>
        <%@include file="/WEB-INF/includes/links.html"%>
    </head>
    <body>
    <%@include file="/WEB-INF/includes/navbar.jsp"%>
    <%
        ArrayList<String> errorPar = (ArrayList<String>) request.getAttribute("error_parameter");

        Categoria categoria = (Categoria) request.getAttribute("categoria");
    %>
    <br><br><br>
    <div id="modificareCategoria">
        <%
            if(errorPar != null){
                String txt = errorPar.get(0);

                for(int i =  1; i < errorPar.size(); i++)
                    txt += ", " + errorPar.get(i);
            %>
              <div id="error-message">
                  Il server non ha accettato le modifiche dei seguenti parametri: <%=txt%>. Riprovare.
              </div>
            <%}%>
        <form action="modifica-categoria" id="modificaCategoria" method="post" onsubmit="return validateFormCategoria()">
            <h2>ID categoria da modificare: <%=categoria.getId()%></h2>
            <label for="nomeCategoria">Nome della categoria:</label>
            <input type="text" id="nomeCategoria" name="nomeCategoria" value="<%=categoria.getNome()%>" required>
            <label for="descrizioneCategoria">Descrizione della categoria:</label><br>
            <textarea id="descrizioneCategoria" name="descrizioneCategoria" rows="5" cols="65" required><%=categoria.getDescrizione()%>
            </textarea>
            <input type="hidden" name="id" id="id_cat"value="<%=categoria.getId()%>">
            <br>
            <br>
            <input type="submit" value="Modifica">
        </form>
    </div>

        <%
            List<Prodotto> prodottiAll =  (List<Prodotto>) request.getAttribute("prodotti");
            List<Prodotto> prodottiCategoria =  prodottiAll.stream().filter(p->categoria.contains(p)).collect(Collectors.toList());
        %>
    <div id="gestione-prodotti-offerta">
        <h1>Prodotti</h1>
        <p>Seleziona i prodotti da eliminare dalla categoria</p>
        <div id="prodotti-categoria">
            <%for(Prodotto p : prodottiCategoria){%>
            <div>
                <input type="checkbox" name="prod-categoria" id="prod-<%=p.getId()%>" value="<%=p.getId()%>">
                <label for="prod-<%=p.getId()%>"><%=p.getMarca()%>, <%=p.getNome()%></label>
            </div>
            <%}%>
        </div>
        <button onclick="rimuoviProdottoCategoria()">Rimuovi Prodotti</button>
        <br>

        <p>Seleziona i prodotti su cui applicare la categoria</p>
        <div id="prodotti-other">
            <%for(Prodotto p : prodottiAll){%>
            <%if(!prodottiCategoria.contains(p)){%>
            <div>
                <input type="checkbox" name="prod-other" id="prod-<%=p.getId()%>" value="<%=p.getId()%>">
                <label for="prod-<%=p.getId()%>"><%=p.getMarca()%>, <%=p.getNome()%></label>
            </div>
            <%}
            }%>
        </div>
        <button onclick="aggiungiProdottoCategoria()">Aggiungi prodotti</button>
    </div>

    <script src="script/caricamentoCategoria.js"></script>
    <script src="script/gestioneCategoria.js"></script>
</body>
</html>
