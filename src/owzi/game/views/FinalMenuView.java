package owzi.game.views;

import owzi.engine.FontLoader;
import owzi.engine.Game;
import owzi.engine.RenderStrategy;
import owzi.engine.View;
import owzi.game.gui.Button;
import owzi.game.gui.MouseListener;

import java.awt.*;

public class FinalMenuView extends View {

    private Button btnRestart;
    private Button btnReset;
    private Button btnExit;
    private Boolean hasWon;
    private String playerName;

    public FinalMenuView(String name, Game game) {
        super(name, game);
        this.hasWon = hasWon;

        Font font = FontLoader.load(
                "assets/fonts/Inconsolata-Regular.ttf",
                Font.BOLD,
                20f,
                Font.TRUETYPE_FONT
        );

        btnReset =  new owzi.game.gui.Button(
                "Menu Inicial",
                getGame().getWidth()/250,
                getGame().getHeight()/2-24,
                200,
                48
        );
        btnReset.setMouseListener(new MouseListener() {
            @Override
            public void whenLeftPressed() {
                getGame().setCurrentViewKey("Menu");
            }
        });
        btnReset.setFont(font);
        btnRestart =  new owzi.game.gui.Button(
                "Jogar novamente",
                getGame().getWidth()/250,
                btnReset.getY2()+20,
                200,
                48
        );
        btnRestart.setMouseListener(new MouseListener() {
            @Override
            public void whenLeftPressed() {
                getGame().setCurrentViewKey("Game");
            }
        });
        btnRestart.setFont(font);
        btnExit =  new Button(
                "Sair",
                getGame().getWidth()/125,
                btnRestart.getY2()+20,
                100,
                48
        );
        btnExit.setMouseListener(new MouseListener() {
            @Override
            public void whenLeftPressed() {
                getGame().exit(0);
            }
        });
        btnExit.setFont(font);
    }

    @Override
    public void render(RenderStrategy renderStrategy) {
        Graphics2D g2d = renderStrategy.getStrategy();

        btnRestart.render(g2d);
        btnReset.render(g2d);
        btnExit.render(g2d);


        String result = this.hasWon ? "Ganhou" : "Perdeu";

        g2d.setColor(new Color(0x000000));
        g2d.drawString("Fim. " + this.playerName + ", você " + result, 100, 50);
    }

    @Override
    public void update() {
        btnRestart.update();
        btnReset.update();
        btnExit.update();
    }

    public Boolean getHasWon() {
        return hasWon;
    }

    public void setHasWon(Boolean hasWon) {
        this.hasWon = hasWon;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
