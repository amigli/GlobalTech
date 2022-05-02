package model;

public class Informatica {

    private String tipologia, so, tipoRam, gpu, cpu_nome, schermo;
    private float hertzCpu;
    private boolean batteria;
    private int quantitaRam;

    public Informatica() {
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
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

    public String getCpu_nome() {
        return cpu_nome;
    }

    public void setCpu_nome(String cpu_nome) {
        this.cpu_nome = cpu_nome;
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
