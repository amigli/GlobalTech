package model;

import java.util.ArrayList;

public class Carrello {
    private ArrayList <Prodotto> p;
    private double totale;

    public Carrello() {
        p=new ArrayList<>();
        totale=0;
    }

    public ArrayList<Prodotto> getP() {
        return p;
    }

    public void setP(ArrayList<Prodotto> p) {
        this.p = p;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public void addProdotto (Prodotto pr){
        if (p.contains(pr)) {
            int i = p.indexOf(pr);

            totale += pr.getPrezzoListino();
        }
    }
}
