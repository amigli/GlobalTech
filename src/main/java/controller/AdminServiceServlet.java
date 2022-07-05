package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;

import java.io.IOException;

@WebServlet(name = "AdminServiceServlet", value = "/admin-service")
public class AdminServiceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession(false);
        Utente u =  ((Utente) session.getAttribute("utente"));

        if(u != null){
            if(u.isAdmin()){
                String parameter =  request.getParameter("s");
                String address = "/WEB-INF/admin/";
                switch (parameter){
                    case "caricamento-prodotto":
                        address += "formCaricamentoProdotto.jsp";
                    break;
                    case "caricamento-offerta":
                        address += "formCaricamentoOfferta.jsp";
                    break;
                    case "caricamento-categoria":
                        address += "formInserimentoCategoria.jsp";
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
            doGet(request, response);
    }
}
