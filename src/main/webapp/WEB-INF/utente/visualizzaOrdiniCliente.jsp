<%@ page import="model.Ordine" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 21/07/2022
  Time: 17:15
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
            List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");
        %>
        <main id="result">
            <table>
                <tr>
                    <th>#</th>
                    <th>Data</th>
                    <th>Totale</th>
                    <th>Stato</th>
                    <th>Dettagli</th>
                </tr>
                <%
                    for (Ordine o : ordini){
                %>
                <tr>
                    <td><%=o.getId()%></td>
                    <td><%=o.getData()%></td>
                    <td><%=o.getPrezzoTotale()%></td>
                    <td><%=o.getStatoString()%></td>
                    <td>
                        <form method="get" action="dettaglio-ordine">
                            <input type="hidden" name="id" value="<%=o.getId()%>">
                            <button type="submit"><i class="fa fa-external-link"></i></button>
                        </form>
                    </td>
                </tr>
                <%}%>
            </table>
        </main>



        <%@include file="/WEB-INF/includes/footer.jsp" %>
    </body>
</html>
