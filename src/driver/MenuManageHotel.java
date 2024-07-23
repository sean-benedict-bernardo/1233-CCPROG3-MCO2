package driver;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Hotel;
import view.viewhotel.RoomAvailability;
import view.viewhotel.RoomInformation;
import view.viewhotel.ViewHotel;
import view.common.auxiliary.Alert;
import view.managehotel.ChangeHotelName;
import view.managehotel.ManageHotel;

public class MenuManageHotel {
    private ManageHotel gui;
    private Hotel hotel;
    private ArrayList<Hotel> hotelList;

    public MenuManageHotel(Hotel hotel, ArrayList<Hotel> hotelList) {
        this.hotel = hotel;
        this.hotelList = hotelList;
        this.gui = new ManageHotel(this.hotel);
        this.initButtons();

        this.gui.setModal(true);
        this.gui.setVisible(true);
    }

    public void initButtons() {
        JButton buttons[] = this.gui.getButtons();

        buttons[0].addActionListener(e -> this.gui.showCard(0));

        JPanel changeHotelNamePanel = this.gui.getCardComponent(0);
        if (changeHotelNamePanel instanceof ChangeHotelName){
            JButton confirmButton = ((ChangeHotelName)changeHotelNamePanel).getConfirmButton();
            JTextField hotelNameField = ((ChangeHotelName)changeHotelNamePanel).getHotelNameField();

            confirmButton.addActionListener((e) -> {
                String hotelName = hotelNameField.getText();
                System.out.println("New Hotel Name: " + hotelName);

                try {
                    if (hotelName == null)
                        throw new Exception("Invalid Input!");
                    else if (!this.isUniqueHotelName(hotelName, getHotelIndex(this.hotel)))
                        throw new Exception(hotelName + " already exists!");
                    else {
                        Alert.displayAlert("Changing name of " + this.hotel.getName() + " to " + hotelName);
                        this.hotel.setName(hotelName);
                    } 
                }

                catch (Exception omg){
                    Alert.displayAlert(omg);
                }
            }); 
        }

        /*
        // the card can only show if there are reservations
        if (this.hotel.getNumReservations() > 0)
            buttons[3].addActionListener((e) -> this.gui.showCard(3));

        buttons[4].addActionListener(e -> this.hideWindow());

        // add eventlistener to the jscrollpane
        JPanel roomInfoPanel = this.gui.getCardComponent(2);

        if (roomInfoPanel instanceof RoomInformation) {
            ArrayList<JButton> roomSelectButtons = ((RoomInformation) roomInfoPanel).getRoomSelectButtons();

            for (JButton jButton : roomSelectButtons) {
                jButton.addActionListener((e) -> {
                    ((RoomInformation) roomInfoPanel).dispRoomInfo(this.hotel.getRoom(e.getActionCommand()));
                });
            }
        }

        JPanel roomAvailabilityPanel = this.gui.getCardComponent(1);

        if (roomAvailabilityPanel instanceof RoomAvailability) {
            JButton calendarButtons[] = ((RoomAvailability) roomAvailabilityPanel).getDateSelector();

            for (JButton jButton : calendarButtons) {
                jButton.addActionListener((e) -> {
                    ((RoomAvailability) roomAvailabilityPanel).updateAvailability(this.hotel.getRooms(),
                            Integer.parseInt(e.getActionCommand()));
                });
            }
        }
        */
    }

    public void hideWindow() {
        System.out.println("ManageHotel: window hidden");
        this.gui.setVisible(false);
    }

    public int getHotelIndex(Hotel hotel){
        int i = 0;
        for (Hotel candidate : this.hotelList){
            if (hotel.getName().equals(candidate.getName()))
                return i;
    
            i++;
        }

        return -1;
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
}
