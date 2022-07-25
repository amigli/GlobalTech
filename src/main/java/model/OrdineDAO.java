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

        PreparedStatement stmt =
                con.prepareStatement("SELECT id_prodotto, quantita, prezzo_acquisto FROM contenere WHERE id_ordine = ?");
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

        stmt.close();
        con.close();

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

    public int doSaveOrdine(Ordine ordine, Utente u){
        try(Connection con =  ConPool.getConnection()){
            PreparedStatement stmt = con.prepareStatement("INSERT INTO ordine(prezzo_totale, data_ordine," +
                    " spese_spedizione, cc_pagamento, indirizzo_spedizione, stato, id_utente) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            stmt.setFloat(1, ordine.getPrezzoTotale());
            stmt.setDate(2, Date.valueOf(ordine.getData()));
            stmt.setFloat(3, ordine.getSpeseSpedizione());
            stmt.setString(4, ordine.getCcPagamento());
            stmt.setString(5, ordine.getIndirizzoSpedizione());
            stmt.setInt(6, ordine.getStato());
            stmt.setInt(7, u.getId());

            stmt.executeUpdate();

            ResultSet res =  stmt.getGeneratedKeys();

            if(res.next()){
                int id =  res.getInt(1);
                stmt.close();
                con.close();
                saveProdottiOrdine(id, ordine.getProdotti());



                return id;


            }else{
                throw new RuntimeException("Errore salvataggio ordine");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }



    private void saveProdottiOrdine(int idOrdine, List<ItemCart> prodotti) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement stmt =
                con.prepareStatement("INSERT INTO contenere (id_prodotto, id_ordine, quantita, prezzo_acquisto)" +
                        "VALUES (?, ?, ?, ?)");
        stmt.setInt(2, idOrdine);

        ProdottoDAO service = new ProdottoDAO();
        for(ItemCart item : prodotti){
            stmt.setInt(1, item.getProdotto().getId());
            stmt.setInt(3, item.getQuantita());
            stmt.setFloat(4, item.getPrezzo());

            stmt.executeUpdate();
            Prodotto p = item.getProdotto();
            p.setDisponibilita(p.getDisponibilita() - item.getQuantita());

            service.doUpdateQuantita(p);
        }


    }

    private Ordine creaOrdine(ResultSet res) throws SQLException {
        int id = res.getInt(1);
        float prezzoTotale=res.getFloat(2);
        String tracking = res.getString(3);
        Date data = res.getDate(4);
        float speseSpedizione = res.getFloat(5);
        String modalitaPagamento = res.getString(6);
        String indirizzoSpedizione =  res.getString(7);
        int stato=res.getInt(8);


        Ordine o = new Ordine();
        o.setId(id);
        o.setPrezzoTotale(prezzoTotale);
        o.setData(data.toLocalDate());
        o.setSpeseSpedizione(speseSpedizione);
        o.setCcPagamento(modalitaPagamento);
        o.setStato(stato);
        o.setIndirizzoSpedizione(indirizzoSpedizione);
        o.setTracking(tracking);
        List<ItemCart> prodotti =  retrieveProdottiOrdine(id);

        o.setProdotti(prodotti);

        return o;

    }
}
