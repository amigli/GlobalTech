package model;


import java.sql.*;
import java.time.LocalDate;
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
                Utente u = creaUtente(rs);
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

    private Utente creaUtente(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String email=rs.getString(2);
        String password=rs.getString(3);
        Date dataNascita = rs.getDate(4);
        String nome=rs.getString(5);
        String cognome=rs.getString(6);
        boolean admin=rs.getBoolean(7);
        String indirizzo=rs.getString(8);
        int civico = rs.getInt(9);
        String citta = rs.getString(10);
        String cap = rs.getString(11);
        int numeroAcquisti=rs.getInt(12);
        String numeroTelefono = rs.getString(13);
        String numeroCc = rs.getString(14);
        String cvv = rs.getString(15);
        Date dataCc = rs.getDate(16);


        Utente u =  new Utente();

        u.setId(id);
        u.setEmail(email);
        u.setPassword(password);
        if (dataNascita==null) {
            u.setDataNascita(null);
        }else{
            u.setDataNascita(dataNascita.toLocalDate());
        }
        u.setNome(nome);
        u.setCognome(cognome);
        u.setAdmin(admin);
        u.setVia(indirizzo);
        u.setNumCivico(civico);
        u.setCitta(citta);
        try{
            int CAP = Integer.parseInt(cap);
            u.setCap(CAP);
        }catch (NumberFormatException e){
            u.setCap(0);
        }
        u.setNumAcquisti(numeroAcquisti);
        u.setNumTelefono(numeroTelefono);
        u.setNumeroCarta(numeroCc);
        try{
            int CVV = Integer.parseInt(cvv);
            u.setCvvCarta(CVV);
        }catch (NumberFormatException e){
            u.setCvvCarta(0);
        }
        if(dataCc==null){
            u.setDataScadenzaCarta(null);
        }else{
           u.setDataScadenzaCarta(dataCc.toLocalDate());
        }

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

    public void aggiornaUtente (Utente u){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("UPDATE utente " +
                    "SET nome = ?, cognome=?, data_nascita=?, via_indirizzo=?, civico=?, citta=?, cap=?, " +
                    "numero_telefono=?, numero_cc=?,cvv_cc=?, data_scadenza_cc=? WHERE id = ?");

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getCognome());
            stmt.setDate(3, Date.valueOf(u.getDataNascita()));
            stmt.setString(4, u.getVia());
            stmt.setInt(5, u.getNumCivico());
            stmt.setString(6, u.getCitta());
            stmt.setInt(7, u.getCap());
            stmt.setString(8, u.getNumTelefono());
            stmt.setString(9, u.getNumeroCarta());
            stmt.setInt(10, u.getCvvCarta());
            stmt.setDate(11, Date.valueOf(u.getDataScadenzaCarta()));
            stmt.setInt(12, u.getId());

            stmt.executeUpdate();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void doSavePassword (String password, int id){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("UPDATE utente SET passwordhash = ? WHERE id = ?");

            stmt.setString(1, password);
            stmt.setInt(2, id);

            stmt.executeUpdate();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

}
