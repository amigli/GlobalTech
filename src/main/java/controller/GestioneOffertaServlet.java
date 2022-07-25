package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

@WebServlet(name = "GestioneOffertaServlet", value = "/gestione-offerta")
public class GestioneOffertaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession();

        synchronized (session){
            Utente u = (Utente) session.getAttribute("utente");

            if(u != null){
                if(u.isAdmin()){
                    OffertaDAO service =  new OffertaDAO();
                    GregorianCalendar lastUpdateOffers =
                            (GregorianCalendar) this.getServletContext().getAttribute("lastUpdateOffers");

                    if(lastUpdateOffers.before(new GregorianCalendar())){
                        List<Offerta> offerteAttive =  service.doRetrieveActive();
                        this.getServletContext().setAttribute("offerte", offerteAttive);
                        this.getServletContext().setAttribute("lastUpdateOffers", new GregorianCalendar());
                    }

                    String idString  =  request.getParameter("id_offerta");

                    try{
                        int id =  Integer.parseInt(idString);

                        Offerta offerta =  service.doRetrieveById(id);

                        if(offerta != null) {
                            ProdottoDAO serviceProdotto =  new ProdottoDAO();
                            List<Prodotto> prodottiList =  serviceProdotto.doRetrieveAll();

                            request.setAttribute("prodotti", prodottiList);
                            request.setAttribute("offerta", offerta);

                            RequestDispatcher dispatcher =
                                    request.getRequestDispatcher("/WEB-INF/admin/gestioneOfferta.jsp");
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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
