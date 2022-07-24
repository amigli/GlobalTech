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

@WebServlet(name = "RicercaProdottiServlet", value = "/ricerca")
public class RicercaProdottiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchKey =  request.getParameter("key");
        ProdottoDAO service =  new ProdottoDAO();

        List<Prodotto> list =  service.doRetrieveByNameOrMarca(searchKey);
        GregorianCalendar lastUpdateOffers =
                (GregorianCalendar) this.getServletContext().getAttribute("lastUpdateOffers");

        if(lastUpdateOffers.before(new GregorianCalendar())){
            OffertaDAO offertaService = new OffertaDAO();
            List<Offerta> activeOffers = offertaService.doRetrieveActive();

            getServletContext().setAttribute("offerte", activeOffers);
            getServletContext().setAttribute("lastUpdateOffers", new GregorianCalendar());
        }

        List<Offerta> offerte  = (List<Offerta>) getServletContext().getAttribute("offerte");
        List<Item> catalogo =  new ArrayList<>();

        for(Prodotto p : list){
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
        request.setAttribute("operation", "ricerca");
        request.setAttribute("catalogo", catalogo);
        String address = "/WEB-INF/public/catalogo.jsp" ;

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
