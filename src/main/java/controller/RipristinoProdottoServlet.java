package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;

import java.io.IOException;

@WebServlet(name = "RipristinoProdottoServlet", value = "/ripristina-prodotto")
public class RipristinoProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u =  (Utente) session.getAttribute("utente");

        synchronized (session){

            if(u != null){
                if(u.isAdmin()){
                    String idString = request.getParameter("id_prod");
                    String disponibilitaString = request.getParameter("disponibilita");
                    if(idString != null){
                        try{
                            int id = Integer.parseInt(idString);
                            int disponibilita = Integer.parseInt(disponibilitaString);

                            ProdottoDAO service = new ProdottoDAO();
                            Prodotto prod = service.doRetrieveById(id);

                            if(prod != null){
                                prod.setDisponibilita(disponibilita);
                                service.doUpdate(prod);


                                RequestDispatcher dispatcher =
                                        request.getRequestDispatcher("/WEB-INF/result/ripristinoProdotto.jsp");

                                dispatcher.forward(request, response);
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
