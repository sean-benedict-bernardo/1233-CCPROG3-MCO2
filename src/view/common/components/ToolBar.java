package view.common.components;

import javax.swing.*;

import view.common.MyStyles;

public class ToolBar extends JToolBar {
    private JLabel titleText;
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
    public ToolBar(String headerText, String buttonTexts[]) {
        this.titleText = MyComponents.bodyText(headerText);
        this.titleText.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 24));
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

        if (this.titleText != null)
            add(this.titleText);

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
