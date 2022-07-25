package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Offerta;
import model.OffertaDAO;
import model.Utente;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@WebServlet(name = "ModificaOffertaServlet", value = "/modifica-offerta")
public class ModificaOffertaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession();

        synchronized (session){
            Utente u =  (Utente) session.getAttribute("utente");

            if(u != null){
                if(u.isAdmin()){
                    String idString = request.getParameter("id_offer");

                    if(idString != null){
                        try{
                            int id =  Integer.parseInt(idString);
                            OffertaDAO service =  new OffertaDAO();

                            Offerta offerta =service.doRetrieveById(id);

                            if(offerta != null){
                                ArrayList<String> errPar =  new ArrayList<>();

                                String nome = request.getParameter("nome");
                                String dataInzioString =  request.getParameter("data-inizio");
                                String dataFineString =  request.getParameter("data-fine");
                                float sconto;
                                try {
                                    sconto = Float.parseFloat(request.getParameter("sconto"));




                                }catch (NumberFormatException e){
                                    sconto = 0;
                                }

                                if(sconto < 0.01)
                                    errPar.add("sconto");

                                LocalDate dataInizio = null,dataFine = null;
                                if(dataInzioString != null && dataFineString != null) {
                                    try {
                                        dataInizio = LocalDate.parse(dataInzioString);
                                    }catch (DateTimeParseException e){
                                        errPar.add("data-inzio");
                                    }

                                    try {
                                        dataFine = LocalDate.parse(dataFineString);
                                    }catch (DateTimeParseException e){
                                        errPar.add("data-fine");
                                    }

                                    if(dataInizio.isAfter(dataFine)){
                                        errPar.add("data-fine");
                                        errPar.add(("data-inizio"));
                                    }
                                }else{
                                    if(dataInzioString == null)
                                        errPar.add("data-inzio");

                                    if(dataFineString == null)
                                        errPar.add("data-fine");
                                }

                                if(nome ==  null){
                                    errPar.add("nome");
                                }

                                String address;

                                if(errPar.isEmpty()){
                                    offerta.setNome(nome);
                                    offerta.setPercentuale(sconto);
                                    offerta.setDataInizio(dataInizio);
                                    offerta.setDataFine(dataFine);

                                    service.doUpdate(offerta);

                                    GregorianCalendar today =  new GregorianCalendar();
                                    ServletContext context = request.getServletContext();
                                    List<Offerta> offertaList = service.doRetrieveActive();
                                    context.setAttribute("offerte", offertaList);
                                    context.setAttribute("lastUpdateOffers", today);

                                    request.setAttribute("offerta", offerta );
                                    request.setAttribute("operation", "modifica");


                                    address = "/WEB-INF/result/caricamentoOffertaCompletato.jsp";

                                }else{
                                    address = "formCaricamentoOfferta.jsp";
                                    request.setAttribute("error_parameter", errPar);
                                }

                                RequestDispatcher dispatcher =  request.getRequestDispatcher(address);

                                dispatcher.forward(request, response);
                            }else{
                                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                            }
                        }catch (NumberFormatException e){
                            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                        }

                    }else{
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    }
                }else{
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
            }else{
                response.sendRedirect("login-page");
            }
        }
    }
}
