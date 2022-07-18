package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Prodotto  {
    private int id, disponibilita;
    private String nome, marca, colore, descrizione,
            sistemaOperativo, tipoRam, cpuNome, quantitaRam;

    private List<Foto> immagini;
    private float prezzoListino;

    private boolean batteria;

    public Prodotto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(int disponibilita) {
        this.disponibilita = disponibilita;
    }

    public String getQuantitaRam() {
        return quantitaRam;
    }

    public void setQuantitaRam(String quantitaRam) {
        this.quantitaRam = quantitaRam;
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

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getTipoRam() {
        return tipoRam;
    }

    public void setTipoRam(String tipoRam) {
        this.tipoRam = tipoRam;
    }

    public String getCpuNome() {
        return cpuNome;
    }

    public void setCpuNome(String cpuNome) {
        this.cpuNome = cpuNome;
    }

    public List<Foto> getImmagini() {
        return immagini;
    }

    public void setImmagini(List<Foto> immagini) {
        this.immagini = immagini;
    }

    public float getPrezzoListino() {
        return prezzoListino;
    }

    public void setPrezzoListino(float prezzoListino) {
        this.prezzoListino = prezzoListino;
    }

    public boolean isBatteria() {
        return batteria;
    }

    public void setBatteria(boolean batteria) {
        this.batteria = batteria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Prodotto prodotto = (Prodotto) o;
        return id == prodotto.id;
    }

}
