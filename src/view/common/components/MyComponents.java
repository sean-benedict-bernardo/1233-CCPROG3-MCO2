package view.common.components;

import java.awt.Font;

import javax.swing.*;

import view.common.MyStyles;

public class MyComponents {
    public static int ITALICS = 1;
    public static int BOLD = 2;

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
     * Custom JLabel empty
     * 
     * @return JLabel header
     */
    public static JLabel headerText() {
        return headerText("", SwingConstants.LEFT);
    }

    /**
     * Custom JLabel as body component with custom alignment
     * 
     * @param align SwingConstants of text alignment
     * @param text  to be put inside body
     * @return JLabel body
     */
    public static JLabel bodyText(String text, int align, int fontStyle) {
        JLabel jLabel = new JLabel(text, align);
        jLabel.setOpaque(true);
        jLabel.setBackground(MyStyles.color.BACKGROUND);
        jLabel.setForeground(MyStyles.color.FOREGROUND);

        switch (fontStyle) {
            case 1:
                jLabel.setFont(MyStyles.font.ITALICS);
                break;
            case 2:
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

    public static JProgressBar progressBar(int min, int max, int value, String text) {
        // UIManager stuff
        UIManager.put("ProgressBar.foreground", MyStyles.color.FOREGROUND);
        UIManager.put("ProgressBar.background", MyStyles.color.ACCENT);
        UIManager.put("ProgressBar.selectionForeground", MyStyles.color.BLACK);
        UIManager.put("ProgressBar.selectionBackground", MyStyles.color.WHITE);

        JProgressBar jProgressBar = new JProgressBar(min, max);
        jProgressBar.setValue(value);
        jProgressBar.setString(text);
        jProgressBar.setStringPainted(true);
        return jProgressBar;
    }

    public static JProgressBar progressBar(int min, int max, int value) {
        // UIManager stuff
        UIManager.put("ProgressBar.foreground", MyStyles.color.FOREGROUND);
        UIManager.put("ProgressBar.background", MyStyles.color.ACCENT);
        UIManager.put("ProgressBar.selectionForeground", MyStyles.color.BLACK);
        UIManager.put("ProgressBar.selectionBackground", MyStyles.color.WHITE);

        JProgressBar jProgressBar = new JProgressBar(min, max);
        jProgressBar.setValue(value);
        return jProgressBar;
    }
}
