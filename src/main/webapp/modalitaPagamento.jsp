<%--
  Created by IntelliJ IDEA.
  User: migli
  Date: 14/07/2022
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Modalità di pagamento</title>
        <%@include file="/WEB-INF/includes/links.html"%>
    </head>
    <body>
        <%@include file="/WEB-INF/includes/navbar.jsp"%>
        <main id="result">
            <h1>Carta di credito</h1>
            <p>Su GlobalTech puoi acquistare con tutte le Carte di Credito dei circuiti Visa, Mastercard emesse in Italia.</p>

            <h1>PayPal (Prossimamente)</h1>
            <p>L'importo viene addebitato sul conto Paypal dopo la conferma dell'ordine da parte di GlobalTech.
                Eventuali rimborsi sono effettuati da GlobalTech sempre sul conto PayPal.
                Quando la transazione va a buon fine ricevi una email di conferma da PayPal.
            </p>
        </main>
        <%@include file="/WEB-INF/includes/footer.jsp"%>
    </body>
</html>
