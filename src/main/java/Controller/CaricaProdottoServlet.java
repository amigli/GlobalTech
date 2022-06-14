package Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CaricaProdottoServlet", value = "/carica-prodotto")
public class CaricaProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String marca =  request.getParameter("marca");
        String colore =  request.getParameter("colore");
        double prezzo;
        try {
            prezzo = (double) Double.parseDouble(request.getParameter("prezzo"));
        }catch (NumberFormatException e){
            prezzo =  -1;
        }

        String descrizione = request.getParameter("descrizione");

        boolean batteria ;

        if(request.getParameter("batteria").equals("true")){
            batteria = true;
        }else {
            batteria = false;
        }

        String ram_tipo =  request.getParameter("ram_tipo");
        int ram_quantita;

        try{
            ram_quantita = Integer.parseInt(request.getParameter("ram_quantita"));
        }catch (NumberFormatException e){
            ram_quantita =  -1;
        }

        String cpu =  request.getParameter("cpu_nome");
        int disponibilita;

        try{
            disponibilita =  Integer.parseInt(request.getParameter("disponibilita"));
        }catch (NumberFormatException e){
            disponibilita =  -1;
        }

        //Validazione dei paramentri
        ArrayList<String> errorPar =  new ArrayList<>();

        if(disponibilita < 1){
            errorPar.add("disponibilita");
        }








    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
