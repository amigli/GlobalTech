<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 20/06/2022
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <title>Errore</title>
</head>
<body>
    <h1>Si e' verificato un errore</h1>
    <%if (exception != null) { %>
            <%=exception.getMessage()%>
            <%=exception.getStackTrace()%>
        <%}%>
    <%=response.getStatus()%>
</body>
</html>
