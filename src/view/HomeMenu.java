package view;

import javax.swing.*;

import view.common.MyStyles;
import view.common.components.MyComponents;

import java.awt.*;

public class HomeMenu extends JFrame {
    private JButton buttonsList[];

    /**
     * HomeMenu Constructor
     */
    public HomeMenu() {
        setLayout(new BorderLayout());

        this.initFrame();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Initializes the Frame
     */
    public void initFrame() {
        setBackground(MyStyles.color.BACKGROUND);
        setForeground(MyStyles.color.FOREGROUND);
        setMinimumSize(new Dimension(720, 405));

        // NORTH FRAME
        JLabel nameLabel = MyComponents.titleText("Hotel Reservation System");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(32, 12, 12, 0));
        add(nameLabel, BorderLayout.NORTH);

        // CENTER FRAME
        // This Extra JPanel chesses and restricts the size of GridLayout
        JPanel buttonPanelContain = new JPanel(new GridBagLayout());
        buttonPanelContain.setBackground(getBackground());

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        buttonPanel.setBackground(buttonPanelContain.getBackground());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 32, 12));

        // BUTTONS
        this.buttonsList = new JButton[5];

        String buttonText[] = {
                "Create Hotel",
                "View Hotel",
                "Manage Hotel",
                "Make a Reservation",
                "Exit Program"
        };

        int i = 0;
        for (String text : buttonText) {
            this.buttonsList[i] = MyComponents.button(text);
            buttonPanel.add(this.buttonsList[i++]);
        }

        buttonPanelContain.add(buttonPanel);
        add(buttonPanelContain, BorderLayout.CENTER);
    }

    /**
     * Getter for Main Menu buttons
     * 
     * @return Array of Buttons
     */
    public JButton[] getButtons() {
        return this.buttonsList;
    }
}
