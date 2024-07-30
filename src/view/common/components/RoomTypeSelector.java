package view.common.components;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import view.common.MyStyles;

/**
 * RoomTypeSelector is a JPanel that enables
 * users to select a specific roomType
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class RoomTypeSelector extends JPanel {
    private ButtonGroup roomTypeButtonGroup;
    private JRadioButton roomTypeButtons[];

    /**
     * RoomTypeSelector Constructor
     */
    public RoomTypeSelector() {
        super(new FlowLayout(FlowLayout.LEFT, 4, 4));

        this.initButtons();
        this.initFrame();
    }

    /**
     * Buttons initilizer
     */
    public void initButtons() {
        this.roomTypeButtonGroup = new ButtonGroup();
        this.roomTypeButtons = new JRadioButton[] {
                new JRadioButton("Standard", true),
                new JRadioButton("Deluxe"),
                new JRadioButton("Executive")
        };

        for (JRadioButton jRadioButton : this.roomTypeButtons) {
            jRadioButton.setFont(MyStyles.font.BODY);
            jRadioButton.setBackground(MyStyles.color.BACKGROUND);
            jRadioButton.setForeground(MyStyles.color.FOREGROUND);
            jRadioButton.setFocusPainted(false);
            this.roomTypeButtonGroup.add(jRadioButton);
        }
    }

    /**
     * Initializes the JPanel
     */
    public void initFrame() {
        setBackground(MyStyles.color.BACKGROUND);
        setForeground(MyStyles.color.FOREGROUND);

        for (JRadioButton jRadioButton : roomTypeButtons)
            add(jRadioButton);
    }

    /**
     * Getter for room type radio buttons
     * 
     * @return character of selected room
     */
    public char getSelected() {

        for (JRadioButton jRadioButton : this.roomTypeButtons) {
            if (jRadioButton.isSelected())
                return jRadioButton.getText().charAt(0);
        }
        // One of the radio buttons is guaranteed to be selected
        // Java just throws a hissy fit if this is not found
        return '\0';
    }
}
