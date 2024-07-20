package viewer;

import main.*;

import java.awt.*;
import javax.swing.*;

public class HotelInfo extends JDialog {
    private Hotel hotel;
    private JButton buttonsList[];

    public HotelInfo(Hotel hotel) {
        super();
        this.hotel = hotel;
        setLayout(new BorderLayout());

        this.initFrame();

        pack();
        setLocationRelativeTo(null);
    }

    public void initFrame() {
        setBackground(CSS.color.BACKGROUND);
        setForeground(CSS.color.FOREGROUND);

        // NORTH FRAME
        JLabel nameLabel = new JLabel("Viewing " + hotel.getName(), SwingConstants.CENTER);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(CSS.color.BACKGROUND);
        nameLabel.setForeground(getForeground());
        nameLabel.setFont(CSS.font.BODY);
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
                new JButton("Hotel Information"),
                new JButton("View Room Availability"),
                new JButton("View Room Information"),
                new JButton("View Reservations"),
                new JButton("Return to Main Menu")
        };

        for (JButton jButton : buttonsList) {
            jButton.setBackground(Color.white);
            jButton.setFont(CSS.font.BUTTON);
            jButton.setFocusPainted(false);
            buttonPanel.add(jButton);
        }
        buttonPanelContain.add(buttonPanel);
        add(buttonPanelContain, BorderLayout.WEST);
    }

    /**
     * case 1:
     * System.out.println();
     * Auxiliary.printBar(40);
     * System.out.println(" Name: " + localHotel.getName());
     * System.out.println(" Number of Rooms: " + localHotel.getNumRooms());
     * System.out.println(" Number of Reservations: " +
     * localHotel.getNumReservations());
     * System.out.printf(" Projected Earnings: %.2f\n",
     * localHotel.getTotalRevenue());
     * Auxiliary.printBar(40);
     * System.out.println();
     */

    public void displayHotelInformation() {
        JPanel hotelInfo = new JPanel(new GridLayout(4, 2));
        hotelInfo.setBackground(CSS.color.BACKGROUND);

        String data[][] = {
                { "Name", this.hotel.getName() },
                { "Room Count", "" + this.hotel.getNumRooms() },
                { "Reservation Count", "" + this.hotel.getNumReservations() },
                { "Total Revenue", "" + this.hotel.getTotalRevenue() }
        };

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                JLabel jLabel = new JLabel(data[i][j], SwingConstants.LEFT);
                jLabel.setOpaque(true);
                jLabel.setBackground(CSS.color.BACKGROUND);
                jLabel.setForeground(getForeground());
                jLabel.setFont(CSS.font.BODY);
                hotelInfo.add(jLabel);
            }
        }

        add(hotelInfo, BorderLayout.CENTER);
    }

    public JButton[] getButtons() {
        return this.buttonsList;
    }

    public JDialog getJDialog() {
        return this;
    }
}
