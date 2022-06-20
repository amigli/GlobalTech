<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 22/05/2022
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inserimento Prodotto</title>
    <%@include file="links.html"%>
</head>
<body>
    <div>
        <%ArrayList<String> list =
                    (ArrayList<String>) request.getAttribute("error_parameter");%>
    </div>

    <form action="carica-prodotto" id="caricamento-prodotto" method="post" onsubmit=" return validateForm()">
        <fieldset>
            <legend>Dati generali</legend>
            <div class="input-section">
                <label for="nome">Nome</label><br>
                <input type="text" name="nome" id="nome"
                    <%if(list != null && list.contains("nome")){%>
                        <%="class = \"error-parameter\" value=\""+ request.getParameter("nome") +"\""%>
                    <%}%>
                       required><br>
                <div class="error" id="err_nome">
                    <%if(list != null && list.contains("name")){%>
                        <%="Inserire nome ammissibile"%>
                    <%}%>
                </div>
            </div>
            <div>
                <label for="marca">Marca</label><br>
                <input type="text" name="marca" id="marca"
                    <%if(list != null && list.contains("marca")){%>
                        <%="class = \"error-parameter\" value=\""+ request.getParameter("marca") +"\""%>
                    <%}%>
               required>
                <div class="error" id="err_marca">
                    <%if(list != null && list.contains("marca")){%>
                        <%="Inserire marca ammissibile"%>
                    <%}%>
                </div>
            </div>
            <div>
                <label for="colore">Colore</label><br>
                <input type="text" name="colore" id="colore"
                    <%if(list != null && list.contains("colore")){%>
                        <%="class = \"error-parameter\" value=\""+ request.getParameter("colore") +"\""%>
                    <%}%>
                       required>
                <div class="error" id="err_colore">
                    <%if(list != null && list.contains("colore")){%>
                        <%="Inserire colore ammissibile"%>
                    <%}%>
                </div>
            </div>
            <div>
                <label for="prezzo">Prezzo</label><br>
                <input type="number" step="any" min="0.01" max="" name="prezzo" id="prezzo"
                    <%if(list != null && list.contains("prezzo")){%>
                        <%="class = \"error-parameter\" "%>
                    <%}%>
               required>
            </div>
            <div>
                <label for="disponibilita">Quantità disponibile</label>
                <input type="number" min="1" max="200" name="disponibilita" id="disponibilita"
                    <%if(list != null && list.contains("disponibilita")){%>
                         <%="class = \"error-parameter\""%>
                    <%}%>
                       required>
            </div>
            <div>
                <label for="descrizione">Descrizione</label><br>
                <textarea rows="8" cols="30" name="descrizione" id="descrizione"></textarea><br>
            </div>
        </fieldset>
        <fieldset>
            <legend>Dati tecnici</legend>
            <div>
                <label>Stai inserendo un prodotto con batteria?</label>
                <input type="radio" name="batteria" id="batteria_true" value="true" required>
                <label for="batteria_true">Si</label>
                <input type="radio" name="batteria" id="batteria_false" value="false" required>
                <label for="batteria_false">No</label>
            </div>
            <div>
                <label for="ram_tipo" >Tipologia RAM</label><br>
                <select name="ram_tipo" onchange="activeRam(value)" id="ram_tipo">
                    <option value="nessuna">Nessuna</option>
                    <option value="DDR4">DDR3</option>
                    <option value="DDR5">DDR4</option>
                    <option value="DDR5">DDR5</option>
                </select>
                <%if(list != null && list.contains("ram_tipo")){%>
                    <div>
                        Inserire uno tipo RAM dalla selezione multipla
                    </div>
                <%}%>

            </div>
            <div>
                <label for="ram_quantita">Quanità RAM</label><br>
                <input type="number"  min="1" max="1024" name="ram_quantita" id="ram_quantita"
                    <%if(list != null && list.contains("quantita_ram")){%>
                       <%="class = \"error-parameter\""%>
                    <%}%>
                       disabled>
                <select name="ram_unit">
                    <option value="kb">KB</option>
                    <option value="mb">MB</option>
                    <option value="mb">GB</option>
                </select>
            </div>
            <div>
                <label for="cpu_nome">Nome CPU</label><br>
                <select name="cpu_nome" id="cpu_nome">
                    <option value="nothing">-</option>
                    <option value="snapdragon-gen-1">SnapDragon Gen 1</option>
                </select>
            </div>
            <div>
                <label for="sistema_operativo">Sistema Operativo</label>
                <select name="sistema_operativo" id="sistema_operativo">
                    <option value="Windows">Windows</option>
                    <option value="Linux">Linux</option>
                    <option value="macOS">MacOS</option>
                    <option value="Android">Android</option>
                    <option value="iOS">iOS</option>
                    <option value="Propietario">Propietario</option>
                </select>
            </div>
        </fieldset>


        <input type="submit">
    </form>
    <script type="text/javascript" src="./script/CaricamentoProdotti.js">
    </script>

</body>
</html>
