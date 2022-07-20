package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;

import java.io.IOException;

@WebServlet(name = "UtenteService", value = "/utente-service")
public class UtenteService extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente u = (Utente) request.getSession().getAttribute("utente");

        if(u!=null){
            if(!u.isAdmin()){
                String p = request.getParameter("s");
                String address = "WEB-INF/utente/";

                switch (p){
                    case "modifica-password":
                        address += "modificaPassword.jsp";
                        break;

                    case "dati-personali":
                        address += "datiPersonali.jsp";
                        break;

                    case "dati-spedizione":
                        address += "datiSpedizione.jsp";
                        break;

                    case "dati-pagamento":
                        address += "datiCarta.jsp";
                        break;

                    default:
                        response.sendError(404);
                        break;
                }
                RequestDispatcher dispatcher =  request.getRequestDispatcher(address);

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
