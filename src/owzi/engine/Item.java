package owzi.engine;

import java.awt.*;

public abstract class Item {

    private int x, y, width, height;
    private Color borderColor, backgroundColor;

    protected Item(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.borderColor = new Color(0x000000);
        this.backgroundColor = new Color(0xffffff);
    }

    public void render(Graphics2D g2d) {
        g2d.setColor(backgroundColor);
        g2d.fillRect(x+1, y+1, width-1, height-1);
        g2d.setColor(borderColor);
        g2d.drawRect(x, y, width, height);
        RenderStrategy.resetConfigurations();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX2(){
        return x+width;
    }

    public int getY2(){
        return y+height;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


}
