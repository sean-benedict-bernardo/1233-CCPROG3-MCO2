package view.managehotel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import model.Hotel;
import model.Reservation;
import view.common.MyStyles;
import view.common.components.MyComponents;

public class ManageReservations extends JPanel {
    private Hotel hotel;

    private JComboBox<String> reservationSelector;

    private JLabel infoName, infoRoom, infoCheckIn, infoCheckOut, infoDiscountCode;

    private JButton deleteReservation;

    public ManageReservations(Hotel hotel) throws Exception {
        super(new BorderLayout());

        if (hotel.getNumReservations() == 0)
            throw new Exception("No reservations in system");

        this.hotel = hotel;
        this.initFrame();
        this.updateReservation(hotel.getReservationIds()[0]);
    }

    private void initFrame() {
        setBackground(MyStyles.color.BACKGROUND);
        setForeground(MyStyles.color.FOREGROUND);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);

        this.reservationSelector = new JComboBox<>(new DefaultComboBoxModel<>(this.hotel.getReservationIds()));
        this.reservationSelector.setSelectedIndex(0);
        this.reservationSelector.setFont(MyStyles.font.BODY);
        this.deleteReservation = MyComponents.button("Delete Reservation");

        JPanel containerPanel = new JPanel(new GridBagLayout());
        containerPanel.setBackground(MyStyles.color.BACKGROUND);

        // Left Info Panel
        this.infoName = MyComponents.headerText();
        this.infoRoom = MyComponents.headerText();
        this.infoCheckIn = MyComponents.headerText();
        this.infoCheckOut = MyComponents.headerText();
        this.infoDiscountCode = MyComponents.headerText();

        JComponent gridMap[][] = {
                { MyComponents.smallTitleText("Manage Reservations") },
                { MyComponents.bodyText("Choose reservation to delete", SwingConstants.CENTER, MyComponents.ITALICS) },
                { MyComponents.bodyText("Selected:"), this.reservationSelector },
                { new JSeparator() },
                { MyComponents.headerText("Guest Name"), this.infoName },
                { MyComponents.headerText("Room"), this.infoRoom },
                { MyComponents.headerText("Check-in"), this.infoCheckIn },
                { MyComponents.headerText("Check-out"), this.infoCheckOut },
                { MyComponents.headerText("Discount Code"), this.infoDiscountCode },
                { this.deleteReservation }
        };

        for (int i = 0; i < gridMap.length; i++) {
            for (int j = 0; j < gridMap[i].length; j++) {

                gbc.gridwidth = (j == gridMap[i].length - 1) ? GridBagConstraints.REMAINDER : 1;
                gbc.fill = (i <= 1 || i == 3 || i == 9) ? GridBagConstraints.NONE : GridBagConstraints.HORIZONTAL;

                gbc.gridy = i;
                gbc.gridx = j;
                containerPanel.add(gridMap[i][j], gbc);
            }
        }
        add(containerPanel, BorderLayout.CENTER);
    }

    /**
     * Getter for JComboBox object
     * 
     * @return
     */
    public JComboBox<String> getDropDown() {
        return this.reservationSelector;
    }

    /**
     * Returns what user selected in JComboBox
     * 
     * @return String
     */
    public String getSelectedReservation() {
        return this.reservationSelector.getSelectedItem().toString();
    }

    /**
     * Getter for deleteReservationButton
     * 
     * @return JButton
     */
    public JButton getDeleteReservation() {
        return this.deleteReservation;
    }

    public void updateReservation(String id) {
        Reservation reservation = this.hotel.getReservation(id);
        this.infoName.setText(reservation.getGuestName());
        this.infoRoom.setText(reservation.getRoom().getName());
        this.infoCheckIn.setText("" + reservation.getCheckInDate());
        this.infoCheckOut.setText("" + reservation.getCheckOutDate());
        this.infoDiscountCode.setText(
                (!reservation.getDiscountCode().equals(Reservation.NODISCOUNT)) ? reservation.getDiscountCode() : "");
    }

    public void updateReservationList(String idList[]) {
        reservationSelector.setModel(new DefaultComboBoxModel<>(idList));
        reservationSelector.setSelectedIndex(0);
        this.updateReservation(this.getSelectedReservation());
    }
}
