package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class OffertaDAO {

    public List<Offerta> doRetrieveActive(){
        List <Offerta> l = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM offerta WHERE data_inizio<=current_date() and data_fine>=current_date()");
            while (rs.next()){
                int id= rs.getInt(1);
                double percentuale=rs.getDouble(2);
                GregorianCalendar dataInizio= (GregorianCalendar) rs.getObject(3);
                GregorianCalendar dataFine= (GregorianCalendar) rs.getObject(4);

                Offerta o = new Offerta(id, percentuale, dataInizio, dataFine);

                l.add(o);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return l;
    }

    public Offerta doRetrieveById(int id){
        Offerta o = null;
        try (Connection con = ConPool.getConnection()) {
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM offerta WHERE id="+id);
            if (rs.next()){
                int ID= rs.getInt(1);
                double percentuale=rs.getDouble(2);
                GregorianCalendar dataInizio= (GregorianCalendar) rs.getObject(3);
                GregorianCalendar dataFine= (GregorianCalendar) rs.getObject(4);

                o.setId(ID);
                o.setPercentuale(percentuale);
                o.setDataInizio(dataInizio);
                o.setDataFine(dataFine);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return o;
    }
}
