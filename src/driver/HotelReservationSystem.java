package driver;

import javax.swing.JButton;

import model.Hotel;
import model.HotelCollection;
import view.HomeMenu;
import view.common.auxiliary.Alert;
import view.common.auxiliary.UserInput;

/**
 * HotelReservationSystem is the main class
 * and entry point for the project
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class HotelReservationSystem {
    private HotelCollection hotelList;
    private HomeMenu gui;

    /**
     * HotelReservationSystem constructor
     */
    public HotelReservationSystem() {
        this.hotelList = new HotelCollection();
        this.gui = new HomeMenu();

        // testing here
        try {
            Hotel myHotel = new Hotel("NewHotel", 'S'),
                    myHotel2 = new Hotel("HotelGemal", 'D');

            for (int i = 0; i < 49; i++) {
                myHotel.addRoom('S');
            }

            myHotel.createReservation("Short", 1, 5, "S1");
            myHotel.createReservation("Mid", 1, 15, "S2");
            myHotel.createReservation("Long", 1, 31, "S3");

            this.hotelList.addHotel(myHotel2);
            this.hotelList.addHotel(myHotel);
        } catch (Exception e) {
            System.out.println(e);
        }

        this.initButtons();
    }

    /**
     * Initializes the home screen buttons
     */
    public void initButtons() {
        JButton buttonsList[] = this.gui.getButtons();

        buttonsList[0].addActionListener((e) -> {
            try {
                this.gui.setVisible(false);
                new MenuCreateHotel(hotelList);
            } catch (Exception err) {
                Alert.displayAlert(err);
            }
            this.gui.setVisible(true);
        });

        buttonsList[1].addActionListener((e) -> {
            try {
                Hotel localHotel = UserInput.selectHotel(hotelList);

                if (localHotel != null) {
                    this.gui.setVisible(false);
                    new MenuViewHotel(localHotel);
                }
            } catch (Exception err) {
                Alert.displayAlert(err);
            }
            this.gui.setVisible(true);
        });

        buttonsList[2].addActionListener((e) -> {
            try {
                Hotel localHotel = UserInput.selectHotel(hotelList);

                if (this.hotelList.getNumHotels() == 0) {
                    throw new Exception("There are no hotels in the system!");
                }

                if (localHotel != null) {
                    this.gui.setVisible(false);
                    new MenuManageHotel(localHotel, this.hotelList);
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
            this.gui.setVisible(true);
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
