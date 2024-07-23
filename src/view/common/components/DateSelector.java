package view.common.components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Hotel;
import view.common.MyStyles;

public class DateSelector extends JPanel {
    private JButton buttons[] = new JButton[31];

    public DateSelector(Hotel hotel) {
        setLayout(new GridBagLayout());
        this.initButtons();
        setBackground(MyStyles.color.BACKGROUND);
    }

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

    public JButton[] getButtons() {
        return this.buttons;
    }

    public static void main(String[] args) {
        JFrame myJFrame = new JFrame();
        myJFrame.add(new DateSelector(null));
        myJFrame.pack();
        myJFrame.setVisible(true);
    }
}
