package model;

import java.time.LocalDate;
import java.util.List;

public class Ordine {
    private int id, stato;
    public static final int inAttesaDiConferma=0, confermato=1, completato=2;
    private float prezzoTotale, speseSpedizione;
    private LocalDate data;
    private String ccPagamento, indirizzoSpedizione, tracking;
    private List<ItemCart> prodotti;

    public Ordine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(float prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public float getSpeseSpedizione() {
        return speseSpedizione;
    }

    public void setSpeseSpedizione(float speseSpedizione) {
        this.speseSpedizione = speseSpedizione;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getCcPagamento() {
        return ccPagamento;
    }

    public void setCcPagamento(String ccPagamento) {
        this.ccPagamento = ccPagamento;
    }

    public int getStato() {
        return stato;
    }

    public void setStato(int stato) {
        this.stato = stato;
    }

    public List<ItemCart> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<ItemCart> prodotti) {
        this.prodotti = prodotti;
    }

    public String getStatoString(){
        String risp;

        switch (stato){
            case 0 :
                risp = "In attesa di conferma";
            break;
            case 1 :
                risp = "In attesa di spedizione";
            break;
            case 2 :
                risp  = "Completato";
            break;
            default:
                risp = "Errore, contattare amministratore";
            break;
        }


        return risp;
    }

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    public String getIndirizzoSpedizione() {
        return indirizzoSpedizione;
    }

    public void setIndirizzoSpedizione(String indirizzoSpedizione) {
        this.indirizzoSpedizione = indirizzoSpedizione;
    }
}
