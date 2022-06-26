package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

@WebServlet(name = "RegistrazioneUtenteServlet", value = "/registra-utente")
public class RegistrazioneUtente extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<String> errorPar =  new ArrayList<>();
        UtenteDAO service = new UtenteDAO();
        String address=null;

        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String dataNascitaString = request.getParameter("data_nascita");

        //aggiungere controlli con regex
        if (email==null || (!email.matches("^[a-z0-9\\.\\_]+@[a-z]+\\.[a-z]{2,3}$")))
            errorPar.add("email_registrazione");

        /*if (password==null || password.length()<8 ||
                (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[/*.!@$%^&()[/]{}:;<>,.?+-_=]).{8,32}")))
            errorPar.add("password");*/

        if (nome==null || nome.length()<3)
            errorPar.add("nome");

        if (cognome==null || cognome.length()<3)
            errorPar.add("cognome");

        LocalDate dataNascita = null;

        if (dataNascitaString==null)
            errorPar.add("dataNascita");
        else {
            try{
                dataNascita = LocalDate.parse(dataNascitaString);
            }catch (DateTimeParseException e){
                errorPar.add("dataNascita");
            }
        }

        if (errorPar.isEmpty()){
            Utente u = new Utente();
            u.setEmail(email);
            u.setPassword(password);
            u.setNome(nome);
            u.setCognome(cognome);
            u.setDataNascita(dataNascita);
            u.setAdmin(false);
            u.setNumAcquisti(0);

            int id = service.registraUtente(u);

            u.setId(id);

            request.setAttribute("utente", u);

            address="/WEB-INF/result/registrazioneResult.jsp";

        }
        else{
            request.setAttribute("error_parameter", errorPar);
            //aggiungere un messaggio al formRegistrazione in questo caso
            address = "/WEB-INF/public/formRegistrazione.jsp";
        }

        RequestDispatcher dispatcher =  request.getRequestDispatcher(address);
        dispatcher.forward(request, resp);
    }
}
