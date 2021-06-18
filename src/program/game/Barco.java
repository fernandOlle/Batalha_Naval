package program.game;

public class Barco {

    private int tipo;
    private boolean vertical = true;
    private int vida;

    public Barco(int tipo, boolean vertical) {
        this.tipo = tipo;
        this.vertical = vertical;
        vida = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public boolean getVertical(){
        return this.vertical;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void levouTiro() {
        this.vida--;
    }

    public boolean estaVivo() {
        return this.getVida() > 0;
    }
}
