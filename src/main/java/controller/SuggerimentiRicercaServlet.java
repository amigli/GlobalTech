package controller;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Prodotto;
import model.ProdottoDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SuggerimentiRicercaServlet", value = "/SuggerimentiRicercaServlet")
public class SuggerimentiRicercaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchKey =  request.getParameter("key");

        ProdottoDAO service =  new ProdottoDAO();

        List<Prodotto> list =  service.doRetrieveByNameOrMarca(searchKey);

        Gson converter = new Gson();
        String json =  converter.toJson(list);

        response.getWriter().write(json);
    }
}
