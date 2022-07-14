package model;

import java.util.ArrayList;

public class Carrello {
    private ArrayList <ItemCart> cart;

    public Carrello() {
        cart = new ArrayList<>();
    }

    public ArrayList<ItemCart> getCart() {
        return cart;
    }

    public float getTotale() {
        return cart.stream().map(ItemCart::getPrezzo).reduce(0F, Float::sum);
    }

    public void addProdotto (Prodotto prodotto, int quantita){
        /*
        *   Bisogna controllare se è già presente un item con lo stesso prodotto, in tal caso biosgna aumentare
        *   soltanto la quantità.
        *   Inoltre, bisogna controllare se il prodotto è in offerta quindi salvare o il prezzo di listino
        *   o quello scontato
        *   Altrimenti inserire creare nuovo item e inserirlo nell'array list.
        *   Sono necessari i seguenti metodi:
        *       - compare per i prodotti per poterli confrontare in base all'id
        *       (magari usiamo una lampda expression ed evitiamo il metodo);
        */

    }

    public void setCart(ArrayList<ItemCart> cart) {
        this.cart = cart;
    }
}
