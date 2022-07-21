package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AccessoUtenteServlet", value = "/login")
public class AccessoUtente extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login-page");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean error =  false;

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if(email == null || !email.matches("^[a-z0-9\\.\\_]+@[a-z]+\\.[a-z]{2,3}$"))
            error =  true;

        if(!error) {
            UtenteDAO service = new UtenteDAO();
            Utente u = service.login(email, password);

            if (u!=null){
                HttpSession session =  request.getSession();
                session.setAttribute("utente", u);

                if(!u.isAdmin()){
                    Carrello cart = (Carrello) session.getAttribute("carrello");
                    CarrelloDAO serviceCarrello =  new CarrelloDAO();
                    if(cart != null && !cart.isEmpty()){
                        //cancello il carrello memorizzato nel database
                        serviceCarrello.doDelete(u.getId());
                        //inserisco nel database un nuovo carrello con i prodotti inseriti più di recente
                        for(ItemCart item : cart.getProdotti()){
                            serviceCarrello.doAggiungiProdotto(u,item);
                        }
                    }else{
                        //nella sessione non ho aggiunto altri prodotti
                        cart = serviceCarrello.doRetrieveByUtente(u);
                        session.setAttribute("carrello", cart);
                    }
                }

                response.sendRedirect("index.html");
            }else{
                error = true;
            }
        }

        if(error){
            request.setAttribute("error_login", error);
            String address = "/WEB-INF/public/loginPage.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }


    }
}
