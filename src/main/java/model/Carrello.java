package model;

import java.util.ArrayList;
import java.util.Iterator;
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

    public ItemCart rimuoviProdotto(Prodotto p){
        if(prodotti.stream().map(ItemCart::getProdotto).anyMatch(prodotto->prodotto.equals(p))){
            Iterator<ItemCart> iterator = prodotti.iterator();

            while(iterator.hasNext()){
                ItemCart tmp = iterator.next();

                if(tmp.getProdotto().equals(p)){
                    iterator.remove();
                    return tmp;
                }
            }
        }
        return null;
    }


    public ItemCart rimuoviProdotto(ItemCart p){
        return rimuoviProdotto(p.getProdotto());
    }
}
