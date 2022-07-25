package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;

import java.io.IOException;

@WebServlet(name = "ConfermaPagamentoPageServlet", value = "/conferma-pagamento-page")
public class ConfermaPagamentoPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u =  (Utente) session.getAttribute("utente");

            if(u != null){
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/utente/confermaDatiPagamento.jsp");
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
