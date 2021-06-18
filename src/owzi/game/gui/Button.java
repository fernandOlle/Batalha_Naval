package owzi.game.gui;

import owzi.engine.GameSystem;
import owzi.engine.Item;

import java.awt.*;

public class Button extends Item {

    private String text;
    private int textX, textY;
    private Font font;
    private Color textColor;
    private Color borderColorDefault, backgroundColorDefault, textColorDefault;
    private Color borderColorDisable, backgroundColorDisable, textColorDisable;
    private Color borderColorMouseEntered, backgroundColorMouseEntered, textColorMouseEntered;

    private MouseListener mouseListener;

    private boolean enabled = true;

    public Button(String text, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.text = text;
        this.textX = 15;
        this.textY = 15;

        this.textColorDefault = new Color(0x000000);
        this.textColorDisable = new Color(0x555555);
        this.textColorMouseEntered = new Color(0xff0000);
        this.textColor = textColorDefault;

        this.borderColorDefault = new Color(0x000000);
        this.borderColorDisable = new Color(0x555555);
        this.borderColorMouseEntered = new Color(0xff0000);
        setBorderColor(borderColorDefault);

        this.backgroundColorDefault = new Color(0xffffff);
        this.backgroundColorDisable = new Color(0xdddddd);
        this.backgroundColorMouseEntered = new Color(0x000000);
        setBackgroundColor(backgroundColorDefault);
    }

    public void update() {
        if (wasLeftClicked() && mouseListener != null && enabled) {
            mouseListener.whenLeftPressed();
        }
        if (wasRightClicked() && mouseListener != null && enabled) {
            mouseListener.whenRightPressed();
        }

        if (enabled) {
            if (!isMouseEntered()) {
                this.textColor = textColorDefault;
                setBackgroundColor(backgroundColorDefault);
                setBorderColor(borderColorDefault);
            } else {
                this.textColor = textColorMouseEntered;
                setBackgroundColor(backgroundColorMouseEntered);
                setBorderColor(borderColorMouseEntered);
            }
        } else {
            this.textColor = textColorDisable;
            setBackgroundColor(backgroundColorDisable);
            setBorderColor(borderColorDisable);
        }
    }

    public void render(Graphics2D g2d) {
        super.render(g2d);
        if (text != null && !text.equals("") && font != null) {
            g2d.setColor(textColor);
            g2d.setFont(font);
            g2d.drawString(text, getX() + textX, getY() + textY);
        }
    }

    private boolean wasLeftClicked() {
        if (GameSystem.isLeftButtonPressed()) {
            return isMouseEntered();
        }
        return false;
    }

    private boolean wasRightClicked() {
        if (GameSystem.isRightButtonPressed()) {
            return isMouseEntered();
        }
        return false;
    }

    private boolean isMouseEntered() {
        int mx = GameSystem.getMouseX();
        int my = GameSystem.getMouseY();
        if (mx > getX() && mx < getX() + getWidth() && my > getY() && my < getY() + getHeight()) {
            return true;
        }
        return false;
    }

    public void setMouseListener(MouseListener mouseListener) {
        this.mouseListener = mouseListener;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTextX() {
        return textX;
    }

    public void setTextX(int textX) {
        this.textX = textX;
    }

    public int getTextY() {
        return textY;
    }

    public void setTextY(int textY) {
        this.textY = textY;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getBorderColorDefault() {
        return borderColorDefault;
    }

    public void setBorderColorDefault(Color borderColorDefault) {
        this.borderColorDefault = borderColorDefault;
    }

    public Color getBackgroundColorDefault() {
        return backgroundColorDefault;
    }

    public void setBackgroundColorDefault(Color backgroundColorDefault) {
        this.backgroundColorDefault = backgroundColorDefault;
    }

    public Color getTextColorDefault() {
        return textColorDefault;
    }

    public void setTextColorDefault(Color textColorDefault) {
        this.textColorDefault = textColorDefault;
    }

    public Color getBorderColorDisable() {
        return borderColorDisable;
    }

    public void setBorderColorDisable(Color borderColorDisable) {
        this.borderColorDisable = borderColorDisable;
    }

    public Color getBackgroundColorDisable() {
        return backgroundColorDisable;
    }

    public void setBackgroundColorDisable(Color backgroundColorDisable) {
        this.backgroundColorDisable = backgroundColorDisable;
    }

    public Color getTextColorDisable() {
        return textColorDisable;
    }

    public void setTextColorDisable(Color textColorDisable) {
        this.textColorDisable = textColorDisable;
    }

    public Color getBorderColorMouseEntered() {
        return borderColorMouseEntered;
    }

    public void setBorderColorMouseEntered(Color borderColorMouseEntered) {
        this.borderColorMouseEntered = borderColorMouseEntered;
    }

    public Color getBackgroundColorMouseEntered() {
        return backgroundColorMouseEntered;
    }

    public void setBackgroundColorMouseEntered(Color backgroundColorMouseEntered) {
        this.backgroundColorMouseEntered = backgroundColorMouseEntered;
    }

    public Color getTextColorMouseEntered() {
        return textColorMouseEntered;
    }

    public void setTextColorMouseEntered(Color textColorMouseEntered) {
        this.textColorMouseEntered = textColorMouseEntered;
    }
}
