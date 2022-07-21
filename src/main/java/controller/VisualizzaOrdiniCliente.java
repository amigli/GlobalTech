package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Ordine;
import model.OrdineDAO;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "VisualizzaOrdiniCliente", value = "/visualizza-ordiniC")
public class VisualizzaOrdiniCliente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idUtenteString = request.getParameter("idCliente");
        Utente u = (Utente) request.getSession().getAttribute("utente");
        List<String> errorPar = new ArrayList<>();
        String address="/WEB-INF/admin/visualizzaOrdini.jsp";

        if (u!=null){
            if (u.isAdmin()){
                if (idUtenteString!=null){
                    int idUtente = Integer.parseInt(idUtenteString);
                    OrdineDAO service = new OrdineDAO();

                    List<Ordine>ordiniById = service.doRetrieveByUtenteId(idUtente);

                    request.setAttribute("ordini", ordiniById);
                }else{
                    errorPar.add("idUtente");
                    request.setAttribute("erroriParametri", errorPar);
                    address = "gestioneUtente.jsp";
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
