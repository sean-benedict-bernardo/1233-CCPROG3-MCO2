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
            this.hotelList.addHotel("NewHotel", 'S');
            this.hotelList.addHotel("HotelGemal", 'D');

            // Hotel myHotel = this.hotelList.getHotel("NewHotel"),
            //         myHotel2 = this.hotelList.getHotel("HotelGemal");

            // for (int i = 0; i < 27; i++) {
            //     myHotel.addRoom('S');
            // }

            // // Test PAYDAY
            // myHotel.createReservation("Payday14", 14, 15, "S1", "PAYDAY");
            // myHotel.createReservation("Payday15", 15, 16, "S1", "PAYDAY");

            // // Test I_WORK_HERE
            // myHotel.createReservation("NonEmployee", 15, 16, "S4", "I_WORK_HERE");
            // myHotel.createReservation("Employee", 15, 16, "S5", Reservation.NODISCOUNT);

            // // Test STAY4_GET1
            // myHotel.createReservation("4DayStay", 15, 19, "S9", "STAY4_GET1");
            // myHotel.createReservation("5DayStay", 15, 20, "S7", "STAY4_GET1");
            // myHotel.createReservation("5DayStay_NoCode", 15, 20, "S8", Reservation.NODISCOUNT);

            // myHotel.createReservation("LongAssStay", 1, 31, "S27", Reservation.NODISCOUNT);

        } catch (Exception e) {
            System.out.println(e.getMessage());
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
                    this.gui.setVisible(false);
                    new MenuMakeReservation(localHotel);
                    this.gui.setVisible(true);
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
        }
    }
}
