package com.ifmg.agendanaee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TelaPrincipal extends AppCompatActivity {
    private Button btnSair;
    private TextView nomeAlunoTxt;
    private ArrayList<Agendamento> agendamentos;
    private ItemListaAgendamentos adapter;
    private ListView listaAgendamentos;
    private String alunoLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        btnSair = (Button) findViewById(R.id.sairBtn);
        nomeAlunoTxt = (TextView) findViewById(R.id.nomeAlunoTxt);
        listaAgendamentos = (ListView) findViewById(R.id.listaAgendamentos);

        Intent intent = getIntent();

        try {
            JSONObject resposta = new JSONObject(intent.getStringExtra("informacao"));
            alunoLogado = resposta.getString("nome");
            nomeAlunoTxt.setText("Olá " + alunoLogado);
            carregaLista();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        acao();

    }

    private void carregaLista() {

        agendamentos = new ArrayList<>();

        RequestQueue pilha = Volley.newRequestQueue(this);

        String url = GlobalVar.urlServidor + "Agendamento";

        StringRequest requisicao = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject resposta = new JSONObject(response);
                    System.out.println(resposta);
                    if (resposta.getInt("cod") == 200) {
                        JSONArray infoJSON = resposta.getJSONArray("informacao");
                        if(infoJSON.length() == 0){
                            Toast.makeText(TelaPrincipal.this, "Você não possui agendamentos", Toast.LENGTH_LONG).show();
                        }else{

                        for (int i = 0; i < infoJSON.length(); i++) {

                            JSONObject obj = infoJSON.getJSONObject(i);

                            Agendamento temp = new Agendamento(obj.getString("nomeServidor"), obj.getString("dataHora"));
                            agendamentos.add(temp);
                        }

                       }
                        adapter = new ItemListaAgendamentos(getApplicationContext(), agendamentos);
                        listaAgendamentos.setAdapter(adapter);
                    } else {
                        //um problema reportado pelo servidor
                        Toast.makeText(TelaPrincipal.this, resposta.getString("informacao"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                    Toast.makeText(TelaPrincipal.this, "Erro no padrão do retorno, contate a equpie de desenvolvimento", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("nome", alunoLogado);
                parametros.put("servico", "consulta");
                return parametros;
            }

        };
        pilha.add(requisicao);
    }

    /*private void trocaTela(String info, String nomeAluno){
        Intent intent = new Intent(TelaPrincipal.this, ItemListaAgendamentos.class);
        intent.putExtra("informacao", info);
        intent.putExtra("nome", nomeAluno);
        startActivity(intent);
    }*/

    private void acao(){
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaPrincipal.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    protected void onActivityResult(int codigoRequest, int codigoResultado, Intent data){
        super.onActivityResult(codigoRequest, codigoResultado, data);

        carregaLista();

    }

}

