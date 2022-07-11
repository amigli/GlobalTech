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
        Utente u = (Utente) request.getSession().getAttribute("utente");
        List<String> errorPar = new ArrayList<>();
        String address="/WEB-INF/admin/visualizzaOrdini.jsp";

        if (u!=null){
            if (u.isAdmin()){
                OrdineDAO service = new OrdineDAO();

                List<Ordine>ordiniAll = service.doRetrieveAll();

                if (ordiniAll!=null){
                    request.setAttribute("ordini", ordiniAll);
                }else{
                    errorPar.add("lista vuota");
                }
            }else{
                response.sendError(401);
            }
        }else{
            response.sendRedirect("login-page");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
