package driver;

import javax.swing.JButton;
import javax.swing.JDialog;

import main.Hotel;
import viewer.viewhotel.*;

public class MenuViewHotel {
    private ViewHotel gui;
    private Hotel hotel;

    public MenuViewHotel(Hotel hotel) {
        this.hotel = hotel;
        this.gui = new ViewHotel(this.hotel);
        this.initButtons();

        JDialog addition = this.gui.getJDialog();
        addition.setModal(true);
        addition.setVisible(true);
    }

    public void initButtons() {
        JButton buttons[] = this.gui.getButtons();

        for (int i = 0; i < buttons.length - 2; i++) {
            // Java throws a hissy fit when i is not final
            final int innerIndex = i;
            buttons[i].addActionListener(e -> this.gui.showCard(innerIndex));
        }
        // the card can only show if there are reservations
        if (this.hotel.getNumReservations() > 0)
            buttons[3].addActionListener((e) -> this.gui.showCard(3));

        buttons[4].addActionListener(e -> this.hideWindow());
    }

    public void hideWindow() {
        System.out.println("ViewHotel: window hidden");
        this.gui.setVisible(false);
    }
}
