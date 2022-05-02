package model;

import java.util.ArrayList;

public abstract class Prodotto  {
    private int id, quantita;
    private String nome, marca, colore, descrizione;
    private ArrayList<String> immagini;
    private double prezzoListino;

    public Prodotto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public ArrayList<String> getImmagini() {
        return immagini;
    }

    public void setImmagini(ArrayList<String> immagini) {
        this.immagini = immagini;
    }

    public double getPrezzoListino() {
        return prezzoListino;
    }

    public void setPrezzoListino(double prezzoListino) {
        this.prezzoListino = prezzoListino;
    }
}
