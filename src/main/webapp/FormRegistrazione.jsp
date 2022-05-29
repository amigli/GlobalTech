<%--
  Created by IntelliJ IDEA.
  User: migli
  Date: 24/05/2022
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrazione</title>
</head>
<body>
<form>
    <fieldset>
        <legend> Dati di accesso</legend>
        <div>
            <label for="email">Email:</label>
            <input type="text" name="email" id="email">
        </div>
        <div>
            <label for="pass">Password</label>
            <input type="password" name="pass" id="pass">
        </div>
    </fieldset>
    <br>
    <fieldset>
        <legend>Dati personali</legend>
        <div>
            <label for="nome">Nome</label>
            <input type="text" name="nome" id="nome">
        </div>
        <div>
            <label for="cognome">Cognome</label>
            <input type="text" name="cognome" id="cognome">
        </div>
        <br>
        <div>
            <label for="data_nascita">Data di nascita</label>
            <input type="date" name="data_nascita" id="data_nascita">
        </div>
        <br>
        <div>
            <label for="citta">Città</label>
            <input type="text" name="citta" id="citta">
        </div>
        <br>
        <div>
            <label for="cap">CAP</label>
            <input type="text" name="cap" id="cap">
        </div>
        <div>
            <label for="via">Via</label>
            <input type="text" name="via" id="via">
        </div>
        <br>
        <div>
            <label for="numero_civico">Numero</label>
            <input type="int" name="numero_civico" id="numero_civico">
        </div>
        <div>
            <label for="numero_telefono">Numero telefono</label>
            <input type="text" name="numero_telefono" id="numero_telefono">
        </div>
    </fieldset>
    <br>
    <fieldset>
        <legend>Dati della carta</legend>
        <div>
            <label for="numero_cc">Numero della carta</label>
            <input type="text" name="numero_cc" id="numero_cc">
        </div>
        <div>
            <label for="cvv">CVV</label>
            <input type="int" name="cvv" id="cvv">
        </div>
        <div>
            <label for="scadenza_carta">Scadenza carta</label>
            <input type="date" name="scadenza_carta" id="scadenza_carta">
        </div>
    </fieldset>
    <br>
    <input type="submit" value="Registrati">
</form>
</body>
</html>
