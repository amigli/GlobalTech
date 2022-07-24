package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProdottiPerCategoria", value = "/prodotti-per-categoria")
public class ProdottiPerCategoria extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String idString = request.getParameter("id");

            if(idString!=null){
                try{
                    int id = Integer.parseInt(idString);

                    CategoriaDAO serviceCat = new CategoriaDAO();
                    Categoria c = serviceCat.doRetrieveById(id);
                    ProdottoDAO service = new ProdottoDAO();
                    String address = "/WEB-INF/public/catalogo.jsp";

                    List<Prodotto> prodottiCategoria = service.doRetrieveByCategoria(c);

                    request.setAttribute("catalogo", prodottiCategoria);

                    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);
                }catch (NumberFormatException e){
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
            }else{
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
