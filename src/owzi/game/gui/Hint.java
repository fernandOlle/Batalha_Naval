package owzi.game.gui;

import owzi.engine.FontLoader;
import owzi.engine.Game;
import owzi.engine.RenderStrategy;
import owzi.game.views.GameView;

import java.awt.*;
import java.awt.event.KeyEvent;

import static owzi.engine.GameSystem.getKey;
import static owzi.engine.GameSystem.isSomeKeyPressed;

public class Hint {
    private boolean shipFound = false;
    private boolean wasCellClicked = false;
    private boolean mustRender = false;
    private int renderInterval = 120;
    private int lockInterval = 40;

    private Button btnHint;
    private int usages = 3;

    private GameView gameView;

    public Hint(GameView gameView) {
        this.gameView = gameView;
        Font font = FontLoader.load(
                "assets/fonts/Inconsolata-Regular.ttf",
                Font.BOLD,
                20f,
                Font.TRUETYPE_FONT
        );

        btnHint = new Button("Dica",
                gameView.getGame().getWidth() - 350,
                25,
                100,
                48
        );

        btnHint.setMouseListener(new MouseListener() {
            @Override
            public void whenLeftPressed() {
                if (
                        gameView.getState() == GameView.PLAYERMOVE &&
                                usages > 0
                ) {
                    gameView.setState(GameView.USEHINT);
                    btnHint.setEnabled(false);
                }
            }
        });

        btnHint.setFont(font);
    }

    public void use(EnemyBoard enemyBoard, int clickedColumn, int clickedRow) {
        if (this.usages > 0 && !this.mustRender) {
            this.wasCellClicked = true;
            this.mustRender = true;
            this.usages--;
            for (int col = 0; col < 10; col++) {
                if (enemyBoard.getCell(col, clickedRow).getShip() != null) {
                    System.out.println("Tem Barco");
                    this.shipFound = true;
                    return;
                }
            }
            for (int row = 0; row < 10; row++) {
                if (enemyBoard.getCell(clickedColumn, row).getShip() != null) {
                    System.out.println("Tem Barco");
                    this.shipFound = true;
                    return;
                }
            }
            this.shipFound = false;
        }
    }

    public void render(Graphics2D g2d) {
        this.btnHint.render(g2d);

        if(this.mustRender){
            // boa
            g2d.setColor(this.shipFound ? Color.GREEN : Color.RED);

            g2d.drawString(
                    this.shipFound ? "Aqui tem" : "Aqui não tem",
                    gameView.getGame().getWidth() - 500,
                    25
            );
        }
    }

    public void update() {
        this.btnHint.update();

        if (this.renderInterval == 0) {
            this.renderInterval = 120;
            this.mustRender  = false;
            if(this.usages > 0)
                btnHint.setEnabled(true);
        }
        if (this.mustRender && renderInterval > 0) renderInterval--;

        if (this.lockInterval == 0) {
            this.lockInterval = 40;
            gameView.setState(GameView.PLAYERMOVE);
            this.wasCellClicked = false; // <<
        }

        if (lockInterval > 0 && wasCellClicked && /* <<<< */ gameView.getState() == GameView.USEHINT) lockInterval--;
    }

    public void setUsages(int usages) {
        this.usages = usages;
    }

    public int getUsages() {
        return usages;
    }

    public void reset() {
        this.usages = 3;
        this.btnHint.setEnabled(true);
    }

}
