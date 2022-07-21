package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.OrdineDAO;
import model.Utente;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "cambiaStatoSpedizione", value = "/stato-spedizione")
public class CambiaStatoSpedizione extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente u = (Utente) request.getSession().getAttribute("utente");
        String  address = "/WEB-INF/result/modificaSpedizioneResult.jsp";

        if (u!=null){
            if (u.isAdmin()){
                ArrayList<String> errorPar =  new ArrayList<>();

                String daConfermare = request.getParameter("stato1");
                String daSpedire = request.getParameter("stato2");
                String idString = request.getParameter("idOrdine");
                String tracking = request.getParameter("numTracking");
                OrdineDAO service = new OrdineDAO();

                if (idString==null)
                    errorPar.add("id");

                if (errorPar.isEmpty()){
                    int id = Integer.parseInt(idString);

                    if (daConfermare!=null){
                        service.setStatusById(1, id);
                    }
                    if (daSpedire!=null){
                        if (tracking!=null){
                            service.setStatusById(2, id);
                            service.setTrackingById(tracking, id);
                        }
                    }
                }else{
                    request.setAttribute("error_parameter", errorPar);
                    //aggiungere un messaggio al form in visualizzaOrdini.jsp in questo caso
                    address = "visualizzaOrdini.jsp";
                }
            }else{
                response.sendError(401);
            }
        }else{
            response.sendRedirect("login-page");
        }
        RequestDispatcher dispatcher =  request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
