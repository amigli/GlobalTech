package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(name = "ModificaDatiCUtente", value = "/modifica-datiCUtente")
public class ModificaDatiCUtente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u = (Utente) session.getAttribute("utente");

            String  address = "/WEB-INF/result/modificaDatiUtente.jsp";
            UtenteDAO service = new UtenteDAO();
            ArrayList<String> errPar = new ArrayList<>();

            if(u!=null){
                String numCarta = request.getParameter("numCarta");
                if(numCarta!=null)
                    u.setNumeroCarta(numCarta);

                String cvv = request.getParameter("cvv");
                try{
                    int CVV = Integer.parseInt(cvv);
                    u.setCvvCarta(CVV);
                }catch (NumberFormatException e){
                    errPar.add("CVV");
                }

                LocalDate dataCarta = LocalDate.parse(request.getParameter("dataCarta"));
                if(dataCarta!=null)
                    u.setDataScadenzaCarta(dataCarta);

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
