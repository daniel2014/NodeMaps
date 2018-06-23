package com.danielrsoares.nodemaps.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.danielrsoares.nodemaps.R;
import com.danielrsoares.nodemaps.config.ConfiguracaoFirebase;
import com.danielrsoares.nodemaps.helper.Base64Custom;
import com.danielrsoares.nodemaps.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText campoNome, campoLogin, campoEmail, campoSenha;
    private Button botaoCadastrarUsuario;
    private CheckBox checkBox_TermoDeUso;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        campoNome = findViewById(R.id.edtxt_CadastroNome);
        campoLogin = findViewById(R.id.edtxt_CadastroLogin);
        campoEmail = findViewById(R.id.edtxt_CadastroEmail);
        campoSenha = findViewById(R.id.edtxt_CadastroSenha);
        botaoCadastrarUsuario = findViewById(R.id.bt_CadastrarUsuario);
        checkBox_TermoDeUso = findViewById(R.id.cbx_TermoDeUso);


        //Método => Botão Cadastrar
        botaoCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textNome = campoNome.getText().toString();
                String textLogin = campoLogin.getText().toString();
                String textEmail = campoEmail.getText().toString();
                String textSenha = campoSenha.getText().toString();

                //Verificando se Campos foram Preenchidos
                if (!textNome.isEmpty()) {// ! é para negar o retorno do isEmpty()
                    if (!textLogin.isEmpty()) {
                        if (!textEmail.isEmpty()) {
                            if (!textSenha.isEmpty()) {
                                if (checkBox_TermoDeUso.isChecked()) {

                                    usuario = new Usuario();
                                    usuario.setNome(textNome);
                                    usuario.setLogin(textLogin);
                                    usuario.setEmail(textEmail);
                                    usuario.setSenha(textSenha);
                                    cadastrarUsuario();//Método cadastrar usuário

                                }else {
                                    Toast.makeText(CadastroUsuarioActivity.this,
                                            "Confirme que você leu os Termos de Uso",
                                            Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(CadastroUsuarioActivity.this,
                                        "Preencha a Senha",
                                        Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(CadastroUsuarioActivity.this,
                                    "Preencha o E-mail",
                                    Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(CadastroUsuarioActivity.this,
                                "Preencha o Login",
                                Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(CadastroUsuarioActivity.this,
                            "Preencha o Nome",
                            Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

    //Método  Statico Cadastrar Usuário
    public void cadastrarUsuario() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    String idUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                    usuario.setIdUsuario(idUsuario);
                    usuario.salvar();
                    finish();
                } else {

                    String execao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {//Informa se a senha não for forte o suficiente
                        execao = "Digite uma senha mais forte";
                    } catch (FirebaseAuthInvalidCredentialsException e) {//Informa se o endereço de email estiver mal formado
                        execao = "Por favor, digite um E-mail Válido";
                    } catch (FirebaseAuthUserCollisionException e) {//Informa se já existir uma conta com o endereço e-mail já cadastrado
                        execao = "E-mail já Cadastrado";
                    } catch (Exception e) {//Exceção Generica
                        execao = "Erro ao Cadastrar Usuário";
                        e.printStackTrace();//para printar essa exeção no nosso log
                    }

                    Toast.makeText(CadastroUsuarioActivity.this,
                            execao,
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }


}


