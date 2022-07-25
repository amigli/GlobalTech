package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;

@WebServlet(name = "GestioneUtente", value = "/gestione-utente")
public class GestioneUtente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u = (Utente) session.getAttribute("utente");

            if (u!=null){
                if (u.isAdmin()){
                    String idString = request.getParameter("id");
                    if (idString!=null){
                        try{
                            int id = Integer.parseInt(idString);
                            UtenteDAO service = new UtenteDAO();

                            Utente uId = service.doRetrieveById(id);

                            if (uId!=null){
                                request.setAttribute("utenteId", uId);
                                String address =  "/WEB-INF/admin/gestioneUtente.jsp";

                                RequestDispatcher dispatcher =  request.getRequestDispatcher(address);

                                dispatcher.forward(request, response);
                            }
                            else{
                                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                            }
                        }catch (NumberFormatException e){
                            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                        }
                    }
                    else{
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    }
                }
                else{
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
            else{
                response.sendRedirect("login-page");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
