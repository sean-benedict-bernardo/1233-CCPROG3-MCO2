package view.common;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * This class contains standard fonts, colors, and other stuff
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class MyStyles {

    public class font {
        public static final Font TITLE = new Font("Sans Serif", Font.BOLD, 32);
        public static final Font SMALLTITLE = new Font("Sans Serif", Font.BOLD, 18);
        public static final Font HEADER = new Font("Sans Serif", Font.PLAIN, 16);
        public static final Font BODY = new Font("Sans Serif", Font.PLAIN, 14);
        public static final Font ITALICS = new Font("Sans Serif", Font.ITALIC, 14);
        public static final Font MONOSPACE = new Font("Ubuntu Mono", Font.PLAIN, 14);
        public static final Font BUTTON = new Font("Sans Serif", Font.BOLD, 16);
    }

    public class color {
        public static final Color BLACK = Color.decode("#121212");
        public static final Color DARKGRAY = Color.decode("#333333");
        public static final Color GRAY = Color.decode("#4E4E4E");
        public static final Color LIGHTGRAY = Color.decode("#C4C4C4");
        public static final Color WHITE = Color.white;

        public static final Color GREEN = Color.decode("#04b459");
        public static final Color RED = Color.decode("#b72615");

        public static final Color BACKGROUND = Color.decode("#1b2a41");
        public static final Color ACCENT = Color.decode("#1b2a41").brighter();
        public static final Color FOREGROUND = WHITE;
    }

    public class misc {
        public static final Border BLACK_BORDER = BorderFactory.createLineBorder(color.BLACK, 4);
        public static final Dimension SCREEN_SIZE = new Dimension(
                (int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 3) / 4),
                (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 3) / 4));
    }
}