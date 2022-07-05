<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 05/07/2022
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Categoria" %>
<%@ page import="java.util.*"%>
<%@ page import="model.Prodotto" %>
<%@ page import="java.util.stream.Collectors" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestione Categorie prodotto ${prodotto.id}</title>
    <%@include file="/WEB-INF/includes/links.html"%>
</head>
<body>

<h4>Categorie del prodotto ${prodotto.id}</h4>
<form action="#" method="post">
    <%@include file="/WEB-INF/includes/navbar.jsp"%>
    <%
        List<Categoria>  categorie =  (List<Categoria>) application.getAttribute("categorie");
        Prodotto prodotto =  (Prodotto) request.getAttribute("prodotto");

        List<Categoria> categorieProd = categorie.stream().filter(c->c.contains(prodotto))
                .collect(Collectors.toList());
    %>

    <form id="categorie-prodotto" >
        <p>Seleziona le categorie da cui vuoi eliminare il prodotto</p>
        <%for(Categoria c : categorieProd){%>
            <input type="checkbox" name="categoria" id="categoria-<%=c.getId()%>" value="<%=c.getId()%>">
            <label for="categoria-<%=c.getId()%>"><%=c.getNome()%></label><br>
        <%}%>
        <br>
        <button>Elimina categorie</button>
    </form>
    <form id="altre-categorie" >
        <p>Seleziona le categorie da cui vuoi eliminare il prodotto</p>
        <%for(Categoria c : categorie){%>
            <%if(!categorieProd.contains(c)){%>
                <input type="checkbox" name="categoria" id="categoria-<%=c.getId()%>" value="<%=c.getId()%>">
                <label for="categoria-<%=c.getId()%>"><%=c.getNome()%></label><br>
        <%}
        }%>
        <br>
        <button>Aggiungi categorie</button>
    </form>

</form>

</body>
</html>
