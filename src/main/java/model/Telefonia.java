package model;

public class Telefonia extends Prodotto{
    private String tipoBatteria, tipoRam, so, nomeCpu;
    private float capacitaBatteria, fotocameraPosteriore, fotocamerAnteriore, hertzCpu;
    private int quantitaRam;

    public Telefonia() {
    }

    public String getTipoBatteria() {
        return tipoBatteria;
    }

    public void setTipoBatteria(String tipoBatteria) {
        this.tipoBatteria = tipoBatteria;
    }

    public String getTipoRam() {
        return tipoRam;
    }

    public void setTipoRam(String tipoRam) {
        this.tipoRam = tipoRam;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public String getNomeCpu() {
        return nomeCpu;
    }

    public void setNomeCpu(String nomeCpu) {
        this.nomeCpu = nomeCpu;
    }

    public float getCapacitaBatteria() {
        return capacitaBatteria;
    }

    public void setCapacitaBatteria(float capacitaBatteria) {
        this.capacitaBatteria = capacitaBatteria;
    }

    public float getFotocameraPosteriore() {
        return fotocameraPosteriore;
    }

    public void setFotocameraPosteriore(float fotocameraPosteriore) {
        this.fotocameraPosteriore = fotocameraPosteriore;
    }

    public float getFotocamerAnteriore() {
        return fotocamerAnteriore;
    }

    public void setFotocamerAnteriore(float fotocamerAnteriore) {
        this.fotocamerAnteriore = fotocamerAnteriore;
    }

    public float getHertzCpu() {
        return hertzCpu;
    }

    public void setHertzCpu(float hertzCpu) {
        this.hertzCpu = hertzCpu;
    }

    public int getQuantitaRam() {
        return quantitaRam;
    }

    public void setQuantitaRam(int quantitaRam) {
        this.quantitaRam = quantitaRam;
    }
}
