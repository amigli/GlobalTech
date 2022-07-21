package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

@WebServlet(name = "ConfermaPagamentoServlet", value = "/conferma-pagamento")
public class ConfermaPagamentoServlet extends HttpServlet {
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
                    ArrayList<String> errorPar =  new ArrayList<>();
                    String numeroCC = request.getParameter("numerocc");
                    String scadenzaMeseString =  request.getParameter("month-scad-cc");
                    String scadenzaAnnoString = request.getParameter("year-scad-cc");
                    String cvvString = request.getParameter("cvv");
                    String saveCheck =  request.getParameter("save");

                    if(numeroCC == null || !numeroCC.matches("^[0-9]{13,16}$"))
                        errorPar.add("Numero Carta");

                    int scadenzaMese = 0, scadenzaAnno = 0;

                    try{
                        scadenzaMese = Integer.parseInt(scadenzaMeseString);

                    }catch (NumberFormatException e){
                        errorPar.add("Mese scadenza carta");
                    }

                    try{
                        scadenzaAnno = Integer.parseInt(scadenzaAnnoString);
                    }catch (NumberFormatException e){
                        errorPar.add("Anno scadenza carta");
                    }
                    int day = scadenzaMese == 2 ? 28 :
                            scadenzaMese == 1 || scadenzaMese == 3 ||
                                    scadenzaMese == 5 || scadenzaMese == 7 ||
                                    scadenzaMese == 8 || scadenzaMese == 10 ||
                                    scadenzaMese == 12 ? 31 : 30;
                    GregorianCalendar today  = new GregorianCalendar();



                    GregorianCalendar scadenza = new GregorianCalendar(scadenzaAnno, scadenzaMese - 1, day);

                    if(scadenza.before(today)){
                        errorPar.add("Scadenza Carta");
                    }

                    int cvv = 0;

                    if(cvvString != null && cvvString.matches("^[0-9]{3}$")){
                        cvv = Integer.parseInt(cvvString);
                    }else{
                        errorPar.add("CVV");
                    }

                    boolean save = Boolean.parseBoolean(saveCheck);

                    if(errorPar.isEmpty()){
                        UtenteDAO service = new UtenteDAO();

                        u.setNumeroCarta(numeroCC);
                        u.setDataScadenzaCarta(scadenza.toZonedDateTime().toLocalDate());
                        u.setCvvCarta(cvv);

                        if(save)
                            service.aggiornaUtente(u);

                        session.setAttribute("utente", u);
                        response.sendRedirect("conferma-ordine-page");
                    }else{
                        request.setAttribute("error-parameter", errorPar);
                        RequestDispatcher dispatcher =
                                request.getRequestDispatcher("/WEB-INF/public/confermaDatiPagamento.jsp");

                        dispatcher.forward(request, response);
                    }
                }else{
                    response.sendRedirect("conferma-ordine-page");
                }
            }else{
                response.sendRedirect("login-page");
            }
        }
    }
}
