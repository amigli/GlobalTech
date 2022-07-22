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
            ArrayList<String> errPar = new ArrayList<>();

            if(u!=null) {
                String via = request.getParameter("indirizzo");
                String civicoString  = request.getParameter("civico");
                String citta = request.getParameter("citta");
                String capString = request.getParameter("cap");

                if(via ==  null || !via.matches("^[A-Za-z]{3,30}"))
                    errPar.add("Via");

                int numeroCivico = 0;

                if(civicoString != null){
                    try {
                        numeroCivico = Integer.parseInt(civicoString);

                    } catch (NumberFormatException e) {
                        errPar.add("Numero civico");
                    }
                }else{
                    errPar.add("Numero civico");
                }

                if(citta ==  null || !citta.matches("^[A-Za-z]{3,30}"))
                    errPar.add("Citta");

                int cap = 0;


                if(capString != null){
                    try {
                        cap = Integer.parseInt(capString);

                    } catch (NumberFormatException e) {
                        errPar.add("CAP");
                    }
                }else{
                    errPar.add("CAP");
                }

                if(errPar.isEmpty()){
                    u.setVia(via);
                    u.setCitta(citta);
                    u.setCap(cap);
                    u.setNumCivico(numeroCivico);

                    UtenteDAO service = new UtenteDAO();
                    service.aggiornaUtente(u);
                    session.setAttribute("utente", u);
                }else{
                    request.setAttribute("error-parameter", errPar);
                    address ="/WEB-INF/utente/datiSpedizione.jsp";
                }


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
