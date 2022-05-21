package model;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    private Prodotto creaProdotto(ResultSet res) throws SQLException {
        int id = res.getInt("id");
        String nome = res.getString("nome");
        String marca = res.getString("marca");
        String colore = res.getString("colore");
        float prezzoListino = res.getFloat("prezzo_listino");
        String descrizione = res.getString("descrizione");
        String sistemaOpeativo =  res.getString("so");
        String ram_tipo = res.getString("ram_tipo");
        int ram_quantita = res.getInt("ram_quantita");
        String nomeCpu = res.getString("cpu_nome");
        float hertzCpu = res.getFloat("cpu_hertz");
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
        p.setTipoRam(ram_tipo);
        p.setQuantitaRam(ram_quantita);
        p.setCpuNome(nomeCpu);
        p.setHertzCpu(hertzCpu);
        p.setBatteria(batteria);
        p.setDisponibilita(disponibilita);
        return p;
    }
}