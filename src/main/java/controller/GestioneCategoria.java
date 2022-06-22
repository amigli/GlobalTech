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
public class GestioneCategoria extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> errorPar =  new ArrayList<>();
        CategoriaDAO service = new CategoriaDAO();
        String address=null;

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
            errorPar.add("id");
            request.setAttribute("error_parameter", errorPar);
            //aggiungere un messaggio al formCategoria in questo caso
            address = "visualizza-categorie";
        }else{
            Categoria c = service.doRetrieveById(id);
            if(c!=null) {
                request.setAttribute("idCategoria", c.getId());
                request.setAttribute("nomeCategoria", c.getNome());
                request.setAttribute("descrizioneCategoria", c.getDescrizione());
                address="formModificaCategoria.jsp";
            }else{
                response.setStatus(404);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
