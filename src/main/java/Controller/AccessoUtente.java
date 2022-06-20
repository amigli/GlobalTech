package controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utente;
import model.UtenteDAO;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "AccessoUtenteServlet", value = "/accesso-utente")
public class AccessoUtente extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UtenteDAO service = new UtenteDAO();
        String address;
        ArrayList<String> errorPar =  new ArrayList<>();

        if (!email.matches("^[a-z0-9/.]+@[a-z]+/.[a-z]{2,3}$"))
            errorPar.add("email");

        if (password.length()<8 || (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[/*.!@$%^&()[/]{}:;<>,.?+-_=]).{8,32}")))
            errorPar.add("password");

        Utente u=service.login(email, password);
        if (u!=null){
            if (u.isAdmin())
                address="#"; //pagina che gestisce l'admin (ancora da fare)
            else
                address="#"; //home del sito
        }
        else{
            request.setAttribute("error_parameter", errorPar);
            //aggiungere un messaggio al loginForm in questo caso
            address = "/loginPage.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
