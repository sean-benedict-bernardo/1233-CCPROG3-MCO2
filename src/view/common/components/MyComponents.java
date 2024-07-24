package view.common.components;

import javax.swing.*;

import view.common.MyStyles;

public class MyComponents {

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
        jLabel.setForeground(MyStyles.color.WHITE);
        jLabel.setFont(MyStyles.font.TITLE);
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
        jLabel.setForeground(MyStyles.color.WHITE);
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
    public static JLabel bodyText(String text, int align) {
        JLabel jLabel = new JLabel(text);
        jLabel.setOpaque(true);
        jLabel.setBackground(MyStyles.color.BACKGROUND);
        jLabel.setForeground(MyStyles.color.WHITE);
        jLabel.setFont(MyStyles.font.BODY);
        return jLabel;
    }

    /**
     * Custom JLabel as body component
     * 
     * @param text to be put inside body
     * @return JLabel body
     */
    public static JLabel bodyText(String text) {
        return bodyText(text, SwingConstants.CENTER);
    }

    /**
     * Blank custom JLabel as body component
     * 
     * @param text to be put inside body
     * @return JLabel body
     */
    public static JLabel bodyText() {
        return bodyText("", SwingConstants.CENTER);
    }

    /**
     * Custom JButton
     * 
     * @param text to be put inside button
     * @return JButton
     */
    public static JButton button(String text) {
        JButton jbutton = new JButton(text);
        jbutton.setBackground(MyStyles.color.WHITE);
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
        jbutton.setBackground(MyStyles.color.WHITE);
        jbutton.setFont(MyStyles.font.BODY);
        jbutton.setFocusPainted(false);
        return jbutton;
    }

    public static JTextField textField(String defaultValue) {
        JTextField jtextfield = new JTextField(defaultValue, 20);
        jtextfield.setBackground(MyStyles.color.WHITE);
        jtextfield.setFont(MyStyles.font.BODY);
        return jtextfield;
    }
}
