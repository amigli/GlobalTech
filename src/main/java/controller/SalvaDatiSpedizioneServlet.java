package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ConfermaDatiSpedizioneServlet", value = "/salva-dati-spedizione")
public class SalvaDatiSpedizioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u =   (Utente) session.getAttribute("utente");
            if(u != null){
                String modifica = request.getParameter("modifica");

                if(modifica == null){
                    String nome =  request.getParameter("nome");
                    String cognome =  request.getParameter("cognome");
                    String via =  request.getParameter("via");
                    String numeroCivicoString = request.getParameter("civico");
                    String citta =  request.getParameter("citta");
                    String capString =  request.getParameter("cap");
                    String telefono =  request.getParameter("telefono");

                    ArrayList<String> errorPar =  new ArrayList<>();
                    int numeroCivico = 0, cap = 0;

                    try{
                        numeroCivico =  Integer.parseInt(numeroCivicoString);
                    }catch (NumberFormatException e){
                        errorPar.add("numero civico");
                    }

                    try{
                        cap = Integer.parseInt(capString);
                    }catch (NumberFormatException e){
                        errorPar.add("cap");
                    }

                    if(nome == null || nome.length() < 2 || !nome.matches("^[A-Za-z\\s]{3,40}$")){
                        errorPar.add("nome");
                    }

                    if(cognome == null || cognome.length() < 2 || !cognome.matches("^[A-Za-z\\s]{3,40}$")){
                        errorPar.add("cognome");
                    }

                    if(via == null || via.length() < 2 || !nome.matches("^[A-Za-z0-9\\s]{3,40}$")){
                        errorPar.add("via");
                    }

                    if(citta == null || citta.length() < 2 || !citta.matches("^[A-Za-z\\s]{3,40}$")){
                        errorPar.add("citta");
                    }

                    if(telefono == null || telefono.length() < 9 || !telefono.matches("^[0-9]{9,10}$")){
                        errorPar.add("telefono");
                    }

                    String address;

                    if(errorPar.isEmpty()){
                        u.setNome(nome);
                        u.setCognome(cognome);
                        u.setVia(via);
                        u.setCitta(citta);
                        u.setNumTelefono(telefono);
                        u.setNumCivico(numeroCivico);
                        u.setCap(cap);

                        UtenteDAO service = new UtenteDAO();

                        service.aggiornaUtente(u);


                        session.setAttribute("utente", u);
                        response.sendRedirect("conferma-pagamento-page");
                    }else{
                        request.setAttribute("error-parameter", errorPar);
                        RequestDispatcher dispatcher =
                                request.getRequestDispatcher("/WEB-INF/public/salvaDatiSpedizione.jsp");

                        dispatcher.forward(request, response);
                    }

                }else{
                    response.sendRedirect("conferma-pagamento-page");
                }
            }else{
                response.sendRedirect("login-page");
            }
        }
    }
}
