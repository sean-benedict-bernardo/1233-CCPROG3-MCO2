package view.common.components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Hotel;
import view.common.MyStyles;

/**
 * DateSelector is a JPanel of 31 buttons corresponding to a specific date
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class DateSelector extends JPanel {
    private JButton buttons[] = new JButton[Hotel.NUM_NIGHTS];

    /**
     * DateSelector Constructor
     */
    public DateSelector() {
        setLayout(new GridBagLayout());
        this.initButtons();
        setBackground(MyStyles.color.BACKGROUND);
    }

    /**
     * Initilaizes buttons
     */
    public void initButtons() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);

        for (int i = 0; i < buttons.length; i++) {
            gbc.gridy = i / 7;
            gbc.gridx = i % 7;

            buttons[i] = MyComponents.button("" + (i + 1));
            buttons[i].setFont(MyStyles.font.BODY);
            buttons[i].setMargin(new Insets(0, 0, 0, 0));

            buttons[i].setMinimumSize(new Dimension(36, 24));
            buttons[i].setPreferredSize(new Dimension(36, 24));
            add(buttons[i], gbc);
        }
    }

    /**
     * Getter for all buttons
     * to be assigned iteratively
     * 
     * @return Array of JButton[] 
     */
    public JButton[] getButtons() {
        return this.buttons;
    }
}
