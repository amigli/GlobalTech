<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 22/06/2022
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Offerta caricata con successo</title>
</head>
<body>
    <div>
        <p>ID:${offerta.id}</p>
        <p>Nome:${offerta.nome}</p>
        <p>Data inizio:${offerta.dataInizio}</p>
        <p>Data fine: ${offerta.dataFine}</p>
        <p>Percentuale di sconto:${offerta.percentuale}%</p>
    </div>

</body>
</html>
