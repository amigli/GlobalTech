package controller;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@WebServlet(name = "FiltraProdottiServlet", value = "/filtra-prodotti")
public class FiltraProdottiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String marca = request.getParameter("marca");
        String ricerca = request.getParameter("ricerca");
        if(marca != null){
            String  idCategoriaString =  request.getParameter("categoria");

            if(idCategoriaString != null){
                int idCategoria = Integer.parseInt(idCategoriaString);

                List<Prodotto> prodottiList =  null;
                ProdottoDAO serviceProdotto =  new ProdottoDAO();
                if(idCategoria != -1){
                    CategoriaDAO serviceCategoria = new CategoriaDAO();
                    Categoria categoria = serviceCategoria.doRetrieveById(idCategoria);

                    if(categoria != null){
                        prodottiList =  serviceProdotto.doRetrieveByCategoria(categoria);
                    }else{
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                }else{
                    prodottiList = serviceProdotto.doRetrieveAll();
                }

                if(!marca.equals("-1")){
                    prodottiList =
                            prodottiList.stream().filter(p->p.getMarca().equals(marca)).collect(Collectors.toList());
                }

                if(ricerca != null){
                    prodottiList =
                            prodottiList.stream().filter(p->p.getNome().toLowerCase().contains(ricerca.toLowerCase()) || p.getMarca().toLowerCase().contains(ricerca.toLowerCase())).collect(Collectors.toList());
                }

                List<Item> catalogo =  new ArrayList<>();
                List<Offerta> offerte = (List<Offerta>) getServletContext().getAttribute("offerte");

                for(Prodotto p : prodottiList){
                    Item item = new Item();

                    item.setProdotto(p);

                    float prezzo =  p.getPrezzoListino();

                    if(offerte.stream().anyMatch(o->o.contains(p))){
                        List<Offerta> tmp =
                                offerte.stream().filter(o->o.contains(p)).collect(Collectors.toList());

                        for(Offerta offerta: tmp){
                            prezzo -= prezzo * offerta.getPercentuale()/100;
                        }
                    }
                    item.setPrezzo(prezzo);

                    catalogo.add(item);
                }


                Gson parser = new Gson();

                String json = parser.toJson(catalogo);

                response.getWriter().write(json);

            }else{
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parameter categoria not found");
            }

        }else{
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parameter marca not found");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
