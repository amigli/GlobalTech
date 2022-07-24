package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "VisualizzaCatalogoServlet", value = "/catalogo")
public class VisualizzaCatalogoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession();

        synchronized (session){
            List<Item> catalogo = (List<Item>) session.getAttribute("catalogo");
            GregorianCalendar lastUpdateCatalogo = (GregorianCalendar) session.getAttribute("lastUpdateCatalogo");

            if( catalogo == null || lastUpdateCatalogo == null || lastUpdateCatalogo.before(new GregorianCalendar()) ){
                ProdottoDAO service  = new ProdottoDAO();
                List<Prodotto> allProdotti =  service.doRetrieveAll();
                GregorianCalendar lastUpdateOffers =
                        (GregorianCalendar) this.getServletContext().getAttribute("lastUpdateOffers");

                if(lastUpdateOffers.before(new GregorianCalendar())){
                    OffertaDAO offertaService = new OffertaDAO();
                    List<Offerta> activeOffers = offertaService.doRetrieveActive();

                    getServletContext().setAttribute("offerte", activeOffers);
                    getServletContext().setAttribute("lastUpdateOffers", new GregorianCalendar());
                }

                List<Offerta> offerte  = (List<Offerta>) getServletContext().getAttribute("offerte");
                catalogo =  new ArrayList<>();

                for(Prodotto p : allProdotti){
                    List<Offerta> offerteProdotto =  offerte.stream().filter(o->o.contains(p)).collect(Collectors.toList());
                    Item item = new Item();

                    item.setProdotto(p);
                    float prezzoTmp =  p.getPrezzoListino();

                    for(Offerta o : offerteProdotto){
                        prezzoTmp -= prezzoTmp * o.getPercentuale()/100;
                    }

                    item.setPrezzo(prezzoTmp);
                    catalogo.add(item);
                }

                session.setAttribute("catalogo", catalogo);
                session.setAttribute("lastUpdateCatalogo", new GregorianCalendar());
            }

            request.setAttribute("catalogo", catalogo);
            String address = "/WEB-INF/public/catalogo.jsp" ;

            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
