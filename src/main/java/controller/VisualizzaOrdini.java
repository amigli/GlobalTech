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

@WebServlet(name = "VisualizzaTuttiOrdini", value = "/visualizza-ordini")
public class VisualizzaOrdini extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u = (Utente) request.getSession().getAttribute("utente");
            List<String> errorPar = new ArrayList<>();

            if (u!=null){
                if (u.isAdmin()){
                    OrdineDAO service = new OrdineDAO();

                    List<Ordine>ordiniAll = service.doRetrieveAll();

                    request.setAttribute("ordini", ordiniAll);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/visualizzaOrdini.jsp");
                    dispatcher.forward(request, response);

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
