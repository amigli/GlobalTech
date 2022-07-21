package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.GregorianCalendar;

@WebServlet(name = "ErroreCarrelloServlet", value = "/errore-carrello")
public class ErroreCarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession();

        synchronized (session){

            session.removeAttribute("catalogo");
            session.removeAttribute("lastUpdateCatalogo");

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/WEB-INF/result/erroreAggiuntaProdottoCarrello.jsp");
            dispatcher.forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
