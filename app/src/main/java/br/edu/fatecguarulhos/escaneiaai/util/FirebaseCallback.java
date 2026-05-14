package br.edu.fatecguarulhos.escaneiaai.util;

import java.util.List;

import br.edu.fatecguarulhos.escaneiaai.models.Evento;

public interface FirebaseCallback {
    void onCallback(List<Evento> lista);
}
