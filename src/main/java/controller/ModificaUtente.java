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
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u = (Utente) request.getSession().getAttribute("utente");

            if (u!=null){
                if (u.isAdmin()){
                    UtenteDAO service = new UtenteDAO();

                    String address = "/WEB-INF/result/modificaUtenteResult.jsp";

                    String rimuoviAdminButton = request.getParameter("rimuoviAdmin");
                    String aggiungiAdminButton = request.getParameter("aggiungiAdmin");
                    String idString = request.getParameter("id");

                    if (idString != null){
                        try{
                            int id = Integer.parseInt(idString);
                            Utente utente = service.doRetrieveById(id);
                            if(utente != null){
                                if (rimuoviAdminButton!=null ||aggiungiAdminButton!=null ) {

                                    if (rimuoviAdminButton != null) {
                                        service.setAdmin(0, id);
                                    }
                                    if (aggiungiAdminButton != null) {
                                        service.setAdmin(1, id);
                                    }

                                    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                                    dispatcher.forward(request, response);
                                }else{
                                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
