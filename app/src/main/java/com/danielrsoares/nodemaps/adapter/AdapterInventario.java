package com.danielrsoares.nodemaps.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.danielrsoares.nodemaps.R;
import com.danielrsoares.nodemaps.activity.InventarioActivity;
import com.danielrsoares.nodemaps.model.MovInventario;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class AdapterInventario extends RecyclerView.Adapter<AdapterInventario.MyViewHolder> {

    int posicao;
    private final List<MovInventario> movInventarios;
    private final Context context;
    private int quantidadeViewCriada = 0;

    public AdapterInventario(List<MovInventario> movInventarios, Context context) {
        this.movInventarios = movInventarios;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        quantidadeViewCriada++;
        View itemLista = LayoutInflater.from(context).inflate(R.layout.adapter_inventario, parent, false);
        Log.i("QQ", "View Criada: " + quantidadeViewCriada);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MovInventario movInventario = movInventarios.get(position);
        holder.vincula(movInventario);
        getItemId(position);
    }

    @Override
    public int getItemCount() {
        return movInventarios.size(); //Vamos descobrir a quantidade de Itens que temos em movimentação
    }


    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
    @Override
    public long getItemId(int position) {
      posicao = position;
        Log.i("Posicao", "posicao: " + position);
        return super.getItemId(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView node, bairro, endereco, numero; //valor, categoria;

        public MyViewHolder(View itemView) {
            super(itemView);

            node = itemView.findViewById(R.id.txtAdapter_Node);
            bairro = itemView.findViewById(R.id.txtAdapter_Bairro);
            endereco = itemView.findViewById(R.id.txtAdapter_Endereco);
            numero = itemView.findViewById(R.id.txtAdapter_Numero);
        }

        public void vincula(MovInventario movInventario){
            node.setText("NODE: " + movInventario.getNode());
            bairro.setText("Bairro: " + movInventario.getBairro());
            endereco.setText("End: " + movInventario.getEndereco());
            numero.setText("N." + movInventario.getNumero());
        }

    }
/*
    InventarioActivity inventarioActivity = new InventarioActivity();
    public void adicionaNovoNode(MovInventario movInventario){
        movInventario.salvarFireBase();
        inventarioActivity.recuperaNodeAdicionado();
        //notifyDataSetChanged();
    }*/




}
