<%@ page import="model.Ordine" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Annalaura
  Date: 09/07/2022
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Ordini</title>
        <%@include file="../includes/links.html"%>
    </head>
    <body>
        <%@include file="../includes/navbar.jsp"%>
        <%
        List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");
        %>
        <br>

        <%if(ordini != null && ordini.size() > 0){%>
            <table id="ordini">
                <tr>
                    <th>ID</th>
                    <th>Prezzo</th>
                    <th>Data</th>
                    <th>Pagamento</th>
                    <th>Indirizzo Spedizione</th>
                    <th>Stato</th>
                    <th>Dettaglio</th>
                </tr>
                <%
                for (Ordine o : ordini){
                %>
                <tr>
                    <td><%=o.getId()%></td>
                    <td><%=o.getPrezzoTotale()%></td>
                    <td><%=o.getData()%></td>
                    <td><%=o.getCcPagamento()%></td>
                    <td><%=o.getIndirizzoSpedizione()%></td>
                    <td>
                    <form action="stato-spedizione">
                        <input type="hidden" id="idOrdine" name="idOrdine" value="<%=o.getId()%>">

                        <%=o.getStatoString()%>
                        <%
                    if (o.getStato()==0){
                    %>

                    <input type="submit" id="stato1" name="stato1" value="Conferma">
                    <%}%>
                    <%if (o.getStato()==1){
                    %>
                        <input type="text" id="numTracking" name="numTracking" placeholder="Numero di tracking" required>
                        <input type="submit" id="stato2" name="stato2" value="Spedisci">

                        <%}%>
                    </form>
                    </td>
                    <td>
                        <form action="dettaglio-ordine" method="get">
                            <input type="hidden" name="id" value="<%=o.getId()%>">
                            <button type="submit"><i class="fa fa-external-link"></i></button>
                        </form>
                    </td>
                    <%}%>

                </tr>
            </table>
        <%}else{%>
            <p>Non risultano Ordini</p>
        <%}%>
    </body>
</html>
