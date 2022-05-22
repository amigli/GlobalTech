<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 22/05/2022
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form>
        <label for="nome">Nome</label><br>
        <input type="text" name="nome" id="nome"><br>
        <label for="marca">Marca</label><br>
        <input type="text" name="marca" id="marca"><br>

        <label for="colore">Colore</label><br>
        <input type="text" name="colore" id="colore"><br>
        <label for="prezzo">Prezzo</label><br>
        <input type="text" name="prezzo" id="prezzo"><br>

        <label for="descrizione">Descrizione</label>
        <textarea name="descrizione" id="descrizione"></textarea><br>

        <p>Stai inserendo un prodotto con batteria?</p>
        <input type="radio" name="batteria" id="batteria_true" value="true"><label for="batteria_true"></label>
        <input type="radio" name="batteria" id="batteria_false" value="false"><label for="batteria_false"></label>

        <fieldset>
            <legend>Dati tecnici</legend>
            <h3>Ram</h3><br>
            <label for="ram_tipo">Tipologia RAM</label><br>
            <input type="text" name="ram_tipo" id="ram_tipo"><br>
            <label for="ram_quantita">Quanità RAM</label><br>
            <input type="text" name="ram_quantita" id="ram_quantita"> GB<br>


            <h3>CPU</h3><br>
            <label for="cpu_nome">Nome CPU</label><br>
            <input type="text" name="cpu_nome" id="cpu_nome"><br>
            <label for="cpu_hertz">Hertz CPU</label><br>
            <input type="text" name="cpu_hertz" id="cpu_hertz"><br>
        </fieldset>

        <label for="disponibilita">Quantità disponibile</label>
        <input type="text" name="disponibilita" id="disponibilita">

        <input type="submit">

    </form>
</body>
</html>
