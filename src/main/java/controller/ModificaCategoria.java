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

@WebServlet(name = "ModificaCategoriaServlet", value = "/modifica-categoria")
public class ModificaCategoria extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> errorPar =  new ArrayList<>();
        CategoriaDAO service = new CategoriaDAO();
        String  address = "/WEB-INF/result/modificaCategoriaResult.jsp";

        String nome = request.getParameter("nomeCategoria");
        String descrizione = request.getParameter("descrizioneCategoria");
        int id ;

        if(request.getParameter("id") != null){
            try{
                id =  Integer.parseInt(request.getParameter("id"));
            }catch (NumberFormatException e){
                id = -1;
            }
        }else{
            id = -1;
        }

        if(id < 0)
            response.setStatus(404);
        else {
            if (nome == null || nome.length() < 3)
                errorPar.add("nome");

            if (descrizione == null || descrizione.length() < 3)
                errorPar.add("descrizione");

            if (errorPar.isEmpty()) {
                Categoria c = new Categoria();

                c.setNome(nome);
                c.setDescrizione(descrizione);
                c.setId(id);
                service.doUpdateCategoria(c);
            } else {
                request.setAttribute("error_parameter", errorPar);
                //aggiungere un messaggio al formInserimentoCategoria in questo caso
                address = "formInserimentoCategoria.jsp";
            }
        }
        RequestDispatcher dispatcher =  request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
