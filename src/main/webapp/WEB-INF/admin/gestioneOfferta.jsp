<%@page import="java.util.*" %>
<%@ page import="model.Prodotto" %>
<%@ page import="model.Offerta" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 14/07/2022
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
  <head>
      <title>Gestisci Offerta ${offerta.id}</title>
      <%@include file="/WEB-INF/includes/links.html"%>
      <script type="text/javascript" src="script/gestione-offerta.js" defer></script>
      <script type="text/javascript" src="script/caricamentoOfferta.js" defer></script>

  </head>
  <body>
    <%@include file="/WEB-INF/includes/navbar.jsp"%>
    <br>
    <div id="gestione-offerta">
    <div id="offerta">
    <form method="post" id="form-modifica" action="modifica-offerta" onsubmit="return validateFormOfferta()">
        <input type="hidden" name="id_offer"  id="id_offer" value="${offerta.id}">
        <div>
            <label for="nome">Nome Offerta</label><br>
            <input type="text" name="nome" id="nome" placeholder="Offerte di primavera..." required disabled value="${offerta.nome}">
        </div>
        <div>
            <label for="data-inizio">Data Inizio</label><br>
            <%
                GregorianCalendar today = new GregorianCalendar();
            %>
            <input type="date" onchange="setDataFine()" name="data-inizio" id="data-inizio" min="<%=today.toZonedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))%>" value="${offerta.dataInizio}" disabled onchange="setDataFine()" required>
        </div>
        <div>
            <label for="data-fine">Data Fine</label><br>
            <input type="date" name="data-fine" id="data-fine" value="${offerta.dataFine}"  disabled required>
        </div>

        <div>
            <label for="sconto">Percentuale di sconto</label>
            <input type="number" step="any" min="0,01" id="sconto" name="sconto" required value="${offerta.percentuale}" disabled max="100">
            <span>%</span>
        </div>
        <input type="submit" value="Salva Modifica" id="salva-modifica" style="display: none">
    </form>
    <button onclick="effettuaModifica()" id="effettua-modifica">Effettua Modifica</button>
    </div>
    <br>

    <%
        Offerta offerta =  (Offerta) request.getAttribute("offerta");

        List<Prodotto> prodottiAll =  (List<Prodotto>) request.getAttribute("prodotti");
        List<Prodotto> prodottiAttivi =  prodottiAll.stream().filter(p->offerta.contains(p)).collect(Collectors.toList());
    %>
    <div id="gestione-prodotti-offerta">
        <h1>Prodotti</h1>
        <p>Seleziona i prodotti da eliminare</p>
        <div id="prodotti-attivi">
            <%for(Prodotto p : prodottiAttivi){%>
            <div>
                <input type="checkbox" name="prod-attivi" id="prod-<%=p.getId()%>" value="<%=p.getId()%>">
                <label for="prod-<%=p.getId()%>"><%=p.getMarca()%>, <%=p.getNome()%></label>
            </div>
            <%}%>
        </div>
        <button onclick="rimuoviProdotto()" id="rimuovi-prodotto">Rimuovi Prodotti</button>
        <br>

        <p>Seleziona i prodotti su cui applicare l'offerta</p>
        <div id="prodotti-other">
            <%for(Prodotto p : prodottiAll){%>
            <%if(!prodottiAttivi.contains(p)){%>
            <div>
                <input type="checkbox" name="prod-other" id="prod-<%=p.getId()%>" value="<%=p.getId()%>">
                <label for="prod-<%=p.getId()%>"><%=p.getMarca()%>, <%=p.getNome()%></label>
            </div>
            <%}
            }%>
        </div>
        <button onclick="aggiungiProdotto()" id="aggiungi-prodotto">Aggiungi prodotti</button>
    </div>
    </div>
    <%@include file="/WEB-INF/includes/footer.jsp"%>
  </body>
</html>
