package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO {
    public List<Ordine> doRetrieveAll(){
        try (Connection con = ConPool.getConnection()) {
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ordine");

            List <Ordine> l = new ArrayList<>();

            while (rs.next()){
                l.add(creaOrdine(rs));
            }

            stmt.close();
            con.close();

            return l;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Ordine doRetrieveById(int id){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement stmt=con.prepareStatement("SELECT * FROM ordine WHERE id = ?");

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                Ordine o  = creaOrdine(rs);

                stmt.close();
                con.close();

                return o;
            }else{
                stmt.close();
                con.close();

                return  null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ordine> doRetrieveByUtenteId(int idUtente){

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement stmt=con.prepareStatement("SELECT * FROM ordine WHERE id_utente = ?");

            stmt.setInt(1, idUtente);
            ResultSet rs = stmt.executeQuery();

            List<Ordine> l = new ArrayList<>();

            while (rs.next()){
                l.add(creaOrdine(rs));
            }

            return l;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ItemCart> retrieveProdottiOrdine(int id_ordine) throws SQLException {
        Connection con =  ConPool.getConnection();

        PreparedStatement stmt = con.prepareStatement("SELECT id_prodotto, quantita, prezzo_acquisto FROM contenere WHERE id_ordine = ?");
        stmt.setInt(1, id_ordine);

        ResultSet res =  stmt.executeQuery();

        List<ItemCart> prodotti = new ArrayList<>();
        ProdottoDAO serviceProdotto = new ProdottoDAO();
        while (res.next()){
            int quantita =  res.getInt(2);
            float prezzo_acquisto  = res.getFloat(3);

            int id_prodotto = res.getInt(1);

            Prodotto p =  serviceProdotto.doRetrieveById(id_prodotto);

            ItemCart item =  new ItemCart();

            item.setProdotto(p);
            item.setQuantita(quantita);
            item.setPrezzo(prezzo_acquisto);

            prodotti.add(item);
        }

        return prodotti;

    }

    public void setStatusById(int status, int id){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("UPDATE ordine SET stato = ? WHERE id = ?");

            stmt.setInt(1, status);
            stmt.setInt(2, id);

            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void setTrackingById(String track, int id){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("UPDATE ordine SET tracking = ? WHERE id = ?");

            stmt.setString(1, track);
            stmt.setInt(2, id);

            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    private Ordine creaOrdine(ResultSet res) throws SQLException {
        int id= res.getInt(1);
        double prezzoTotale=res.getDouble(2);
        Date data= res.getDate(4);
        double speseSpedizione=res.getDouble(5);
        String modalitaPagamento=res.getString(6);
        int stato=res.getInt(7);

        Ordine o = new Ordine();
        o.setId(id);
        o.setPrezzoTotale(prezzoTotale);
        o.setData(data.toLocalDate());
        o.setSpeseSpedizione(speseSpedizione);
        o.setModalitaPagamento(modalitaPagamento);
        o.setStato(stato);

        List<ItemCart> prodotti =  retrieveProdottiOrdine(id);

        o.setProdotti(prodotti);

        return o;

    }
}
