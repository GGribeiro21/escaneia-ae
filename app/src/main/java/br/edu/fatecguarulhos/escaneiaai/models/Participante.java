package br.edu.fatecguarulhos.escaneiaai.models;

public class Participante {
    private String nome, email, ra;
    private boolean saida;
    public Participante() {
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isSaida() {
        return saida;
    }

    public void setSaida(boolean saida) {
        this.saida = saida;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }
}
