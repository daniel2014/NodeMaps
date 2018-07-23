package com.danielrsoares.nodemaps.model;

import com.danielrsoares.nodemaps.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

public class MovInventario implements Serializable {

    private String key;
    private String node;
    private String modelo;
    private String numeroSerie;
    private String totalAtivos;
    private String cidade;
    private String bairro;
    private String endereco;
    private String numero;

    public MovInventario() {
    }

    // Método => Para salvar no Firebase
    public void salvarFireBase(){ // dataEscolhida recebe do parâmetro data
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("mov_inventarioNode") //Entra em Nó inventario
                .child(cidade) //Entra em Nó Mês da movimentação
                .push() // Cria o ID único do FireBase para cada incrementação ou seja cada vez que for salvo as informações ele gere um ID para aquele salvamento
                .setValue(this); // Pega os valor dos Atributos
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getTotalAtivos() {
        return totalAtivos;
    }

    public void setTotalAtivos(String totalAtivos) {
        this.totalAtivos = totalAtivos;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
