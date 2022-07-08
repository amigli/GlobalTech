package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Categoria;
import model.CategoriaDAO;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "visualizzaUtentiServlet", value = "/visualizza-utenti")
public class VisualizzaUtenti extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente u = (Utente) request.getSession().getAttribute("utente");

        if(u!=null){
            if(u.isAdmin()){
                UtenteDAO service = new UtenteDAO();
                String address="/WEB-INF/admin/visualizzaUtenti.jsp";
                List <Utente> ut = service.doRetrieveAll();

                request.setAttribute("utenti", ut);

                RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                dispatcher.forward(request, response);
            }else{
                response.sendError(401);
            }
        }else{
            response.sendRedirect("login-page");
        }
    }
}
