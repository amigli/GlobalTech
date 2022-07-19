package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "RimuoviImmaginiServlet", value = "/rimuovi-immagine")
public class RimuoviImmaginiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u =  (Utente) session.getAttribute("utente");

            if(u != null){
                if(u.isAdmin()){
                    String idProdottoString = request.getParameter("id_prodotto");
                    String idImageString = request.getParameter("id_image");

                    try{
                        int idProdotto =  Integer.parseInt(idProdottoString);
                        int idImage =  Integer.parseInt(idImageString);

                        ProdottoDAO serviceProdotto = new ProdottoDAO();
                        Prodotto prodotto = serviceProdotto.doRetrieveById(idProdotto);

                        if(prodotto != null){
                            if(prodotto.getImmagini().stream().map(Foto::getNumeroId).anyMatch(f->f==idImage)){
                                List<Foto> fotoTmp =
                                        prodotto.getImmagini().stream().filter(f->f.getNumeroId() == idImage).collect(Collectors.toList());

                                Foto foto =  fotoTmp.get(0);
                                FotoDAO serviceFoto =  new FotoDAO();

                                serviceFoto.doRemove(foto);

                                File fotoFile = new File( request.getServletContext().getRealPath("") + "/" + foto.getDirectory());
                                fotoFile.delete();
                            }else{
                                response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
                            }
                        }else{
                            response.sendError(HttpServletResponse.SC_NOT_FOUND);
                        }


                    }catch (NumberFormatException e){

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
