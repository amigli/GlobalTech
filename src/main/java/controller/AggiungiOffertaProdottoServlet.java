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
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
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
                                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
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
