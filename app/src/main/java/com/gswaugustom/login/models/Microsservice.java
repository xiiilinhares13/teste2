package com.gswaugustom.login.models;

public class Microsservice {

    private String Name;
    private String Consumidor;
    private int img;
    private int photoRanking;

    //Construtor
    public Microsservice() {
    }

    public Microsservice(String name, String consumidor, int photoRanking, int img) {
        Name = name;
        Consumidor = consumidor;
        this.img = img;
        this.photoRanking = photoRanking;
    }
    //Getter and Setters


    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getConsumidor() {
        return Consumidor;
    }

    public void setConsumidor(String consumidor) {
        Consumidor = consumidor;
    }

    public int getPhotoRanking() {
        return photoRanking;
    }

    public void setPhotoRanking(int photoRanking) {
        this.photoRanking = photoRanking;
    }
}
