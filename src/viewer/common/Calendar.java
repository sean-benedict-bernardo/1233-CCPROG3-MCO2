package viewer.common;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Hotel;

public class Calendar extends JPanel {
    private JButton buttons[] = new JButton[31];

    public Calendar(Hotel hotel) {
        setLayout(new GridBagLayout());
        this.initButtons();
    }

    public void initButtons() {
        JPanel buttonsPanel = new JPanel(new GridLayout(0, 7));

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = MyComponents.JButton("" + (i + 1));
            buttonsPanel.add(buttons[i]);
        }

        add(buttonsPanel);
    }

    public JButton[] getButtons() {
        return this.buttons;
    }
}
