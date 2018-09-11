package com.gswaugustom.login.models;

public class Perfil {
    private String usergoogle;
    private String emailgoogle;
    private String photogoogle;
    private String uid;

    public Perfil() {
    }

    public Perfil(String usergoogle) {
        this.usergoogle = usergoogle;
    }

    public Perfil(String usergoogle, String emailgoogle, String photogoogle, String uid) {
        this.usergoogle = usergoogle;
        this.emailgoogle = emailgoogle;
        this.photogoogle = photogoogle;
        this.uid = uid;
    }

    public String getUsergoogle() {
        return usergoogle;
    }

    public void setUsergoogle(String usergoogle) {
        this.usergoogle = usergoogle;
    }

    public String getEmailgoogle() {
        return emailgoogle;
    }

    public void setEmailgoogle(String emailgoogle) {
        this.emailgoogle = emailgoogle;
    }

    public String getPhotogoogle() {
        return photogoogle;
    }

    public void setPhotogoogle(String photogoogle) {
        this.photogoogle = photogoogle;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
