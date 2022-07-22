package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;

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

                if(numCarta == null || numCarta.matches("^[0-9]{13,16}$"))
                    errPar.add("Numero Carta");


                String cvvString = request.getParameter("cvv");

                int cvv = 0;

                if(cvvString != null && cvvString.matches("^[0-9]{3}$")){
                    cvv = Integer.parseInt(cvvString);
                }else{
                    errPar.add("CVV");
                }

                String scadenzaMeseString =  request.getParameter("month-scad-cc");
                String scadenzaAnnoString = request.getParameter("year-scad-cc");

                int scadenzaMese = 0, scadenzaAnno = 0;

                try{
                    scadenzaMese = Integer.parseInt(scadenzaMeseString);

                }catch (NumberFormatException e){
                    errPar.add("Mese scadenza carta");
                }

                try{
                    scadenzaAnno = Integer.parseInt(scadenzaAnnoString);
                }catch (NumberFormatException e){
                    errPar.add("Anno scadenza carta");
                }
                int day = scadenzaMese == 2 ? 28 :
                        scadenzaMese == 1 || scadenzaMese == 3 ||
                                scadenzaMese == 5 || scadenzaMese == 7 ||
                                scadenzaMese == 8 || scadenzaMese == 10 ||
                                scadenzaMese == 12 ? 31 : 30;

                GregorianCalendar today  = new GregorianCalendar();
                GregorianCalendar scadenza = new GregorianCalendar(scadenzaAnno, scadenzaMese - 1, day);

                if(scadenza.before(today)){
                    errPar.add("Scadenza Carta");
                }

                if(errPar.isEmpty()){
                    u.setNumeroCarta(numCarta);
                    u.setCvvCarta(cvv);
                    u.setDataScadenzaCarta(scadenza.toZonedDateTime().toLocalDate());

                    service.aggiornaUtente(u);
                    session.setAttribute("utente", u);


                }else{
                    request.setAttribute("error-parameter", errPar);
                    address = "/WEB-INF/utente/datiCarta.jsp";
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
