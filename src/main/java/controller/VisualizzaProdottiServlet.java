package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "VisualizzaProdottiServlet", value = "/visualizza-prodotti")
public class VisualizzaProdottiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession(false);

        if(session != null && session.getAttribute("utente") != null){
             Utente u = (Utente) session.getAttribute("utente");

             if(u.isAdmin()){
                    ProdottoDAO service =  new ProdottoDAO();

                    List<Prodotto> list =  service.doRetrieveAll();

                    request.setAttribute("prodotti", list);
                    RequestDispatcher dispatcher =
                            request.getRequestDispatcher("/WEB-INF/admin/visualizzaProdotti.jsp");
                    dispatcher.forward(request, response);
                }else{
                    response.sendError(401);
                }
            }else{
                response.sendRedirect("login-page");
            }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
