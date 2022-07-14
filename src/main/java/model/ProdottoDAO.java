package model;


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
            con.close();
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Prodotto doRetrieveById(int id){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement statement =
                    con.prepareStatement("SELECT * from prodotto where id = ?");

            statement.setInt(1, id);
            ResultSet res =  statement.executeQuery();
            res.next();

            Prodotto p = this.creaProdotto(res);
            con.close();

            return p;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    public List<Prodotto> doRetrieveByCategoria(Categoria cat){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement stmt =
                    con.prepareStatement(
                            "SELECT p.id, p.nome, p.marca, p.colore, " +
                                    "p.prezzo_listino, p.descrizione," +
                                    "p.batteria, p.ram_tipo, p.ram_quantita, " +
                                    "p.sistema_operativo, p.cpu_nome , p.disponibilita " +
                                    "FROM prodotto p, appartenere a " +
                                    "WHERE p.id = a.id_prodotto AND a.id_categoria = ?");

            stmt.setInt(1, cat.getId());

            ResultSet res = stmt.executeQuery();

            ArrayList<Prodotto> list = new ArrayList<>();

            while (res.next()){
                list.add(creaProdotto(res));
            }

            return list;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public List<Prodotto> doRetrieveByOfferta(Offerta offerta){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement stmt =
                    con.prepareStatement(
                                "SELECT p.id, p.nome, p.marca, p.colore, " +
                                    "p.prezzo_listino, p.descrizione," +
                                    "p.batteria, p.ram_tipo, p.ram_quantita, " +
                                    "p.sistema_operativo, p.cpu_nome , p.disponibilita " +
                                    "FROM prodotto p, applicare a " +
                                    "WHERE p.id = a.id_prodotto AND a.id_offerta = ?");

            stmt.setInt(1, offerta.getId());

            ResultSet res = stmt.executeQuery();

            ArrayList<Prodotto> list = new ArrayList<>();

            while (res.next()){
                list.add(creaProdotto(res));
            }

            return list;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public synchronized int doSaveProdotto(Prodotto p){
        try(Connection con =  ConPool.getConnection()){
            PreparedStatement stmt =  con.prepareStatement(
                    "INSERT INTO prodotto (nome, marca, colore, prezzo_listino, descrizione, batteria, ram_tipo," +
                            " ram_quantita, sistema_operativo, cpu_nome, disponibilita)"+
                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?,?, ?, ? )", Statement.RETURN_GENERATED_KEYS);

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

            ResultSet res =  stmt.getGeneratedKeys();

            if(res.next()){
                int id =  res.getInt(1);
                con.close();

                return id;
            }else{
                con.close();
                throw new RuntimeException();
            }
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
        String sistemaOpeativo =  res.getString("sistema_operativo");
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

        FotoDAO service =  new FotoDAO();

        List<Foto> photos =  service.doRetrieveByProdotto(id);

        p.setImmagini(photos);

        return p;
    }
}