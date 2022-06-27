package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Foto;
import model.FotoDAO;
import model.Utente;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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

        if(u != null ){
            if(u.isAdmin()){
                int id = Integer.parseInt(request.getParameter("id"));

                String[] tmp = {"jpg", "jpeg", "png", "gif", "bmp"};
                List<String> extensions = Arrays.asList(tmp);
                List<Part> parts =  (List<Part>) request.getParts();
                List<Part> foto = parts.stream().filter(p -> p.getName().equals("foto")).collect(Collectors.toList());

                boolean error = false;

                for(Part f : foto) {
                    String fileName = f.getSubmittedFileName();
                    String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

                    if (!extensions.stream().anyMatch(e -> e.equalsIgnoreCase(extension))){
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

                    String extendendPath =  path + File.separator + id;
                    File fileExtendendPath =  new File(extendendPath);

                    if(!fileExtendendPath.exists())
                        fileExtendendPath.mkdir();
                    Foto photos = new Foto();
                    int i = 1;
                    for (Part p : foto) {
                        String fileName = p.getSubmittedFileName();
                        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

                        String newFileName =  i + "." + extension;

                        p.write(extendendPath + File.separator + newFileName);
                        photos.addFoto(id + "/" + newFileName);
                        i++;
                    }

                    FotoDAO serviceFoto =  new FotoDAO();
                    serviceFoto.doSave(photos);
                    address = "/WEB-INF/result/caricamentoCompletato.jsp";
                }else{
                    address = "/error.jsp";
                }


                RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                dispatcher.forward(request, response);
            }else{
                response.sendError(401);
            }
        }response.sendRedirect("login-page");




    }
}
