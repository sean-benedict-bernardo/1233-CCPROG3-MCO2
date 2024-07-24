package view.common;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * This class contains standard fonts, colors, and
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class MyStyles {
    public class font {
        public static final Font TITLE = new Font("Sans Serif", Font.BOLD, 32);
        public static final Font HEADER = new Font("Sans Serif", Font.PLAIN, 18);
        public static final Font BODY = new Font("Sans Serif", Font.PLAIN, 14);
        public static final Font MONOSPACE = new Font("Ubuntu Mono", Font.PLAIN, 14);
        public static final Font BUTTON = new Font("Sans Serif", Font.BOLD, 16);
    }

    public class color {
        public static final Color BLACK = Color.decode("#121212");
        public static final Color DARKGRAY = Color.decode("#333333");
        public static final Color GRAY = Color.decode("#4E4E4E");
        public static final Color LIGHTGRAY = Color.decode("#C4C4C4");
        public static final Color WHITE = Color.white;

        public static final Color BACKGROUND = BLACK;
        public static final Color FOREGROUND = WHITE;
    }

    public class misc {
        public static final Border BLACK_BORDER = BorderFactory.createLineBorder(color.BLACK, 4);
        public static final Dimension SCREEN_SIZE = new Dimension(
                (int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 3) / 4),
                (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 3) / 4));
    }
}
/**
 * https://coolors.co/palette/121212-4e4e4e-c4c4c4-ffffff-ffb600-04b459-0885d9-b72615
 * 
 * --night: #121212ff;
 * --davys-gray: #4e4e4eff;
 * --silver: #c4c4c4ff;
 * --white: #ffffffff;
 * --selective-yellow: #ffb600ff;
 * --pigment-green: #04b459ff;
 * --bleu-de-france: #0885d9ff;
 * --engineering-orange: #b72615ff;
 */