package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@WebServlet(name = "ConfermaOrdineServlet", value = "/conferma-ordine")
public class ConfermaOrdineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u =   (Utente) session.getAttribute("utente");
            Carrello cart = (Carrello) session.getAttribute("carrello");

            if(u != null){
                if(cart != null && !cart.isEmpty()){
                    String checkcodeRequestString = request.getParameter("checkcode");
                    int checkcodeSession =  (Integer) session.getAttribute("checkcode");

                    try{
                        int checkcodeRequest = Integer.parseInt(checkcodeRequestString);
                        if(checkcodeSession == checkcodeRequest){
                            GregorianCalendar today =  new GregorianCalendar();
                            List<ItemCart> prodotti =  cart.getProdotti();
                            int spese_spedizione =  cart.getTotale() >= 100 ? 0 : 5;
                            float totale_ordine =  cart.getTotale() + spese_spedizione;
                            String indirizzo =  u.getNome() + " " +u.getCognome() + ", Via " +  u.getVia()
                                    + ", "  + u.getNumCivico() +", " + u.getCap() +", " + u.getCitta();
                            String cc_pagamento = u.getNumeroCarta();
                            Ordine  ordine =  new Ordine();

                            ordine.setProdotti(prodotti);
                            ordine.setSpeseSpedizione(spese_spedizione);
                            ordine.setPrezzoTotale(totale_ordine + spese_spedizione);
                            ordine.setCcPagamento(cc_pagamento);
                            ordine.setStato(Ordine.inAttesaDiConferma);
                            ordine.setData(today.toZonedDateTime().toLocalDate());
                            ordine.setIndirizzoSpedizione(indirizzo);

                            OrdineDAO service =  new OrdineDAO();
                            int id = service.doSaveOrdine(ordine, u);

                            CarrelloDAO serviceCarello = new CarrelloDAO();
                            serviceCarello.doDelete(u.getId());
                            cart.svuotaCarrello();
                            session.setAttribute("carrello", cart);

                            ordine.setId(id);

                            UtenteDAO serviceUtente = new UtenteDAO();
                            u.setNumAcquisti((u.getNumAcquisti()) + 1);

                            serviceUtente.doUpdateNumeroOrdini(u);


                            session.setAttribute("utente", u);

                            request.setAttribute("ordine", ordine);

                            RequestDispatcher dispatcher =
                                    request.getRequestDispatcher("/WEB-INF/result/confermaOrdineResult.jsp");

                            dispatcher.forward(request, response);
                        }else{
                            response.sendError(HttpServletResponse.SC_FORBIDDEN);
                        }
                    }catch (NumberFormatException e){
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                }
            }else{
                response.sendRedirect("login-page");
            }
        }
    }
}
