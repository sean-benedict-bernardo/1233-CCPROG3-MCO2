package viewer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

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
        // NORTH FRAME
        JLabel name = new JLabel("BOW: Big Old Wrangler");
        name.setFont(CSS.font.SANSERIF);
        name.setForeground(CSS.color.WHITE);
        name.setBackground(getBackground());

        // CENTER FRAME
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(CSS.color.BACKGROUND);

        JButton createHotelButton = new JButton("Create Hotel");
        JButton viewHotelButton = new JButton("View Hotel");
        JButton manageHotelButton = new JButton("Manage Hotel");
        JButton makeReservationButton = new JButton("Make a Reservation");
        JButton exitButton = new JButton("Exit Program");

        this.buttonsList = new JButton[] { createHotelButton, viewHotelButton, manageHotelButton, makeReservationButton,
                exitButton };

        for (JButton jButton : buttonsList) {
            jButton.setBackground(Color.white);
            jButton.setFont(CSS.font.SANSERIF);
            buttonPanel.add(jButton);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    public JButton[] getButtons() {
        return this.buttonsList;
    }
}
