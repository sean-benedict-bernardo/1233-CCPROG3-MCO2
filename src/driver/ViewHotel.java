package driver;

import javax.swing.JButton;
import javax.swing.JDialog;

import main.Hotel;
import viewer.HotelInfo;

public class ViewHotel {
    private HotelInfo gui;

    public ViewHotel(Hotel hotel) {
        this.gui = new HotelInfo(hotel);

        System.out.println("Balls");
        this.initButtons();

        JDialog addition = this.gui.getJDialog();

        addition.setLocationRelativeTo(null);
        addition.setModal(true);
        addition.setVisible(true);
    }

    public void initButtons() {
        JButton buttons[] = this.gui.getButtons();

        buttons[0].addActionListener(e -> this.hotelInformation());
        buttons[4].addActionListener(e -> this.hideWindow());
    }

    public void hotelInformation() {
        System.out.println("ViewHotel: Showing HotelInfo");
        gui.displayHotelInformation();
    }

    public void hideWindow() {
        System.out.println("ViewHotel: window hidden");
        this.gui.setVisible(false);
    }
}
