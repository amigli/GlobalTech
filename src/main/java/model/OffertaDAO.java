package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class OffertaDAO {

    public List<Offerta> doRetrieveActive(){

        try (Connection con = ConPool.getConnection()) {
            List <Offerta> l = new ArrayList<>();
            String sql = "SELECT * FROM offerta WHERE data_inizio<=current_date() " +
                    "and data_fine >= current_date()";
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                l.add(this.creaOfferta(rs));
            }
            return l;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Offerta doRetrieveById(int id){
        try (Connection con = ConPool.getConnection()) {
            Offerta o = null;
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM offerta WHERE id="+id);
            if (rs.next()){
                return this.creaOfferta(rs);
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    private Offerta creaOfferta(ResultSet res) throws SQLException{
        int id = res.getInt(1);
        String nome = res.getString(2);
        double percentuale = res.getDouble(3);
        Date dataInizio = res.getDate(4);
        Date dataFine = res.getDate(5);

        Offerta offerta = new Offerta();
        offerta.setId(id);
        offerta.setNome(nome);
        offerta.setPercentuale(percentuale);
        offerta.setDataInizio(dataInizio.toLocalDate());
        offerta.setDataFine(dataFine.toLocalDate());

        return offerta;
    }
}
