package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Offerta {
    private int id;
    private String nome;
    private float percentuale;
    private LocalDate dataInizio, dataFine;
    private ArrayList<Prodotto> prodotti;

    public Offerta() {
        prodotti = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPercentuale() {
        return percentuale;
    }

    public void setPercentuale(float percentuale) {
        this.percentuale = percentuale;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public boolean addProdotto(Prodotto p){
        if(this.prodotti.contains(p))
            return false;
        else{
            this.prodotti.add(p);
            return true;
        }
    }

    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public boolean contains(Prodotto p){
        return this.prodotti.contains(p);
    }
}
