package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Carrello;
import model.Utente;

import java.io.IOException;

@WebServlet(name = "CompletaOrdineServlet", value = "/completa-ordine")
public class CompletaOrdineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session  = request.getSession();

        synchronized (session){
            Utente u = (Utente) session.getAttribute("utente");

            if(u != null){
                Carrello cart =  (Carrello) session.getAttribute("carrello");

                if(cart != null && !cart.isEmpty()){
                    RequestDispatcher dispatcher =
                            request.getRequestDispatcher("/WEB-INF/public/completaOrdine.jsp");
                    dispatcher.forward(request, response);
                }else{
                    response.sendRedirect("visualizza-carrello");
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
