package owzi.game.views;

import owzi.engine.Game;
import owzi.engine.RenderStrategy;
import owzi.engine.View;

import java.awt.*;

public class RankView extends View {

    public RankView(String name, Game game ) {
        super(name, game);
    }

    @Override
    public void render(RenderStrategy renderStrategy) {

        Graphics2D g2d = renderStrategy.getStrategy();

    }

    @Override
    public void update() {

    }

}
