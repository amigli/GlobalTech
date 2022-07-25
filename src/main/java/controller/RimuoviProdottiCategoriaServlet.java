package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "RimuoviProdottiCategoriaServlet", value = "/rimuovi-categoria-prodotto")
public class RimuoviProdottiCategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u =  (Utente) session.getAttribute("utente");

            if(u != null){
                if(u.isAdmin()){
                    String idCategoriaString  =  request.getParameter("id");
                    String[] prodottiStringId = request.getParameterValues("prodotto");
                    if(idCategoriaString != null){
                        try{
                            int id =  Integer.parseInt(idCategoriaString);
                            ArrayList<Integer> idProdottiDaRimuovere = new ArrayList<>();

                            for(String s: prodottiStringId){
                                idProdottiDaRimuovere.add(Integer.parseInt(s));
                            }
                            CategoriaDAO categoriaDAO = new CategoriaDAO();
                            Categoria categoria =  categoriaDAO.doRetrieveById(id);

                            if(categoria != null){
                                ProdottoDAO serviceProdotto =  new ProdottoDAO();
                                List<Prodotto> prodottiAll =  serviceProdotto.doRetrieveAll();
                                List<Prodotto> prodottiDaRimuovere  =
                                        prodottiAll.stream().filter(o->idProdottiDaRimuovere.contains(o.getId()))
                                                .collect(Collectors.toList());


                                if(prodottiDaRimuovere.stream().allMatch(p->categoria.getProdotti().contains(p))){
                                    for(Prodotto prodotto : prodottiDaRimuovere){
                                        categoriaDAO.doRemoveProdottoFromCategoria(categoria, prodotto);
                                    }

                                    List<Categoria> allCategorie = categoriaDAO.doRetrieveAll();
                                    getServletContext().setAttribute("categorie", allCategorie);
                                }else{
                                    response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
                                }
                            }else{
                                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                            }

                        }catch (NumberFormatException e){
                            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                        }
                    }else{
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
