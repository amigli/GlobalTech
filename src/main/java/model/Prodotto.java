package model;

import java.util.ArrayList;

public class Prodotto  {
    private int id, quantita;
    private String nome, marca, colore, descrizione;
    private ArrayList<String> immagini;
    private double prezzoListino;
    private String so, tipoRam, gpu, cpuNome, schermo;
    private float hertzCpu;
    private boolean batteria;
    private int quantitaRam;

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

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public String getTipoRam() {
        return tipoRam;
    }

    public void setTipoRam(String tipoRam) {
        this.tipoRam = tipoRam;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getCpuNome() {
        return cpuNome;
    }

    public void setCpuNome(String cpuNome) {
        this.cpuNome = cpuNome;
    }

    public String getSchermo() {
        return schermo;
    }

    public void setSchermo(String schermo) {
        this.schermo = schermo;
    }

    public float getHertzCpu() {
        return hertzCpu;
    }

    public void setHertzCpu(float hertzCpu) {
        this.hertzCpu = hertzCpu;
    }

    public boolean isBatteria() {
        return batteria;
    }

    public void setBatteria(boolean batteria) {
        this.batteria = batteria;
    }

    public int getQuantitaRam() {
        return quantitaRam;
    }

    public void setQuantitaRam(int quantitaRam) {
        this.quantitaRam = quantitaRam;
    }
}
