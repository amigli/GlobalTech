package model;

import java.time.LocalDate;
import java.util.GregorianCalendar;

public class Offerta {
    private int id;
    private String nome;
    private float percentuale;
    private LocalDate dataInizio, dataFine;

    public Offerta() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPercentuale() {
        return percentuale;
    }

    public void setPercentuale(float percentuale) {
        this.percentuale = percentuale;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }
}
