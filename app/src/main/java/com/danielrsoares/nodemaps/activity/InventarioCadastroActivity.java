package com.danielrsoares.nodemaps.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.danielrsoares.nodemaps.R;
import com.danielrsoares.nodemaps.model.MovInventario;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

public class InventarioCadastroActivity extends AppCompatActivity{

    private TextInputEditText campoNode, campoCidade, campoBairro, campoEndereco, campoNumero;
    private MovInventario movInventario;
    private ProgressBar progressBarInventarioCadastro;
    private MovInventario cidade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_cadastro);

        progressBarInventarioCadastro = findViewById(R.id.progressBar_CadastroNode);
        progressBarInventarioCadastro.setVisibility(View.GONE);

        campoNode = findViewById(R.id.edtxt_CadastroNode);
        campoCidade = findViewById(R.id.edtxt_CadastroCidade);
        campoBairro = findViewById(R.id.edtxt_CadastroBairro);
        campoEndereco = findViewById(R.id.edtxt_CadastroEndereco);
        campoNumero = findViewById(R.id.edtxt_CadastroNumero);

        //Recuperando a Seleção da Cidade pelo usuário no InventarioActivity.class
        Bundle dados = getIntent().getExtras();
        cidade = (MovInventario) dados.getSerializable("cidade");
        campoCidade.setText(cidade.getCidade());

        //Toast.makeText(InventarioCadastroActivity.this, "Cadastro para: " + cidade.getCidade(), Toast.LENGTH_SHORT).show();


    }

    //Movimentação serão salvas no firebase
    public void salvarNode(View view) {

        if (validarCamposSalvarNode()) {
            movInventario = new MovInventario();
            movInventario.setNode(campoNode.getText().toString().toUpperCase());
            movInventario.setCidade(cidade.getCidade());
            movInventario.setBairro(campoBairro.getText().toString());
            movInventario.setEndereco(campoEndereco.getText().toString());
            movInventario.setNumero(campoNumero.getText().toString());
            movInventario.salvarFireBase();

        }

    }

    //Método para Validar os Campos Preenchidos cadastro Node antes de Salvar
    public Boolean validarCamposSalvarNode() {
        String textNode = campoNode.getText().toString();
        String textBairro = campoBairro.getText().toString();
        String textEndereco = campoEndereco.getText().toString();
        String textNumero = campoNumero.getText().toString();


            if (!textNode.isEmpty()) {
                if (!textBairro.isEmpty()) {
                    if (!textEndereco.isEmpty()) {
                        if (!textNumero.isEmpty()) {

                            carregarProgressBar();
                            return true;

                        } else {
                           Toast.makeText(InventarioCadastroActivity.this,
                                    "Preencha o Campo Número",
                                    Toast.LENGTH_SHORT).show();
                            return false;
                        }

                    } else {
                        Toast.makeText(InventarioCadastroActivity.this,
                                "Preencha o Campo Endereço",
                                Toast.LENGTH_SHORT).show();
                        return false;
                    }

                } else {
                    Toast.makeText(InventarioCadastroActivity.this,
                            "Preencha o Cammpo Bairro",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }

            } else {
                Toast.makeText(InventarioCadastroActivity.this,
                        "Preencha o Campo Node",
                        Toast.LENGTH_SHORT).show();
                return false;
            }

    }

    public void carregarProgressBar() {

        progressBarInventarioCadastro.setVisibility(View.VISIBLE);
        //this.progresso = this.progresso + 10;
        //progressBarCadastroNode.setProgress(this.progresso);


        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i <= 25; i++) {
                    System.out.println(i);

                    final int progresso = i; // Éssa variável tenque ser final

                    runOnUiThread(new Runnable() {//Thread responsável para atualizar a interface
                        @Override
                        public void run() {

                            progressBarInventarioCadastro.setProgress(progresso);
                            if (progresso == 25) {
                                finish(); // Fecha a Tela de Cadastro
                            }
                        }
                    });

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    public void btFecharTela(View view){
        finish();
    }

}
