package owzi.game.views;

import owzi.engine.FontLoader;
import owzi.engine.Game;
import owzi.engine.RenderStrategy;
import owzi.engine.View;
import owzi.game.bean.Player;
import owzi.game.gui.Button;
import owzi.game.gui.MouseListener;
import owzi.game.util.Score;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class RankView extends View {

    private Button btnReturn;

    public RankView(String name, Game game ) {
        super(name, game);

        Font font = FontLoader.load(
                "assets/fonts/Inconsolata-Regular.ttf",
                Font.BOLD,
                20f,
                Font.TRUETYPE_FONT
        );

        btnReturn =  new Button(
                "Menu",
                getGame().getWidth()-300,
                getGame().getHeight()/2-24,
                100,
                48
        );
        btnReturn.setMouseListener(new MouseListener() {
            @Override
            public void whenLeftPressed() {
                getGame().setCurrentViewKey("Menu");
            }
        });
        btnReturn.setFont(font);
    }

    @Override
    public void render(RenderStrategy renderStrategy) {

        Graphics2D g2d = renderStrategy.getStrategy();


        btnReturn.render(g2d);


        String rank = buildRank();
        g2d.setColor(new Color(0x000000));
        g2d.drawString(rank, 100, 50);

    }

    @Override
    public void update() {
        btnReturn.update();
    }

    public String buildRank () {
        ArrayList<Player> players =  Score.getInstance().loadPlayers();

        try {
            verifyList(players);

            Collections.sort(players);

            StringBuilder ranking = new StringBuilder();

            for (Player player : players){
                ranking.append(player.getNome() + " 		" + player.getPontuacao() + "\n");
            }

            String rankingFinal = ranking.toString();

            return rankingFinal;
        } catch (IOException e){
            System.out.println(e.getMessage());
            return "O rank esta vazio";
        }
    }

    public void verifyList(ArrayList<Player> players) throws IOException{
        if (players == null || players.isEmpty()){
            throw new IOException("O rank está vazio!");
        }
    }

}
