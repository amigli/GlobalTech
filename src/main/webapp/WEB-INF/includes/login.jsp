

<form id="login-form" action="login" method="post">
    <h2>Sei gi&agrave; un cliente GlobalTech?</h2>
    <br>
    <%if(request.getAttribute("error_login") != null) {
        boolean error = (boolean) request.getAttribute("error_login");

        if(error){%>
    <div id="error">
        Email e/o password errata, ritentare.
    </div>
    <%}
    }%>
    <label for="email">Email:</label><br>
    <input type="text" name="email" id="email"><br>
    <label for="password">Password:</label><br>
    <input type="password" name="password" id="password">
    <br>
    <br>
    <input type="submit" value="Login">
    <br>
    <a href="registrazione">Sei un nuovo cliente? Registrati!</a>
</form>