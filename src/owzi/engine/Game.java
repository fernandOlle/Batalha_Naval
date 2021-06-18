package owzi.engine;

import java.awt.*;
import java.awt.image.BufferStrategy;

public abstract class Game extends Canvas {

    private BufferStrategy bufferStrategy;
    private Gameloop gameloop;
    private RenderStrategy renderStrategy;
    private ViewManager viewManager;

    private int viewChangeInterval;
    private String currentViewKey;

    public Game() {
        viewChangeInterval = 0;
        viewManager = new ViewManager();
        gameloop = new Gameloop(this);
    }

    public void init() {
        this.setIgnoreRepaint(true);
        this.createGame();
        renderStrategy = new RenderStrategy(this.getWidth(), this.getHeight());
        gameloop.start();
    }

    protected abstract void createGame();

    public void update() {
        viewManager.update(currentViewKey);
    }


    public void render(){

        if(bufferStrategy == null){
            createBufferStrategy(3);
        }

        bufferStrategy = getBufferStrategy();
        Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();

        if(viewChangeInterval == 0) {
            renderStrategy.clear(this);
            renderStrategy.render(currentViewKey, viewManager, g2d);
        }

        g2d.dispose();
        if(!bufferStrategy.contentsLost()) bufferStrategy.show();

    }

    public ViewManager getViewManager(){
        return viewManager;
    }

    public String getCurrentViewKey() {
        return currentViewKey;
    }

    public void setCurrentViewKey(String currentViewKey) {
        this.currentViewKey = currentViewKey;
    }

    public void exit(int status) {
        gameloop.close();
        System.exit(status);
    }

}
