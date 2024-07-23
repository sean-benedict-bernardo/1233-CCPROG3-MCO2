package view.viewhotel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.rooms.Room;
import view.common.MyStyles;
import view.common.components.MyComponents;
import view.common.components.RoomSelector;

public class RoomInformation extends JPanel {
    private JPanel roomSelectPanel;
    private JButton roomButtons[];

    // Info blocks
    private JLabel infoRoomName;
    private JLabel infoRoomType;
    private JLabel infoNightlyRate;
    private JLabel infoAvailabilityDate[] = new JLabel[31];

    public RoomInformation(ArrayList<Room> rooms) {
        super();
        setLayout(new BorderLayout());
        this.roomButtons = new JButton[rooms.size()];
        this.roomSelectPanel = new RoomSelector(rooms);

        this.initFrame(rooms);
    }

    private void initFrame(ArrayList<Room> rooms) {
        setBackground(MyStyles.color.BACKGROUND);
        setBackground(MyStyles.color.FOREGROUND);

        add(this.roomSelectPanel, BorderLayout.WEST);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.ipadx = 4;
        gbc.ipady = 4;

        // CENTER
        JPanel infoPanel = new JPanel(new GridBagLayout());
        JPanel availabilityPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcCalendar = new GridBagConstraints();

        infoPanel.setBackground(MyStyles.color.BACKGROUND);
        availabilityPanel.setBackground(MyStyles.color.BACKGROUND);

        // Info text
        Room firstRoom = rooms.get(0); // This is assumed to exist

        this.infoRoomName = MyComponents.bodyText("Room " + firstRoom.getName());
        this.infoRoomType = MyComponents.bodyText("Room Type: " + firstRoom.getRoomType());
        this.infoNightlyRate = MyComponents.bodyText(String.format("Base Nightly Rate: %.2f", firstRoom.getPrice()));
        for (int i = 0; i < infoAvailabilityDate.length; i++)
            infoAvailabilityDate[i] = MyComponents.bodyText(
                    String.format("%d - %c", i + 1, !firstRoom.getDayAvailability(i) ? '✓' : '✗'),
                    JLabel.RIGHT);

        gbcCalendar.ipadx = 5;
        gbcCalendar.ipady = 2;
        gbcCalendar.weightx = 1;
        gbcCalendar.fill = GridBagConstraints.HORIZONTAL;
        for (int i = 0; i < infoAvailabilityDate.length; i++) {
            gbcCalendar.gridx = i % 7;
            gbcCalendar.gridy = i / 7;

            availabilityPanel.add(infoAvailabilityDate[i], gbcCalendar);
        }

        ++gbcCalendar.gridx;
        infoPanel.add(infoRoomName, gbc);
        infoPanel.add(infoNightlyRate, gbc);
        infoPanel.add(availabilityPanel, gbc);
        infoPanel.add(MyComponents.bodyText("✓ - Room is available | ✗ - Room is Reserved"), gbc);
        add(infoPanel, BorderLayout.CENTER);
    }

    /**
     * Room Name
     * Price Per Night
     * JPanel containing availability
     */
    public void dispRoomInfo(Room room) {
        this.infoRoomName.setText("Room " + room.getName());
        this.infoRoomType.setText("Room Type: " + room.getRoomType());
        this.infoNightlyRate.setText(String.format("Nightly Rate: %.2f", room.getPrice()));

        for (int i = 0; i < this.infoAvailabilityDate.length; i++) {
            this.infoAvailabilityDate[i]
                    .setText(String.format("%d - %c", i + 1, !room.getDayAvailability(i) ? '✓' : '✗'));
        }
    }

    public ArrayList<JButton> getRoomSelectButtons() {
        return ((RoomSelector) this.roomSelectPanel).getRoomSelectButtons();
    }
}
