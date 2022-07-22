<%@ page import="model.Ordine" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Annalaura
  Date: 22/07/2022
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ordini per cliente</title>
    <%@include file="../includes/links.html"%>
</head>
<body>
<%@include file="../includes/navbar.jsp"%>
<%
List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");
%>
<br>
<table id="ordiniCliente">
    <tr>
        <th>ID</th>
        <th>Prezzo</th>
        <th>Data</th>
        <th>Pagamento</th>
        <th>Indirizzo Spedizione</th>
        <th>Stato</th>
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
                <%
                    if (o.getStato()==0){
                %>
                In attesa di conferma
                <input type="submit" id="stato1" name="stato1" value="Conferma">
                <%}%>
                <%if (o.getStato()==1){
                %>
                In attesa di spedizione
                <input type="text" id="numTracking" name="numTracking" placeholder="Numero di tracking" required>
                <input type="submit" id="stato2" name="stato2" value="Spedisci">

                <%}%>
                <%if (o.getStato()==2){%>
                Completato
                <%}%>
            </form>
        </td>
        <%}%>
    </tr>
</table>
</body>
</html>
