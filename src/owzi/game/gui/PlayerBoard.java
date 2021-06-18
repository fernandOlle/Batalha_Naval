package owzi.game.gui;

import owzi.engine.View;

import java.awt.*;

public class PlayerBoard extends Board{


    public PlayerBoard(View view) {
        super(25, (50*10) + 50, 50, 50, view);
    }

    public void setCellColors() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if(getCell(col, row).getShip() != null)
                    getCell(col, row).setBackgroundColorDisable(new Color(0x0000FF));
            }
        }
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
    }

}
