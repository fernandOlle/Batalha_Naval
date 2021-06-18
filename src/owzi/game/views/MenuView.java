package owzi.game.views;

import owzi.engine.*;

import java.awt.*;
import owzi.game.gui.Button;
import owzi.game.gui.MouseListener;

public class MenuView extends View {

    private Button btnStart;
    private Button btnRank;
    private Button btnExit;

    public MenuView(String name, Game game) {
        super(name, game);

        Font font = FontLoader.load(
                "assets/fonts/Inconsolata-Regular.ttf",
                Font.BOLD,
                20f,
                Font.TRUETYPE_FONT
        );

        btnStart =  new Button(
                "Jogar",
                getGame().getWidth()/2-50,
                getGame().getHeight()/2-24,
                100,
                48
        );
        btnStart.setMouseListener(new MouseListener() {
            @Override
            public void whenLeftPressed() {
                getGame().setCurrentViewKey("Name");
            }
        });
        btnStart.setFont(font);

        btnRank =  new Button(
                "Rank",
                getGame().getWidth()/2-50,
                btnStart.getY2()+20,
                100,
                48
        );
        btnRank.setMouseListener(new MouseListener() {
            @Override
            public void whenLeftPressed() {
                getGame().setCurrentViewKey("Rank");
            }
        });
        btnRank.setFont(font);

        btnExit =  new Button(
                "Sair",
                getGame().getWidth()/2-50,
                btnRank.getY2()+20,
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

        btnStart.render(g2d);
        btnRank.render(g2d);
        btnExit.render(g2d);

        g2d.setColor(new Color(0x000000));
        g2d.drawString("Batalha Naval", 100, 50);
    }

    @Override
    public void update() {
        btnStart.update();
        btnRank.update();
        btnExit.update();
    }
}
