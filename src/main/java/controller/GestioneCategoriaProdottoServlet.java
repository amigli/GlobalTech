package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;

import java.io.IOException;

@WebServlet(name = "GestioneCategoriaProdottoServlet", value = "/gestione-categoria-prodotto")
public class GestioneCategoriaProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           HttpSession session =  request.getSession();

           synchronized (session){
               Utente u = (Utente) session.getAttribute("utente");

               if( u != null){
                   if(u.isAdmin()){
                       String idString =  request.getParameter("id_prod");
                       try{
                           Integer id =  Integer.parseInt(idString);

                           ProdottoDAO service = new ProdottoDAO();

                           Prodotto prodotto = service.doRetrieveById(id);

                           if(prodotto != null){
                               String address =  "WEB-INF/admin/gestioneCategorieProdotto.jsp";

                               request.setAttribute("prodotto", prodotto);

                               RequestDispatcher dispatcher = request.getRequestDispatcher(address);

                               dispatcher.forward(request, response);
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
