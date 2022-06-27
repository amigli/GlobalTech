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

@WebServlet(name = "GestioneCategoria", value = "/gestisci-categoria")
public class GestioneCategoria extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");

        if(u != null){
            if(u.isAdmin()){
                int id;

                if(request.getParameter("id") != null){
                    try{
                        id = Integer.parseInt(request.getParameter("id"));
                    }catch (NumberFormatException e){
                        id = -1;
                    }

                }else{
                    id = -1;
                }


                if (id < 1){
                    response.sendError(404);
                }else{
                    CategoriaDAO service = new CategoriaDAO();
                    Categoria c = service.doRetrieveById(id);
                    if(c!=null) {
                        request.setAttribute("idCategoria", c.getId());
                        request.setAttribute("nomeCategoria", c.getNome());
                        request.setAttribute("descrizioneCategoria", c.getDescrizione());
                        String address="formModificaCategoria.jsp";

                        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                        dispatcher.forward(request, response);
                    }else{
                        response.sendError(404);
                    }
                }
            }else{
                response.sendError(401);
            }
        }else{
            response.sendRedirect("login-page");
        }



    }
}
