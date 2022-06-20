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

@WebServlet(name = "RimuoviCategoriaServlet", value = "/rimuovi-categoria")
public class RimuoviCategoria extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        ArrayList<String> errorPar =  new ArrayList<>();
        CategoriaDAO service = new CategoriaDAO();
        String address=null;


        if (id!=-1){
            Categoria c = new Categoria();
            c.setId(id);

            service.rimuoviCategoria(c);

            address="formCategoria.jsp";
        }else{
            errorPar.add("id");
            request.setAttribute("error_parameter", errorPar);
            //aggiungere un messaggio al formCategoria in questo caso
            address = "formCategoria.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }
}
