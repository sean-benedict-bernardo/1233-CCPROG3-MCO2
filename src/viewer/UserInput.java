package viewer;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import main.Hotel;
import main.rooms.*;

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
     * @return Character representation of room
     */
    public static String selectRoom(ArrayList<Room> roomsList) {
        String[] roomNames = new String[roomsList.size()];
        int i = 0;
        for (Room room : roomsList) {
            roomNames[i++] = room.getName();
        }

        String selectedRoom = (String) JOptionPane.showInputDialog(null,
                "Select room to add", "Room Selection",
                JOptionPane.QUESTION_MESSAGE, null,
                roomNames, roomNames[0]);

        return selectedRoom;
    }

    public static void main(String[] args) {
        Hotel testHotel = new Hotel("Alexander");

        for (int i = 30; i >= 0; i--) {
            try {
                switch ((i + 1) % 3) {
                    case 0:
                        testHotel.addRoom('S');
                        break;
                    case 1:
                        testHotel.addRoom('E');
                        break;
                    case 2:
                        testHotel.addRoom('D');
                        break;

                    default:
                        break;
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        System.out.println(selectRoom(testHotel.getRooms()));
    }
}
