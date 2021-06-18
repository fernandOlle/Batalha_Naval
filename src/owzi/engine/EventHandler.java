package owzi.engine;

import javax.swing.*;
import java.awt.event.*;

public class EventHandler {

    private Game game;

    public EventHandler(Game game) {
        this.game = game;
        setKeyEvents();
        setMouseEvents();
    }

    public void update() {

    }

    public void setKeyEvents(){
        game.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                GameSystem.setSomeKeyPressed(true);
                GameSystem.setKey(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                GameSystem.setSomeKeyPressed(false);
                GameSystem.setKey(0);
            }
        });
    }

    public void setMouseEvents(){
        game.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                GameSystem.setMouseX(e.getX());
                GameSystem.setMouseY(e.getY());
            }

            @Override
            public void mouseDragged(MouseEvent e){
                GameSystem.setMouseX(e.getX());
                GameSystem.setMouseY(e.getY());
            }
        });

        game.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    GameSystem.setLeftButtonPressed(true);
                    GameSystem.setMouseXPressed(e.getX());
                    GameSystem.setMouseYPressed(e.getY());
                }
                if(SwingUtilities.isRightMouseButton(e)) GameSystem.setRightButtonPressed(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)) GameSystem.setLeftButtonPressed(false);
                if(SwingUtilities.isRightMouseButton(e)) GameSystem.setRightButtonPressed(false);
            }

        });
    }

}
