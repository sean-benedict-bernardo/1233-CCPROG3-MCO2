package driver;

import model.HotelCollection;
import view.common.auxiliary.Alert;
import view.createhotel.CreateHotel;

/**
 * MenuCreateHotel is the controller class
 * for the "Create Hotel" functionality.
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class MenuCreateHotel {
    private HotelCollection hotels;
    private CreateHotel gui;

    /**
     * MenuCreateHotel constructor
     * 
     * @param hotels HotelCollection to compare and add a new hotel to
     */
    public MenuCreateHotel(HotelCollection hotels) {
        this.hotels = hotels;
        this.gui = new CreateHotel();

        this.initButtons();
        this.gui.pack();
        this.gui.setModal(true);
        this.gui.setVisible(true);
    }

    /**
     * Initializes and adds actionlisteners to buttons
     */
    private void initButtons() {
        this.gui.getCreateButton().addActionListener((e) -> {
            System.out.println(this.gui.getFirstRoomType());
            this.createHotel();
        });
        this.gui.getCancelButton().addActionListener((e) -> {
            System.out.println("Exiting Hotel Creation Menu");
            this.gui.dispose();
        });
    }

    /**
     * Adds a new hotel to the system with a unique name provided by the user
     * This includes a check on whether the user entered a unique hotel name
     */
    public void createHotel() {
        try {
            String hotelName = this.gui.getHotelField().toString();
            char roomType = this.gui.getFirstRoomType();
            this.hotels.addHotel(hotelName, roomType);
            Alert.displayAlert("Creating " + hotelName);
            this.gui.dispose();
        } catch (Exception e) {
            Alert.displayAlert(e);
        }
    }
}
