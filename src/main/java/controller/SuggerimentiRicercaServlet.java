package controller;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Prodotto;
import model.ProdottoDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SuggerimentiRicercaServlet", value = "/search-suggest")
public class SuggerimentiRicercaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchKey =  request.getParameter("key");

        ProdottoDAO service =  new ProdottoDAO();

        List<Prodotto> listAll =  service.doRetrieveByNameOrMarca(searchKey);

        List<Prodotto> list = listAll.subList(0, Math.min(listAll.size(), 5));

        Gson converter = new Gson();
        String json =  converter.toJson(list);

        response.getWriter().write(json);
    }
}
