package model;

import java.util.ArrayList;

public class Categoria {
    private int id;
    private String nome, descrizione;
    private ArrayList<Prodotto> prodotti;

    public Categoria(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public boolean contains(Object o) {
        return prodotti.contains(o);
    }

    public boolean add(Prodotto prodotto) {
        if(this.prodotti.contains(prodotto))
            return false;
        else{
            this.prodotti.add(prodotto);
            return true;
        }
    }
}
