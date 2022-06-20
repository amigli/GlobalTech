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
import java.util.ArrayList;

@WebServlet(name = "RegistrazioneUtenteServlet", value = "/registra-utente")
public class RegistrazioneUtente extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<String> errorPar =  new ArrayList<>();
        UtenteDAO service = new UtenteDAO();
        String address=null;

        String email = req.getParameter("email");
        String password = req.getParameter("pass");
        String nome = req.getParameter("nome");
        String cognome = req.getParameter("cognome");
        String dataNascita = req.getParameter("data_nascita");

        //aggiungere controlli con regex
        if (email==null || (!email.matches("^[a-z0-9/.]+@[a-z]+/.[a-z]{2,3}$")))
            errorPar.add("email");

        if (password==null || password.length()<8 || (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[/*.!@$%^&()[/]{}:;<>,.?+-_=]).{8,32}")))
            errorPar.add("password");

        if (nome==null || nome.length()<3)
            errorPar.add("nome");

        if (cognome==null || cognome.length()<3)
            errorPar.add("cognome");

        if (dataNascita==null)
            errorPar.add("dataNascita");

        if (errorPar.isEmpty()){
            Utente u = new Utente();
            u.setEmail(email);
            u.setPassword(password);
            u.setNome(nome);
            u.setCognome(cognome);
            u.setDataNascita(dataNascita);
            u.setAdmin(false);
            u.setNumAcquisti(0);
            service.registraUtente(u);
            //aggiungere collegamento alla Home
            address="#";
        }
        else{
            req.setAttribute("error_parameter", errorPar);
            //aggiungere un messaggio al formRegistrazione in questo caso
            address = "/formRegistrazione.jsp";
        }

        RequestDispatcher dispatcher =  req.getRequestDispatcher(address);
        dispatcher.forward(req, resp);

    }
}
