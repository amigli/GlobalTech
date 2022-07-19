package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {

    public List<Utente> doRetrieveAll(){
        try (Connection con = ConPool.getConnection()) {
            List <Utente> l = new ArrayList<>();
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM utente");
            while (rs.next()){
                Utente u =  this.creaUtente(rs);
                l.add(u);
            }

            stmt.close();
            con.close();

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
                stmt.close();
                con.close();
                return creaUtente(rs);
            }else{
                stmt.close();
                con.close();
                return null;
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Utente creaUtente(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String email=rs.getString(2);
        String password=rs.getString(3);
        Date dataNascita = rs.getDate(4);
        String nome=rs.getString(5);
        String cognome=rs.getString(6);
        boolean admin=rs.getBoolean(7);
        String indirizzo=rs.getString(8);
        int numeroAcquisti=rs.getInt(12);


        Utente u =  new Utente();

        u.setId(id);
        u.setEmail(email);
        u.setPassword(password);
        u.setDataNascita(dataNascita.toLocalDate());
        u.setNome(nome);
        u.setCognome(cognome);
        u.setVia(indirizzo);
        u.setNumAcquisti(numeroAcquisti);
        u.setAdmin(admin);

        return u;
    }

    public int registraUtente (Utente u){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement stmt=
                    con.prepareStatement("INSERT INTO utente(nome, cognome, email, passwordhash," +
                            " data_nascita, admin, num_acquisti) values (?,?,?,?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1,u.getNome());
            stmt.setString(2, u.getCognome());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPassword());
            stmt.setDate(5, Date.valueOf(u.getDataNascita()));
            stmt.setBoolean(6, u.isAdmin());
            stmt.setInt(7, u.getNumAcquisti());

            stmt.executeUpdate();

            ResultSet res = stmt.getGeneratedKeys();

            res.next();
            int id = res.getInt(1);
            stmt.close();
            con.close();

            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente login (String email, String password){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement stmt=
                    con.prepareStatement("SELECT *" +
                    "FROM utente WHERE email = ? AND passwordhash = sha1(?)");

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                Utente u =  this.creaUtente(rs);
                stmt.close();
                con.close();

                return u;

            }else{
                stmt.close();
                con.close();
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAdmin (int valore, int id){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("UPDATE utente SET admin = ? WHERE id = ?");

            stmt.setInt(1, valore);
            stmt.setInt(2, id);

            stmt.executeUpdate();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
