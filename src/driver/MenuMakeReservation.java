package driver;

import main.Hotel;
import viewer.viewhotel.ViewHotel;

public class MenuMakeReservation {
    private ViewHotel gui;
    private Hotel hotel;

    public MenuMakeReservation(Hotel hotel) {
        this.hotel = hotel;
        this.gui = new ViewHotel(this.hotel);
    }
}