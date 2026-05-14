package br.edu.fatecguarulhos.escaneiaai.models;

import com.google.type.DateTime;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class Evento {
    private String titulo, id;
    private List<Participante> participantes = new ArrayList<>();
    private DateTime dataInicio, dataFim;
    public Evento(String titulo){
        this.titulo = titulo;
    }

    public Evento() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(DateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public DateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(DateTime dataFim) {
        this.dataFim = dataFim;
    }
}
