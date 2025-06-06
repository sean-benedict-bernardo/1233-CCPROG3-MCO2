package view.common.components;

import javax.swing.*;

import view.common.MyStyles;

/**
 * ToolBar is a JToolBar that collates the buttons of the functionalities
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class ToolBar extends JToolBar {
    private JButton buttons[];

    /**
     * ToolBar constructor
     * 
     * @param buttons array of JButtons to be added to the toolbar
     */
    public ToolBar(JButton buttons[]) {
        super();
        this.buttons = buttons;
        this.initToolbar();
    }

    /**
     * ToolBar constructor
     * 
     * @param headerText String of ToolBar text to beinitialized
     * @param buttons    array of Strings to be added as toolbar buttons
     */
    public ToolBar(String buttonTexts[]) {
        this.buttons = new JButton[buttonTexts.length];
        int i = -1;
        for (String text : buttonTexts)
            this.buttons[++i] = MyComponents.smallButton(text);

        this.initToolbar();
    }

    /**
     * Tool bar initializier
     */
    private void initToolbar() {
        setBackground(MyStyles.color.BACKGROUND);
        setFloatable(false);

        for (JButton jButton : this.buttons) {
            add(jButton);
        }
    }

    /**
     * Getter for all JButtons
     * 
     * @return Array of JButtons from toolbar
     */
    public JButton[] getButtons() {
        return this.buttons;
    }
}
