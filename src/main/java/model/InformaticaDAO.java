package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InformaticaDAO {
    public List<Informatica> doRetrieveAll(){
        try(Connection con = ConPool.getConnection()){
            String sql ="SELECT * from prodotto p join informatica i on i.id_prodotto=p.id";
            Statement stmt = con.createStatement();

            ResultSet res =  stmt.executeQuery(sql);
            List<Informatica> list =  new ArrayList<>();
            while(res.next()){
                Informatica i = this.creaInformatica(res);

                list.add(i);
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Informatica doRetrieveById(int id){
        try(Connection con = ConPool.getConnection()){
            String sql ="SELECT * from prodotto p join informatica i on i.id_prodotto=p.id WHERE id ="+ id ;
            Statement statement = con.createStatement();

            ResultSet res =  statement.executeQuery(sql);
            res.next();

            Informatica i =  this.creaInformatica(res);

            return i;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    private Informatica creaInformatica(ResultSet res) throws SQLException {
        int id = res.getInt("id");
        String nome = res.getString("nome");
        String marca = res.getString("marca");
        String colore = res.getString("colore");
        double prezzoListino = res.getDouble("prezzo_listino");
        String descrizione = res.getString("descrizione");

        String tipologia = res.getString("tipologia");
        String so =  res.getString("so");
        String ram_tipo = res.getString("ram_tipo");
        int ram_quantita = res.getInt("ram_quantita");
        String gpu = res.getString("gpu");
        String cpu_nome= res.getString("cpu_nome");
        float cpu_hertz = res.getFloat("cpu_hertz");
        boolean batteria =  res.getBoolean("batteria");
        String schermo = res.getString("schermo");

        Informatica p = new Informatica();

        p.setId(id);
        p.setNome(nome);
        p.setMarca(marca);
        p.setColore(colore);
        p.setPrezzoListino(prezzoListino);
        p.setDescrizione(descrizione);

        p.setTipologia(tipologia);
        p.setSo(so);
        p.setTipoRam(ram_tipo);
        p.setQuantitaRam(ram_quantita);
        p.setGpu(gpu);
        p.setCpuNome(cpu_nome);
        p.setHertzCpu(cpu_hertz);
        p.setBatteria(batteria);
        p.setSchermo(schermo);

        return p;
    }
}
