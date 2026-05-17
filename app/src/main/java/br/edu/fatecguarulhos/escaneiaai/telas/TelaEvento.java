package br.edu.fatecguarulhos.escaneiaai.telas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.edu.fatecguarulhos.escaneiaai.R;
import br.edu.fatecguarulhos.escaneiaai.adapter.ParticipanteAdapter;
import br.edu.fatecguarulhos.escaneiaai.dao.EventoDao;
import br.edu.fatecguarulhos.escaneiaai.models.Evento;
import br.edu.fatecguarulhos.escaneiaai.models.Participante;
import br.edu.fatecguarulhos.escaneiaai.util.FirebaseCallback;

public class TelaEvento extends AppCompatActivity {
    private TextView txtTituloEvento;
    private FloatingActionButton fabRetorno;
    private Evento evento;
    private RecyclerView rvParticipantes;
    private ParticipanteAdapter adapter;
    private ArrayList<Participante> participantesArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_evento);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inicializarComponentes();
        configurarComponentes();
        inicializarCardView();
    }

    private void inicializarCardView() {
        rvParticipantes.setLayoutManager(new LinearLayoutManager(this));
        participantesArrayList = new ArrayList<>();
        adapter = new ParticipanteAdapter(this, participantesArrayList);
        participantesArrayList.addAll(evento.getParticipantes());
        rvParticipantes.setAdapter(adapter);
    }
    private void inicializarComponentes(){
        Intent it = getIntent();
        evento = new Gson().fromJson(it.getStringExtra("eventoJson"), Evento.class);
        txtTituloEvento = findViewById(R.id.txtTituloEvento_actv_tela_evento);
        fabRetorno = findViewById(R.id.fabQrCode_telaEvento);
        rvParticipantes = findViewById(R.id.rvPresentes);
    }
    private void configurarComponentes(){
        txtTituloEvento.setText(evento.getTitulo());
        fabRetorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaQrCode();
            }
        });
    }
    private void telaQrCode(){
        Intent it  = new Intent(this, TelaQrCode.class);
        // enviar id para uso na criação do qrCode
        String id = evento.getId();
        it.putExtra("id", id);
        startActivity(it);
    }
    // appbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar_exibir_evento, menu);
        return true;
    }


    public void voltar(View view){
        finish();
    }

    public void voltar(MenuItem menuItem){
        finish();
    }
}