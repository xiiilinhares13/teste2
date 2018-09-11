package com.gswaugustom.login.models;

public class Usuario {
    private int id;
    private String nome;
    private String usuario;
    private int reverse_token;


    public Usuario(int id, String nome, String usuario, int reverse_token) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.reverse_token = reverse_token;
    }

    public Usuario() {
        super();
    }

    public int getId(int anInt) {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReverse_token() {
        return reverse_token;
    }

    public void setReverse_token(int reverse_token) {
        this.reverse_token = reverse_token;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void getUsuario(String string) {
    }
}
