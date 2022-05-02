package model;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class UtenteDAO {

    public List<Utente> doRetrieveAll(){
        List <Utente> l = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM utente");
            while (rs.next()){
                int id= rs.getInt(1);
                String nomeUtente=rs.getString(2);
                String password=rs.getString(3);
                GregorianCalendar dataNascita = (GregorianCalendar) rs.getObject(4);
                String nome=rs.getString(5);
                String cognome=rs.getString(6);
                String indirizzo=rs.getString(7);
                int numeroAcquisti=rs.getInt(8);
                boolean admin=rs.getBoolean(9);

                Utente u=new Utente(id, nomeUtente, password, dataNascita, nome, cognome, indirizzo, numeroAcquisti, admin);

                l.add(u);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return l;
    }

    public Utente doRetrieveById(int id){
        Utente u = null;
        try (Connection con = ConPool.getConnection()) {
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM utente WHERE id="+id);
            if (rs.next()){
                int ID= rs.getInt(1);
                String nomeUtente=rs.getString(2);
                String password=rs.getString(3);
                GregorianCalendar dataNascita = (GregorianCalendar) rs.getObject(4);
                String nome=rs.getString(5);
                String cognome=rs.getString(6);
                String indirizzo=rs.getString(7);
                int numeroAcquisti=rs.getInt(8);
                boolean admin=rs.getBoolean(9);

                u.setId(ID);
                u.setNomeUtente(nomeUtente);
                u.setPassword(password);
                u.setDataNascita(dataNascita);
                u.setNome(nome);
                u.setCognome(cognome);
                u.setIndirizzo(indirizzo);
                u.setNumAcquisti(numeroAcquisti);
                u.setAdmin(admin);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return u;
    }
}
