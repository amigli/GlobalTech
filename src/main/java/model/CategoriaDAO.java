package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    public List<Categoria> doRetrieveAll(){
        List <Categoria> l = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM categoria");
            while (rs.next()){
                int id= rs.getInt(1);
                String nome = rs.getString(2);
                String descrizione = rs.getString(3);

                Categoria c = new Categoria();
                c.setId(id);
                c.setNome(nome);
                c.setDescrizione(descrizione);

                l.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return l;
    }

    public Categoria doRetrieveById(int id){
        Categoria c = new Categoria();
        try (Connection con = ConPool.getConnection()) {
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM categoria WHERE id="+id);
            if (rs.next()){

                int ID= rs.getInt(1);
                String nome = rs.getString(2);
                String descrizione = rs.getString(3);

                c.setId(ID);
                c.setNome(nome);
                c.setDescrizione(descrizione);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    public void doSave (Categoria c){
        try (Connection con = ConPool.getConnection()) {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO categoria(nome, descrizione) " +
                    "VALUES(\"" + c.getNome()+ "\", \""+ c.getDescrizione() + "\")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doRemoveCategoria(Categoria c){
        try (Connection con = ConPool.getConnection()) {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("DELETE FROM categoria WHERE id="+c.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateCategoria(Categoria c){
        try (Connection con = ConPool.getConnection()) {
            Statement stmt=con.createStatement();
            String sql = "UPDATE categoria " +
                    "SET nome= \"" + c.getNome() + "\", descrizione=\"" + c.getDescrizione() +"\"" +
                    "WHERE id= " + c.getId();

            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
