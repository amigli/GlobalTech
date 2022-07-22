package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;

import java.io.IOException;
import java.util.Random;


@WebServlet(name = "ConfermaOrdinePageServlet", value = "/conferma-ordine-page")
public class ConfermaOrdinePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u =   (Utente) session.getAttribute("utente");
            if(u != null){
                if((u.getNumCivico() != 0) && (u.getCap()!= 0) && (u.getCitta() != null)
                        && (u.getNumTelefono() != null) && (u.getVia() != null)
                        && (u.getCognome() != null) && (u.getNome() != null)){
                    if((u.getNumeroCarta() != null) && (u.getDataScadenzaCarta() != null)
                            && (u.getCvvCarta() != 0)){
                        Random generator =  new Random();
                        int checkCode =  generator.nextInt();

                        session.setAttribute("checkcode", checkCode);
                        request.setAttribute("checkcode", checkCode);


                        RequestDispatcher dispatcher =  request.getRequestDispatcher("/WEB-INF/utente/confermaOrdine.jsp");
                        dispatcher.forward(request, response);
                    }else{
                        response.sendRedirect("conferma-pagamento");
                    }
                }else{
                    response.sendRedirect("completa-ordine");
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
