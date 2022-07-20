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

@WebServlet(name = "ModificaPassword", value = "/modifica-password")
public class ModificaPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u = (Utente) session.getAttribute("utente");
            UtenteDAO service = new UtenteDAO();
            String address ="/WEB-INF/result/modificaPassword.jsp";

            String oldP = request.getParameter("oldPassword");

            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA-1");
                digest.reset();
                digest.update(oldP.getBytes(StandardCharsets.UTF_8));
                oldP = String.format("%040x", new BigInteger(1, digest.digest()));

                if(oldP.equals(u.getPassword())){
                    String newP1 = request.getParameter("newPassword1");
                    String newP2 = request.getParameter("newPassword2");

                    if (newP1.equals(newP2)){
                        u.setPassword(newP1);
                        service.doSavePassword(u.getPassword(), u.getId());

                        session.setAttribute("utente", u);

                        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                        dispatcher.forward(request, response);
                    }
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
