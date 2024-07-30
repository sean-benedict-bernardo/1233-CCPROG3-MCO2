package view.common.components;

import java.awt.Font;

import javax.swing.*;

import view.common.MyStyles;

/**
 * MyComponents is a collection of standardized
 * JComponents for better styling
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class MyComponents {
    public static final int ITALICS = 1;
    public static final int BOLD = 2;

    /**
     * Custom JLabel as title component
     * 
     * @param text to be put inside title
     * @return JLabel title
     */
    public static JLabel titleText(String text) {
        JLabel jLabel = new JLabel(text, SwingConstants.CENTER);
        jLabel.setOpaque(true);
        jLabel.setBackground(MyStyles.color.BACKGROUND);
        jLabel.setForeground(MyStyles.color.FOREGROUND);
        jLabel.setFont(MyStyles.font.TITLE);
        return jLabel;
    }

    /**
     * 
     * @param text
     * @param align
     * @return JLabel of titel
     */
    public static JLabel smallTitleText(String text) {
        JLabel jLabel = new JLabel(text, SwingConstants.CENTER);
        jLabel.setOpaque(true);
        jLabel.setBackground(MyStyles.color.BACKGROUND);
        jLabel.setForeground(MyStyles.color.FOREGROUND);
        jLabel.setFont(MyStyles.font.SMALLTITLE);
        return jLabel;
    }

    /**
     * Custom JLabel as header component with custom alignment
     * 
     * @param text  to be put inside header
     * @param align SwingConstant of alignment
     * @return JLabel header
     */
    public static JLabel headerText(String text, int align) {
        JLabel jLabel = new JLabel(text, align);
        jLabel.setOpaque(true);
        jLabel.setBackground(MyStyles.color.BACKGROUND);
        jLabel.setForeground(MyStyles.color.FOREGROUND);
        jLabel.setFont(MyStyles.font.HEADER);
        return jLabel;
    }

    /**
     * Custom JLabel as header component
     * 
     * @param text to be put inside header
     * @return JLabel header
     */
    public static JLabel headerText(String text) {
        return headerText(text, SwingConstants.LEFT);
    }

    /**
     * Empty custom JLabel header component
     * 
     * @return JLabel header
     */
    public static JLabel headerText() {
        return headerText("", SwingConstants.LEFT);
    }

    /**
     * Custom JLabel as body component with custom alignment
     * 
     * @param align     SwingConstants of text alignment
     * @param text      to be put inside body
     * @param fontStyle integer as by the MyComponents constants
     * @return JLabel body
     */
    public static JLabel bodyText(String text, int align, int fontStyle) {
        JLabel jLabel = new JLabel(text, align);
        jLabel.setOpaque(true);
        jLabel.setBackground(MyStyles.color.BACKGROUND);
        jLabel.setForeground(MyStyles.color.FOREGROUND);

        switch (fontStyle) {
            case MyComponents.ITALICS:
                jLabel.setFont(MyStyles.font.ITALICS);
                break;
            case MyComponents.BOLD:
                jLabel.setFont(new Font("Sans Serif", Font.BOLD, 14));
                break;
            default:
                jLabel.setFont(MyStyles.font.BODY);
                break;
        }
        return jLabel;
    }

    /**
     * Custom JLabel as body component
     * 
     * @param text to be put inside body
     * @return JLabel body
     */
    public static JLabel bodyText(String text, int align) {
        return bodyText(text, align, 0);
    }

    /**
     * Custom JLabel with default alignment and style
     * 
     * @param text to be put inside body
     * @return JLabel body
     */
    public static JLabel bodyText(String text) {
        return bodyText(text, SwingConstants.CENTER, 0);
    }

    /**
     * Blank custom JLabel as body component
     * 
     * @param text to be put inside body
     * @return JLabel body
     */
    public static JLabel bodyText() {
        return bodyText("", SwingConstants.CENTER, 0);
    }

    /**
     * Custom JButton
     * 
     * @param text to be put inside button
     * @return JButton
     */
    public static JButton button(String text) {
        JButton jbutton = new JButton(text);
        jbutton.setBackground(MyStyles.color.FOREGROUND);
        jbutton.setFont(MyStyles.font.BUTTON);
        jbutton.setFocusPainted(false);
        return jbutton;
    }

    /**
     * Custom JButton but with smaller text
     * 
     * @param text to be put inside button
     * @return JButton
     */
    public static JButton smallButton(String text) {
        JButton jbutton = new JButton(text);
        jbutton.setBackground(MyStyles.color.FOREGROUND);
        jbutton.setFont(MyStyles.font.BODY);
        jbutton.setFocusPainted(false);
        return jbutton;
    }

    /**
     * Custom JTextField
     * 
     * @param defaultValue String to preload textfield with
     * @return JTextField
     */
    public static JTextField textField(String defaultValue) {
        JTextField jtextfield = new JTextField(defaultValue, 20);
        jtextfield.setBackground(MyStyles.color.WHITE);
        jtextfield.setForeground(MyStyles.color.BACKGROUND);
        jtextfield.setFont(MyStyles.font.BODY);
        return jtextfield;
    }

    /**
     * Wrapper for custom JProgressBar
     * 
     * @param min   minimum value
     * @param max   maximum value
     * @param value starting value
     * @param text  to be shown in the JProgressBar
     * @return
     */
    public static JProgressBar progressBar(int min, int max, int value, String text) {
        // UIManager stuff
        UIManager.put("ProgressBar.foreground", MyStyles.color.FOREGROUND);
        UIManager.put("ProgressBar.background", MyStyles.color.ACCENT);
        UIManager.put("ProgressBar.selectionForeground", MyStyles.color.BLACK);
        UIManager.put("ProgressBar.selectionBackground", MyStyles.color.WHITE);

        JProgressBar jProgressBar = new JProgressBar(min, max);
        jProgressBar.setValue(value);
        jProgressBar.setString((text != null && !text.isEmpty()) ? text : "");
        jProgressBar.setStringPainted(true);
        return jProgressBar;
    }

    /**
     * Overloaded JProgress with default text
     * 
     * @param min
     * @param max
     * @param value
     * @return
     */
    public static JProgressBar progressBar(int min, int max, int value) {
        return progressBar(min, max, value, null);
    }
}
