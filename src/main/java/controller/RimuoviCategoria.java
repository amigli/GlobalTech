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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession();

        synchronized (session){
            Utente u = (Utente) session.getAttribute("utente");

            if(u != null ){
                if( u.isAdmin()){
                    String idstring =  request.getParameter("id");
                    if(idstring != null){
                        try{
                            int id = Integer.parseInt(idstring);

                            CategoriaDAO service = new CategoriaDAO();

                            Categoria cat = service.doRetrieveById(id);

                            if(cat != null){
                                service.doRemoveCategoria(id);
                                request.setAttribute("deleted_cat", cat);

                                String address = "/WEB-INF/result/removeCategoriaResult.jsp";
                                RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                                dispatcher.forward(request, response);
                            }else{
                                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                            }
                        }catch (NumberFormatException e){
                            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        }
                    }else{
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    }
                }else{
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }else{
                response.sendRedirect("login-page");
            }


        }


    }
}
