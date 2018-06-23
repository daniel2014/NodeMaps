package com.danielrsoares.nodemaps.model;

import com.danielrsoares.nodemaps.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MovInventario {

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
    public void salvar(){ // dataEscolhida recebe do parâmetro data
        //Utilizando a Base64 para usar como um identificador
        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();//Recupeando email do usuário
        //String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail()); //Codificando E-mail para Base64
        //String mesAno = DateCustom.mesAnoDataEscolhida(dataEscolhida);

        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("mov_inventarioNode") //Entra em Nó inventario
                //.child(idUsuario) //Entra em Nó identificador de Usuário usando E-mail em Base64
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

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
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
