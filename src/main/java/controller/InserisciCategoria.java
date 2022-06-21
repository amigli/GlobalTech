package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Categoria;
import model.CategoriaDAO;
import model.UtenteDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "InserisciCategoriaServlet", value = "/inserisci-categoria")
public class InserisciCategoria extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> errorPar =  new ArrayList<>();
        CategoriaDAO service = new CategoriaDAO();
        String address=null;

        String nome = request.getParameter("nomeCategoria");
        String descrizione = request.getParameter("descrizioneCategoria");

        if (nome==null || nome.length()<3)
            errorPar.add("nome");

        if (descrizione==null || descrizione.length()<3)
            errorPar.add("descrizione");

        if (errorPar.isEmpty()){
            Categoria c = new Categoria();
            c.setNome(nome);
            c.setDescrizione(descrizione);

            service.doSave(c);

            address="formCategoria.jsp";
        }
        else{
            request.setAttribute("error_parameter", errorPar);
            //aggiungere un messaggio al formInserimentoCategoria in questo caso
            address = "formInserimentoCategoria.jsp";
        }
        RequestDispatcher dispatcher =  request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
