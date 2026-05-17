package br.edu.fatecguarulhos.escaneiaai.interfaces;

import java.util.List;

import br.edu.fatecguarulhos.escaneiaai.models.Evento;

public interface FirebaseCallback {
    void onCallbackForAll(List<Evento> lista);
    void onCallBackByid(Evento e);
}
