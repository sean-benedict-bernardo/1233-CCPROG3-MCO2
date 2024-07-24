package view.common.auxiliary;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Hotel;
import model.rooms.*;

public class UserInput {
    /**
     * 
     * @return Character representation of room
     */
    public static char selectRoomType() {
        String[] roomTypes = { "Standard", "Deluxe", "Executive" };

        String selectedRoom = (String) JOptionPane.showInputDialog(null,
                "Select room to add", "Room Selection",
                JOptionPane.QUESTION_MESSAGE, null,
                roomTypes, roomTypes[0]);

        return (selectedRoom != null) ? selectedRoom.charAt(0) : '\0';
    }

    /**
     * 
     * @return String representation of Room
     */
    public static String selectRoom(ArrayList<Room> roomsList) {
        String[] roomNames = new String[roomsList.size()];
        int i = 0;
        for (Room room : roomsList) {
            roomNames[i++] = room.getName();
        }

        String selectedRoom = (String) JOptionPane.showInputDialog(null,
                "Select room", "Room Selection",
                JOptionPane.QUESTION_MESSAGE, null,
                roomNames, roomNames[0]);

        return selectedRoom;
    }

    public static Hotel selectHotel(ArrayList<Hotel> hotelList) throws Exception {

        if (hotelList.size() == 0)
            throw new Exception("No hotels in system!");
        else {
            String[] hotelNames = new String[hotelList.size()];
            int i = 0;
            for (Hotel hotel : hotelList) {
                hotelNames[i++] = hotel.getName();
            }

            try {
                String selectedHotel = (String) JOptionPane.showInputDialog(null,
                        "Select Hotel", "Hotel Selection",
                        JOptionPane.QUESTION_MESSAGE, null,
                        hotelNames, hotelNames[0]);

                for (Hotel hotel : hotelList) {
                    if (hotel.getName().equals(selectedHotel))
                        return hotel;
                }
            } catch (Exception e) {
            }

            return null;
        }
    }

    public static boolean confirmAction(String prompt){
        int option = JOptionPane.showConfirmDialog(null, prompt, "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.YES_NO_OPTION){
            return true;
        }

        else return false;
    }
}
