package owzi.game.bean;

import java.io.Serializable;

public class Player implements Serializable, Comparable<Player> {
    private static final long serialVersionUID = 1L;

    private String nome;
    private int pontuacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Nome: " + this.nome + " Pontuacao: " + this.pontuacao);
        return str.toString();
    }

    @Override
    public int compareTo(Player jog) {
        int compararPontos = ((Player) jog).getPontuacao();
        return compararPontos - this.pontuacao;
    }
}
