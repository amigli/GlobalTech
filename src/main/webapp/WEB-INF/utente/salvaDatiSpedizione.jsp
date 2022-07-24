<%@ page import="model.Carrello" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 20/07/2022
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Verifica Dati Spedizione</title>
        <%@include file="/WEB-INF/includes/links.html" %>
    </head>
    <body>
        <%@include file="/WEB-INF/includes/navbar.jsp" %>
        <main id="salva-dati-ordine">
            <h1>Dati spedizione</h1>
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

            <form id="dati-spedizione" method="post" onsubmit="return validateFormDatiSpedizione()" action="salva-dati-spedizione">
                <div>
                    <label for="nome">Nome</label>
                    <input type="text" name="nome" id="nome" required
                        <%if(u.getNome() != null){%>
                           value="<%=u.getNome()%>"
                           disabled
                        <%}%>
                    >
                </div>
                <div>
                    <label for="cognome">Cognome</label>
                    <input type="text" name="cognome" id="cognome" required
                        <%if(u.getCognome() != null){%>
                           value="<%=u.getCognome()%>"
                           disabled
                        <%}%>
                    >
                </div>
                <div>
                    <label for="via">Via</label>
                    <input type="text" name="via" id="via" required
                        <%if(u.getVia() != null){%>
                           value="<%=u.getVia()%>"
                           disabled
                        <%}%>
                    >
                </div>
                <div>
                    <label for="civico">Civico</label>
                    <input type="number" name="civico" id="civico" required
                        <%if(u.getNumCivico() != 0){%>
                           value="<%=u.getNumCivico()%>"
                           disabled
                        <%}%>
                    >
                </div>
                <div>
                    <label for="citta">Citta</label>
                    <input type="text" name="citta" id="citta" required
                        <%if(u.getCitta() != null){%>
                           value="<%=u.getCitta()%>"
                           disabled
                        <%}%>
                    >
                </div>

                <div>
                    <label for="cap">CAP</label>
                    <input type="text" name="cap" id="cap"
                        <%if(u.getCap()!= 0){%>
                           value="<%=u.getCap()%>"
                           disabled
                        <%}%>
                    >
                </div>

                <div>
                    <label for="telefono">Telefono</label>
                    <input type="tel" name="telefono" id="telefono" pattern="[0-9]{9,10}" placeholder="3234214141" required
                        <%if(u.getNumTelefono() != null){%>
                           value="<%=u.getNumTelefono()%>"
                           disabled
                        <%}%>
                    >
                </div>
                <input type="submit" id="salva_button" value="salva" style="display: none" disabled>
            </form>

            <%if((u.getNumCivico() != 0) && (u.getCap()!= 0) && (u.getCitta() != null)
                    && (u.getNumTelefono() != null) && (u.getVia() != null)
                    && (u.getCognome() != null) && (u.getNome() != null)){%>
            <button id="modifica-button" onclick="activeForm()">Modifica</button>
            <form action="salva-dati-spedizione" method="post" id="form-no-modifica">
                <input type="hidden" name="modifica" value="0">
                <button id="avanti-button" type="submit">Avanti</button>
            </form>
            <%}else{%>
            <script>
                $(document).ready(function (){
                    let form =  document.forms["dati-spedizione"];

                    for(let input of form){
                        input.removeAttribute("disabled");
                    }

                    let submit = document.getElementById("salva_button");

                    submit.style.display = "block";
                })
            </script>
            <%}%>
        </main>

        <script type="text/javascript" src="script/salvaDatiSpedizione.js"></script>
        <%@include file="/WEB-INF/includes/footer.jsp" %>
    </body>
</html>
