package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(name = "ModificaDatiPUtente", value = "/modifica-datiPUtente")
public class ModificaDatiPUtente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u = (Utente) session.getAttribute("utente");

            String  address = "/WEB-INF/result/modificaDatiUtente.jsp";
            UtenteDAO service = new UtenteDAO();
            ArrayList<String> errPar = new ArrayList<>();

            if(u!=null){
                String nome = request.getParameter("nome");
                if(nome!=null)
                    u.setNome(nome);

                String cognome = request.getParameter("cognome");
                if(cognome!=null)
                    u.setCognome(cognome);

                LocalDate dataNascita = LocalDate.parse(request.getParameter("dataNascita"));
                if(dataNascita!=null)
                    u.setDataNascita(dataNascita);

                String numTelefono = request.getParameter("telefono");
                if(numTelefono!=null)
                    u.setNumTelefono(numTelefono);

                service.aggiornaUtente(u);
                session.setAttribute("utente", u);

                RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                dispatcher.forward(request, response);
            }else{
                response.sendRedirect("login-page");
        }

        }
 }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
