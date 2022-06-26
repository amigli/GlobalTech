package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    public List<Categoria> doRetrieveAll(){
        try (Connection con = ConPool.getConnection()) {
            List <Categoria> l = new ArrayList<>();
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
            con.close();
            return l;
        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    public Categoria doRetrieveById(int id){
        try (Connection con = ConPool.getConnection()) {
            Categoria c = new Categoria();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM categoria WHERE id= ?");

            stmt.setInt(1, id);
            ResultSet rs  = stmt.executeQuery();
            if (rs.next()){

                int ID= rs.getInt(1);
                String nome = rs.getString(2);
                String descrizione = rs.getString(3);

                c.setId(ID);
                c.setNome(nome);
                c.setDescrizione(descrizione);

            }
            con.close();

            return c;
        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    public int doSaveCategoria(Categoria c){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement stmt =
                    con.prepareStatement("INSERT INTO categoria(nome, descrizione) VALUES(?,?)",
                            Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getDescrizione());

            int result =  stmt.executeUpdate();

            if(result  > 0){
                ResultSet res = stmt.getGeneratedKeys();

                res.next();
                int id =  res.getInt(1);

                con.close();

                return id;
            }else{
                con.close();
                throw new RuntimeException("Result < 0");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void doRemoveCategoria(int id){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement stmt =
                    con.prepareStatement("DELETE FROM categoria WHERE id= ?");

            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void doUpdateCategoria(Categoria c){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement stmt =
                    con.prepareStatement("UPDATE categoria SET nome = ?, descrizione = ? WHERE id = ?");

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getDescrizione());
            stmt.setInt(3, c.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


}
