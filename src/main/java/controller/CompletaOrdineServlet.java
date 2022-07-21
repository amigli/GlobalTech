package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CompletaOrdineServlet", value = "/completa-ordine")
public class CompletaOrdineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session  = request.getSession();

        synchronized (session){
            Utente u = (Utente) session.getAttribute("utente");

            if(u != null){
                Carrello cart =  (Carrello) session.getAttribute("carrello");
                if(cart != null && !cart.isEmpty()){
                    List<ItemCart> prodottiCarrello =  cart.getProdotti();

                    ProdottoDAO service =  new ProdottoDAO();
                    List<ItemCart> removedProduct = new ArrayList<>();

                    for(ItemCart item : prodottiCarrello){
                        Prodotto p  = service.doRetrieveById(item.getProdotto().getId());

                        if(p.getDisponibilita() < item.getQuantita()){
                            removedProduct.add(item);
                        }
                    }

                    if(removedProduct.isEmpty()){
                        RequestDispatcher dispatcher =
                                request.getRequestDispatcher("/WEB-INF/public/salvaDatiSpedizione.jsp");
                        dispatcher.forward(request, response);
                    }else{
                        for (ItemCart item : removedProduct){
                            cart.rimuoviProdotto(item);
                        }

                        response.sendRedirect("errore-carrello");
                    }



                }else{
                    response.sendRedirect("visualizza-carrello");
                }
            }else{
                response.sendRedirect("login-page");
            }
        }
    }
}
