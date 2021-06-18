package owzi.engine;

import java.awt.*;

public interface IConfig {

    int MONITOR_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    int MONITOR_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    int MONITOR_CENTER_X = MONITOR_WIDTH/2;
    int MONITOR_CENTER_Y = MONITOR_HEIGHT/2;

    int WIDTH = 700;
    int HEIGHT = 500;

}
