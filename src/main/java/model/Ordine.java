package model;

import java.time.LocalDate;
import java.util.List;

public class Ordine {
    private int id, stato;
    private double prezzoTotale, speseSpedizione;
    private LocalDate data;
    private String modalitaPagamento;
    private List<ItemCart> prodotti;

    public Ordine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public double getSpeseSpedizione() {
        return speseSpedizione;
    }

    public void setSpeseSpedizione(double speseSpedizione) {
        this.speseSpedizione = speseSpedizione;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getModalitaPagamento() {
        return modalitaPagamento;
    }

    public void setModalitaPagamento(String modalitaPagamento) {
        this.modalitaPagamento = modalitaPagamento;
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
}
