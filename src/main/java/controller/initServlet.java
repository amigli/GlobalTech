package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Categoria;
import model.CategoriaDAO;
import model.Offerta;
import model.OffertaDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "initServlet", value = "/initServlet", loadOnStartup = 0)
public class initServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        CategoriaDAO serviceCategorie = new CategoriaDAO();
        List<Categoria> categoriaList = serviceCategorie.doRetrieveAll();
        OffertaDAO serviceOfferta = new OffertaDAO();

        List<Offerta> offertaList =  serviceOfferta.doRetrieveActive();

        ServletContext context =  this.getServletContext();
        context.setAttribute("offerte", offertaList);
        context.setAttribute("categorie", categoriaList);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
