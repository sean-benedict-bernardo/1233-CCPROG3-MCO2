package viewer.viewhotel;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.Reservation;

/** https://www.youtube.com/watch?v=KOI1WbkKUpQ */

public class Reservations extends JPanel {
    private JList<Reservation> reservationsList;
    private DefaultListModel<Reservation> model;
    private ArrayList<Reservation> reservations;

    public Reservations(ArrayList<Reservation> reservations) {
        super(new GridLayout());

        this.reservations = reservations;
        if (this.reservations.size() > 0)
            this.initFrame();
    }

    public void initFrame() {
        reservationsList.setModel(model);

        for (Reservation reservation : reservations) {
            model.addElement(reservation);
        }

        add(new JScrollPane(reservationsList));
    }

}
