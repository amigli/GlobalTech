package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;

@WebServlet(name = "RimuoviPorodottoCarrelloServlet", value = "/rimuovi-prodotto-carrello")
public class RimuoviProdottoCarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        synchronized (session){
            Carrello cart = (Carrello) session.getAttribute("carrello");

            if(cart != null && !cart.isEmpty()){
                String prodoottoIdString =  request.getParameter("id_prod");

                if(prodoottoIdString !=  null){
                    try{
                        int id = Integer.parseInt(prodoottoIdString);

                        ProdottoDAO serviceProdotto = new ProdottoDAO();
                        Prodotto prodotto =  serviceProdotto.doRetrieveById(id);

                        if(prodotto != null){
                            ItemCart deleted = cart.rimuoviProdotto(prodotto);


                            if(deleted != null){
                                Utente u = (Utente) session.getAttribute("utente");

                                if(u != null){
                                    CarrelloDAO serviceCarrello = new CarrelloDAO();

                                    serviceCarrello.doRemoveProdotto(u, prodotto);
                                }

                                session.setAttribute("carrello", cart);

                                response.sendRedirect("visualizza-carrello");

                            }else{
                                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                            }
                        }
                    }catch (NumberFormatException e){
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                }else{
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
            }else{
                response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
            }
        }
    }
}
