package view.viewhotel;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Reservation;
import view.common.components.ReservationSelector;

public class Reservations extends JPanel {
    private ReservationSelector reservationSelectPanel;
    private JButton reservationButtons[];
    private JScrollPane reservationSelectScroll;

    public Reservations(ArrayList<Reservation> reservations) {
        super(new GridLayout());

        if (reservations.size() > 0)
            this.initFrame(reservations);
    }

    public void initFrame(ArrayList<Reservation> reservations) {
        this.reservationSelectPanel = new ReservationSelector(reservations);
        
        System.out.println();
    }

}
