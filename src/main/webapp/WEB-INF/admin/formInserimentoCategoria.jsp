<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Annalaura
  Date: 20/06/2022
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Inserisci categoria</title>
        <%@include file="../includes/links.html"%>
    </head>
    <body>
        <%@include file="../includes/navbar.jsp"%>
        <br>
        <div id="inserireCategoria">
            <%
                List<String> errorPar = (List<String>) request.getAttribute("error_parmeter");
            %>
            <form action="inserisci-categoria" id="inserisciCategoria" onsubmit="return validateFormCategoria()" method="post">
                <label for="nomeCategoria">Nome della categoria:</label>
                <input type="text" id="nomeCategoria" name="nomeCategoria" required
                    <%if(errorPar != null){%>
                        <%if(errorPar.contains("nome")){%>
                           class = "error-parameter"
                        <%}%>
                       value="<%=request.getParameter("nome")%>"
                    <%}%> >
                <label for="descrizioneCategoria">Descrizione della categoria:</label><br>
                <textarea id="descrizioneCategoria" name="descrizioneCategoria" rows="5" cols="40" <%if(errorPar != null && errorPar.contains("nome")){%>
                          class = "error-parameter"<%}%> required><%if(errorPar!=null){%><%=request.getParameter("descrizioneCategoria")%><%}%></textarea>
                <br>
                <input type="submit" value="Inserisci">
            </form>
        </div>
        <script type="text/javascript" src="script/caricamentoCategoria.js"></script>
    </body>
</html>
