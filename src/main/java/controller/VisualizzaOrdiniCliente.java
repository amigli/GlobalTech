package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Ordine;
import model.OrdineDAO;
import model.Utente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "VisualizzaOrdiniCliente", value = "/visualizza-ordiniC")
public class VisualizzaOrdiniCliente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u = (Utente) session.getAttribute("utente");


            if (u!=null){
                if (u.isAdmin()){
                    String idUtenteString = request.getParameter("idCliente");
                    if (idUtenteString != null){
                        try{

                            int idUtente = Integer.parseInt(idUtenteString);
                            OrdineDAO service = new OrdineDAO();

                            List<Ordine>ordiniById = service.doRetrieveByUtenteId(idUtente);

                            request.setAttribute("ordini", ordiniById);

                            RequestDispatcher dispatcher = request.getRequestDispatcher( "/WEB-INF/admin/visualizzaOrdini.jsp");
                            dispatcher.forward(request, response);
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
