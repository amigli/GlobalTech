package model;

import java.io.File;
import java.sql.*;

public class FotoDAO {

    public Foto doRetrieveById(int id){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement stmt = con.prepareStatement("SELECT * from foto WHERE id_prodotto = ? ");

            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery();
            Foto foto = new Foto();
            while(res.next()){
                foto.addFoto(this.trasformFoto(res));
            }

            return foto;


        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private String trasformFoto(ResultSet res)throws SQLException {
        String numero = res.getString(1);
        String id =  res.getString(2);
        String estensione =  res.getString(3);

       return id + File.separator + numero + "." + estensione;
    }


    public void doSave(Foto f){
        try(Connection con = ConPool.getConnection()) {
            for (String s : f.getFoto()) {
                PreparedStatement stmt = con.prepareStatement(
                        "INSERT into foto(numero, id_prodotto, estensione) VALUES(?, ?, ?)");

                String[] tmp = s.split("/");

                int id = Integer.parseInt(tmp[0]);

                tmp = tmp[1].split("\\.");

                int number = Integer.parseInt(tmp[0]);
                String extension = tmp[1];
                stmt.setInt(1, number);
                stmt.setInt(2, id);
                stmt.setString(3, extension);

                stmt.executeUpdate();
            }
        }catch (SQLException e){
           e.printStackTrace();
           throw new RuntimeException();
        }
    }
}