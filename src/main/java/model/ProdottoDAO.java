package model;


import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO {
    public List<Prodotto> doRetrieveAll(){
        try(Connection con = ConPool.getConnection()){
            String sql ="SELECT * from prodotto";
            Statement stmt = con.createStatement();

            ResultSet res =  stmt.executeQuery(sql);
            List<Prodotto> list =  new ArrayList<>();
            while(res.next()){
                Prodotto i = this.creaProdotto(res);

                list.add(i);
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Prodotto doRetrieveById(int id){
        try(Connection con = ConPool.getConnection()){
            String sql ="SELECT * from prodotto" ;
            Statement statement = con.createStatement();
            ResultSet res =  statement.executeQuery(sql);
            res.next();

            return  this.creaProdotto(res);

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }



    public synchronized int doSaveProdotto(Prodotto p){
        try(Connection con =  ConPool.getConnection()){
            PreparedStatement stmt =  con.prepareStatement(
                    "INSERT INTO prodotto (nome, marca, colore, prezzo_listino, descrizione, batteria, ram_tipo," +
                            " ram_quantita, sistema_operativo, cpu_nome, disponibilita)"+
                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?,?, ?, ? )"

            );

            stmt.setString(1,p.getNome());
            stmt.setString(2, p.getMarca());
            stmt.setString(3, p.getColore());
            stmt.setFloat(4, p.getPrezzoListino());
            stmt.setString(5, p.getDescrizione());
            stmt.setBoolean(6, p.isBatteria());
            stmt.setString(7, p.getTipoRam());
            stmt.setString(8, p.getQuantitaRam());
            stmt.setString(9, p.getSistemaOperativo());
            stmt.setString(10, p.getCpuNome());
            stmt.setInt(11, p.getDisponibilita());

            stmt.executeUpdate();

            Statement stmt2 = con.createStatement();
            String sql = "SELECT MAX(id) FROM Prodotto";
            ResultSet res = stmt2.executeQuery(sql);
            res.next();

            int id =  res.getInt(1);

            return id;
        }catch (SQLException e){
            throw  new RuntimeException();
        }
    }

    private Prodotto creaProdotto(ResultSet res) throws SQLException {
        int id = res.getInt("id");
        String nome = res.getString("nome");
        String marca = res.getString("marca");
        String colore = res.getString("colore");
        float prezzoListino = res.getFloat("prezzo_listino");
        String descrizione = res.getString("descrizione");
        String sistemaOpeativo =  res.getString("so");
        String ramTipo = res.getString("ram_tipo");
        String ramQuantita = res.getString("ram_quantita");
        String nomeCpu = res.getString("cpu_nome");
        boolean batteria =  res.getBoolean("batteria");
        int disponibilita =  res.getInt("disponibilita");

        Prodotto p = new Prodotto();

        p.setId(id);
        p.setNome(nome);
        p.setMarca(marca);
        p.setColore(colore);
        p.setPrezzoListino(prezzoListino);
        p.setDescrizione(descrizione);
        p.setSistemaOperativo(sistemaOpeativo);
        p.setTipoRam(ramTipo);
        p.setQuantitaRam(ramQuantita);
        p.setCpuNome(nomeCpu);
        p.setBatteria(batteria);
        p.setDisponibilita(disponibilita);
        return p;
    }
}