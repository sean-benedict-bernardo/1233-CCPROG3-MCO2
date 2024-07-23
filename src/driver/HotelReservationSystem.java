package driver;

import java.util.ArrayList;
import javax.swing.JButton;

import model.Hotel;
import view.HomeMenu;
import view.common.auxiliary.Alert;
import view.common.auxiliary.UserInput;

public class HotelReservationSystem {
    private ArrayList<Hotel> hotelList;
    private HomeMenu gui;

    public HotelReservationSystem() {
        this.hotelList = new ArrayList<Hotel>();
        this.gui = new HomeMenu();

        try {
            Hotel myHotel = new Hotel("HotelGemal", 'D');

            for (int i = 0; i < 49; i++) {
                switch (i % 3) {
                    case 0:
                        myHotel.addRoom('S');
                        break;
                    case 1:
                        myHotel.addRoom('D');
                        break;
                    default:
                        myHotel.addRoom('E');
                        break;
                }
            }

            myHotel.createReservation("Andrew", 1, 5, "S1");

            this.hotelList.add(myHotel);
        } catch (Exception e) {
            // TODO: handle exception
        }

        this.initButtons();
    }

    public void initButtons() {
        JButton buttonsList[] = this.gui.getButtons();

        buttonsList[0].addActionListener((e) -> {
            try {
                this.gui.setVisible(false);
                new MenuCreateHotel(hotelList);
                this.gui.setVisible(true);
            } catch (Exception err) {
                Alert.displayAlert(err);
            }
        });

        buttonsList[1].addActionListener((e) -> {
            try {
                Hotel localHotel = UserInput.selectHotel(hotelList);

                if (localHotel != null) {
                    this.gui.setVisible(false);
                    new MenuViewHotel(localHotel);
                    this.gui.setVisible(true);
                }
            } catch (Exception err) {
                Alert.displayAlert(err);
            }
        });

        buttonsList[3].addActionListener((e) -> {
            try {
                Hotel localHotel = UserInput.selectHotel(hotelList);
                if (localHotel != null) {
                    new MenuMakeReservation(localHotel);
                }
            } catch (Exception err) {
                Alert.displayAlert(err);
            }
        });

        // Exit Program
        buttonsList[4].addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        try {
            new HotelReservationSystem();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
