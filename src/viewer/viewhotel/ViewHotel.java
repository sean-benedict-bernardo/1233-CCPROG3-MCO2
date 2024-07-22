package viewer.viewhotel;

import main.*;
import viewer.common.MyStyles;
import viewer.common.MyComponents;

import java.awt.*;
import javax.swing.*;

public class ViewHotel extends JDialog {
    private Hotel hotel;
    private JButton buttonsList[];
    private JPanel cardComponents[] = new JPanel[4];
    private JPanel cardPanel;
    private CardLayout cardPanelLayout = new CardLayout();

    public ViewHotel(Hotel hotel) {
        super();
        this.hotel = hotel;
        setLayout(new BorderLayout());

        this.initFrame();
        this.initButtons();
        this.initCardContent();

        setMinimumSize(new Dimension(720, 405));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void initFrame() {
        setBackground(MyStyles.color.BACKGROUND);
        setForeground(MyStyles.color.FOREGROUND);

        // NORTH FRAME
        JLabel nameLabel = MyComponents.bodyText("Viewing " + hotel.getName(), SwingConstants.CENTER);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 0));
        add(nameLabel, BorderLayout.NORTH);
    }

    private void initButtons() {
        // WEST FRAME
        // This Extra JPanel chesses and restricts the size of GridLayout
        JPanel buttonPanelContain = new JPanel(new GridBagLayout());
        buttonPanelContain.setBackground(getBackground());

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        buttonPanel.setBackground(buttonPanelContain.getBackground());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        // BUTTONS
        this.buttonsList = new JButton[] {
                MyComponents.JButton("Hotel Information"),
                MyComponents.JButton("View Room Availability"),
                MyComponents.JButton("View Room Information"),
                MyComponents.JButton("View Reservations"),
                MyComponents.JButton("Return to Main Menu")
        };

        for (JButton jButton : buttonsList)
            buttonPanel.add(jButton);

        buttonPanelContain.add(buttonPanel);
        add(buttonPanelContain, BorderLayout.WEST);
    }

    private void initCardContent() {
        this.cardPanel = new JPanel(this.cardPanelLayout);

        this.cardComponents[0] = new HotelInfo(hotel);
        this.cardComponents[1] = new RoomAvailability(hotel);
        this.cardComponents[2] = new RoomInformation(hotel.getRooms());
        this.cardComponents[3] = new Reservations(hotel.getReservations());

        for (int i = 0; i < this.cardComponents.length; i++)
            this.cardPanel.add(this.cardComponents[i], "" + i);

        JPanel dummyPanel = new JPanel();
        dummyPanel.setSize(720, 400);
        this.cardPanel.add(dummyPanel, "" + -1);

        add(cardPanel);
        this.cardPanelLayout.show(this.cardPanel, "-1");
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

    public void showCard(int index) {
        if (0 <= index && index <= 3) {
            this.cardPanelLayout.show(this.cardPanel, "" + index);
        }
    }

    public JButton[] getButtons() {
        return this.buttonsList;
    }

    public JDialog getJDialog() {
        return this;
    }

    public JPanel getCardComponent(int index) {
        return (0 <= index && index <= 3) ? this.cardComponents[index] : null;
    }
}
