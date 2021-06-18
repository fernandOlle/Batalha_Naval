package owzi.game.views;

import owzi.engine.FontLoader;
import owzi.engine.Game;
import owzi.engine.RenderStrategy;
import owzi.engine.View;
import owzi.game.gui.Button;
import owzi.game.gui.MouseListener;
import owzi.game.gui.TextBox;

import java.awt.*;

public class NameView extends View {

    private TextBox nameTextBox;
    private Button btnNormal;
    private Button btnRandom;

    public NameView(String name, Game game) {
        super(name, game);

        Font font = FontLoader.load(
                "assets/fonts/Inconsolata-Regular.ttf",
                Font.BOLD,
                20f,
                Font.TRUETYPE_FONT
        );

        nameTextBox = new TextBox(
                (int) (getGame().getWidth() * 0.25),
                (int) (getGame().getHeight() * 0.25),
                1000,
                50,
                "Entre seu nome : "
        );

        btnNormal = new Button(
                "Normal Game",
                (int) (getGame().getWidth() * 0.25),
                getGame().getHeight() / 2 - 24,
                150,
                48
        );
        btnNormal.setMouseListener(new MouseListener() {
            @Override
            public void whenLeftPressed() {
                setGameView(nameTextBox.getContent(), false);
            }
        });
        btnNormal.setFont(font);

        btnRandom = new Button(
                "Random Game",
                (int) (getGame().getWidth() * 0.70),
                getGame().getHeight() / 2 - 24,
                150,
                48
        );
        btnRandom.setMouseListener(new MouseListener() {
            @Override
            public void whenLeftPressed() {
               setGameView(nameTextBox.getContent(), true);
            }
        });
        btnRandom.setFont(font);
    }

    @Override
    public void render(RenderStrategy renderStrategy) {
        Graphics2D g2d = renderStrategy.getStrategy();

        nameTextBox.render(g2d);
        btnNormal.render(g2d);
        btnRandom.render(g2d);
    }

    @Override
    public void update() {
        nameTextBox.update();
        btnNormal.update();
        btnRandom.update();
        btnRandom.setEnabled(nameTextBox.isFilled());
        btnNormal.setEnabled(nameTextBox.isFilled());
    }

    private void setGameView(String playerName, boolean isRandom) {
        GameView gv = (GameView) getGame().getViewManager().getView("Game");
        gv.setRandom(isRandom);
        gv.setPlayerName(playerName);
        getGame().setCurrentViewKey("Game");
        //gv.startGame();
    }
}
