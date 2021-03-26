package com.ifmg.agendanaee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemListaAgendamentos extends ArrayAdapter<Agendamento> {

    private Context contextoPai;
    private ArrayList<Agendamento> agendamentos;

    private static class ViewHolder {
        private TextView nomeServidor;
        private TextView dataHora;
    }

    public ItemListaAgendamentos(Context contexto, ArrayList<Agendamento> dados) {
        super(contexto, R.layout.activity_item_lista_agendamentos, dados);
        this.contextoPai = contexto;
        this.agendamentos = dados;
    }

    @NonNull
    @Override
    public View getView(int indice, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(indice, convertView, parent);

        Agendamento agendamentoAtual = agendamentos.get(indice);
        ViewHolder novaView;

        final  View resultado;

        //1 º = lista sendo montada pela primeira vez
        if (convertView == null){
            novaView = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_item_lista_agendamentos, parent, false);

            //linkando componentes xml
            novaView.nomeServidor = (TextView) convertView.findViewById(R.id.nomeServidor);
            novaView.dataHora = (TextView) convertView.findViewById(R.id.dataHora);

            resultado = convertView;
            convertView.setTag(novaView);
        }else{
            //2º = item modificado
            novaView = (ViewHolder) convertView.getTag();
            resultado = convertView;
        }

        //seta os valores de cada campo
        novaView.nomeServidor.setText(agendamentoAtual.getServidor());
        novaView.dataHora.setText(agendamentoAtual.getDataHora());

        return resultado;
    }

}
