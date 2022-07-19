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
               l.add(creaCategoria(rs));
            }
            stmt.close();
            con.close();
            return l;
        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    public Categoria doRetrieveById(int id){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM categoria WHERE id= ?");

            stmt.setInt(1, id);
            ResultSet rs  = stmt.executeQuery();
            Categoria c = null;
            if (rs.next()){
                c = this.creaCategoria(rs);
            }
            stmt.close();
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
                stmt.close();
                con.close();

                return id;
            }else{
                stmt.close();
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
            stmt.close();
            con.close();
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
            stmt.close();
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    public void doRemoveProdottoFromCategoria(Categoria categoria, Prodotto prodotto){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement stmt =  con.prepareStatement(
                    "DELETE FROM appartenere where id_categoria = ? AND id_prodotto = ?");

            stmt.setInt(1, categoria.getId());
            stmt.setInt(2, prodotto.getId());

            stmt.executeUpdate();
            stmt.close();
            con.close();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void doSaveProdottoCategoria(Categoria categoria, Prodotto prodotto){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement stmt =  con.prepareStatement(
                    "INSERT INTO appartenere(id_categoria, id_prodotto) VALUES(?, ?)");

            stmt.setInt(1, categoria.getId());
            stmt.setInt(2, prodotto.getId());

            stmt.executeUpdate();

            stmt.close();
            con.close();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    private  Categoria creaCategoria(ResultSet res) throws SQLException{
        int ID= res.getInt(1);
        String nome = res.getString(2);
        String descrizione = res.getString(3);

        Categoria c = new Categoria();
        c.setId(ID);
        c.setNome(nome);
        c.setDescrizione(descrizione);

        ProdottoDAO service =  new ProdottoDAO();

        ArrayList<Prodotto> prodotti = new ArrayList<>(service.doRetrieveByCategoria(c));


        c.setProdotti(prodotti);

        return  c;
    }

}
