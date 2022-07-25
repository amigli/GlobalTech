package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

@WebServlet(name = "ModificaDatiPUtente", value = "/modifica-datiPUtente")
public class ModificaDatiPUtente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u = (Utente) session.getAttribute("utente");
            if(u!=null){
                String  address = "/WEB-INF/result/modificaDatiUtente.jsp";
                ArrayList<String> errPar = new ArrayList<>();

                String nome = request.getParameter("nome");
                String cognome = request.getParameter("cognome");
                String numTelefono = request.getParameter("telefono");
                String dataNascitaString = request.getParameter("dataNascita");

                if(nome ==  null || nome.length() < 3 || !nome.matches("^[A-Za-z\\s]{3,30}$"))
                    errPar.add("Nome");

                if(cognome ==  null || cognome.length() < 3 || !cognome.matches("^[A-Za-z\\s]{3,30}$"))
                    errPar.add("Cognome");

                if(numTelefono ==  null || numTelefono.length() < 9 || !numTelefono.matches("^[0-9]{9,10}$"))
                    errPar.add("Numero telefono");

                LocalDate dataNascita = null;

                if(dataNascitaString != null){
                    try {
                        dataNascita = LocalDate.parse(dataNascitaString);
                    }catch (DateTimeParseException e){
                        errPar.add("Data Nascita");
                    }
                }else {
                    errPar.add("Data Nascita");
                }

                if(errPar.isEmpty()){
                    u.setNome(nome);
                    u.setCognome(cognome);
                    u.setDataNascita(dataNascita);

                    UtenteDAO service = new UtenteDAO();
                    service.aggiornaUtente(u);
                    session.setAttribute("utente", u);
                }else{
                    request.setAttribute("error-parameter", errPar);
                    address = "/WEB-INF/utente/datiPersonali.jsp";
                }


                RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                dispatcher.forward(request, response);

            }else{
                response.sendRedirect("login-page");
            }

        }
    }
}
