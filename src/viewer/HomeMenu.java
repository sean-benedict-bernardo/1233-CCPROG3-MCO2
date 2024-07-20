package viewer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class HomeMenu extends JFrame {
    private JButton buttonsList[];

    public HomeMenu() {
        super();
        setLayout(new BorderLayout());

        this.initFrame();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void initFrame() {
        setBackground(CSS.color.BACKGROUND);
        setForeground(CSS.color.FOREGROUND);

        // NORTH FRAME
        JLabel nameLabel = new JLabel("Hotel Reservation System");
        nameLabel.setFont(CSS.font.HEADER);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        add(nameLabel, BorderLayout.NORTH);

        // CENTER FRAME
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,1));
        buttonPanel.setBackground(CSS.color.BACKGROUND);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton createHotelButton = new JButton("Create Hotel");
        JButton viewHotelButton = new JButton("View Hotel");
        JButton manageHotelButton = new JButton("Manage Hotel");
        JButton makeReservationButton = new JButton("Make a Reservation");
        JButton exitButton = new JButton("Exit Program");

        this.buttonsList = new JButton[] {
                createHotelButton, viewHotelButton, manageHotelButton,
                makeReservationButton, exitButton
        };

        for (JButton jButton : buttonsList) {
            jButton.setBackground(Color.white);
            jButton.setFont(CSS.font.BUTTON);
            jButton.setAlignmentX(CENTER_ALIGNMENT);
            // jButton.setSize(, jButton.getHeight());
            buttonPanel.add(jButton);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    public JButton[] getButtons() {
        return this.buttonsList;
    }
}
