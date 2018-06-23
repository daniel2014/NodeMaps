package com.danielrsoares.nodemaps.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.danielrsoares.nodemaps.R;
import com.danielrsoares.nodemaps.config.ConfiguracaoFirebase;
import com.danielrsoares.nodemaps.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import io.fabric.sdk.android.Fabric;

public class LoginUsuarioActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button botaoLoginEntrar, botaoIrCadastro;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_login_usuario);

        campoEmail = findViewById(R.id.edtxt_LoginEmail);
        campoSenha = findViewById(R.id.edtxt_LoginSenha);
        botaoLoginEntrar = findViewById(R.id.bt_LoginEntrar);
        botaoIrCadastro = findViewById(R.id.bt_IrCadastro);


        botaoLoginEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text_Email = campoEmail.getText().toString();
                String text_Senha = campoSenha.getText().toString();

                //Validar se os campos foram preenchidos
                if (!text_Email.isEmpty()){
                    if (!text_Senha.isEmpty()){

                        usuario = new Usuario();
                        usuario.setEmail(text_Email);
                        usuario.setSenha(text_Senha);
                        validarLogin();

                    }else{
                        Toast.makeText(LoginUsuarioActivity.this,
                                "Preencha a Senha",
                                Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(LoginUsuarioActivity.this,
                            "Preencha o E-mail",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }//OnCreate

    //Método => Validar Login
    public void validarLogin(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //Abrindo tela principal após login
                    abrirTelaPrincipal();

                }else {
                    //Link https://firebase.google.com/docs/reference/android/com/google/firebase/auth/FirebaseAuth?authuser=0#exceptions_1
                    //Tratando exceção
                    String excecao = "";
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthInvalidUserException e){// Quando o e-mail do usuário não existe ou foi desabilitado
                        excecao = "Usuário não esta cadastrado";
                    }catch (FirebaseAuthInvalidCredentialsException e){//Quando o usuário digita E-mail ou Senha errada
                        excecao = "Senha não correnponde ao Usuário cadastrado";
                    }catch (Exception e){//Exceção Generica
                        excecao = "Erro ao logar Usuário" + e.getMessage();
                        e.printStackTrace();//para printar essa exeção no nosso log
                    }
                    Toast.makeText(LoginUsuarioActivity.this,
                            excecao,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Método => Verifica Usuário Cadastrado
    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioCadastrado();
    }

    //Método => Verifica Usuário Logado
    public void verificarUsuarioCadastrado(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        //autenticacao.signOut(); //Deslogar Usuário =======
        if (autenticacao.getCurrentUser() != null){//Verifica se usuário esta logado
            abrirTelaPrincipal();
        }
    }

    //Método => Abrindo Tela Principal após Login do Usuário
    public void abrirTelaPrincipal(){
        startActivity(new Intent(LoginUsuarioActivity.this, MainActivity.class));
        finish();// finish() para fechar activity de login
    }

    public void setBotaoIrCadastro(View view){
        startActivity(new Intent(LoginUsuarioActivity.this, CadastroUsuarioActivity.class));
    }
}
