package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.sql.SQLData;

@WebServlet(name = "RegistrazioneUtenteServlet", value = "/registra-utente")
public class RegistrazioneUtente extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UtenteDAO service = new UtenteDAO();

        String email = req.getParameter("email");
        String password = req.getParameter("pass");
        String nome = req.getParameter("nome");
        String cognome = req.getParameter("cognome");
        String dataNascita = req.getParameter("data_nascita");
        String via = req.getParameter("via");
        int civico = Integer.parseInt(req.getParameter("numero_civico"));
        String citta = req.getParameter("citta");
        int cap = Integer.parseInt(req.getParameter("cap"));
        String telefono = req.getParameter("numero_telefono");
        String numeroCarta = req.getParameter("numero_cc");
        int cvv = Integer.parseInt(req.getParameter("cvv"));
        String scadenzaCarta = req.getParameter("scadenza_carta");

       Utente u = new Utente();
       u.setEmail(email);
       u.setPassword(password);
       u.setNome(nome);
       u.setCognome(cognome);
       u.setDataNascita(dataNascita);
       u.setVia(via);
       u.setNumCivico(civico);
       u.setCitta(citta);
       u.setCap(cap);
       u.setNumTelefono(telefono);
       u.setNumeroCarta(numeroCarta);
       u.setCvvCarta(cvv);
       u.setDataScadenzaCarta(scadenzaCarta);
       u.setAdmin(false);

       service.registraUtente(u);


    }
}
