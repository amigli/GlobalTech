package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Ordine;
import model.OrdineDAO;
import model.Utente;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "VisualizzaDettaglioOrdineServlet", value = "/dettaglio-ordine")
public class VisualizzaDettaglioOrdineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       HttpSession session = request.getSession();

       synchronized (session){
           Utente u = (Utente) session.getAttribute("utente");

           if(u != null){
               String idOrdineString = request.getParameter("id");

               try{
                   int idOrdine = Integer.parseInt(idOrdineString);

                   OrdineDAO service = new OrdineDAO();

                   if(u.isAdmin()){
                      Ordine o = service.doRetrieveById(idOrdine);
                      request.setAttribute("ordine", o);

                       RequestDispatcher dispatcher =
                               request.getRequestDispatcher("/WEB-INF/admin/dettagliOrdine.jsp");

                       dispatcher.forward(request, response);
                   }else {
                       List<Ordine> all = service.doRetrieveByUtenteId(u.getId());

                       if (!all.isEmpty() && all.stream().anyMatch(o -> o.getId() == idOrdine)) {
                           List<Ordine> filterList =
                                   all.stream().filter(o -> o.getId() == idOrdine).collect(Collectors.toList());

                           Ordine o = filterList.get(0);

                           request.setAttribute("ordine", o);

                           RequestDispatcher dispatcher =
                                   request.getRequestDispatcher("/WEB-INF/admin/dettagliOrdine.jsp");

                           dispatcher.forward(request, response);
                       } else {
                           response.sendError(HttpServletResponse.SC_NOT_FOUND);
                       }
                   }
               }catch (NumberFormatException e){
                   response.sendError(HttpServletResponse.SC_BAD_REQUEST);
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
