package driver;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import main.Hotel;
import viewer.Alert;
import viewer.HomeMenu;
import viewer.UserInput;

public class HotelReservationSystem {
    private ArrayList<Hotel> hotelList;
    private HomeMenu gui;

    public HotelReservationSystem() {
        this.hotelList = new ArrayList<Hotel>();
        this.gui = new HomeMenu();

        try {
            this.hotelList.add(new Hotel("HotelGemal"));
        } catch (Exception e) {
            // TODO: handle exception
        }

        this.initButtons();
    }

    /**
     * checks if potential hotel name is unique
     * 
     * @param key         hotel name to check from hotelList
     * @param ignoreIndex index in list to be ignored
     * @return boolean whether hotel name is unique or not
     */
    private boolean isUniqueHotelName(String key, int ignoreIndex) {
        int i = 0;
        for (Hotel hotel : this.hotelList) {
            if ((key.equals(hotel.getName())) && i != ignoreIndex)
                // We assume no one will name their hotel /EXIT
                return false;
            i++;
        }

        return true;
    }

    /**
     * Adds a new hotel to the system with a unique name provided by the user
     * This includes a check on whether the user entered a unique hotel name
     */
    public void createHotel() {
        String hotelName = JOptionPane.showInputDialog(null, "Enter Name of Hotel");
        System.out.println(hotelName);
        try {
            if (hotelName == null)
                throw new Exception("Invalid Input!");
            else if (!this.isUniqueHotelName(hotelName, -1))
                throw new Exception(hotelName + " already exists!");
            else {
                hotelName = hotelName.toString();
                this.hotelList.add(new Hotel(hotelName));
                Alert.displayAlert("Creating " + hotelName);
            }
        } catch (Exception e) {
            Alert.displayAlert(e);
        }
    }

    public void initButtons() {
        JButton buttonsList[] = this.gui.getButtons();

        buttonsList[0].addActionListener((e) -> {
            this.gui.setVisible(false);
            this.createHotel();
            this.gui.setVisible(true);
        });

        buttonsList[1].addActionListener((e) -> {
            try {
                Hotel localHotel = UserInput.selectHotel(hotelList);

                if (localHotel != null) {
                    // nothing is returned or kept
                    ViewHotel viewHotel = new ViewHotel(localHotel);
                }
            } catch (Exception err) {
                Alert.displayAlert(err);
            }
        });

        // Exit Program
        buttonsList[4].addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        HotelReservationSystem hrs = new HotelReservationSystem();
    }
}
