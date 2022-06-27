<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 21/06/2022
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.*"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Caricamento offerta</title>
    <%@include file="/WEB-INF/includes/links.html"%>
</head>
<body>
    <%@include file="/WEB-INF/includes/navbar.jsp"%>
    <form method="post" action="carica-offerta">
        <%  GregorianCalendar inizio = (new GregorianCalendar());
            inizio.add(Calendar.DATE, 1);

            GregorianCalendar fine = new GregorianCalendar();
            fine.add(Calendar.DATE, 2);
        %>
        <div>
            <label for="nome">Nome Offerta</label><br>
            <input type="text" name="nome" id="nome" placeholder="Offerte di primavera...">
        </div>
        <div>
            <label for="data-inizio">Data Inizio</label><br>
            <input type="date" name="data-inizio" id="data-inizio" min="<%=inizio.toZonedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))%>" required>
        </div>
        <div>
            <label for="data-fine">Data Fine</label><br>
            <input type="date" name="data-fine" id="data-fine" min="<%=fine.toZonedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))%>" required>
        </div>

        <div>
            <label for="sconto">Percentuale di sconto</label>
            <input type="number" step="any" min="0,01" id="sconto" name="sconto"max="100"><span>%</span>
        </div>

        <input type="submit" value="Invia" id="carica-offerta">
    </form>
</body>
</html>
