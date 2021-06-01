package com.senai.projeto_eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.senai.projeto_eventos.database.EventoDAO;
import com.senai.projeto_eventos.modelo.Evento;

public class CadastroEventoActivity extends AppCompatActivity {

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("Cadastro de Evento");
        carregarEvento();

    }

    private void carregarEvento(){
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("eventoEdicao") != null) {
            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");
            EditText editTextNome = findViewById(R.id.editText_nome);
            EditText editTextData = findViewById(R.id.editText_date);
            EditText editTextLocal = findViewById(R.id.editTextTextPostalAddress);
            editTextNome.setText(evento.getNome());
            editTextData.setText(String.valueOf(evento.getData()));
            editTextLocal.setText(evento.getLocal());
            id = evento.getId();
        }

    }
    public void onClickVoltar(View v){
        finish();
    }

    public void onClickSalvar(View v){
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextData = findViewById(R.id.editText_date);
        EditText editTextLocal = findViewById(R.id.editTextTextPostalAddress);


        String nome = editTextNome.getText().toString();
        String data = editTextData.getText().toString();
        String local = editTextLocal.getText().toString();

        Evento evento = new Evento(id,nome, data, local);
        EventoDAO eventoDAO = new EventoDAO((getBaseContext()));
        boolean salvou = eventoDAO.salvar(evento);
        if (salvou){
            finish();
        }else {
            Toast.makeText(CadastroEventoActivity.this,"Erro ao salvar", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickExcluir (View v){
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextData = findViewById(R.id.editText_date);
        EditText editTextLocal = findViewById(R.id.editTextTextPostalAddress);


        String nome = editTextNome.getText().toString();
        String data = editTextData.getText().toString();
        String local = editTextLocal.getText().toString();

        Evento evento = new Evento(id,nome, data, local);
        EventoDAO eventoDAO = new EventoDAO((getBaseContext()));
        boolean excluir = eventoDAO.excluir(evento);
        if (excluir){
            finish();
        }else {
            Toast.makeText(CadastroEventoActivity.this,"Erro ao excluir", Toast.LENGTH_LONG).show();
        }

    }
}