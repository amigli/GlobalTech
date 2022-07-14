package model;

import java.io.File;

public class Foto {
    private int numeroId, prodottoId;
    private String estensione;

    public Foto() {
    }

    public String getDirectory(){
        return "image/"  +prodottoId + "/" + numeroId + "." + estensione;
    }


    public int getNumeroId() {
        return numeroId;
    }

    public void setNumeroId(int numeroId) {
        this.numeroId = numeroId;
    }

    public int getProdottoId() {
        return prodottoId;
    }

    public void setProdottoId(int prodottoID) {
        this.prodottoId = prodottoID;
    }

    public String getEstensione() {
        return estensione;
    }

    public void setEstensione(String estensione) {
        this.estensione = estensione;
    }
}
