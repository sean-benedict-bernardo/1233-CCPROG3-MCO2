package view.common.components;

import javax.swing.*;
import java.awt.*;

import model.Reservation;
import view.common.MyStyles;

import java.util.ArrayList;

public class ReservationSelector extends JPanel {
    private ArrayList<JButton> reservationButtons;
    private JScrollPane reservationSelect;

    public ReservationSelector(ArrayList<Reservation> reservations) {
        super(new BorderLayout());

        this.reservationButtons = new ArrayList<>();
        this.initFrame(reservations);
    }

    private void initFrame(ArrayList<Reservation> reservations) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel scrollArea = new JPanel(new GridBagLayout());

        int innerId = 0;
        for (Reservation reservation : reservations) {
            JButton button = MyComponents.button(String.format("%s | %s", reservation.getGuestName()));
            button.setName("reservationButton" + innerId++);
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
