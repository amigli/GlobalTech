package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "AggiungiAlCarrelloServlet", value = "/aggiungi-carrello")
public class AggiungiAlCarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){

            String prodottoIdString =  request.getParameter("prodotto");
            String quantitaString = request.getParameter("quantita");


            try{
                int prodottoId = Integer.parseInt(prodottoIdString);
                int quantita = Integer.parseInt(quantitaString);

                ProdottoDAO service = new ProdottoDAO();
                Prodotto p = service.doRetrieveById(prodottoId);

                if(p != null){
                    if(p.getDisponibilita() >= quantita){
                        List<Offerta> offerte = (List<Offerta>)getServletContext().getAttribute("offerte");
                        List<Offerta> offerteProdotto =
                                offerte.stream().filter(o-> o.contains(p)).collect(Collectors.toList());

                        ItemCart item =  new ItemCart();

                        item.setProdotto(p);
                        item.setQuantita(quantita);

                        float prezzo = p.getPrezzoListino();

                        for(Offerta o : offerteProdotto){
                            prezzo -= prezzo * o.getPercentuale()/100;
                        }

                        item.setPrezzo(prezzo);

                        Carrello cart = (Carrello)  session.getAttribute("carrello");
                        Utente u =  (Utente) session.getAttribute("utente");

                        if(cart == null){
                            cart = new Carrello();
                        }
                        if(u != null){
                            CarrelloDAO serviceCarrello = new CarrelloDAO();

                            if(cart.getProdotti().stream().anyMatch(i->i.getProdotto().equals(p))){
                                List<ItemCart> itemList = cart.getProdotti().stream().filter(i->i.getProdotto().equals(p)).collect(Collectors.toList());

                                ItemCart tmp = itemList.get(0);

                                serviceCarrello.doUpdateQuantityProduct(u,tmp);
                            }else{
                                serviceCarrello.doAggiungiProdotto(u, item);
                            }
                        }

                        cart.addProdotto(item);

                        request.setAttribute("carrello", cart);
                        session.setAttribute("carrello", cart);

                        RequestDispatcher dispatcher =
                                request.getRequestDispatcher("/WEB-INF/public/visualizzaCarrello.jsp");

                        dispatcher.forward(request, response);
                    }else{
                        response.sendRedirect("errore-carrello");
                    }

                }else{
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            }catch (NumberFormatException e){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }
}
