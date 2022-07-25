<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: migli
  Date: 20/07/2022
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Modifica dati di pagamento</title>
        <%@include file="../includes/links.html"%>
    </head>
    <body>
        <%@include file="../includes/navbar.jsp"%>

        <main id="result">
            <h1>Dati Pagamento</h1>
            <%
            Utente u =  (Utente) session.getAttribute("utente");
            List<String> errorPar = (List<String>) request.getAttribute("error-parameter");
            if(errorPar != null){
                String txt = errorPar.get(0);
                for(int i =  1; i < errorPar.size(); i++)
                    txt += ", " + errorPar.get(i);
        %>
            <div id="error-message">
                Il server non ha accettato le modifiche dei seguenti parametri: <%=txt%>. Riprovare.
            </div>
            <%}%>
            <form action="modifica-datiCUtente" method="post" onsubmit="return validateDatiCUtente()">

                <label for="numCarta">Numero Carta</label>
                <div>
                    <input type="text" id="numCarta" name="numCarta" maxlength="16"
                        <%
                            if(u.getNumeroCarta()!=null){
                            %>
                            value="<%=u.getNumeroCarta()%>"
                        <%}%>
                    >
                </div>

                <div>
                    <label for="cvv">CVV carta:</label>
                    <input type="text" id="cvv" name="cvv" maxlength="3"
                        <%
                        if(u.getCvvCarta()>0){
                       %>
                           value="<%=u.getCvvCarta()%>"
                        <%}%>
                    >
                </div>


                <div>
                    <label>Scadenza</label>
                    <select id="month-scad-cc" name="month-scad-cc" required>
                        <%
                            if(u.getDataScadenzaCarta() == null){
                        %>
                        <option disabled selected value="">Seleziona Mese</option>
                        <%}
                            for(int i = 1; i < 13; i++){%>
                        <option <%if(u.getDataScadenzaCarta() != null && u.getDataScadenzaCarta().getMonthValue() == i){%>
                                selected <%}%> value="<%=i%>"><%=i%></option>
                        <%}%>
                    </select>
                    <select id="year-scad-cc" name="year-scad-cc"  required>
                        <%
                            if(u.getDataScadenzaCarta() == null){
                        %>
                        <option disabled selected value="">Seleziona Mese</option>
                        <%}
                            GregorianCalendar today =  new GregorianCalendar();
                            for(int i = today.get(Calendar.YEAR); i < today.get(Calendar.YEAR) + 30; i++){%>
                        <option <%if(u.getDataScadenzaCarta() != null && u.getDataScadenzaCarta().getYear() == i){%>
                                selected <%}%> value="<%=i%>"><%=i%></option>
                        <%}%>
                    </select>
                </div>
                <input type="submit" value="Salva">
            </form>
        </main>
        <%@include file="/WEB-INF/includes/footer.jsp" %>
        <script type="text/javascript" src="script/datiCarta.js"></script>
    </body>
</html>
