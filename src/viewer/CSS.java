package viewer;

import java.awt.*;

public class CSS {
    public class font {
        public static final Font SANSERIF = (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
                ? Font.decode("Calibri")
                : Font.decode(Font.SANS_SERIF);
        public static final Font COMICSANS = Font.decode("Comic Sans MS");
    }

    public class color {
        public static final Color BLACK = Color.decode("#121212");
        public static final Color GRAY = Color.decode("#4E4E4E");
        public static final Color LIGHTGRAY = Color.decode("#C4C4C4");
        public static final Color WHITE = Color.white;

        public static final Color BACKGROUND = Color.decode("#121212");
        public static final Color FOREGROUND = Color.decode("#054A91");
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