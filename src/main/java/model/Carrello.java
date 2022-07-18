package model;

import java.util.ArrayList;
import java.util.Optional;

public class Carrello {
    private ArrayList <ItemCart> prodotti;

    public Carrello() {
        prodotti = new ArrayList<>();
    }


    public float getTotale() {
        return prodotti.stream().map(ItemCart::getPrezzo).reduce(0F, Float::sum);
    }

    public void addProdotto (ItemCart prodotto){
        if(prodotti.stream().anyMatch(i->i.getProdotto().equals(prodotto.getProdotto()))){
            for(ItemCart i : prodotti){
                if(i.getProdotto().equals(prodotto.getProdotto())){
                    i.setQuantita(i.getQuantita() + prodotto.getQuantita());
                }
            }
        }else{
            prodotti.add(prodotto);
        }

    }

    public boolean isEmpty(){
        return prodotti.isEmpty();
    }

    public ArrayList<ItemCart> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<ItemCart> prodotti) {
        this.prodotti = prodotti;
    }
}
