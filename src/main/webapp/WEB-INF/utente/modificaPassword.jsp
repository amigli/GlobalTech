<%--
  Created by IntelliJ IDEA.
  User: Annalaura
  Date: 20/07/2022
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Modifica password</title>
        <%@include file="../includes/links.html"%>
    </head>
    <body>
        <%@include file="../includes/navbar.jsp"%>
        <% String message = (String) request.getAttribute("message");
            if(message != null){%>
                <div class="error-message">
                    <%=message%>
                </div>
            <%}%>
        <form action="modifica-password" method="post">
            <label for="oldPassword">Vecchia password:</label>
            <input type="password" id="oldPassword" name="old-password">
            <label for="newPassword1">Nuova password:</label>
            <input onkeyup="validatePassword('newPassword1')" type="password" id="newPassword1" name="new-password-1">
            <div id="password-check">
                <ul id="password-check-list">
                    <li id="min-lenght"><i class="fa fa-circle-o"></i> Lunghezza minima 8 caratteri</li>
                    <li id="min-symbol"><i class="fa fa-circle-o"></i> Almeno un simbolo tra !_;,:.-+</li>
                    <li id="min-letter"><i class="fa fa-circle-o"></i> Almeno una lettera minuscola e una maiuscola</li>
                    <li id="min-number"><i class="fa fa-circle-o"></i> Almeno un numero</li>
                </ul>
            </div>

            <label for="newPassword2">Ripeti nuova password:</label>
            <input type="password" id="newPassword2" name="new-password-2" onkeyup="validateEqualsPassword()">

            <div id="newPassword-check">
                <ul id="password-equals-list">
                    <li id="uguali"><i class="fa fa-circle-o"></i> Password uguali</li>
                </ul>
            </div>

            <input type="submit" value="Modifica">
        </form>
        <script type="text/javascript" src="script/passwordUguali.js"></script>
        <script type="text/javascript" src="script/registrazioneUtente.js"></script>
    </body>
</html>
