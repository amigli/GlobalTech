package model;

public class Accessorio extends Prodotto{
    private String tipologia, connessione ;
    private int autonomia;

    public Accessorio() {
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getConnessione() {
        return connessione;
    }

    public void setConnessione(String connessione) {
        this.connessione = connessione;
    }

    public int getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(int autonomia) {
        this.autonomia = autonomia;
    }
}
