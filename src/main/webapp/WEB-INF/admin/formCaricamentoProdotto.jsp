<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 22/05/2022
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="model.Offerta" %>
<%@ page import="model.Categoria" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inserimento Prodotto</title>
    <%@include file="/WEB-INF/includes/links.html"%>
</head>
<body>
    <%@include file="/WEB-INF/includes/navbar.jsp"%>
    <br>
    <div id="caricareProdotto">
        <%
            ArrayList<String> list =
                    (ArrayList<String>) request.getAttribute("error_parameter");
        %>

<br>
    <form action="carica-prodotto" id="caricamento-prodotto" method="post" onsubmit=" return validateForm()">
        <fieldset>
            <legend>Dati generali</legend>
            <div class="input-section">
                <label for="nome">Nome</label><br>
                <input type="text" name="nome" id="nome"required
                    <%if(list != null){%>
                        <%if(list.contains("nome")){%>
                            class = "error-parameter"
                        <%}%>
                        value="<%=request.getParameter("nome")%>"
                    <%}%>
                       ><br>
                <div class="error" id="err_nome">
                    <%if(list != null && list.contains("name")){%>
                        <%="Inserire nome ammissibile"%>
                    <%}%>
                </div>
            </div>
            <div>
                <label for="marca">Marca</label><br>
                <input type="text" name="marca" id="marca" required
                    <%if(list != null){%>
                    <%if(list.contains("marca")){%>
                       class = "error-parameter"
                    <%}%>
                       value="<%=request.getParameter("marca")%>"
                    <%}%>
               >
                <div class="error" id="err_marca">
                    <%if(list != null && list.contains("marca")){%>
                        <%="Inserire marca ammissibile"%>
                    <%}%>
                </div>
            </div>
            <div>
                <label for="colore">Colore</label><br>
                <input type="text" name="colore" id="colore" required
                    <%if(list != null){%>
                    <%if(list.contains("colore")){%>
                       class = "error-parameter"
                    <%}%>
                       value="<%=request.getParameter("colore")%>"
                    <%}%>
                       >
                <div class="error" id="err_colore">
                    <%if(list != null && list.contains("colore")){%>
                        <%="Inserire colore ammissibile"%>
                    <%}%>
                </div>
            </div>
            <div>
                <label for="prezzo">Prezzo</label><br>
                <input type="number" step="any" min="0.01" max="" name="prezzo" id="prezzo" required
                    <%if(list != null){%>
                        <%if(list.contains("prezzo")){%>
                           class = "error-parameter"
                        <%}%>
                        value="<%=request.getParameter("prezzo")%>"
                    <%}%>
               >
            </div>
            <div>
                <label for="disponibilita">Quantità disponibile</label>
                <input type="number" min="1" max="200" name="disponibilita" id="disponibilita" required
                    <%if(list != null){%>
                        <%if(list.contains("disponibilita")){%>
                           class = "error-parameter"
                        <%}%>
                        value="<%=request.getParameter("disponibilita")%>"
                    <%}%>
                >
            </div>
            <div>
                <label for="descrizione">Descrizione</label><br>
                <textarea rows="8" name="descrizione" id="descrizione"></textarea><br>
            </div>
        </fieldset>
        <br>
        <fieldset>
            <legend>Dati tecnici</legend>
            <br>
            <div>
                <label>Stai inserendo un prodotto con batteria?</label>
                <input type="radio" name="batteria" id="batteria_true" value="true" required>
                <label for="batteria_true">Si</label>
                <input type="radio" name="batteria" id="batteria_false" value="false" required>
                <label for="batteria_false">No</label>
            </div>
            <br>
            <div>
                <label for="ram_tipo" >Tipologia RAM</label><br>
                <select name="ram_tipo" onchange="activeRam(value)" id="ram_tipo">
                    <option value="nessuna">Nessuna</option>
                    <option value="DDR3">DDR3</option>
                    <option value="DDR4">DDR4</option>
                    <option value="DDR5">DDR5</option>
                </select>
                <%if(list != null && list.contains("ram_tipo")){%>
                    <div class="error-message">
                        Inserire uno tipo din RAM dalla selezione multipla
                    </div>
                <%}%>

            </div>
            <div>
                <label for="ram_quantita">Quanità RAM</label><br>
                <input type="number"  min="1" max="1024" name="ram_quantita" id="ram_quantita"
                    <%if(list != null){%>
                    <%if(list.contains("ram_quantita")){%>
                       class = "error-parameter"
                    <%}%>
                       value="<%=request.getParameter("ram_quantita")%>"
                    <%}%>
                       disabled>
                <select name="ram_unit">
                    <option value="kb">KB</option>
                    <option value="mb">MB</option>
                    <option value="gb">GB</option>
                </select>
                <%if(list != null && list.contains("ram_unit")){%>
                <div class="error-message">
                   Selezionare un'unità dalla selezione multipla
                </div>
                <%}%>
            </div>
            <div>
                <label for="cpu_nome">Nome CPU</label><br>
                <input type="text" name="cpu_nome" id="cpu_nome" required>
            </div>
            <div>
                <label for="sistema_operativo">Sistema Operativo</label>
                <select name="sistema_operativo" id="sistema_operativo" required>
                    <option value="Windows">Windows</option>
                    <option value="Linux">Linux</option>
                    <option value="macOS">MacOS</option>
                    <option value="Android">Android</option>
                    <option value="iOS">iOS</option>
                    <option value="Proprietario">Propietario</option>
                </select>
                <%if(list != null && list.contains("sistema_operativo")){%>
                    <div class="error-message">
                        Inserire un tipo di sistema operativo
                    </div>
                <%}%>
            </div>
        </fieldset>
        <input type="submit">
    </form>
    </div>
    <script type="text/javascript" src="script/CaricamentoProdotti.js">
    </script>

</body>
</html>
