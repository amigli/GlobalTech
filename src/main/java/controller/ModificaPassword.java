package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "ModificaPassword", value = "/modifica-password")
public class ModificaPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u = (Utente) session.getAttribute("utente");

            if(u != null){

                UtenteDAO service = new UtenteDAO();
                String address ="/WEB-INF/result/modificaPasswordResult.jsp";
                String oldP = request.getParameter("old-password");
                Utente tmp = new Utente();

                tmp.setPassword(oldP);

                    if(tmp.getPassword().equals(u.getPassword())){
                        String newP1 = request.getParameter("new-password-1");
                        String newP2 = request.getParameter("new-password-2");

                        if (newP1.equals(newP2)){
                            Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!_;,:+.-]).{8,20}$");
                            Matcher matcher = pattern.matcher(newP1);
                            if (newP1 != null || matcher.matches()){
                                u.setPassword(newP1);
                                service.doSavePassword(newP1, u.getId());

                                session.setAttribute("utente", u);
                            }else{
                                address ="/WEB-INF/utente/modificaPassword.jsp";
                                request.setAttribute("message", "Le password non rispetta il formato richiesto.");
                            }
                        }else{
                            address ="/WEB-INF/utente/modificaPassword.jsp";
                            request.setAttribute("message", "Le password inserite non sono uguali.");
                        }
                    }else{
                        address ="/WEB-INF/utente/modificaPassword.jsp";
                        request.setAttribute("message", "La vecchia password non corrisponde.");
                    }

                    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);
            }else{
                response.sendRedirect("login-page");
            }

        }

    }
}
