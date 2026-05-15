package br.edu.fatecguarulhos.escaneiaai.util;

import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.edu.fatecguarulhos.escaneiaai.models.Evento;
import br.edu.fatecguarulhos.escaneiaai.models.Participante;

public class DbManager {
    private View v;
    private FirebaseDatabase database;
    public DbManager(){
        database = FirebaseDatabase.getInstance();
    }
    public void adicionarEvento(Evento e){
        DatabaseReference myRef = database.getReference("eventos");
        String eventoId = myRef.push().getKey();
        e.setId(eventoId);
        myRef.child(eventoId).setValue(e);
    }


    public void getAllEventos(FirebaseCallback callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("eventos");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Evento> listaEventos = new ArrayList<Evento>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Evento evento = postSnapshot.getValue(Evento.class);
                    listaEventos.add(evento);
                }
                try{
                    callback.onCallbackForAll(listaEventos);
                } catch (NullPointerException npe){
                    System.out.println("Erro -> " + npe.getMessage());
                } catch (Exception e){
                    System.out.println("Erro -> " + e.getMessage());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Firebase", "Falha na leitura.", databaseError.toException());
            }
        });
    }

    public void getEventoById(String id, FirebaseCallback callback){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("eventos");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Evento e = null;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Evento evento = postSnapshot.getValue(Evento.class);
                    if(evento.getId().equals(id))
                        e = evento;
                }
                callback.onCallBackByid(e);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Firebase", "Falha na leitura.", databaseError.toException());
            }
        });
    }
    public void registrarEntradaParticipante(Evento evento, Participante participante) {
        DatabaseReference myRef = database.getReference("eventos").child(evento.getId());
        List<Participante> participantes = evento.getParticipantes();
        participantes.add(participante);
        evento.setParticipantes(participantes);
        myRef.setValue(evento);

    }
    public void registrarSaidaParticipante(Evento evento, Participante participante){
        DatabaseReference myRef = database.getReference("eventos").child(evento.getId());
        List<Participante> lista = evento.getParticipantes();
        for(int i = 0; i < lista.size(); i++){
            Participante p = lista.get(i);
                if(
                        p.getNome().equals(participante.getNome())
                                && p.getEmail().equals(participante.getEmail())
                                && p.getRa().equals(participante.getRa())
                ) {
                    lista.get(i).setSaida(true);
                }
            }
        evento.setParticipantes(lista);
        myRef.setValue(evento);
        }
    }



