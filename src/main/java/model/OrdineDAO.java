package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO {
    public List<Ordine> doRetrieveAll(){
        List <Ordine> l = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ordine");
            while (rs.next()){
                int id= rs.getInt(1);
                double prezzoTotale=rs.getDouble(2);
                Date data = rs.getDate(4);
                double speseSpedizione=rs.getDouble(5);
                String modalitaPagamento=rs.getString(6);
                String stato=rs.getString(7);

                Ordine o=new Ordine();
                o.setId(id);
                o.setPrezzoTotale(prezzoTotale);
                o.setData(data.toLocalDate());
                o.setSpeseSpedizione(speseSpedizione);
                o.setModalitaPagamento(modalitaPagamento);
                o.setStato(stato);

                l.add(o);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return l;
    }

    public Ordine doRetrieveById(int id){
        Ordine o = null;
        try (Connection con = ConPool.getConnection()) {
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ordine WHERE id="+id);
            if (rs.next()){
                int ID= rs.getInt(1);
                double prezzoTotale=rs.getDouble(2);
                Date data= rs.getDate(4);
                double speseSpedizione=rs.getDouble(5);
                String modalitaPagamento=rs.getString(6);
                String stato=rs.getString(7);

                o.setId(ID);
                o.setPrezzoTotale(prezzoTotale);
                o.setData(data.toLocalDate());
                o.setSpeseSpedizione(speseSpedizione);
                o.setModalitaPagamento(modalitaPagamento);
                o.setStato(stato);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return o;
    }

    public List<Ordine> doRetrieveByCustomerId(int idCustomer){
        List<Ordine> l = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ordine WHERE id_utente="+idCustomer);
            while (rs.next()){
                int ID= rs.getInt(1);
                double prezzoTotale=rs.getDouble(2);
                Date data= rs.getDate(4);
                double speseSpedizione=rs.getDouble(5);
                String modalitaPagamento=rs.getString(6);
                String stato=rs.getString(7);

                Ordine o = new Ordine();
                o.setId(ID);
                o.setPrezzoTotale(prezzoTotale);
                o.setData(data.toLocalDate());
                o.setSpeseSpedizione(speseSpedizione);
                o.setModalitaPagamento(modalitaPagamento);
                o.setStato(stato);

                l.add(o);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return l;
    }

    public void setStatusById(String status, int id){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("UPDATE ordine SET stato = ? WHERE id = ?");

            stmt.setString(1, status);
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
}
