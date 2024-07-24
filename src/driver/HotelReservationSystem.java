package driver;

import java.util.ArrayList;
import javax.swing.JButton;

import model.Hotel;
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
    private ArrayList<Hotel> hotelList;
    private HomeMenu gui;

    /**
     * HotelReservationSystem constructor
     */
    public HotelReservationSystem() {
        this.hotelList = new ArrayList<Hotel>();
        this.gui = new HomeMenu();

        try {
            Hotel myHotel = new Hotel("HotelGemal", 'S');

            for (int i = 0; i < 49; i++) {
                myHotel.addRoom('S');
            }

            for (int i = 0; i < 50; i++) {
                try {
                    myHotel.createReservation("Person" + i, (i % 30) + 1, (i % 30) + 2, "S" + (i + 1));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

            this.hotelList.add(myHotel);
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

                if (this.hotelList.isEmpty()){
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
