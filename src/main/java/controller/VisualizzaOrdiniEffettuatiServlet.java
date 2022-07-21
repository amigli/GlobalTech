package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Ordine;
import model.OrdineDAO;
import model.Utente;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "VisualizzaOrdiniEffettuatiServlet", value = "/ordini-effettuati")
public class VisualizzaOrdiniEffettuatiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession();

        synchronized (session){
            Utente u = (Utente) session.getAttribute("utente");

            if(u != null){
                OrdineDAO service = new OrdineDAO();
                List<Ordine> ordini = service.doRetrieveByUtenteId(u.getId());

                request.setAttribute("ordini", ordini);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/public/visualizzaOrdiniCliente.jsp");
                dispatcher.forward(request, response);

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
