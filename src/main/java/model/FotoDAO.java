package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FotoDAO {

    public List<Foto> doRetrieveByProdotto(int idProdotto){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement stmt = con.prepareStatement("SELECT * from foto WHERE id_prodotto = ? ");

            stmt.setInt(1, idProdotto);

            ResultSet res = stmt.executeQuery();
            List<Foto> fotoList = new ArrayList<>();
            while(res.next()){
                fotoList.add(this.creaFoto(res));
            }

            con.close();
            return fotoList;
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public List<Foto> doRetrieveByProdotto(Prodotto p){
        return  doRetrieveByProdotto(p.getId());
    }

    private Foto creaFoto(ResultSet res)throws SQLException {
        Foto foto = new Foto();

        int numero = res.getInt(1);
        int idProdotto =  res.getInt(2);
        String estensione =  res.getString(3);

        foto.setNumeroId(numero);
        foto.setProdottoId(idProdotto);
        foto.setEstensione(estensione);

        return foto;
    }


    public void doSave(Foto f){
        try(Connection con = ConPool.getConnection()){
                PreparedStatement stmt = con.prepareStatement(
                        "INSERT into foto(numero, id_prodotto, estensione) VALUES(?, ?, ?)");

                stmt.setInt(1, f.getNumeroId());
                stmt.setInt(2, f.getProdottoId());
                stmt.setString(3, f.getEstensione());

                stmt.executeUpdate();

                con.close();
        }catch (SQLException e){
           throw new RuntimeException();
        }
    }
}