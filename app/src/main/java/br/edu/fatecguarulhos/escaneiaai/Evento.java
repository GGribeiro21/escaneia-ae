package br.edu.fatecguarulhos.escaneiaai;

public class Evento {
    private String titulo;

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

    @Override
    public String toString() {
        return "Evento{\n" +
                "titulo='" + titulo + '\'' +
                "\n}";
    }
}
