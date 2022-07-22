<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 20/07/2022
  Time: 17:08
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
        <form method="post" action="conferma-pagamento" id="form-dati-pagamento">
            <div>
                <label for="numCarta">Numero Carta</label>
                <input type="text" name="numerocc" id="numCarta" required disabled
                    <%if(u.getNumeroCarta() != null){%>
                       value="<%=u.getNumeroCarta()%>"
                    <%}%>
                >
            </div>
            <div>
                <label>Scadenza</label>
                <select id="month-scad-cc" name="month-scad-cc" required disabled>
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
                <select id="year-scad-cc" name="year-scad-cc" disabled required>
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
            <div>
                <label for="cvv">CVV</label>
                <input type="text" name="cvv" id="cvv" required disabled
                    <%if(u.getCvvCarta() != 0){%>
                       value="<%=u.getCvvCarta()%>"
                    <%}%>
                >
            </div>
            <%if((u.getNumeroCarta() == null) || (u.getDataScadenzaCarta() == null) ||(u.getCvvCarta() == 0)){%>
                <input type="checkbox" name="save" value="true" id="save-check">
                <label for="save-check">Spunta questa casella per salvare i dati del pagamento per i tuoi acquisti successivi</label>
            <%}%>

            <input type="submit" id="continua" value="Continua" style="display: none">

        </form>
        <%if((u.getNumeroCarta() != null) && (u.getDataScadenzaCarta() != null) && (u.getCvvCarta() != 0)){%>
            <button id="modifica-button"onclick="activeFormPagamento()">Modifica</button>
            <form action="conferma-pagamento" method="post" id="form-no-modifica">
                <input type="hidden" name="modifica" value="0">
                <button type="submit">Avanti</button>
            </form>
        <%}else{%>
            <script>
                $(document).ready(function (){
                    let form =  document.forms["form-dati-pagamento"];
                    for(let input of form){
                        input.removeAttribute("disabled");
                    }
                    let submit = document.getElementById("continua");
                    submit.style.display = "block";
                })
            </script>
        <%}%>
        <%@include file="/WEB-INF/includes/footer.jsp" %>
        <script type="text/javascript" src="script/confermaDatiPagamento.js"></script>
        <script type="text/javascript" src="script/datiCarta.js"></script>
    </body>
</html>
