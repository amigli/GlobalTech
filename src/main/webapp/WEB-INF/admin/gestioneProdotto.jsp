<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 27/06/2022
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List" %>
<%@ page import="model.Prodotto" %>
<%@ page import="model.Offerta" %>
<%@ page import="com.oracle.wls.shaded.org.apache.xalan.xsltc.dom.ArrayNodeListIterator" %>
<%@ page import="model.Categoria" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Gestione prodotto : ${prodotto.id}</title>

        <%@include file="/WEB-INF/includes/links.html" %>
    </head>
    <body>
    <%
        ArrayList<String> errorPar = (ArrayList<String>) request.getAttribute("error_parameter");
        Prodotto p =  (Prodotto) request.getAttribute("prodotto");
    %>

    <%@include file="/WEB-INF/includes/navbar.jsp" %>
        <h1>Dettagli prodotto ${prodotto.id}</h1>
        <%
            if(errorPar != null){
                String txt = errorPar.get(0);

                for(int i =  1; i < errorPar.size(); i++)
                    txt += ", " + errorPar.get(i);
        %>
        <div id="error-message">
            Il server non ha accettato le modifiche dei seguenti parametri: <%=txt%>. Riprovare.
        </div>
        <%}%>
        <div id="modificareProdotto">
        <form action="modifica-prodotto"  id="modifica-prodotto" method="post">
            <input type="hidden" name="id_prod" value="${prodotto.id}">
            <fieldset>
                <legend>Dati generali</legend>
                <div class="input-section">
                    <label for="nome">Nome</label><br>
                    <input type="text" name="nome" id="nome" value="${prodotto.nome}" disabled required><br>
                    <div class="error" id="err_nome">
                    </div>
                </div>
                <div>
                    <label for="marca">Marca</label><br>
                    <input type="text" name="marca" id="marca" value="${prodotto.marca}" disabled required>
                    <div class="error" id="err_marca">
                    </div>
                </div>
                <div>
                    <label for="colore">Colore</label><br>
                    <input type="text" name="colore" id="colore" value="${prodotto.colore}" disabled required>
                    <div class="error" id="err_colore">
                    </div>
                </div>
                <div>
                    <label for="prezzo">Prezzo</label><br>
                    <input type="number" step="any" min="0.01" max="" name="prezzo" id="prezzo"
                           value="${prodotto.prezzoListino}" disabled required>
                </div>
                <div>
                    <label for="disponibilita">Quantità disponibile</label>
                    <input type="number" min="1" max="200" name="disponibilita" id="disponibilita"
                           value="${prodotto.disponibilita}" disabled required>
                </div>
                <div>
                    <label for="descrizione">Descrizione</label><br>
                    <textarea rows="8" cols="30" name="descrizione" id="descrizione" disabled>${prodotto.descrizione}</textarea><br>
                </div>
            </fieldset>
            <fieldset>
                <legend>Dati tecnici</legend>
                <div>
                    <label>Stai inserendo un prodotto con batteria?</label>
                    <input type="radio" name="batteria" id="batteria_true"
                           value="true" <%if(p.isBatteria()){%> checked <%}%> disabled required>
                    <label for="batteria_true">Si</label>
                    <input type="radio" name="batteria" id="batteria_false"
                           value="false"  <%if(!p.isBatteria()){%> checked <%}%> disabled required>
                    <label for="batteria_false">No</label>
                </div>
                <div>
                    <label for="ram_tipo" >Tipologia RAM</label><br>
                    <select name="ram_tipo" onchange="activeRam(value)" id="ram_tipo" disabled>
                        <option  <%if(p.getTipoRam() == null){%> selected <%}%>
                                                                 value="nessuna">Nessuna</option>
                        <option <%if(p.getTipoRam() != null && p.getTipoRam().equalsIgnoreCase("DDR3")){%> selected <%}%>
                                                                                 value="DDR3">DDR3</option>
                        <option <%if(p.getTipoRam() != null && p.getTipoRam().equalsIgnoreCase("DDR4")){%> selected <%}%>
                                                                                 value="DDR4">DDR4</option>
                        <option <%if( p.getTipoRam() != null && p.getTipoRam().equalsIgnoreCase("DDR5")){%> selected <%}%>
                                                                                 value="DDR5">DDR5</option>
                    </select>
                </div>
                <div>
                    <%String[] ramQuantita = null;
                        if(p.getTipoRam() != null) {
                            ramQuantita = p.getQuantitaRam().split(" ");
                        }
                    %>
                    <label for="ram_quantita">Quanità RAM</label><br>
                    <input type="number"  min="1" max="1024" name="ram_quantita" <%if(p.getTipoRam() != null){%>value="<%=ramQuantita[0]%>"<%}%> id="ram_quantita"
                           disabled>
                    <select name="ram_unit" disabled>
                        <option <%if(p.getTipoRam() != null && ramQuantita[1].equalsIgnoreCase("kb")){%>selected<%}%>
                                value="kb">KB</option>
                        <option <%if(p.getTipoRam() != null && ramQuantita[1].equalsIgnoreCase("mb")){%>selected<%}%>
                                value="mb">MB</option>
                        <option <%if( p.getTipoRam() != null && ramQuantita[1].equalsIgnoreCase("gb")){%>selected<%}%>
                                value="gb">GB</option>
                    </select>
                </div>
                <div>
                    <label for="cpu_nome">Nome CPU</label><br>
                    <input id="cpu_nome" name="cpu_nome" value="${prodotto.cpuNome}" disabled>
                </div>
                <div>
                    <%
                        String sistemaOperativo =  p.getSistemaOperativo();
                    %>
                    <label for="sistema_operativo">Sistema Operativo</label>
                    <select name="sistema_operativo" id="sistema_operativo" disabled>
                        <option <%if(sistemaOperativo.equalsIgnoreCase("Windows")){%>selected<%}%>
                                value="Windows">Windows</option>
                        <option <%if(sistemaOperativo.equalsIgnoreCase("Linux")){%>selected<%}%>
                                value="Linux">Linux</option>
                        <option <%if(sistemaOperativo.equalsIgnoreCase("MacOS")){%>selected<%}%>
                                value="macOS">MacOS</option>
                        <option <%if(sistemaOperativo.equalsIgnoreCase("Android")){%>selected<%}%>
                                value="Android">Android</option>
                        <option <%if(sistemaOperativo.equalsIgnoreCase("iOS")){%>selected<%}%>
                                value="iOS">iOS</option>
                        <option <%if(sistemaOperativo.equalsIgnoreCase("Proprietario")){%>selected<%}%>
                                value="Propietario">Propietario</option>
                    </select>
                </div>
            </fieldset>
            <input type="submit" id="modifica-prodotto-submit" value="Modifica" style="display: none" disabled>
        </form>
        </div>
        <button onclick="effettuaModificaProdotto()" id="effettua-modifica-prodotto-button">Effettua Modifica</button>


        <form action="elimina-prodotto" method="post">
            <input type="hidden" name="id_prod" value="${prodotto.id}">
            <input type="submit" value="Elimina prodotto">
        </form>

        <h3>Altre azioni :</h3>
    <div id="altreAzioniProdotto">
        <a href="gestione-immagini-prodotto?id_prod=${prodotto.id}" class="gestioneProdotto">Gestioni foto prodotto ${prodotto.id}</a>

        <a href="gestione-categoria-prodotto?id_prod=${prodotto.id}" class="gestioneProdotto">Gestioni categorie prodotto</a>

        <a href="gestione-offerte-prodotto?id_prod=${prodotto.id}" class="gestioneProdotto">Gestione offerte sul prodotto</a>

    </div>
    <script type="text/javascript" src="script/gestioneProdotto.js"></script>
    </body>
</html>
