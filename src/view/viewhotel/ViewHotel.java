package view.viewhotel;

import java.awt.*;
import javax.swing.*;

import model.*;
import view.common.MyStyles;
import view.common.components.ToolBar;

public class ViewHotel extends JDialog {
    private Hotel hotel;
    private JPanel cardComponents[] = new JPanel[4];
    private JPanel cardPanel;
    private CardLayout cardPanelLayout = new CardLayout();
    private ToolBar toolBar;

    public ViewHotel(Hotel hotel) {
        super((Dialog) null);
        this.hotel = hotel;
        setLayout(new BorderLayout());

        setTitle(hotel.getName() + " View Mode");

        this.initFrame();
        this.initButtons();
        this.initCardContent();

        setMinimumSize(MyStyles.misc.SCREEN_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void initFrame() {
        setBackground(MyStyles.color.BACKGROUND);
        setForeground(MyStyles.color.FOREGROUND);
    }

    private void initButtons() {
        // BUTTONS
        String buttonsList[] = {
                "Hotel Info",
                "Room Availability",
                "Room Info",
                "Reservations",
                "Main Menu" };

        this.toolBar = new ToolBar(buttonsList);

        // give user context clue that there's no reservation
        if (this.hotel.getNumReservations() == 0) {
            JButton reservationButton = this.toolBar.getButtons()[3];
            reservationButton.setBackground(MyStyles.color.LIGHTGRAY);
            // disable button
            reservationButton.setEnabled(false);
        }

        add(this.toolBar, BorderLayout.NORTH);
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
        dummyPanel.setBackground(MyStyles.color.BACKGROUND);
        this.cardPanel.add(dummyPanel, "" + -1);

        add(cardPanel);
        this.cardPanelLayout.show(this.cardPanel, "-1");
    }

    /**
     * shows
     * 
     * @param index index of card to be shown 0-3
     */
    public void showCard(int index) {
        if (0 <= index && index <= 3) {
            this.cardPanelLayout.show(this.cardPanel, "" + index);
        }
    }

    public JButton[] getButtons() {
        return this.toolBar.getButtons();
    }

    public JDialog getJDialog() {
        return this;
    }

    public JPanel getCardComponent(int index) {
        return (0 <= index && index <= 3) ? this.cardComponents[index] : null;
    }
}
