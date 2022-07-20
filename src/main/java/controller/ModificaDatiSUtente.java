package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ModificaDatiSUtente", value = "/modifica-datiSUtente")
public class ModificaDatiSUtente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u = (Utente) session.getAttribute("utente");

            String  address = "/WEB-INF/result/modificaDatiUtente.jsp";
            UtenteDAO service = new UtenteDAO();
            ArrayList<String> errPar = new ArrayList<>();

            if(u!=null) {
                String via = request.getParameter("indirizzo");
                if (via != null)
                    u.setVia(via);

                String civico = request.getParameter("civico");
                try {
                    int c = Integer.parseInt(civico);
                    u.setNumCivico(c);
                } catch (NumberFormatException e) {
                    errPar.add("civico");
                }

                String citta = request.getParameter("citta");
                if (citta != null)
                    u.setCitta(citta);

                String cap = request.getParameter("cap");
                try {
                    int CAP = Integer.parseInt(cap);
                    u.setCap(CAP);
                } catch (NumberFormatException e) {
                    errPar.add("CAP");
                }

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
