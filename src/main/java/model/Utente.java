package model;

import java.util.GregorianCalendar;

public class Utente {
    private int id, numAcquisti, numCivico, cap, cvvCarta;
    private String nome, cognome, indirizzo, email, password;
    private String citta, via, numTelefono, numeroCarta, dataNascita, dataScadenzaCarta;
    private boolean admin;

    public Utente() {
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

    public void setDataNascita(String dataNascita) {
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

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public boolean isAdmin() {
        return admin;
    }

    public int getId() {
        return id;
    }

    public int getNumCivico() {
        return numCivico;
    }

    public void setNumCivico(int numCivico) {
        this.numCivico = numCivico;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public int getCvvCarta() {
        return cvvCarta;
    }

    public void setCvvCarta(int cvvCarta) {
        this.cvvCarta = cvvCarta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public String getNumeroCarta() {
        return numeroCarta;
    }

    public void setNumeroCarta(String numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    public String getDataScadenzaCarta() {
        return dataScadenzaCarta;
    }

    public void setDataScadenzaCarta(String dataScadenzaCarta) {
        this.dataScadenzaCarta = dataScadenzaCarta;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }
}
