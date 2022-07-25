package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Offerta;
import model.OffertaDAO;
import model.Utente;

import java.time.LocalDate;
import java.io.IOException;
import java.time.chrono.ChronoLocalDate;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "VisualizzaOfferteServlet", value = "/visualizza-offerte")
public class VisualizzaOfferteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession();

        synchronized (session){
            Utente u = (Utente) session.getAttribute("utente");

            if(u != null){
                if(u.isAdmin()){
                    OffertaDAO service =  new OffertaDAO();
                    GregorianCalendar lastUpdateOffers =
                            (GregorianCalendar) this.getServletContext().getAttribute("lastUpdateOffers");

                    if(lastUpdateOffers.before(new GregorianCalendar())){
                        List<Offerta> offerteAttive =  service.doRetrieveActive();
                        this.getServletContext().setAttribute("offerte", offerteAttive);
                        this.getServletContext().setAttribute("lastUpdateOffers", new GregorianCalendar());
                    }

                    List<Offerta> offerte = service.doRetrieveAll();

                    request.setAttribute("offerteAll", offerte);

                    RequestDispatcher dispatcher =
                            request.getRequestDispatcher("/WEB-INF/admin/visualizzaOfferte.jsp");

                    dispatcher.forward(request, response);


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
