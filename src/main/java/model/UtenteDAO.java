package model;

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
        String dataNascita = (String) rs.getObject(4);
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

    public void registraUtente (Utente u){
        try (Connection con = ConPool.getConnection()) {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("INSERT INTO utente(email, passwordhash, data_nascita, nome, cognome, admin, " +
                    "via_indirizzo, civico, citta, cap, num_acquisti, numero_telefono, numero_cc, cvv_cc, " +
                    "data_scadenza_cc) " +
                    "values (" + u.getEmail()+ ", "+ u.getPassword() + ", " + u.getDataNascita() + "," + u.getNome() + ","
                        + u.getCognome() + ", " + u.isAdmin() + ", " + u.getVia() + ", " + u.getNumCivico() + ", " +
                        u.getCitta() + ", " + u.getCap() + ", " + u.getNumAcquisti() + ", " + u.getNumTelefono() + ", "
                        + u.getNumeroCarta() + ", " + u.getCvvCarta() + ", " + u.getDataScadenzaCarta() + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
