<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 22/05/2022
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inserimento Prodotto</title>
</head>
<body>
    <form action="#" id="caricamento-prodotto" method="post" onsubmit=" return validateForm()">
        <div>
            <label for="nome">Nome</label><br>
            <input type="text" name="nome" id="nome" required><br>
            <div class="error" id="err_nome"></div>
        </div>
        <div>
            <label for="marca">Marca</label><br>
            <input type="text" name="marca" id="marca" required><br>
            <div class="error" id="err_marca"></div>
        </div>
        <div>
            <label for="colore">Colore</label><br>
            <input type="text" name="colore" id="colore" required>
            <div class="error" id="err_colore"></div>
        </div>
        <div>
            <label for="prezzo">Prezzo</label><br>
            <input type="number" step="any" min="0.01" max="" name="prezzo" id="prezzo" required><br>
        </div>
        <div>
            <label for="descrizione">Descrizione</label><br>
            <textarea rows="8" cols="30" name="descrizione" id="descrizione"></textarea><br>
        </div>
        <div>
            <p>Stai inserendo un prodotto con batteria?</p>
            <input type="radio" name="batteria" id="batteria_true" value="true" required><label for="batteria_true">Si</label>
            <input type="radio" name="batteria" id="batteria_false" value="false" required><label for="batteria_false">No</label>
        </div>

        <fieldset>
            <legend>Dati tecnici</legend>
            <h3>Ram</h3>
            <div>
                <label for="ram_tipo" >Tipologia RAM</label><br>
                <select name="ram_tipo" onchange="activeRam(value)" id="ram_tipo">
                    <option value="nessuna">Nessuna</option>
                    <option value="DDR4">DDR3</option>
                    <option value="DDR5">DDR4</option>
                    <option value="DDR5">DDR5</option>
                </select>
            </div>
            <div>
                <label for="ram_quantita">Quanità RAM</label><br>
                <input type="number" step="any" min="0.2" max="128" name="ram_quantita" id="ram_quantita" disabled>
            </div>
            <h3>CPU</h3><br>
            <div>
                <label for="cpu_nome">Nome CPU</label><br>
                <select name="cpu_nome" id="cpu_nome">
                    <option value="nothing">-</option>
                    <option>SnapDragon Gen 1</option>
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

        <div>
            <label for="disponibilita">Quantità disponibile</label>
            <input type="number" min="1" max="200" name="disponibilita" id="disponibilita" required>
        </div>
        <input type="submit">
    </form>
    <script type="text/javascript" src="./script/CaricamentoProdotti.js">
    </script>

</body>
</html>
