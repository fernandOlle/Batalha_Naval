package owzi.game.gui;

import owzi.engine.GameSystem;
import owzi.engine.View;
import owzi.game.bean.Ship;

import java.awt.*;
import java.util.Random;

public class EnemyBoard extends Board {

    private Random random = new Random();


    public EnemyBoard(View view) {
        super(25, 25, 50, 50, view);
        setIsPlayer(false);
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

    public void reset() {
        // aqui organiza o tabuleiro do oponente
        // =====================


    }
}
