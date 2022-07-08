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
        String address=null;

        if (u!=null){
            if (u.isAdmin()){
                ArrayList<String> errorPar =  new ArrayList<>();
                UtenteDAO service = new UtenteDAO();

                address = "/WEB-INF/result/modificaUtenteResult.jsp";

                String adminString = request.getParameter("adminUtente");
                String idString = request.getParameter("id");

                if (adminString==null)
                    errorPar.add("admin");

                if (idString==null)
                    errorPar.add("id");

                if (errorPar.isEmpty()){
                    int id = Integer.parseInt(idString);

                    if (adminString.equals("Admin"))
                        service.setAdmin(true, id);
                    else
                        service.setAdmin(false, id);

                }else{
                    request.setAttribute("error_parameter", errorPar);
                    //aggiungere un messaggio al form in gestioneUtente.jsp in questo caso
                    address = "gestioneUtente.jsp";
                }
            }else{
                response.sendError(401);
            }
        }else{
            response.sendRedirect("login-page");
        }
        RequestDispatcher dispatcher =  request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
