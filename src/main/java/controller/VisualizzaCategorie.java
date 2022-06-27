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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "visualizzaCategoriaServlet", value = "/visualizza-categorie")
public class VisualizzaCategorie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");

            if(u !=  null ){
                if(u.isAdmin()){
                CategoriaDAO service = new CategoriaDAO();
                String address="/WEB-INF/admin/visualizzaCategorie.jsp";
                List<Categoria> cat = service.doRetrieveAll();

                request.setAttribute("categorie", cat);

                RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                dispatcher.forward(request, response);
            }else{
                response.sendError(401);
            }
        }else{
            response.sendRedirect("login-page");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
