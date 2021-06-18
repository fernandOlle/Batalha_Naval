package owzi.game.bean;

public class Ship {

    public static final int PORTA = 4;
    public static final int ESCOLTA = 3;
    public static final int SUB = 2;
    public static final int CACA = 1;

    private int size;
    private int type;
    private int health;
    private boolean vertical = true;

    public Ship(int size, int type, boolean vertical) {
        this.size = size;
        this.type = type;
        this.vertical = vertical;
        health = size;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void tookAShot() {
        this.health--;
    }

    public boolean isAlive() {
        return this.health > 0;
    }
}
