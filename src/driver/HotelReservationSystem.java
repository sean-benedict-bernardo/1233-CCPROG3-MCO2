package driver;

import java.util.ArrayList;
import javax.swing.JButton;

import main.Hotel;
import viewer.HomeMenu;

public class HotelReservationSystem {
    ArrayList<Hotel> hotelList;
    HomeMenu gui;

    public HotelReservationSystem() {
        this.hotelList = new ArrayList<Hotel>();
        this.gui = new HomeMenu();

        this.initButtons();
    }

    public void initButtons() {
        JButton buttonsList[] = this.gui.getButtons();


        // Exit Program
        buttonsList[4].addActionListener(e -> System.exit(0));   
    }

    public static void main(String[] args) {
        HotelReservationSystem hrs = new HotelReservationSystem();

    }
}
