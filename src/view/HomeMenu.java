package view;

import javax.swing.*;

import view.common.MyStyles;

import java.awt.*;

public class HomeMenu extends JFrame {
    private JButton buttonsList[];

    public HomeMenu() {
        setLayout(new BorderLayout());

        this.initFrame();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void initFrame() {
        setBackground(MyStyles.color.BACKGROUND);
        setForeground(MyStyles.color.FOREGROUND);
        setMinimumSize(new Dimension(720, 405));


        // NORTH FRAME
        JLabel nameLabel = new JLabel("<html><p style=\"text-align: center;\">Hotel Reservation System</p></html>",
                SwingConstants.CENTER);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(MyStyles.color.BACKGROUND);
        nameLabel.setForeground(getForeground());
        nameLabel.setFont(MyStyles.font.HEADER);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 0));
        add(nameLabel, BorderLayout.NORTH);

        // CENTER FRAME
        // This Extra JPanel chesses and restricts the size of GridLayout
        JPanel buttonPanelContain = new JPanel(new GridBagLayout());
        buttonPanelContain.setBackground(getBackground());

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        buttonPanel.setBackground(buttonPanelContain.getBackground());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        // BUTTONS
        this.buttonsList = new JButton[] {
                new JButton("Create Hotel"),
                new JButton("View Hotel"),
                new JButton("Manage Hotel"),
                new JButton("Make a Reservation"),
                new JButton("Exit Program")
        };

        for (JButton jButton : buttonsList) {
            jButton.setBackground(Color.white);
            jButton.setFont(MyStyles.font.BUTTON);
            jButton.setFocusPainted(false);
            buttonPanel.add(jButton);
        }

        buttonPanelContain.add(buttonPanel);
        add(buttonPanelContain, BorderLayout.CENTER);
    }

    public JButton[] getButtons() {
        return this.buttonsList;
    }
}
