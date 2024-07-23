package view.viewhotel;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

import model.Hotel;
import model.rooms.Room;
import view.common.MyStyles;
import view.common.components.*;

public class RoomAvailability extends JPanel {
    private DateSelector dateSelector;

    private JLabel selectedDate;
    private JLabel roomsAvailability[];

    public RoomAvailability(Hotel hotel) {
        super(new BorderLayout());
        setBackground(MyStyles.color.BACKGROUND);
        setBackground(MyStyles.color.FOREGROUND);

        this.dateSelector = new DateSelector(hotel);
        this.roomsAvailability = new JLabel[hotel.getNumRooms()];
        this.initFrame(hotel);
    }

    private void initFrame(Hotel hotel) {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 12;
        gbc.ipady = 12;

        // WEST
        JPanel dateSelectPanel = new JPanel(new GridBagLayout());
        dateSelectPanel.setBackground(MyStyles.color.BACKGROUND);

        this.selectedDate = MyComponents.bodyText("Viewing Availability on Day: 1");

        dateSelectPanel.add(this.selectedDate, gbc);
        gbc.gridy = 1;
        dateSelectPanel.add(this.dateSelector, gbc);

        add(dateSelectPanel, BorderLayout.WEST);

        // CENTER
        JPanel infoContainer = new JPanel(new GridBagLayout());
        infoContainer.setBackground(MyStyles.color.BACKGROUND);

        int i = 0;
        for (Room room : hotel.getRooms()) {
            gbc.gridy = i / 7;
            gbc.gridx = i % 7;
            this.roomsAvailability[i] = MyComponents.bodyText(
                    String.format("%s - %c",
                            room.getName(),
                            !room.getDayAvailability(0) ? '✓' : '✗'),
                    SwingConstants.CENTER);
            infoContainer.add(this.roomsAvailability[i], gbc);

            this.roomsAvailability[i].setMinimumSize(new Dimension(64, 24));
            this.roomsAvailability[i].setPreferredSize(new Dimension(64, 24));
            i++;
        }

        add(infoContainer, BorderLayout.CENTER);
    }

    public void updateAvailability(ArrayList<Room> rooms, int index) {
        this.selectedDate.setText("Viewing Availability on Day: " + index);
        index--;
        for (int i = 0; i < this.roomsAvailability.length; i++) {
            this.roomsAvailability[i].setText(
                    String.format("%s - %c",
                            rooms.get(i).getName(),
                            !rooms.get(i).getDayAvailability(index) ? '✓' : '✗'));
        }
    }

    public JButton[] getDateSelector() {
        return dateSelector.getButtons();
    }
}
