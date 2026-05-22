package br.edu.fatecguarulhos.escaneiaai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.edu.fatecguarulhos.escaneiaai.R;
import br.edu.fatecguarulhos.escaneiaai.models.Evento;
import br.edu.fatecguarulhos.escaneiaai.models.Participante;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventoHelper> {

    private Context context;
    private ArrayList<Evento> eventos;

    public EventoAdapter(Context context, ArrayList<Evento> eventos) {
        this.context = context;
        this.eventos = eventos;
    }

    @NonNull
    @Override
    public EventoHelper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_evento_new, parent, false);
        return new EventoHelper(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoHelper holder, int position) {
        Evento evento = eventos.get(position);
        holder.setDetails(evento);
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    class EventoHelper extends RecyclerView.ViewHolder{
        private TextView txtNome, txtEmail, txtRa, txtEntrada;
        EventoHelper(View itemView){
            super(itemView);
        }
        void setDetails(Evento evento){
        }
    }
}
