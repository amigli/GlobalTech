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

@WebServlet(name = "ModificaInterCategoriaServlet", value = "/modificainter-categoria")
public class ModificaInterCategoria extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> errorPar =  new ArrayList<>();
        CategoriaDAO service = new CategoriaDAO();
        String address=null;

        int Id = Integer.parseInt(request.getParameter("id"));

        if (Id!=0){
            Categoria c = service.doRetrieveById(Id);

            if(c!=null) {
                request.setAttribute("idCategoria", c.getId());
                request.setAttribute("nomeCategoria", c.getNome());
                request.setAttribute("descrizioneCategoria", c.getDescrizione());
                address="formModificaCategoria.jsp";
            }

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
