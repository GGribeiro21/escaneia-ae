package br.edu.fatecguarulhos.escaneiaai.components;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import br.edu.fatecguarulhos.escaneiaai.models.Evento;
import br.edu.fatecguarulhos.escaneiaai.R;
import br.edu.fatecguarulhos.escaneiaai.telas.TelaEvento;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CardEvento extends ConstraintLayout {
    public CardEvento(Context context){
        super(context);
        inicializarComponentes(context);
    }
    public CardEvento(Context context, @Nullable AttributeSet attrs){
        super(context,attrs);
        inicializarComponentes(context);
    }
    private void inicializarComponentes(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.card_evento_old, this, true);
    }
}
