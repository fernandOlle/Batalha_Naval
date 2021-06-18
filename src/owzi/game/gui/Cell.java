package owzi.game.gui;

import owzi.game.bean.Ship;

import java.awt.*;

public class Cell extends Button {

    private Ship ship;

    public Cell(int x, int y, int width, int height) {
        super("", x, y, width, height);
        setBackgroundColorDefault(new Color(0x888888));
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
    }

    @Override
    public void setBorderColorDefault(Color borderColorDefault) {
        super.setBorderColorDefault(borderColorDefault);
    }

    @Override
    public void setBackgroundColorDefault(Color backgroundColorDefault) {
        super.setBackgroundColorDefault(backgroundColorDefault);
    }

    public boolean wasShot(Board board) {
        setBackgroundColorDefault(new Color(0x0000000));
        this.setEnabled(false);

        if (ship != null) {
            ship.tookAShot();
            if(!ship.isAlive()) board.setShipCount(board.getShipCount() - 1);
            setBackgroundColorDefault(new Color(0xAA00000));
            return true;
        }
        return false;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

}
