package br.edu.fatecguarulhos.escaneiaai.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.edu.fatecguarulhos.escaneiaai.R;
import br.edu.fatecguarulhos.escaneiaai.models.Evento;
import br.edu.fatecguarulhos.escaneiaai.models.Participante;
import br.edu.fatecguarulhos.escaneiaai.telas.TelaEvento;

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
        private TextView txtTitulo, txtAndamento, txtHorario;
        private int andamento;
        private String jsonEvento;
        EventoHelper(View itemView){
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(v.getContext() , TelaEvento.class);
                    it.putExtra("eventoJson", jsonEvento);
                    v.getContext().startActivity(it);
                }
            });
            txtTitulo = itemView.findViewById(R.id.txtTitulo_cardEventoNew);
            txtAndamento = itemView.findViewById(R.id.txtAndamento_cardEventoNew);
            txtHorario = itemView.findViewById(R.id.textHorarioEvento_cardEventoNew);
        }
        void setDetails(Evento evento){
            jsonEvento = new Gson().toJson(evento);
            txtTitulo.setText(evento.getTitulo());
            alterarAndamentoEvento(evento);
            txtHorario.setText(
                    "Inicio: " + evento.getDataInicio()
                            + "\nFim:    " + evento.getDataFim());
        }

        public void alterarAndamentoEvento(Evento evento){
            int momenotInt = momentoEvento(evento);
            andamento = momenotInt;
            if(momenotInt == 0){
                txtAndamento.setText("Em breve");
                txtAndamento.setTextColor(Color.parseColor("#40E0D0"));
            }
            else if(momenotInt == 1){
                txtAndamento.setText("Em andamento!");
                txtAndamento.setTextColor(Color.parseColor("#E4B32E"));
            }
            else if(momenotInt == 2){
                txtAndamento.setText("Encerrado");
                txtAndamento.setTextColor(Color.parseColor("#EF3232"));
            }
            else {
                txtAndamento.setText("Erro");
            }
        }
        public int momentoEvento(Evento evento){
            Calendar momentoInicio, momentoFim, dataHoraAtual;
            momentoInicio = stringToCalendar(evento.getDataInicio());
            momentoFim = stringToCalendar(evento.getDataFim());
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault());
            String formattedDate = df.format(Calendar.getInstance().getTime());
            dataHoraAtual = stringToCalendar(formattedDate);
            if(dataHoraAtual.before(momentoInicio))
                return 0;
            if(dataHoraAtual.after(momentoFim))
                return 2;
            return 1;
        }
        public Calendar stringToCalendar(String dateString) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault());
            try {
                Date date = sdf.parse(dateString);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                return cal;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        public int getAndamento(){
            return andamento;
        }


    }
}
