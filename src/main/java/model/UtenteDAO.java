package model;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class UtenteDAO {

    public List<Utente> doRetrieveAll(){
        try (Connection con = ConPool.getConnection()) {
            List <Utente> l = new ArrayList<>();
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM utente");
            while (rs.next()){
                Utente u =  this.trovaUtente(rs);
                l.add(u);
            }

            return l;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Utente doRetrieveById(int id){

        try (Connection con = ConPool.getConnection()) {
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM utente WHERE id="+id);
            if (rs.next()){
                return trovaUtente(rs);
            }else{
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Utente trovaUtente(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String email=rs.getString(2);
        String password=rs.getString(3);
        GregorianCalendar dataNascita = (GregorianCalendar) rs.getObject(4);
        String nome=rs.getString(5);
        String cognome=rs.getString(6);
        String indirizzo=rs.getString(7);
        int numeroAcquisti=rs.getInt(8);
        boolean admin=rs.getBoolean(9);

        Utente u =  new Utente();

        u.setId(id);
        u.setEmail(email);
        u.setPassword(password);
        u.setDataNascita(dataNascita);
        u.setNome(nome);
        u.setCognome(cognome);
        u.setIndirizzo(indirizzo);
        u.setNumAcquisti(numeroAcquisti);
        u.setAdmin(admin);

        return u;
    }
}
