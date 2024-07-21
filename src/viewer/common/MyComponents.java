package viewer.common;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MyComponents {
    public static JLabel JLabel(String text, int align) {
        JLabel jLabel = new JLabel(text);
        jLabel.setOpaque(true);
        jLabel.setBackground(CSS.color.BACKGROUND);
        jLabel.setForeground(CSS.color.WHITE);
        jLabel.setFont(CSS.font.BODY);
        return jLabel;
    }

    public static JLabel JLabel(String text){
        return JLabel(text, SwingConstants.CENTER);
    }
}
