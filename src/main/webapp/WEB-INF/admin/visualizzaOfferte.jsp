<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 14/07/2022
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Gestione Offerte</title>
        <%@include file="/WEB-INF/includes/links.html"%>
    </head>
    <body>
        <%@include file="/WEB-INF/includes/navbar.jsp"%>
<br>
        <h1 style="margin-left: 1em">Visualizza tutte le offerte</h1>
        <table id="offerte">
            <tr>
                <th>
                    ID
                </th>
                <th>
                    Nome
                </th>
                <th>
                    Percentuale di Sconto
                </th>
                <th>
                    Dal
                </th>
                <th>
                    Al
                </th>
                <th>
                    Azione
                </th>
            </tr>
            <c:forEach items="${offerteAll}" var="off">
                <tr>
                    <td>
                            ${off.id}
                    </td>
                    <td>
                            ${off.nome}
                    </td>
                    <td>
                            ${off.percentuale}
                    </td>
                    <td>
                            ${off.dataInizio}
                    </td>
                    <td>
                           ${off.dataFine}
                    </td>
                    <td>
                        <form action="gestione-offerta">
                            <input type="hidden" name="id_offerta" value="${off.id}">
                            <button type="submit"><i class="fa fa-external-link"></i></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <%@include file="/WEB-INF/includes/footer.jsp" %>
     </body>
</html>
