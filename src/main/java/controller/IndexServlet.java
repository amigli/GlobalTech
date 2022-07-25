package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;


@WebServlet(name = "IndexServlet", value = "/index.html")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u = (Utente) session.getAttribute("utente");
            String address;

            if(u == null || (u != null && !u.isAdmin())){
                ProdottoDAO serviceProdotto = new ProdottoDAO();

                List<Prodotto> allProdotti = (List<Prodotto>) getServletContext().getAttribute("prodotti");

                if(allProdotti == null) {
                    allProdotti = serviceProdotto.doRetrieveAll();
                    getServletContext().setAttribute("prodotti", allProdotti);
                }


                request.setAttribute("prodotti", allProdotti);

                address =  "/WEB-INF/index.jsp";
            }else{
                address = "/WEB-INF/admin/HomeAdmin.jsp";
            }


            RequestDispatcher dispatcher = request.getRequestDispatcher(address);

            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
