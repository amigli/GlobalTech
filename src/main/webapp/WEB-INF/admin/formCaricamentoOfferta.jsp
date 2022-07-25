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
    <br>
    <h1 style="margin-left: 2em">Inserire una nuova offerta</h1>
    <div id="caricareOfferta">
    <form method="post" action="carica-offerta" onsubmit=" return validateFormOfferta()">
        <%  GregorianCalendar inizio = (new GregorianCalendar());
            inizio.add(Calendar.DATE, 1);

            List<String> errorParameter = (List<String>) request.getAttribute("error_parameter");
        %>
        <div>
            <label for="nome">Nome Offerta</label><br>
            <input type="text" name="nome" id="nome" placeholder="Offerte di primavera..." required
                <%if(errorParameter != null && errorParameter.contains("nome")){%>
                    class="error-parameter" value="<%=request.getParameter("nome")%>"
                <%}%>
            >
        </div>
        <div>
            <label for="data-inizio">Data Inizio</label><br>
            <input type="date" name="data-inizio" id="data-inizio" onchange="setDataFine()"
                   min="<%=inizio.toZonedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))%>" required
                <%if(errorParameter != null && errorParameter.contains("data-inizio")){%>
                   class="error-parameter" value="<%=request.getParameter("data-inizio")%>"
                <%}%>
            >
        </div>
        <div>
            <label for="data-fine" >Data Fine</label><br>
            <input type="date" name="data-fine" id="data-fine" disabled required
                <%if(errorParameter != null && errorParameter.contains("data-fine")){%>
                   class="error-parameter" value="<%=request.getParameter("data-fine")%>"
                <%}%>
            >
        </div>

        <div>
            <label for="sconto">Percentuale di sconto</label>
            <input type="number" step="any" min="0,01" id="sconto" name="sconto"max="99" minlength="3" style="width: 10%" required
                <%if(errorParameter != null && errorParameter.contains("sconto")){%>
                   class="error-parameter" value="<%=request.getParameter("sconto")%>"
                <%}%>
            ><span>%</span>
        </div>

        <input type="submit" value="Invia" id="carica-offerta">
    </form>
    </div>
    <script type="text/javascript" src="script/caricamentoOfferta.js"></script>
    <%@include file="/WEB-INF/includes/footer.jsp" %>
</body>
</html>
