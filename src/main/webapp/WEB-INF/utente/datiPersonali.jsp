<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Annalaura
  Date: 20/07/2022
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Modifica dati personali</title>
        <%@include file="../includes/links.html"%>
    </head>
    <body>
        <%@include file="../includes/navbar.jsp"%>
        <main id="result">
            <h1>Dati Personali</h1>
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

            <form action="modifica-datiPUtente" method="post" onsubmit="return validateFormDatiPUtente()">
                <div>
                    <label for="nome">Nome:</label>
                    <input type="text" id="nome" name="nome" required
                        <%
                    if(u.getNome()!=null){
                    %>
                           value="<%=u.getNome()%>"
                        <%}%>
                    >
                </div>

                <div>
                    <label>Cognome:</label>
                    <input type="text" id="cognome" name="cognome" required
                        <%
                    if(u.getCognome()!=null){
                    %>
                           value="<%=u.getCognome()%>"
                        <%}%>
                    >
                </div>

                <div>
                    <label>Data di nascita:</label>
                    <input type="date" id="dataNascita" name="dataNascita" max="2005-01-01" required
                        <%
                    if(u.getDataNascita()!=null){
                    %>
                           value="<%=u.getDataNascita()%>"
                        <%}%>
                    >
                </div>

                <div>
                    <label>Numero di telefono:</label>
                    <input type="tel" id="telefono" pattern="[0-9]{9,10}" name="telefono" maxlength="10"
                        <%
                    if(u.getNumTelefono()!=null){
                    %>
                           value="<%=u.getNumTelefono()%>"
                        <%}%>
                    >
                </div>

                <input type="submit" value="Salva">

            </form>
        </main>
        <%@include file="/WEB-INF/includes/footer.jsp" %>

        <script type="text/javascript" src="script/datiPersonali.js"></script>
    </body>
</html>
