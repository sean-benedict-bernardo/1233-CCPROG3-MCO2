package viewer.viewhotel;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.Reservation;

/** https://www.youtube.com/watch?v=KOI1WbkKUpQ */

public class Reservations extends JPanel {
    private JPanel reservationSelectPanel;
    private JButton reservationButtons[];
    private JScrollPane reservationSelectScroll;

    public Reservations(ArrayList<Reservation> reservations) {
        super(new GridLayout());

        if (reservations.size() > 0)
            this.initFrame(reservations);
    }

    public void initFrame(ArrayList<Reservation> reservations) {
        this.reservationSelectPanel = new JPanel();
        this.reservationButtons = new JButton[reservations.size()];
        this.reservationSelectScroll = new JScrollPane();

        System.out.println();
    }

}
