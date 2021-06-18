package owzi.engine;

import java.awt.*;
import java.io.IOException;

public class FontLoader {

    public static Font load(String pathname, int style, float size, int fontType) {

        Font font = null;

        try {

            font = Font.createFont(
                    fontType,
                    //FontLoader.class.getClassLoader().getResourceAsStream(pathname)
                    Thread.currentThread().getContextClassLoader().getResourceAsStream(pathname)
            )
            .deriveFont(style, size);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

        } catch (IOException | FontFormatException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return font;

    }

}
