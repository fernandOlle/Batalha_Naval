package owzi.game.gui;

import owzi.engine.Item;

import java.awt.*;
import java.awt.event.KeyEvent;

import static owzi.engine.GameSystem.getKey;
import static owzi.engine.GameSystem.isSomeKeyPressed;

public class TextBox extends Item {
    private String title;
    private Font font;

    private Color textColor;
    private int interval = 0;
    private int count = 0;
    private String content = "";

    public TextBox(int x, int y, int width, int height, String title) {
        super(x, y, width, height);
        this.title = title;
        this.textColor = new Color(0x000000);
    }

    public void update() {
        if(isSomeKeyPressed() && interval == 0) {
            interval = 7;
            if(getKey() == KeyEvent.VK_BACK_SPACE && count > 0) {
                count --;
                content = content.substring(0, content.length() -1);
            } else if(this.count < 4 && getKey() >= 65 && getKey() <= 90) {
                count ++;
                Character key = (char) getKey();
                content = content + key.toString();
            }
        }
        if(interval > 0) interval--;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(new Color(0x000000));
        super.render(g2d);
        if(font != null)
            g2d.setFont(font);
        g2d.setColor(textColor);
        g2d.drawString(this.title, getX()+200, getY()-20);
        g2d.drawString(this.content, getX()+20, getY()+(getHeight()/2)-5);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFilled() {
        return content.length() == 4;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
}
