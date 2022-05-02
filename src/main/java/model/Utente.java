package model;

import java.util.GregorianCalendar;

public class Utente {
    private int id, numAcquisti;
    private String nomeUtente, password, nome, cognome, indirizzo;
    private GregorianCalendar dataNascita;
    private boolean admin;

    public Utente (int id, String nomeUtente, String password, GregorianCalendar dataNascita, String nome, String cognome, String indirizzo, int numAcquisti, boolean admin){
        this.id = id;
        this.nomeUtente = nomeUtente;
        this.password = password;
        this.dataNascita = dataNascita;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.numAcquisti = numAcquisti;
        this.admin = admin;
    }

    public Utente (String nomeUtente, String password, GregorianCalendar dataNascita, String nome, String cognome, String indirizzo, int numAcquisti, boolean admin){
        this.nomeUtente = nomeUtente;
        this.password = password;
        this.dataNascita = dataNascita;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.numAcquisti = numAcquisti;
        this.admin = admin;
    }

    public int getID() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setDataNascita(GregorianCalendar dataNascita) {
        this.dataNascita = dataNascita;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setNumAcquisti(int numAcquisti) {
        this.numAcquisti = numAcquisti;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getNumAcquisti() {
        return numAcquisti;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public GregorianCalendar getDataNascita() {
        return dataNascita;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public boolean isAdmin() {
        return admin;
    }
}
