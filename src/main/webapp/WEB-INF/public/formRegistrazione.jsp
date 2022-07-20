<%--
  Created by IntelliJ IDEA.
  User: Annalaura
  Date: 24/05/2022
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrazione</title>
    <%@include file="/WEB-INF/includes/links.html"%>
</head>
    <body>
        <%@ include file="/WEB-INF/includes/navbar.jsp"%>
        <br>
        <form action="registra-utente" id="registrazione-form" method="post">
            <%
                ArrayList<String> errList =  (ArrayList<String>) request.getAttribute("error_parameter");
            %>
            <h2>Crea un account</h2>
            <fieldset>
                <legend> Dati di accesso</legend>
                <div>
                    <label for="email-registrazione">Email:</label>
                    <input type="email" name="email" id="email-registrazione"  required>
                    <%if(errList != null && errList.contains("email_registrazione")){%>
                        <p>Inserire email valida</p>
                    <%}%>
                </div>
                <div>
                    <label for="pass">Password</label>
                    <input type="password" name="pass" id="pass" onkeyup="validatePassword()" required>
                    <%if(errList != null && errList.contains("password")){%>
                        <p>Inserire password valida</p>
                    <%}%>
                    <div id="password-check">
                        <ul id="password-check-list">
                            <li id="min-lenght"><i class="fa fa-circle-o"></i> Lunghezza minima 8 caratteri</li>
                            <li id="min-symbol"><i class="fa fa-circle-o"></i> Almeno un simbolo tra !_;,:.-+</li>
                            <li id="min-letter"><i class="fa fa-circle-o"></i> Almeno una lettera</li>
                            <li id="min-number"><i class="fa fa-circle-o"></i> Almeno un numero</li>
                        </ul>
                    </div>
                </div>
            </fieldset>
            <br>
            <fieldset>
                <legend>Dati personali</legend>
                <div>
                    <label for="nome">Nome</label>
                    <input type="text" name="nome" id="nome">
                    <%if(errList != null && errList.contains("nome")){%>
                    <p>Inserire nome valido</p>
                    <%}%>
                </div>
                <div>
                    <label for="cognome">Cognome</label>
                    <input type="text" name="cognome" id="cognome">
                    <%if(errList != null && errList.contains("cognome")){%>
                    <p>Inserire cognome valido</p>
                    <%}%>
                </div>
                <br>
                <div>
                    <label for="data_nascita">Data di nascita</label>
                    <%
                        GregorianCalendar today = new GregorianCalendar();
                        GregorianCalendar dataCalculate =
                                new GregorianCalendar(today.get(Calendar.YEAR) - 18, today.get(Calendar.MONTH),
                                        today.get(Calendar.DAY_OF_MONTH));
                    %>
                    <input type="date" name="data_nascita" id="data_nascita"
                           max="<%=dataCalculate.toZonedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))%>">
                    <%if(errList != null && errList.contains("password")){%>
                        <p>Inserire data valida</p>
                    <%}%>
                </div>
                <br>
            </fieldset>
                <br>
            <input type="submit" value="Registrati">
        </form>

        <script type="text/javascript" src="script/registrazioneUtente.js"></script>
    </body>
</html>
