package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GestioneCategoria", value = "/gestisci-categoria")
public class GestioneCategoria extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u = (Utente) session.getAttribute("utente");
            if(u != null){
                if(u.isAdmin()){
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
                        response.sendError(response.SC_NOT_FOUND);
                    }else{
                        CategoriaDAO service = new CategoriaDAO();
                        Categoria c = service.doRetrieveById(id);
                        if(c!=null) {
                            request.setAttribute("categoria", c);
                            ProdottoDAO serviceProdotto =  new ProdottoDAO();

                            List<Prodotto> prodottiList = serviceProdotto.doRetrieveAll();

                            request.setAttribute("prodotti", prodottiList);

                            String address="/WEB-INF/admin/formModificaCategoria.jsp";
                            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                            dispatcher.forward(request, response);
                        }else{
                            response.sendError(HttpServletResponse.SC_NOT_FOUND);
                        }
                    }
                }else{
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }else{
                response.sendRedirect("login-page");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
