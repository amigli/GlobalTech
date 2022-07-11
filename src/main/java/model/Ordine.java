package model;

import java.time.LocalDate;

public class Ordine {
    private int id;
    private double prezzoTotale, speseSpedizione;
    private LocalDate data;
    private String modalitaPagamento, stato;

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

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
}
