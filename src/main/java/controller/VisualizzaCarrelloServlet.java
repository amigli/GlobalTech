package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Carrello;

import java.io.IOException;

@WebServlet(name = "VisualizzaCarrelloServlet", value = "/visualizza-carrello")
public class VisualizzaCarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Carrello cart =  (Carrello) session.getAttribute("carrello");
            if(cart == null){
                cart =  new Carrello();
                session.setAttribute("carrello", cart);
            }

            request.setAttribute("carrello", cart);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/public/visualizzaCarrello.jsp");
            dispatcher.forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
