package com.danielrsoares.nodemaps.model;

import com.danielrsoares.nodemaps.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Usuario {

    private String idUsuario;
    private String nome;
    private String login;
    private String email;
    private String senha;

    public Usuario() {
    }

    public void salvar(){
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("usuarios")//Cria Nó usuário
                .child(this.idUsuario)//Cria Nó ID do usuário em base64
                .setValue(this);
    }

    @Exclude //Anotação do Firebase remove esse dado na hora de Salvar o Objeto
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude //Anotação do Firebase remove esse dado na hora de Salvar o Objeto
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
