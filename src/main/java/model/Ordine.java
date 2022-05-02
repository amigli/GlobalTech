package model;

import java.util.GregorianCalendar;

public class Ordine {
    private int id;
    private double prezzoTotale, speseSpedizione;
    private GregorianCalendar data;
    private String modalitaPagamento, stato;

    public Ordine(int id, double prezzoTotale, double speseSpedizione, GregorianCalendar data, String modalitaPagamento, String stato) {
        this.id = id;
        this.prezzoTotale = prezzoTotale;
        this.speseSpedizione = speseSpedizione;
        this.data = data;
        this.modalitaPagamento = modalitaPagamento;
        this.stato = stato;
    }

    public Ordine(double prezzoTotale, double speseSpedizione, GregorianCalendar data, String modalitaPagamento, String stato) {
        this.prezzoTotale = prezzoTotale;
        this.speseSpedizione = speseSpedizione;
        this.data = data;
        this.modalitaPagamento = modalitaPagamento;
        this.stato = stato;
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

    public GregorianCalendar getData() {
        return data;
    }

    public void setData(GregorianCalendar data) {
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
