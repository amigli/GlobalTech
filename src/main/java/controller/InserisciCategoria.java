package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Categoria;
import model.CategoriaDAO;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "InserisciCategoriaServlet", value = "/inserisci-categoria")
public class InserisciCategoria extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession();

        synchronized (session){
            Utente u = (Utente) session.getAttribute("utente");

            if(u != null){
                if(u.isAdmin()){
                    ArrayList<String> errorPar =  new ArrayList<>();
                    CategoriaDAO service = new CategoriaDAO();
                    String address;

                    String nome = request.getParameter("nomeCategoria");
                    String descrizione = request.getParameter("descrizioneCategoria");

                    if (nome == null || nome.length()<3 || nome.matches("^[A-Za-z0-9\\s]{3,45}"))
                        errorPar.add("nome");

                    if (descrizione == null)
                        errorPar.add("descrizione");

                    if (errorPar.isEmpty()){
                        Categoria c = new Categoria();
                        c.setNome(nome);
                        c.setDescrizione(descrizione);

                        int id = service.doSaveCategoria(c);

                        c.setId(id);

                        request.setAttribute("categoria_inserita", c);

                        address="/WEB-INF/result/caricamentoCategoriaCompletato.jsp";
                    }
                    else{
                        request.setAttribute("error_parameter", errorPar);
                        address = "/WEB-INF/admin/formInserimentoCategoria.jsp";
                    }
                    RequestDispatcher dispatcher =  request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);
                }else{
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }else{
                response.sendRedirect("login-page");
            }
        }
    }
}
