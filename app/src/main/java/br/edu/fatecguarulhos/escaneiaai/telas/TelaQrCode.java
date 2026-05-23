package br.edu.fatecguarulhos.escaneiaai.telas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dantsu.escposprinter.textparser.PrinterTextParserImg;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.edu.fatecguarulhos.escaneiaai.R;
import br.edu.fatecguarulhos.escaneiaai.util.ImpressoraTermica;
import br.edu.fatecguarulhos.escaneiaai.util.QrCodeManager;

public class TelaQrCode extends AppCompatActivity {
    private TextView txtTituloEvento, txtTipoQC;
    private Button btnQCEntrada, btnQCSaida;
    private String idEvento, tituloEvento;
    private ImageView imgQrCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_qr_code);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inicializarValores();
        inicializarComponentes();
        configurarComponentes();
    }

    private void inicializarValores() {
        try{
            Intent it =  getIntent();
            idEvento = it.getStringExtra("id");
            tituloEvento = it.getStringExtra("titulo");
        } catch (RuntimeException re){
            Toast.makeText(this, re.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void inicializarComponentes(){
        txtTituloEvento = findViewById(R.id.txtTituloEvento_telaQrCode);
        txtTipoQC = findViewById(R.id.txtTipoQrCode_telaQrCode);
        imgQrCode = findViewById(R.id.imgQrCode_telaQrCode);
        btnQCEntrada = findViewById(R.id.btnQrCodeEntrada_telaQrCode);
        btnQCSaida = findViewById(R.id.btnQrCodeSaida_telaQrCode);
    }
    private void configurarComponentes(){
        txtTituloEvento.setText(tituloEvento);
        mostrarQrCodeEntrada();
        btnQCEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarQrCodeEntrada();
            }
        });
        btnQCSaida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarQrCodeSaida();
            }
        });
    }

    private void mostrarQrCodeSaida(){
        txtTipoQC.setText("QrCode de Saida");
        Bitmap qrCode = QrCodeManager.gerarQrCode(idEvento + "/type=saida");
        imgQrCode.setImageBitmap(qrCode);
    }
    private void mostrarQrCodeEntrada(){
        txtTipoQC.setText("QrCode de Entrada");
        Bitmap qrCode = QrCodeManager.gerarQrCode(idEvento + "/type=entrada");
        imgQrCode.setImageBitmap(qrCode);
    }
    public void imprimirQrCodeEntrada(View view){
        try {
            Toast.makeText(this, "Imprimindo...", Toast.LENGTH_SHORT).show();
            ImpressoraTermica impressora = new ImpressoraTermica(this, this);
            String texto =
                    "[C]<b><font size='big'>Escaneia Ae</font></b>\n \n" +
                            "[C]"+ tituloEvento +"\n" +
                            "[L]Entrada do evento\n"
                            +"[C]<img>" + PrinterTextParserImg.bitmapToHexadecimalString(impressora.getImpressora(),  QrCodeManager.gerarQrCode(idEvento + "/type=entrada"))+"</img>\n\n"
                            +"[L]Impresso em: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) + "\n";
            impressora.imprimir(texto);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void imprimirQrCodeSaida(View view){
        try {
            Toast.makeText(this, "Imprimindo...", Toast.LENGTH_SHORT).show();
            ImpressoraTermica impressora = new ImpressoraTermica(this, this);
            String texto =
                    "[C]<b><font size='big'>Escaneia Ae</font></b>\n \n" +
                            "[C]"+ tituloEvento +"\n" +
                            "[L]Saida do evento\n"
                            +"[C]<img>" + PrinterTextParserImg.bitmapToHexadecimalString(impressora.getImpressora(),  QrCodeManager.gerarQrCode(idEvento + "/type=saida"))+"</img>\n\n"+
                            "[L]Impresso em: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) + "\n";
            impressora.imprimir(texto);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void voltar(View view){
        finish();
    }
}