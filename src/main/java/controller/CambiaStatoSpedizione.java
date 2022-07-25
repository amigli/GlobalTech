package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Ordine;
import model.OrdineDAO;
import model.Utente;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "cambiaStatoSpedizione", value = "/stato-spedizione")
public class CambiaStatoSpedizione extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u = (Utente) session.getAttribute("utente");

            if (u!=null){
                if (u.isAdmin()){
                    ArrayList<String> errorPar =  new ArrayList<>();

                    String daConfermare = request.getParameter("stato1");
                    String daSpedire = request.getParameter("stato2");
                    String idString = request.getParameter("idOrdine");
                    String tracking = request.getParameter("numTracking");


                    if(daConfermare != null || (daSpedire != null && tracking != null && tracking.matches("^[A-Za-z0-9]{3,15}$"))){
                        if (idString != null){
                            try{
                                int id = Integer.parseInt(idString);
                                OrdineDAO service = new OrdineDAO();

                                Ordine ordine = service.doRetrieveById(id);

                                if(ordine != null){
                                    if (daConfermare!=null){
                                        service.setStatusById(1, id);
                                    }else{
                                        service.setStatusById(2, id);
                                        service.setTrackingById(tracking, id);
                                    }

                                    String  address = "/WEB-INF/result/modificaSpedizioneResult.jsp";

                                    RequestDispatcher dispatcher =  request.getRequestDispatcher(address);
                                    dispatcher.forward(request, response);
                                }else{
                                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                                }
                            }catch (NumberFormatException e){
                                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                            }
                        }else{
                            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                        }
                    }else {
                        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
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
