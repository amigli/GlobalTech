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

@WebServlet(name = "RimuoviCategoriaServlet", value = "/rimuovi-categoria")
public class RimuoviCategoria extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession();
        Utente u = (Utente) session.getAttribute("Utente");

        if(u != null ){
            if( u.isAdmin()){
                String idstring =  request.getParameter("id");
                String address = "/WEB-INF/result/removeCategoriaResult.jsp";

                if(idstring != null){
                    try{
                        int id = Integer.parseInt(idstring);

                        CategoriaDAO service = new CategoriaDAO();

                        Categoria cat = service.doRetrieveById(id);

                        if(cat != null){
                            service.doRemoveCategoria(id);
                            request.setAttribute("deleted_cat", cat);
                        }
                    }catch (NumberFormatException e){
                        response.setStatus(500);
                    }
                }else{
                    response.setStatus(404);
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                dispatcher.forward(request, response);
            }
        }else{
            response.sendRedirect("login-page");
        }


    }
}
