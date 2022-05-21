package model;

import java.util.ArrayList;

public class Foto {
    private ArrayList<String> directories;

    public Foto() {
        directories = new ArrayList<>();
    }

    public void addFoto(String directory){
        this.directories.add(directory);
    }

    public ArrayList<String> getFoto() {
        return directories;
    }

    public void setFoto(ArrayList<String> directory) {
        this.directories = directory;
    }
}
