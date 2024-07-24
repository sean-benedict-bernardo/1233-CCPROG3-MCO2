package driver;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Hotel;
import view.common.auxiliary.Alert;
import view.common.auxiliary.UserInput;
import view.managehotel.ChangeHotelName;
import view.managehotel.ManageHotel;
import view.managehotel.RemoveRoom;
import view.managehotel.AddRoom;

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

        JPanel currentPanel1 = this.gui.getCardComponent(0);
        if (currentPanel1 instanceof ChangeHotelName){
            JButton confirmButton = ((ChangeHotelName) currentPanel1).getConfirmButton();
            JTextField hotelNameField = ((ChangeHotelName) currentPanel1).getHotelNameField();

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

        buttons[1].addActionListener(e -> this.gui.showCard(1));

        JPanel currentPanel2 = this.gui.getCardComponent(1);
        if (currentPanel2 instanceof AddRoom){
            JButton confirmButton = ((AddRoom)currentPanel2).getConfirmButton();

            confirmButton.addActionListener((e) -> {
                char roomType = ((AddRoom)currentPanel2).getRoomType();
                System.out.println("Adding " + Character.toString(roomType) + " to " + this.hotel.getName());

                try {
                   this.hotel.addRoom(roomType);
                   String roomTypeName;
                   switch (roomType){
                        case 'S': roomTypeName = "a standard";
                        break;
                        case 'D': roomTypeName = "a deluxe";
                        break;
                        default: roomTypeName = "an executive";
                   }
                   Alert.displayAlert("Adding " + roomTypeName + " room to " + this.hotel.getName());
                }

                catch (Exception omg){
                    Alert.displayAlert(omg);
                }
            });
        }

        buttons[2].addActionListener(e -> this.gui.showCard(2));

        JPanel currentPanel3 = this.gui.getCardComponent(2);
        if (currentPanel3 instanceof RemoveRoom){
            JButton confirmButton = ((RemoveRoom)currentPanel3).getConfirmButton();
            JButton nameSelectButton = ((RemoveRoom)currentPanel3).getNameSelectButton();

            confirmButton.addActionListener((e) -> {
                String roomName = ((RemoveRoom)currentPanel3).getCurrentRoomName();
                System.out.println("Deleting room" + roomName + " from " + this.hotel.getName());

                try {
                   this.hotel.removeRoom(roomName);
                   Alert.displayAlert("Removing " + roomName + " from " + this.hotel.getName());
                }

                catch (Exception omg){
                    Alert.displayAlert(omg);
                }
            });

            nameSelectButton.addActionListener((e) -> {
                nameSelectButton.setText("...");
                String buttonText = UserInput.selectRoom(this.hotel.getRooms());
                if (buttonText.equals(""))
                    nameSelectButton.setText("...");

                else nameSelectButton.setText(buttonText);
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
