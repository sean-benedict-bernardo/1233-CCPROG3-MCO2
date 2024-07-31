package view.viewhotel;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

import model.Hotel;
import model.rooms.Room;
import view.common.MyStyles;
import view.common.components.*;

/**
 * RoomAvailability allows user
 * to select a certain date and
 * display room availability
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class RoomAvailability extends JPanel {
    private DateSelector dateSelector;

    private JLabel selectedDate;
    private JLabel roomsAvailability[];

    /**
     * RoomAvailability Constructor
     * 
     * @param hotel whose rooms will be displayed
     */
    public RoomAvailability(Hotel hotel) {
        super(new BorderLayout());
        setBackground(MyStyles.color.BACKGROUND);
        setBackground(MyStyles.color.FOREGROUND);

        this.dateSelector = new DateSelector();
        this.roomsAvailability = new JLabel[hotel.getNumRooms()];
        this.initFrame(hotel);
    }

    /**
     * Initializes frame
     * 
     * @param hotel whose rooms will be displayed
     */
    private void initFrame(Hotel hotel) {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // WEST
        JPanel dateSelectPanel = new JPanel(new GridBagLayout());
        dateSelectPanel.setBackground(MyStyles.color.BACKGROUND);

        this.selectedDate = MyComponents.headerText("Selected: Day 1");

        dateSelectPanel.add(this.selectedDate, gbc);
        gbc.gridy = 2;
        dateSelectPanel.add(this.dateSelector, gbc);

        add(dateSelectPanel, BorderLayout.WEST);

        // CENTER
        JPanel infoContainer = new JPanel(new GridBagLayout());
        infoContainer.setBackground(MyStyles.color.BACKGROUND);

        int i = 0;
        gbc.insets = new Insets(4, 4, 4, 4);
        for (Room room : hotel.getRooms()) {
            gbc.gridy = i / 7;
            gbc.gridx = i % 7 * 2;
            this.roomsAvailability[i] = MyComponents.smallTitleText("");

            gbc.insets.right = 4;
            gbc.insets.left = 8;
            infoContainer.add(MyComponents.headerText(room.getName()), gbc);
            gbc.insets.right = 8;
            gbc.insets.left = 4;
            gbc.gridx++;

            infoContainer.add(this.roomsAvailability[i], gbc);
            i++;
        }

        this.updateAvailability(hotel.getRooms(), 1);

        add(infoContainer, BorderLayout.CENTER);
    }

    /**
     * Updates the availability of the rooms
     * 
     * @param rooms array list of rooms
     * @param index day of room to be entered
     */
    public void updateAvailability(ArrayList<Room> rooms, int index) {
        this.selectedDate.setText("Selected: Day " + index);
        index--;

        Dimension checkSize = new Dimension(25, 12);

        for (int i = 0; i < this.roomsAvailability.length; i++) {
            boolean isAvailable = rooms.get(i).getDayAvailability(index);
            this.roomsAvailability[i].setText("" + (isAvailable ? '✗' : '✓'));
            this.roomsAvailability[i].setForeground((isAvailable) ? MyStyles.color.RED : MyStyles.color.GREEN);

            this.roomsAvailability[i].setMinimumSize(checkSize);
            this.roomsAvailability[i].setPreferredSize(checkSize);
            this.roomsAvailability[i].setMaximumSize(checkSize);
        }

    }

    /**
     * Getter for date selector buttons
     * 
     * @return date selector buttons
     */
    public JButton[] getDateSelector() {
        return dateSelector.getButtons();
    }
}
