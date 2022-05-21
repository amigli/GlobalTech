package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
                GregorianCalendar data= (GregorianCalendar) rs.getObject(3);
                double speseSpedizione=rs.getDouble(4);
                String modalitaPagamento=rs.getString(5);
                String stato=rs.getString(6);

                Ordine o=new Ordine();
                o.setId(id);
                o.setPrezzoTotale(prezzoTotale);
                o.setData(data);
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
                GregorianCalendar data= (GregorianCalendar) rs.getObject(3);
                double speseSpedizione=rs.getDouble(4);
                String modalitaPagamento=rs.getString(5);
                String stato=rs.getString(6);

                o.setId(ID);
                o.setPrezzoTotale(prezzoTotale);
                o.setData(data);
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
                GregorianCalendar data= (GregorianCalendar) rs.getObject(3);
                double speseSpedizione=rs.getDouble(4);
                String modalitaPagamento=rs.getString(5);
                String stato=rs.getString(6);

                Ordine o = new Ordine();
                o.setId(ID);
                o.setPrezzoTotale(prezzoTotale);
                o.setData(data);
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
}
