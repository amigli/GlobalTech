package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ModificaUtente", value = "/modifica-utente")
public class ModificaUtente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente u = (Utente) request.getSession().getAttribute("utente");

        if (u!=null){
            if (u.isAdmin()){
                ArrayList<String> errorPar =  new ArrayList<>();
                UtenteDAO service = new UtenteDAO();

                String address = "/WEB-INF/result/modificaUtenteResult.jsp";

                String rimuoviAdminButton = request.getParameter("rimuoviAdmin");
                String aggiungiAdminButton = request.getParameter("aggiungiAdmin");
                String idString = request.getParameter("id");

                if (idString==null)
                    errorPar.add("id");

                if (errorPar.isEmpty()){
                    int id = Integer.parseInt(idString);

                    if (rimuoviAdminButton!=null){
                        service.setAdmin(0,id);
                    }
                    if(aggiungiAdminButton!=null){
                        service.setAdmin(1,id);
                    }

                }else{
                    request.setAttribute("error_parameter", errorPar);
                    //aggiungere un messaggio al form in gestioneUtente.jsp in questo caso
                    address = "gestioneUtente.jsp";
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
