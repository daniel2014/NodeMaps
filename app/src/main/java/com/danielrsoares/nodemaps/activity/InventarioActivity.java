package com.danielrsoares.nodemaps.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.danielrsoares.nodemaps.R;
import com.danielrsoares.nodemaps.adapter.AdapterInventario;
import com.danielrsoares.nodemaps.config.ConfiguracaoFirebase;
import com.danielrsoares.nodemaps.model.MovInventario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InventarioActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AlertDialog dialog_campoCidades;
    private int int_posicaoArmazenada;
    private int int_posicao_Nova;

    private RecyclerView recyclerView;
    private AdapterInventario adapterInventario;
    private List<MovInventario> movInventarios = new ArrayList<>();

    //private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private DatabaseReference movInventarioRef;
    private ValueEventListener valueEventListenerMovInventario;

    private MovInventario movInventarioCidade = new MovInventario(); //Criando Novo Objeto
    String[] cidadesBrasil = {"Mauá", "Diadema", "São Paulo", "Santo André", "São Caetano do Sul", "São Bernardo do Campo"};
    int posicaoNegativa = -1; // Posição é inicializada '-1' pois o array sempre inicializa com '0'


    @Override
    //Método => onCreate ============================================================================================= Método => onCreate//
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        toolbar = findViewById(R.id.toolbar_Inventario);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sem Filtro"); // Outro modificador de título dinâmico se encontra no bloco FILTRO de CIDADES = Com Dialog
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //============== FILTRO de CIDADES = Com Dialog ======================================================================== FILTRO de CIDADES = Com Dialog//
        AlertDialog.Builder builderDialog_Cidades = new AlertDialog.Builder(this);
        builderDialog_Cidades.setTitle("Selecione a Cidade:");
        builderDialog_Cidades.setCancelable(false);


        builderDialog_Cidades.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // Atualiza os dados da pesquisa de cidades comparando com a posição armazenada(int_posicaoArmazenada)
                // com a posição atual modificada pelo usuário(int_posicao_Nova)
                if (int_posicaoArmazenada == int_posicao_Nova) {
                    getSupportActionBar().setTitle(cidadesBrasil[int_posicao_Nova]); //Altera o Título da ToolBar
                    recuperarMovInventario(); //Atualizar dados
                }

            }
        });
        builderDialog_Cidades.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //vazio
            }
        });


        builderDialog_Cidades.setSingleChoiceItems(cidadesBrasil, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                int_posicao_Nova = i;
                int_posicaoArmazenada = int_posicao_Nova; // Serve para Comparar com a posição Armazenada com a posição atual alterada da cidade
                posicaoNegativa = int_posicaoArmazenada; // com para com a posição posicaoNegativa que é inicializada com (-1) com a int_posicaoArmazenada

            }
        });
        dialog_campoCidades = builderDialog_Cidades.create();
        //---------------------------------------------------------------------------------------------------------------------- FILTRO de CIDADES = Com Dialog//


        //Configurar Adapter
        adapterInventario = new AdapterInventario(movInventarios, this);

        //Configurar RecyclerView
        recyclerView = findViewById(R.id.recyrcler_Inventario);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterInventario);

    } //Método => onCreate ------------------------------------------------------------------------------------------------------ Método => onCreate//


    // Método Recuperando Movimentações do FIREBASE
    public void recuperarMovInventario() {

        movInventarioRef = firebaseRef.child("mov_inventarioNode")
                .child(cidadesBrasil[int_posicao_Nova]); // Adicionado (cidades) para o filtro de Cidades através do Spinner

        valueEventListenerMovInventario = movInventarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                movInventarios.clear(); //Limpando movimentações

                Log.i("Evento", "Evento foi adicionado!");
                for (DataSnapshot dados : dataSnapshot.getChildren()) { //Para recuperar todos os filhos de dataSnapshot
                    Log.i("DADOS_DataSnapshot", "retorno: " + dados.toString());

                    MovInventario movInventario = dados.getValue(MovInventario.class);
                    movInventario.setKey(dados.getKey());//Recupera a chave ID do Item de cada movimentação lá no FireBase
                    movInventarios.add(movInventario); //Criando um Array de List
                    Log.i("dadosRetorno", "dados: " + movInventario.getNode());

                }
                //Método => Notifica o adapterInventario que os dados foram atualizado
                adapterInventario.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.i("CIDADE", "cidade:");

    }


    @Override
    protected void onResume() {
        super.onResume();
        //recuperarMovInventario(); //recuperando movimentações
        //Toast.makeText(InventarioActivity.this, "onResume", Toast.LENGTH_LONG).show();

    }

    // === Recuperando o Resumo no estado onStart ou seja recupera Evento do Listener do método recuperarResumo abaixo ====/
    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(InventarioActivity.this, "onStart", Toast.LENGTH_LONG).show();
        //recuperarResumo();


    }

    @Override
    protected void onPause() {
        super.onPause();
        //Toast.makeText(InventarioActivity.this, "onPause", Toast.LENGTH_LONG).show();

    }

    //Sobreescrever a Classe onStop / ele é chamado sempre que o app não estiver mais sendo utilizado.
    //Removendo Evento do Listener
    @Override
    protected void onStop() {
        super.onStop();
        recuperarMovInventario(); //Atualizar dados
        movInventarioRef.removeEventListener(valueEventListenerMovInventario); //Remove EventListener de Inventario
       // Log.i("Evento", "Evento foi removido!");
    }

    //======== Menu ToolBar ========================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_inventario, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.m_inventario_filtrarCidade: //Filtrar Cidades
                dialog_campoCidades.show(); //Abre Dialog de Cidades
                break;

            case R.id.m_inventario_criarNode: //Criar Novo Node

                if (posicaoNegativa >= 0) { // Posição inicializada com -1 e após o usuário seleionar ex: Cidade; Mauá (0) irá ser true

                    Intent intent = new Intent(getApplicationContext(), InventarioCadastroActivity.class);
                    movInventarioCidade.setCidade(cidadesBrasil[int_posicao_Nova]);
                    intent.putExtra("cidade", movInventarioCidade); // Esse parâmetro "cidade" é um tipo de Array e movInventarioCidade recupera os dados do objeto movInventarioCidade
                    startActivity(intent);

                } else {
                    Toast.makeText(InventarioActivity.this, "Selecione um Filtro antes de Cadastrar", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.m_inventario_criarCidade: //Criar Nova Cidade
                Toast.makeText(this, "Criar Nova Cidade", Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:
                finish(); //Fecha a Tela
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
