package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ModificaUtente", value = "/modifica-utente")
public class ModificaUtente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        synchronized (session){
            Utente u = (Utente) request.getSession().getAttribute("utente");

            if (u!=null){
                if (u.isAdmin()){
                    String rimuoviAdminButton = request.getParameter("rimuoviAdmin");
                    String aggiungiAdminButton = request.getParameter("aggiungiAdmin");
                    String idString = request.getParameter("id");

                    if (idString != null){
                        try{
                            int id = Integer.parseInt(idString);

                            UtenteDAO service = new UtenteDAO();

                            String address = "/WEB-INF/result/modificaUtenteResult.jsp";

                            Utente utente = service.doRetrieveById(id);
                            if(utente != null){
                                if ((rimuoviAdminButton != null && utente.isAdmin())
                                        || (aggiungiAdminButton != null && !utente.isAdmin())) {

                                    if (rimuoviAdminButton != null && utente.isAdmin()) {
                                        service.setAdmin(0, id);
                                    }else{
                                        service.setAdmin(1, id);
                                    }

                                    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                                    dispatcher.forward(request, response);
                                }else{
                                    response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
                                }
                            }else{
                                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                            }

                        }catch (NumberFormatException e){
                            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                        }
                    }else {
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
