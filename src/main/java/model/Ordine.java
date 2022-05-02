package model;

import java.util.GregorianCalendar;

public class Ordine {
    private int id, idUtente;
    private double prezzoTotale, speseSpedizione;
    private GregorianCalendar data;
    private String modalitaPagamento, stato;

    public Ordine(int id, int idUtente, double prezzoTotale, double speseSpedizione, GregorianCalendar data, String modalitaPagamento, String stato) {
        this.id = id;
        this.idUtente = idUtente;
        this.prezzoTotale = prezzoTotale;
        this.speseSpedizione = speseSpedizione;
        this.data = data;
        this.modalitaPagamento = modalitaPagamento;
        this.stato = stato;
    }

    public Ordine(int idUtente, double prezzoTotale, double speseSpedizione, GregorianCalendar data, String modalitaPagamento, String stato) {
        this.idUtente = idUtente;
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

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
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
