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

@WebServlet(name = "RimuoviProdottoOffertaServlet", value = "/rimuovi-prodotto-offerta")
public class RimuoviProdottoOffertaServlet extends HttpServlet {
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
                            ArrayList<Integer> idProdottiDaRimuovere = new ArrayList<>();

                            for(String s: prodottiStringId){
                                idProdottiDaRimuovere.add(Integer.parseInt(s));
                            }
                            OffertaDAO serviceOfferta = new OffertaDAO();
                            Offerta offerta =  serviceOfferta.doRetrieveById(id);

                            if(offerta != null){
                                ProdottoDAO serviceProdotto =  new ProdottoDAO();
                                List<Prodotto> prodottiAll =  serviceProdotto.doRetrieveAll();
                                List<Prodotto> prodottiDaRimuovere  =
                                        prodottiAll.stream().filter(o->idProdottiDaRimuovere.contains(o.getId()))
                                                .collect(Collectors.toList());


                                if(prodottiDaRimuovere.stream().allMatch(p->offerta.getProdotti().contains(p))){
                                    for(Prodotto prodotto : prodottiDaRimuovere){
                                        serviceOfferta.doRemoveProdottoOfferta(offerta, prodotto);
                                    }

                                    if(offerta.isActive()){
                                        List<Offerta> offertaList =  serviceOfferta.doRetrieveActive();

                                        GregorianCalendar today =  new GregorianCalendar();
                                        ServletContext context =  this.getServletContext();
                                        context.setAttribute("offerte", offertaList);
                                        context.setAttribute("lastUpdateOffers", today);
                                    }
                                }else{
                                    response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
                                }

                            }else{
                                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                            }

                        }catch (NumberFormatException e){
                            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                        }
                    }else{
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
}
