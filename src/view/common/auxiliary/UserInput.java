package view.common.auxiliary;

import javax.swing.JOptionPane;

import model.Hotel;
import model.HotelCollection;

public class UserInput {
    public static Hotel selectHotel(HotelCollection hotelList) throws Exception {

        if (hotelList.getNumHotels() == 0)
            throw new Exception("No hotels in system!");
        else {
            String[] hotelNames = new String[hotelList.getNumHotels()];
            int i = 0;
            for (Hotel hotel : hotelList.getHotels())
                hotelNames[i++] = hotel.getName();

            try {
                String selectedHotel = (String) JOptionPane.showInputDialog(null,
                        "Select Hotel", "Hotel Selection",
                        JOptionPane.QUESTION_MESSAGE, null,
                        hotelNames, hotelNames[0]);

                for (Hotel hotel : hotelList.getHotels()) {
                    if (hotel.getName().equals(selectedHotel))
                        return hotel;
                }
            } catch (Exception e) {
            }

            return null;
        }
    }

    public static boolean confirmAction(String prompt) {
        int option = JOptionPane.showConfirmDialog(null, prompt, "Confirmation", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.YES_NO_OPTION) {
            return true;
        }

        else
            return false;
    }
}
