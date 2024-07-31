package view.managehotel;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import model.Hotel;
import view.common.MyStyles;
import view.common.components.ToolBar;

/**
 * ManageHotel contains the GUI
 * that allows user to manage hotel
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class ManageHotel extends JDialog {
    private Hotel hotel;
    private JPanel cardComponents[] = new JPanel[3];
    private JPanel cardPanel;
    private CardLayout cardPanelLayout = new CardLayout();
    private ToolBar toolBar;

    /**
     * ManageHotel Constructor
     */
    public ManageHotel(Hotel hotel) {
        super((Dialog) null);
        this.hotel = hotel;

        setLayout(new BorderLayout());

        setTitle("Managing " + hotel.getName());

        setBackground(MyStyles.color.BACKGROUND);
        setForeground(MyStyles.color.FOREGROUND);

        initButtons();

        initCardContent();

        setMinimumSize(MyStyles.misc.SCREEN_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    /**
     * Initializes ToolBar Buttons
     */
    private void initButtons() {
        String buttonsList[] = {
                "Manage Hotel",
                "Manage Rooms",
                "Manage Reservation",
                "Main Menu"
        };

        this.toolBar = new ToolBar(buttonsList);
        this.updateReservationButton(this.hotel.getNumReservations() == 0);
        add(this.toolBar, BorderLayout.NORTH);
    }

    /**
     * Initialize card JPanels
     */
    private void initCardContent() {
        this.cardPanel = new JPanel(this.cardPanelLayout);

        this.cardComponents[0] = new ManageHotelPanel(this.hotel);
        this.cardComponents[1] = new ManageRooms(this.hotel);
        try {
            this.cardComponents[2] = new ManageReservations(this.hotel);
        } catch (Exception e) {
            // No reservations
            this.cardComponents[2] = new JPanel();
            this.cardComponents[2].setBackground(MyStyles.color.BACKGROUND);
        }

        for (int i = 0; i < this.cardComponents.length; i++)
            this.cardPanel.add(this.cardComponents[i], "" + i);

        JPanel dummyPanel = new JPanel();
        dummyPanel.setBackground(MyStyles.color.BACKGROUND);
        dummyPanel.setSize(MyStyles.misc.SCREEN_SIZE);
        this.cardPanel.add(dummyPanel, "" + -1);

        add(cardPanel);
        this.cardPanelLayout.show(this.cardPanel, "-1");
    }

    /**
     * Updates whether reservation button can be used or not
     * 
     * @param isAccessible boolean of whether button can be used
     */
    public void updateReservationButton(boolean isAccessible) {
        if (isAccessible) {
            JButton reservationButton = this.toolBar.getButtons()[2];
            reservationButton.setBackground(MyStyles.color.LIGHTGRAY);
            // disable button
            reservationButton.setEnabled(false);
        }
    }

    /**
     * Show card at index
     * 
     * @param index
     */
    public void showCard(int index) {
        if (0 <= index && index <= 3) {
            this.cardPanelLayout.show(this.cardPanel, "" + index);
        }
    }

    /**
     * Getter for ToolBar buttons
     * 
     * @return ToolBar buttons
     */
    public JButton[] getButtons() {
        return this.toolBar.getButtons();
    }

    /**
     * Getter for card components
     * 
     * @param index of cardcomponent
     * @return specific JPanel at index
     */
    public JPanel getCardComponent(int index) {
        return (0 <= index && index <= 2) ? this.cardComponents[index] : null;
    }
}
