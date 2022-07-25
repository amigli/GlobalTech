package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "AggiungiCategoriaProdottoServlet", value = "/aggiungi-prodotto-categoria")
public class AggiungiCategoriaProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession();

        synchronized (session){
            Utente u = (Utente) session.getAttribute("utente");

            if(u != null){
                if(u.isAdmin()){
                    String idString = request.getParameter("id");
                    String[] idCategorieString =  request.getParameterValues("categoria");

                    try{
                        int id =  Integer.parseInt(idString);
                        ArrayList<Integer> idCategorie =  new ArrayList<>();


                        for(int i = 0; i < idCategorieString.length; i++){
                            int tmp = Integer.parseInt(idCategorieString[i]);
                            idCategorie.add(tmp);
                        }

                        ProdottoDAO service =  new ProdottoDAO();
                        Prodotto prodotto =  service.doRetrieveById(id);

                        if(prodotto != null){

                            List<Categoria> allCategorie = (List<Categoria>) this.getServletContext().getAttribute("categorie");
                            List<Categoria> categorieDaAggiungere =
                                    allCategorie.stream().filter(a->idCategorie.contains(a.getId())).collect(Collectors.toList());


                            if(categorieDaAggiungere.stream().noneMatch(a -> a.contains(prodotto))){
                                CategoriaDAO serviceCategoria =  new CategoriaDAO();
                                for(Categoria c : categorieDaAggiungere){
                                    serviceCategoria.doSaveProdottoCategoria(c, prodotto);
                                }

                                List<Categoria> categoriaList = serviceCategoria.doRetrieveAll();
                                getServletContext().setAttribute("categorie", categoriaList);
                            }else{
                                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                            }
                        }else{
                            response.sendError(HttpServletResponse.SC_NOT_FOUND);
                        }
                    }catch (NumberFormatException e){
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    }
                }else{
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }else{
                response.sendRedirect("login-page");
            }
        }

    }
}
