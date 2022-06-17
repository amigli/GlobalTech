package Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Prodotto;
import model.ProdottoDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CaricaProdottoServlet", value = "/carica-prodotto")
public class CaricaProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> errorPar =  new ArrayList<>();

        String nome = request.getParameter("nome");

        if(nome == null || nome.length() <= 3)
            errorPar.add("nome");

        String marca =  request.getParameter("marca");

        if(marca == null  || marca.length() < 3)
            errorPar.add("marca");

        String colore =  request.getParameter("colore");

        if(colore == null || colore.length() < 3)
            errorPar.add("colore");

        float prezzo;
        try {
            prezzo = Float.parseFloat(request.getParameter("prezzo"));
        }catch (NumberFormatException e){
            prezzo = -1;
        }

        if(prezzo < 0.01)
            errorPar.add("prezzo");

        String descrizione = request.getParameter("descrizione");

        boolean batteria ;

        if(request.getParameter("batteria").equals("true")){
            batteria = true;
        }else {
            batteria = false;
        }

        String ramTipo =  request.getParameter("ram_tipo");

        if(!ramTipo.matches("^DDR[3-5]$/"))
            errorPar.add("ram_tipo");

        int ramQuantita;

        try{
            ramQuantita = Integer.parseInt(request.getParameter("ram_quantita"));
        }catch (NumberFormatException e){
            ramQuantita =  -1;
        }

        if(ramQuantita < 1)
            errorPar.add("ram_quantita");

        String nomeCpu =  request.getParameter("cpu_nome");
        int disponibilita;

        try{
            disponibilita =  Integer.parseInt(request.getParameter("disponibilita"));
        }catch (NumberFormatException e){
            disponibilita =  -1;
        }

        if(disponibilita < 1)
            errorPar.add("disponibilita");

        String sistemaOperativo = request.getParameter("sistema_operativo");
        String address;
        if(errorPar.isEmpty()){
            Prodotto prod =  new Prodotto();

            prod.setId(ProdottoDAO.doRetrieveNextId());
            prod.setNome(nome);
            prod.setMarca(marca);
            prod.setColore(colore);
            prod.setPrezzoListino(prezzo);
            prod.setDescrizione(descrizione);
            prod.setSistemaOperativo(sistemaOperativo);
            prod.setTipoRam(ramTipo);
            prod.setQuantitaRam(ramQuantita);
            prod.setCpuNome(nomeCpu);
            prod.setBatteria(batteria);
            prod.setDisponibilita(disponibilita);


            address = "Test";
        }else{
            request.setAttribute("error_parameter", errorPar);
            address = "/formCaricamentoProdotto.jsp";
        }

        RequestDispatcher dispatcher =  request.getRequestDispatcher(address);

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
