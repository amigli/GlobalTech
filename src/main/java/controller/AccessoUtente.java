package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;

@WebServlet(name = "AccessoUtenteServlet", value = "/accesso-utente")
public class AccessoUtente extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UtenteDAO service = new UtenteDAO();
        String address;

        /*
        * I controlli non li inserisco perché si suppone che, essendo fatti già in fase di registrazione,
        * sia email e password siano coerenti con quelli che sono i formati richiesti.
        * */

        Utente u=service.login(email, password);
        if (u!=null){
            if (u.isAdmin())
                address="#"; //pagina che gestisce l'admin (ancora da fare)
            else
                address="#"; //home del sito
        }
        else{
            //anche qui inserire un messaggio nella pagina di login che indica che qualcosa è andato storto
            address = "/loginPage.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
