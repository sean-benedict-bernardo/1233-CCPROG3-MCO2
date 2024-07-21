package viewer.common;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MyComponents {
    public static JLabel JLabel(String text, int align) {
        JLabel jLabel = new JLabel(text);
        jLabel.setOpaque(true);
        jLabel.setBackground(MyStyles.color.BACKGROUND);
        jLabel.setForeground(MyStyles.color.WHITE);
        jLabel.setFont(MyStyles.font.BODY);
        return jLabel;
    }

    public static JLabel JLabel(String text) {
        return JLabel(text, SwingConstants.CENTER);
    }

    public static JButton JButton(String text) {
        JButton jbutton = new JButton(text);
        jbutton.setBackground(MyStyles.color.WHITE);
        jbutton.setFont(MyStyles.font.BUTTON);
        jbutton.setFocusPainted(false);
        return jbutton;
    }
}
