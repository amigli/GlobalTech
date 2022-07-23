package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarrelloDAO {
    private List<Offerta> offerteAttive;

    public CarrelloDAO() {
        OffertaDAO offertaService = new OffertaDAO();
        offerteAttive = offertaService.doRetrieveActive();
    }

    public Carrello doRetrieveByUtente(int id){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement stmt = con.prepareStatement("SELECT id_prodotto, quantita FROM carrello WHERE id_utente = ? ");

            stmt.setInt(1, id);

            ResultSet res =  stmt.executeQuery();

            ArrayList<ItemCart> prodotti = new ArrayList<>();

            while(res.next()){
                prodotti.add(creaItem(res));
            }

            Carrello cart = new Carrello();

            cart.setProdotti(prodotti);

            stmt.close();
            con.close();

            return cart;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Carrello doRetrieveByUtente(Utente utente){
        return doRetrieveByUtente(utente.getId());
    }

    public void doAggiungiProdotto(Utente u, ItemCart item){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement stmt =
                    con.prepareStatement("INSERT INTO carrello(id_utente, id_prodotto, quantita) VALUES (?, ?, ?)");

            stmt.setInt(1, u.getId());
            stmt.setInt(2, item.getProdotto().getId());
            stmt.setInt(3, item.getQuantita());


            stmt.executeUpdate();
            stmt.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void doUpdateQuantityProduct(Utente u, ItemCart item){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement stmt =
                    con.prepareStatement("UPDATE carrello set quantita = ? WHERE id_utente = ? AND id_prodotto = ? ");


            stmt.setInt(1, item.getQuantita());
            stmt.setInt(2, u.getId());
            stmt.setInt( 3,item.getProdotto().getId());

            if(stmt.executeUpdate() == 0)
                throw new RuntimeException();


            stmt.close();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void doDelete(int idUtente){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement stmt = con.prepareStatement("DELETE FROM carrello WHERE id_utente = ?");

            stmt.setInt(1, idUtente);

            stmt.executeUpdate();

            stmt.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void doDelete(Utente utente){
        doDelete(utente.getId());
    }

    public void doRemoveProdotto(Utente u, Prodotto prodotto){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement stmt =
                    con.prepareStatement("DELETE FROM carrello WHERE id_utente = ? AND id_prodotto = ?");

            stmt.setInt(1, u.getId());
            stmt.setInt(2, prodotto.getId());


            stmt.executeUpdate();

            stmt.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }



    private ItemCart creaItem(ResultSet res) throws SQLException {
        ItemCart itemCart = new ItemCart();

        int prodottoId = res.getInt(1);
        int quantita =  res.getInt(2);

        ProdottoDAO service = new ProdottoDAO();
        Prodotto prodotto = service.doRetrieveById(prodottoId);

        float prezzo = prodotto.getPrezzoListino();
        if(offerteAttive.stream().anyMatch(o->o.contains(prodotto))){
            List<Offerta> offerteProdotto =
                    offerteAttive.stream().filter(o->o.contains(prodotto)).collect(Collectors.toList());
            for(Offerta offerta : offerteProdotto){
                prezzo -= prezzo * offerta.getPercentuale()/100;
            }
        }

        itemCart.setPrezzo(prezzo);
        itemCart.setQuantita(quantita);
        itemCart.setProdotto(prodotto);

        return itemCart;
    }



}
