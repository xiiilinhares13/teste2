package com.gswaugustom.login.models;

public class Consumidor {

    private String Name;
    private String Cargo;
    private int photoPerfil;
    private int photoRanking;

    //Construtor
    public Consumidor() {
    }

    public Consumidor(String name, String cargo, int photoPerfil, int photoRanking) {
        Name = name;
        Cargo = cargo;
        this.photoPerfil = photoPerfil;
        this.photoRanking = photoRanking;
    }

    //Getter and Setters

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String cargo) {
        Cargo = cargo;
    }

    public int getPhotoPerfil() {
        return photoPerfil;
    }

    public void setPhotoPerfil(int photoPerfil) {
        this.photoPerfil = photoPerfil;
    }

    public int getPhotoRanking() {
        return photoRanking;
    }

    public void setPhotoRanking(int photoRanking) {
        this.photoRanking = photoRanking;
    }
}
