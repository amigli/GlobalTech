package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Categoria;
import model.CategoriaDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "visualizzaCategoriaServlet", value = "/visualizza-categorie")
public class VisualizzaCategorie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> errorPar =  new ArrayList<>();
        CategoriaDAO service = new CategoriaDAO();
        String address=null;

        List<Categoria> cat = service.doRetrieveAll();

        if (cat.isEmpty()){
            errorPar.add("categorie vuote");
            address="#"; //rimanda alla pagina admin
        }
        else{
            address = "formCategoria.jsp";
            request.setAttribute("categorie", cat);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }
}
