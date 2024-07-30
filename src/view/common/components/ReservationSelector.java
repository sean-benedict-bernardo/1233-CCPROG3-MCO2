package view.common.components;

import javax.swing.*;
import java.awt.*;

import model.Reservation;
import view.common.MyStyles;

import java.util.ArrayList;

/**
 * ReservationSelector is a JPanel that enables
 * users to select a specific reservation
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class ReservationSelector extends JPanel {
    private ArrayList<JButton> reservationButtons;
    private JScrollPane reservationSelect;

    /**
     * ReservationSelector Constrctor
     * 
     * @param reservations button options to be generated 
     */
    public ReservationSelector(ArrayList<Reservation> reservations) {
        super(new BorderLayout());

        this.reservationButtons = new ArrayList<>();
        this.initFrame(reservations);
    }

    /**
     * Initializes the scrollpane
     * 
     * @param reservations ArrayList to show
     */
    private void initFrame(ArrayList<Reservation> reservations) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel scrollArea = new JPanel(new GridBagLayout());

        for (Reservation reservation : reservations) {
            JButton button = MyComponents
                    .button(String.format("%s | %s", reservation.getRoom().getName(), reservation.getGuestName()));
            button.setName(reservation.getId());
            button.setHorizontalAlignment(SwingConstants.LEFT);
            this.reservationButtons.add(button);
            scrollArea.add(button, gbc);
        }

        this.reservationSelect = new JScrollPane(scrollArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        reservationSelect.setBackground(MyStyles.color.GRAY);
        reservationSelect.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        reservationSelect.getVerticalScrollBar().setUnitIncrement(16);

        add(reservationSelect, BorderLayout.CENTER);
    }

    /**
     * Getter for JButton
     * 
     * @return ArrayList of all room select JButtons
     */
    public ArrayList<JButton> getReservationSelectButtons() {
        return this.reservationButtons;
    }
}
