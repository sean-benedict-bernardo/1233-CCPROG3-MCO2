package view.managehotel;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import model.Hotel;
import view.common.MyStyles;
import view.common.components.ToolBar;

public class ManageHotel extends JDialog {
    private Hotel hotel;
    private JPanel cardComponents[] = new JPanel[3];
    private JPanel cardPanel;
    private CardLayout cardPanelLayout = new CardLayout();
    private ToolBar toolBar;

    public ManageHotel(Hotel hotel){
        super((Dialog) null);
        this.hotel = hotel;

        setLayout(new BorderLayout());

        setTitle("Manage " + hotel.getName());

        setBackground(MyStyles.color.BACKGROUND);
        setForeground(MyStyles.color.FOREGROUND);

        initButtons();

        initCardContent();

        setMinimumSize(MyStyles.misc.SCREEN_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void initButtons() {
        // BUTTONS
        String buttonsList[] = {
                "Change Hotel Name",
                "Add Room",
                "Remove Room",
                "Update Base Price",
                "Edit Date Price Modifer",
                "Remove Reservation",
                "Remove Hotel"
                };

        this.toolBar = new ToolBar(buttonsList);

        add(this.toolBar, BorderLayout.NORTH);
    }

    private void initCardContent() {
        this.cardPanel = new JPanel(this.cardPanelLayout);

        this.cardComponents[0] = new ChangeHotelName(hotel);
        this.cardComponents[1] = new AddRoom(hotel);
        this.cardComponents[2] = new RemoveRoom(hotel.getRooms());
        /* 
        this.cardComponents[3] = new UpdateBasePrice(hotel.getReservations());
        //Sean will cook date price modifier
        this.cardComponents[5] = new RemoveReservation(hotel.getReservations());
        this.cardComponents[6] = new RemoveHotel(hotel.getReservations());
        */

        for (int i = 0; i < this.cardComponents.length; i++)
            this.cardPanel.add(this.cardComponents[i], "" + i);

        JPanel dummyPanel = new JPanel();
        dummyPanel.setSize(MyStyles.misc.SCREEN_SIZE);
        this.cardPanel.add(dummyPanel, "" + -1);

        add(cardPanel);
        this.cardPanelLayout.show(this.cardPanel, "-1");
    }

    public void showCard(int index) {
        if (0 <= index && index <= 5) {
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
