package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "DettaglioProdottoServlet", value = "/dettaglio-prodotto")
public class DettaglioProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProdottoString =  request.getParameter("id");

        try{
            int id = Integer.parseInt(idProdottoString);

            ProdottoDAO service = new ProdottoDAO();

            Prodotto p =  service.doRetrieveById(id);

            if(p != null){
                GregorianCalendar lastUpdateOffers =
                        (GregorianCalendar) this.getServletContext().getAttribute("lastUpdateOffers");

                if(lastUpdateOffers.before(new GregorianCalendar())){
                    OffertaDAO offertaService = new OffertaDAO();
                    List<Offerta> activeOffers = offertaService.doRetrieveActive();

                    getServletContext().setAttribute("offerte", activeOffers);
                    getServletContext().setAttribute("lastUpdateOffers", new GregorianCalendar());
                }

                List<Offerta> offerte  = (List<Offerta>) getServletContext().getAttribute("offerte");

                List<Offerta> offerteProdotto =  offerte.stream().filter(o->o.contains(p)).collect(Collectors.toList());
                Item item = new Item();

                item.setProdotto(p);
                float prezzoTmp =  p.getPrezzoListino();

                for(Offerta o : offerteProdotto){
                    prezzoTmp -= prezzoTmp * o.getPercentuale()/100;
                }

                item.setPrezzo(prezzoTmp);

                request.setAttribute("item",item);

                RequestDispatcher dispatcher =
                        request.getRequestDispatcher("/WEB-INF/public/visualizzaDettaglioProdotto.jsp");

                dispatcher.forward(request, response);
            }else{
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }catch (NumberFormatException e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
