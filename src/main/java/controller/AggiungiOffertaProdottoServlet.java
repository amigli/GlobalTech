package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "AggiungiOffertaServlet", value = "/applica-offerta-prodotto")
public class AggiungiOffertaProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u =  (Utente) session.getAttribute("utente");

            if(u != null){
                if(u.isAdmin()){
                    String idString  =  request.getParameter("id");
                    String[] offerteStringId = request.getParameterValues("offerta");

                    if(idString != null){
                        try{
                            int id =  Integer.parseInt(idString);
                            ArrayList<Integer> idOfferteDaApplicare = new ArrayList<>();

                            for(String s: offerteStringId){
                                idOfferteDaApplicare.add(Integer.parseInt(s));
                            }
                            ProdottoDAO serviceProdotto = new ProdottoDAO();
                            Prodotto prod = serviceProdotto.doRetrieveById(id);

                            if(prod != null){
                                OffertaDAO serviceOfferta = new OffertaDAO();

                                List<Offerta> offerteAll =  serviceOfferta.doRetrieveAll();
                                List<Offerta> offerteDaApplicare  =
                                        offerteAll.stream().filter(o->idOfferteDaApplicare.contains(o.getId()))
                                                .collect(Collectors.toList());

                                if(offerteDaApplicare.stream().noneMatch(o->o.contains(prod))){
                                    for(Offerta offerta : offerteDaApplicare){
                                        serviceOfferta.doSaveProdottoOfferta(offerta, prod);
                                    }
                                }else{
                                    response.sendError(401);
                                }

                            }else{
                                response.sendError(402);
                            }

                        }catch (NumberFormatException e){
                            response.sendError(404);
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
