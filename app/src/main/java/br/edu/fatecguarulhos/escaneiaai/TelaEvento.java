package br.edu.fatecguarulhos.escaneiaai;

import android.content.Intent;
import android.graphics.Bitmap;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarMenu;
import com.google.android.material.navigation.NavigationBarView;

import br.edu.fatecguarulhos.escaneiaai.util.QrCodeManager;

public class TelaEvento extends AppCompatActivity {
    private TextView txtTituloEvento;
    private FloatingActionButton fabRetorno;
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
        Intent it = getIntent();
        txtTituloEvento = findViewById(R.id.txtTituloEvento_actv_tela_evento);
        String str = it.getStringExtra("titulo");
        txtTituloEvento.setText(it.getStringExtra("titulo"));
        //inicializarValores();
    }
    private void imprimirQrCode(View view){
        Bitmap qrCode = QrCodeManager.gerarQrCode("identificacao");

    }

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