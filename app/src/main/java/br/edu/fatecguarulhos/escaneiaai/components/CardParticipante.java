package br.edu.fatecguarulhos.escaneiaai.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import br.edu.fatecguarulhos.escaneiaai.R;
import br.edu.fatecguarulhos.escaneiaai.models.Participante;

public class CardParticipante extends CardView {
    public CardParticipante(@NonNull Context context) {
        super(context);
        inicializarComponentes(context);
    }

    public void inicializarComponentes(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.card_participante, this, true);

    }
}
