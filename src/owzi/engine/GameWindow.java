package owzi.engine;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame{

    private GraphicsDevice[] gd;
    private Game game;
    private boolean fullscreen;

    public GameWindow(Game game, boolean fullscreen){
        this.game = game;
        this.fullscreen = fullscreen;
        if(fullscreen)
            init(0, 0);
        else
            init(700, 500);
    }

    public GameWindow(Game game, int width, int height){
        this.game = game;
        this.fullscreen = false;
        init(width, height);
    }

    private void init(int width, int height){

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        this.setUndecorated(fullscreen);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);

        if(!fullscreen){
            this.game.setBounds(0, 0, width, height);
        }
        this.add(game);

        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.requestFocus();

        if(fullscreen){
            gd = ge.getScreenDevices();
            gd[0].setFullScreenWindow(this);
        }

        game.init();

    }



}