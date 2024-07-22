package driver;

import java.util.ArrayList;

import main.Hotel;
import viewer.common.Alert;
import viewer.createhotel.CreateHotel;

public class MenuCreateHotel {
    private ArrayList<Hotel> hotelList;
    private CreateHotel gui;

    public MenuCreateHotel(ArrayList<Hotel> hotelList) {
        this.hotelList = hotelList;
        this.gui = new CreateHotel();

        this.initButtons();
        this.gui.pack();
        this.gui.setModal(true);
        this.gui.setVisible(true);
    }

    private void initButtons() {
        this.gui.getCreateButton().addActionListener((e) -> {
            System.out.println(this.gui.getFirstRoomType());
            this.createHotel();
        });
        this.gui.getCancelButton().addActionListener((e) -> {
            this.gui.dispose();
        });
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
        String hotelName = this.gui.getHotelField();
        System.out.println(hotelName);
        try {
            if (hotelName == null)
                throw new Exception("Invalid Input!");
            else if (!this.isUniqueHotelName(hotelName, -1))
                throw new Exception(hotelName + " already exists!");
            else {
                hotelName = hotelName.toString();
                this.hotelList.add(new Hotel(hotelName, this.gui.getFirstRoomType()));
                Alert.displayAlert("Creating " + hotelName);
                // Return to main menu
                this.gui.dispose();
            }
        } catch (Exception e) {
            Alert.displayAlert(e);
        }
    }
}
