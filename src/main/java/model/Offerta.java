package model;

import java.util.GregorianCalendar;

public class Offerta {
    private int id;
    private double percentuale;
    private GregorianCalendar dataInizio, dataFine;

    public Offerta(int id, double percentuale, GregorianCalendar dataInizio, GregorianCalendar dataFine) {
        this.id = id;
        this.percentuale = percentuale;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public Offerta(double percentuale, GregorianCalendar dataInizio, GregorianCalendar dataFine) {
        this.percentuale = percentuale;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPercentuale() {
        return percentuale;
    }

    public void setPercentuale(double percentuale) {
        this.percentuale = percentuale;
    }

    public GregorianCalendar getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(GregorianCalendar dataInizio) {
        this.dataInizio = dataInizio;
    }

    public GregorianCalendar getDataFine() {
        return dataFine;
    }

    public void setDataFine(GregorianCalendar dataFine) {
        this.dataFine = dataFine;
    }
}
