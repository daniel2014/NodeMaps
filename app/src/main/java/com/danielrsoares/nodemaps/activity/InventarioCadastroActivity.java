package com.danielrsoares.nodemaps.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.danielrsoares.nodemaps.R;
import com.danielrsoares.nodemaps.model.MovInventario;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

public class InventarioCadastroActivity extends AppCompatActivity {

    private TextInputEditText campoNode, campoBairro, campoEndereco, campoNumero;
    private MovInventario movInventario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_cadastro);

        campoNode = findViewById(R.id.edtxt_CadastroNode);
        campoBairro = findViewById(R.id.edtxt_CadastroBairro);
        campoEndereco = findViewById(R.id.edtxt_CadastroEndereco);
        campoNumero = findViewById(R.id.edtxt_CadastroNumero);

    }
/*
    //Movimentação serão salvas no firebase
    public void salvarNode(View view) {
        if (validarCamposSalvarNode()) {
            movInventario = new MovInventario();
            movInventario.setNode(campoNode.getText().toString().toUpperCase());
            //movInventario.setCidade(cidade);
            movInventario.setBairro(campoBairro.getText().toString());
            movInventario.setEndereco(campoEndereco.getText().toString());
            movInventario.setNumero(campoNumero.getText().toString());
            movInventario.salvar();

        }

    }

    //Método para Validar os Campos Preenchidos cadastro Node antes de Salvar
    public Boolean validarCamposSalvarNode() {
        String textNode = campoNode.getText().toString();
        String textBairro = campoBairro.getText().toString();
        String textEndereco = campoEndereco.getText().toString();
        String textNumero = campoNumero.getText().toString();
        String spinnerCidade = cidade;

        if (cidadeInvalida > 0) { // Confirma se a Posição da Cidade é maior que 0. Pois (0) é apenas um título do Array que não deve ser inserido na pesquisa
            if (!textNode.isEmpty()) {
                if (!textBairro.isEmpty()) {
                    if (!textEndereco.isEmpty()) {
                        if (!textNumero.isEmpty()) {

                            carregarProgressBar();
                            return true;

                        } else {
                            Toast.makeText(CadastroNodeActivity.this,
                                    "Preencha o Campo Número",
                                    Toast.LENGTH_SHORT).show();
                            return false;
                        }

                    } else {
                        Toast.makeText(CadastroNodeActivity.this,
                                "Preencha o Campo Endereço",
                                Toast.LENGTH_SHORT).show();
                        return false;
                    }

                } else {
                    Toast.makeText(CadastroNodeActivity.this,
                            "Preencha o Cammpo Bairro",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }

            } else {
                Toast.makeText(CadastroNodeActivity.this,
                        "Preencha o Campo Node",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(CadastroNodeActivity.this,
                    "Escolha uma Cidade",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    } */

    public void btFecharTela(View view){
        finish();
    }

}
