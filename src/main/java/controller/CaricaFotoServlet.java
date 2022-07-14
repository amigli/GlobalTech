package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet(name = "CaricaFotoServlet", value = "/carica-foto")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class CaricaFotoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession();
        Utente u = ((Utente) session.getAttribute("utente"));

        synchronized (session){
            if(u != null ){
                if(u.isAdmin()){
                    String idString = request.getParameter("id");

                    if(idString != null){
                        try{
                            int id =  Integer.parseInt(idString);
                            ProdottoDAO serviceProdotto =  new ProdottoDAO();

                            Prodotto prodotto =  serviceProdotto.doRetrieveById(id);

                            if(prodotto != null){
                                //per controlli sui i tipi dei file caricati
                                String[] tmp = {"jpg", "jpeg", "png", "gif", "bmp"};
                                List<String> extensions = Arrays.asList(tmp);


                                List<Part> parts =  (List<Part>) request.getParts();
                                List<Part> foto = parts.stream().filter(p -> p.getName().equals("foto")).collect(Collectors.toList());

                                boolean error = false;

                                for(Part f : foto) {
                                    String fileName = f.getSubmittedFileName();
                                    String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

                                    if (extensions.stream().noneMatch(e -> e.equalsIgnoreCase(extension))){
                                        error = true;
                                        break;
                                    }
                                }

                                String address;

                                if(!error) {
                                    String path = request.getServletContext().getRealPath("") + File.separator + "image";
                                    File filePath =  new File(path);
                                    if(!filePath.exists())
                                        filePath.mkdir();

                                    String extendendPath =  path +File.separator+ id;
                                    File fileExtendendPath =  new File(extendendPath);

                                    if(!fileExtendendPath.exists())
                                        fileExtendendPath.mkdir();

                                    ArrayList<Foto> photos = new ArrayList<>();

                                    FotoDAO serviceFoto =  new FotoDAO();
                                    Optional<Integer> maxNumber = prodotto.getImmagini().stream().map(Foto::getNumeroId).max(Integer::compareTo);

                                    int i = maxNumber.isPresent() ? maxNumber.get() : 1;

                                    for (Part p : foto) {
                                        String fileName = p.getSubmittedFileName();
                                        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

                                        String newFileName =  i + "." + extension;

                                        p.write(extendendPath + File.separator + newFileName);


                                        Foto f  = new Foto();

                                        f.setNumeroId(i);
                                        f.setEstensione(extension);
                                        f.setProdottoId(id);

                                        photos.add(f);

                                        i++;
                                    }

                                    FotoDAO service =  new FotoDAO();

                                    for(Foto f : photos){
                                        service.doSave(f);
                                    }

                                    request.setAttribute("immagini", photos);

                                    address = "/WEB-INF/result/caricamentoFotoCompletato.jsp";


                                }else{

                                    address =  "/WEB-INF/admin/gestioneImmaginiProdotto.jsp";
                                    request.setAttribute("message",
                                            "I file caricati non hanno delle estensioni accettabilie, riprovare");
                                    request.setAttribute("prodotto", prodotto);
                                }


                                RequestDispatcher dispatcher = request.getRequestDispatcher(address);
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
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }else {
                response.sendRedirect("login-page");
            }

        }
    }
}
