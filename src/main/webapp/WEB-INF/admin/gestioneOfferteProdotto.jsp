<%@ page import="model.Categoria" %>
<%@ page import="model.Offerta" %>
<%@ page import="java.util.*" %>
<%@ page import="model.Prodotto" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 13/07/2022
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Gestione Offerte Prodotto ${prodotto.id}</title>
        <%@include file="/WEB-INF/includes/links.html"%>
        <script type="text/javascript" src="script/gestione-offerte-prodotto.js" defer></script>
    </head>
    <body>
        <input type="hidden" id="id-prod" value="${prodotto.id}">
        <%@include file="/WEB-INF/includes/navbar.jsp"%>
        <%
            Prodotto p = (Prodotto) request.getAttribute("prodotto");

            List<Offerta> offerteAttiveList =  (List<Offerta>) application.getAttribute("offerte");
            List<Offerta> offerteAttiveProdotto =
                    offerteAttiveList.stream().filter(o->o.contains(p)).collect(Collectors.toList());

            List<Offerta> offerteFutureList =  (List<Offerta>) request.getAttribute("offerte_future");
            List<Offerta> offerteFutureProdotto =
                    offerteFutureList.stream().filter(o->o.contains(p)).collect(Collectors.toList());

        %>
        <br>
        <div id="offerteOra">
        <h3>Offerte attualmente attive</h3>
        <section id="offerte-attive-prodotto">
            <%for(Offerta o : offerteAttiveProdotto){%>
                <div>
                    <input type="checkbox" name="offerta-attiva-prodotto" id="offerta-<%=o.getId()%>" value="<%=o.getId()%>">
                    <label for="offerta-<%=o.getId()%>"><%=o.getNome()%>, dal
                        <%=o.getDataInizio().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))%> al
                        <%=o.getDataInizio().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))%></label>
                </div>
            <%}%>
        </section>
        <button onclick="rimuoviOfferta('offerta-attiva-prodotto')" id="rimuoviOfferte">Rimuovi Offerte</button>
        <br>

        <section id="offerte-attive-other">
            <%for(Offerta o : offerteAttiveList){
                if(!offerteAttiveProdotto.contains(o)){%>
                <div>
                    <input type="checkbox" name="offerta-attiva-other" id="offerta-<%=o.getId()%>" value="<%=o.getId()%>">
                    <label for="offerta-<%=o.getId()%>"><%=o.getNome()%>, dal
                        <%=o.getDataInizio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))%> al
                        <%=o.getDataFine().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))%>
                    </label>
                </div>
            <%}
            }%>
        </section>
        <button onclick="aggiungiOfferta('offerta-attiva-other')" id="attivaOfferta">Attiva Offerte</button>
        </div>
        <div id="offerteFuture">
        <h3>Offerte future</h3>
        <section id="offerte-future-prodotto">
            <%for(Offerta o : offerteFutureProdotto){%>
            <div>
                <input type="checkbox" name="offerta-futura-prodotto" id="offerta-<%=o.getId()%>" value="<%=o.getId()%>">
                <label for="offerta-<%=o.getId()%>"><%=o.getNome()%>, dal
                    <%=o.getDataInizio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))%> al
                    <%=o.getDataFine().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))%></label>
            </div>
            <%}%>
        </section>
        <button onclick="rimuoviOfferta('offerta-futura-prodotto')" id="rimuoviOfferta2">Rimuovi Offerte</button>

        <section id="offerte-future-other">
            <%for(Offerta o : offerteFutureList){
                if(!offerteFutureProdotto.contains(o)){%>
                <div>
                    <input type="checkbox" name="offerta-futura-other" id="offerta-<%=o.getId()%>" value="<%=o.getId()%>">
                    <label for="offerta-<%=o.getId()%>"><%=o.getNome()%>, dal
                        <%=o.getDataInizio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))%> al
                        <%=o.getDataFine().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))%>
                    </label>
             </div>
            <%}
            }%>
        </section>
        <button onclick="aggiungiOfferta('offerta-futura-other')" id="attivaOfferta2">Attiva Offerte</button>
        </div>

        <%@include file="/WEB-INF/includes/footer.jsp" %>
    </body>
</html>
