package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;

import java.io.IOException;

@WebServlet(name = "IndexServlet", value = "/index.html")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente u = (Utente) request.getSession().getAttribute("utente");
        String address;

        if(u == null || (u != null && !u.isAdmin())){
            address =  "/WEB-INF/index.jsp";
        }else{
            address = "/WEB-INF/admin/HomeAdmin.jsp";
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher(address);

        dispatcher.forward(request, response);







    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
