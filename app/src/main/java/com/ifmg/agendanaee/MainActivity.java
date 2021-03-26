package com.ifmg.agendanaee;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText txtEmail, txtSenha;
    private Button btnEntrar;
    private ArrayList<Aluno> alunos;
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = (EditText) findViewById(R.id.emailTxt);
        txtSenha = (EditText) findViewById(R.id.senhaTxt);
        btnEntrar = (Button) findViewById(R.id.entrarBtn);
        acao();
    }
    private void acao(){
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    private void login() {

        alunos = new ArrayList<>();

        mQueue = Volley.newRequestQueue(this);
        String url = GlobalVar.urlServidor + "Aluno";
        RequestQueue fila = Volley.newRequestQueue(this);

        StringRequest requisicao = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject resposta = new JSONObject(response);

                    if (resposta.getInt("cod") == 200) {

                        //System.out.println(resposta.getString("informacao"));
                        trocaTela(resposta.getString("informacao"));

                        //Toast.makeText(MainActivity.this, resposta.getString("informacao"), Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(MainActivity.this, resposta.getString("informacao"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Erro no padrão do retorno, contate a equipe de desenvolvimento", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Verifique a sua conexão e tente novamente...", Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> parametros = new HashMap<>();
                parametros.put("servico", "login");
                parametros.put("email", txtEmail.getText().toString());
                parametros.put("senha", txtSenha.getText().toString());

                return parametros;
            }
        };

        fila.add(requisicao);

    }
    private void trocaTela(String info){
        Intent intent = new Intent(MainActivity.this, TelaPrincipal.class);
        intent.putExtra("informacao", info);
        startActivity(intent);
    }


}