package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

       return id + "/" + numero + "." + estensione;
    }
}