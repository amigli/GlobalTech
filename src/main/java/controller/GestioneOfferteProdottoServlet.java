package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

@WebServlet(name = "GestioneOfferteProdottoServlet", value = "/gestione-offerte-prodotto")
public class GestioneOfferteProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if(utente != null){
            if(utente.isAdmin()){
                String idString =  request.getParameter("id_prod");

                try{
                    int id =  Integer.parseInt(idString);

                    ProdottoDAO service =  new ProdottoDAO();
                    Prodotto prodotto = service.doRetrieveById(id);

                    if(prodotto != null){
                        GregorianCalendar lastUpdateOffers = (GregorianCalendar)
                                this.getServletContext().getAttribute("lastUpdateOffers");
                        OffertaDAO serviceOfferta = new OffertaDAO();
                        if(lastUpdateOffers.before(new GregorianCalendar())){
                            List<Offerta> offertaList =  serviceOfferta.doRetrieveActive();

                            this.getServletContext().setAttribute("offerte", offertaList);
                            this.getServletContext().setAttribute("lastUpdateOffers", new GregorianCalendar());
                        }

                        List<Offerta> offerte = serviceOfferta.doRetrieveFuture();

                        request.setAttribute("offerte_future", offerte);
                        request.setAttribute("prodotto", prodotto);

                        RequestDispatcher dispatcher =
                                request.getRequestDispatcher("/WEB-INF/admin/gestioneOfferteProdotto.jsp");

                        dispatcher.forward(request, response);
                    }else{
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }

                }catch (NumberFormatException e){
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }

            }else{
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }else{
            response.sendRedirect("login-page");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
