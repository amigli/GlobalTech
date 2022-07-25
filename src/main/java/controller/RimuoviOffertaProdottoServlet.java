package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "RimuoviOffertaProdottoServlet", value = "/rimuovi-offerta-prodotto")
public class RimuoviOffertaProdottoServlet extends HttpServlet {
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
                            ArrayList<Integer> idOfferteDaRimuovere = new ArrayList<>();

                            for(String s: offerteStringId){
                                idOfferteDaRimuovere.add(Integer.parseInt(s));
                            }
                            ProdottoDAO serviceProdotto = new ProdottoDAO();
                            Prodotto prod = serviceProdotto.doRetrieveById(id);

                            if(prod != null){
                                OffertaDAO serviceOfferta = new OffertaDAO();

                                List<Offerta> offerteAll =  serviceOfferta.doRetrieveAll();
                                List<Offerta> offerteDaRimuovere  =
                                        offerteAll.stream().filter(o->idOfferteDaRimuovere.contains(o.getId()))
                                                .collect(Collectors.toList());

                                if(offerteDaRimuovere.stream().allMatch(o->o.contains(prod))){
                                    for(Offerta offerta : offerteDaRimuovere){
                                        serviceOfferta.doRemoveProdottoOfferta(offerta, prod);
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
