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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "RegistrazioneUtenteServlet", value = "/registra-utente")
public class RegistrazioneUtente extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> errorPar =  new ArrayList<>();
        UtenteDAO service = new UtenteDAO();
        String address="/WEB-INF/result/registrazioneResult.jsp";

        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String dataNascitaString = request.getParameter("data_nascita");

        if (email==null || (!email.matches("^[a-z0-9\\.\\_]+@[a-z]+\\.[a-z]{2,3}$")))
            errorPar.add("email_registrazione");

        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!_;,:+.-]).{8,20}$");
        Matcher matcher = pattern.matcher(password);
        if (password==null || !matcher.matches())
            errorPar.add("password");

        if (nome==null || nome.length()<3 || !nome.matches("^[A-Za-z\\s]{3,30}$"))
            errorPar.add("nome");

        if (cognome==null || cognome.length()<3 || !cognome.matches("^[A-Za-z\\s]{3,30}$"))
            errorPar.add("cognome");

        LocalDate dataNascita = null;

        if (dataNascitaString == null)
            errorPar.add("dataNascita");
        else {
            try {
                dataNascita = LocalDate.parse(dataNascitaString);
            }catch (DateTimeParseException e){
                errorPar.add("dataNascita");
            }
        }

        if (errorPar.isEmpty()){
            Utente tmp =  service.doRetrieveByEmail(email);

            if(tmp ==  null){

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


            }else{
                address = "/WEB-INF/public/formRegistrazione.jsp";
                request.setAttribute("message", "Esiste già un account con questa email");
            }
        }else{
            request.setAttribute("error_parameter", errorPar);
            //aggiungere un messaggio al formRegistrazione in questo caso
            address = "/WEB-INF/public/formRegistrazione.jsp";
        }

        RequestDispatcher dispatcher =  request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
