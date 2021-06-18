package owzi.engine;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class RenderStrategy extends BufferedImage {

    private static Graphics2D strategy;

    private static int width, height;

    public RenderStrategy(int width, int height){
        super(width, height, BufferedImage.TYPE_INT_ARGB);

        strategy = (Graphics2D) getGraphics();

        RenderStrategy.width = getWidth();
        RenderStrategy.height = getHeight();
    }

    public static void add(BufferedImage image, int x, int y, int xoffset, int yoffset, ImageObserver imageObserver){
        strategy.drawImage(image, x, y, xoffset, yoffset, imageObserver);
    }

    public static void add(BufferedImage image, int x, int y, int xoffset, int yoffset, int srcx1, int srcy1, int srcx2, int srcy2, ImageObserver imageObserver){
        strategy.drawImage(image, x, y, xoffset, yoffset, srcx1, srcy1, srcx2, srcy2, imageObserver);
    }

    public void clear(Game game) {
        strategy.setColor(new Color(255, 255, 255));
        strategy.fillRect(0, 0, game.getWidth(), game.getHeight());
    }

    public void render(String currentViewKey, ViewManager viewManager, Graphics2D g2d){

        viewManager.render(currentViewKey, this);

        g2d.drawImage(this, 0, 0, width, height, null);

        this.flush();

    }

    public static void resetConfigurations(){
        strategy.setColor(Color.WHITE);
        strategy.setStroke(new BasicStroke(1));
    }

    public static Graphics2D getStrategy(){
        return strategy;
    }

}
