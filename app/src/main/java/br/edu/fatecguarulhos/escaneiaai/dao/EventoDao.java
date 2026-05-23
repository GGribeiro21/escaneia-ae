package br.edu.fatecguarulhos.escaneiaai.dao;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.edu.fatecguarulhos.escaneiaai.models.Evento;
import br.edu.fatecguarulhos.escaneiaai.interfaces.FirebaseCallback;

public class EventoDao {
    private FirebaseDatabase database;
    private DatabaseReference ref;
    public EventoDao(){
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    Log.d(TAG, "connected");
                } else {
                    Log.d(TAG, "not connected");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Listener was cancelled");
            }
        });
         ref = FirebaseDatabase.getInstance().getReference("eventos");

        database = FirebaseDatabase.getInstance();

    }
    public void adicionarEvento(Evento e){
        String eventoId = ref.push().getKey();
        e.setId(eventoId);
        ref.child(eventoId).setValue(e)
                .addOnSuccessListener(a -> {
                    //Toast informando sucesso
                })
                .addOnFailureListener(ex->{
                    throw new RuntimeException(ex.getMessage());
                });
    }


    public void getAllEventos(FirebaseCallback callback) {
        ref.orderByChild("momentoInicio").addValueEventListener(new ValueEventListener() {
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
    public void updateEvento(Evento evento){
        ref.child(evento.getId()).setValue(evento);
    }
    public void deleteEvento(String idEvento){
        ref.child(idEvento).setValue(null);
    }


}
