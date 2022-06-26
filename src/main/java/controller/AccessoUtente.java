package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AccessoUtenteServlet", value = "/login")
public class AccessoUtente extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
