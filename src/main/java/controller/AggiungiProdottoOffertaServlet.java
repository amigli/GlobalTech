package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "AggiungiProdottoOffertaServlet", value = "/aggiungi-prodotto-offerta")
public class AggiungiProdottoOffertaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u =  (Utente) session.getAttribute("utente");

            if(u != null){
                if(u.isAdmin()){
                    String idOffertaString  =  request.getParameter("id");
                    String[] prodottiStringId = request.getParameterValues("prodotto");

                    if(idOffertaString != null){
                        try{
                            int id =  Integer.parseInt(idOffertaString);
                            ArrayList<Integer> idProdottiDaAggiungere = new ArrayList<>();

                            for(String s: prodottiStringId){
                                idProdottiDaAggiungere.add(Integer.parseInt(s));
                            }
                            OffertaDAO serviceOfferta = new OffertaDAO();
                            Offerta offerta =  serviceOfferta.doRetrieveById(id);

                            if(offerta != null){
                                ProdottoDAO serviceProdotto =  new ProdottoDAO();
                                List<Prodotto> prodottiAll =  serviceProdotto.doRetrieveAll();
                                List<Prodotto> prodottiDaAggiungere  =
                                        prodottiAll.stream().filter(o->idProdottiDaAggiungere.contains(o.getId()))
                                                .collect(Collectors.toList());

                                if(offerta.getProdotti().stream().noneMatch(p->prodottiDaAggiungere.contains(p))){
                                    for(Prodotto prodotto : prodottiDaAggiungere){
                                        serviceOfferta.doSaveProdottoOfferta(offerta, prodotto);
                                    }
                                }else{
                                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                                }

                            }else{
                                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                            }

                        }catch (NumberFormatException e){
                            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                        }
                    }else{
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST,"ID offerta non presente");
                    }


                }else{
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }else{
                response.sendRedirect("login-page");
            }
        }
    }

}
