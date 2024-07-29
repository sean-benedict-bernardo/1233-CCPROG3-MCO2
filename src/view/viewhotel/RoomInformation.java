package view.viewhotel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Hotel;
import model.rooms.Room;
import view.common.MyStyles;
import view.common.components.MyComponents;
import view.common.components.RoomSelector;

public class RoomInformation extends JPanel {
    private JPanel roomSelectPanel;

    // Info blocks
    private JLabel infoRoomName;
    private JLabel infoRoomType;
    private JLabel infoNightlyRate;
    private JLabel infoAvailabilityDate[] = new JLabel[Hotel.NUM_NIGHTS];

    public RoomInformation(ArrayList<Room> rooms) {
        super();
        setLayout(new BorderLayout());
        this.roomSelectPanel = new RoomSelector(rooms);

        this.initFrame(rooms);
        // show first room info
        this.dispRoomInfo(rooms.get(0));
    }

    private void initFrame(ArrayList<Room> rooms) {
        setBackground(MyStyles.color.BACKGROUND);
        setBackground(MyStyles.color.FOREGROUND);

        add(this.roomSelectPanel, BorderLayout.WEST);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.weightx = 0;
        gbc.weighty = 0;

        // CENTER
        JPanel infoPanel = new JPanel(new GridBagLayout());
        JPanel availabilityPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcCalendar = new GridBagConstraints();

        infoPanel.setBackground(MyStyles.color.BACKGROUND);
        availabilityPanel.setBackground(MyStyles.color.BACKGROUND);

        this.infoRoomName = MyComponents.headerText();
        this.infoRoomType = MyComponents.headerText();
        this.infoNightlyRate = MyComponents.headerText();
        for (int i = 0; i < infoAvailabilityDate.length; i++)
            infoAvailabilityDate[i] = MyComponents.smallTitleText("");

        gbcCalendar.insets = new Insets(2, 4, 2, 4);
        gbcCalendar.fill = GridBagConstraints.HORIZONTAL;
        for (int i = 0; i < infoAvailabilityDate.length; i++) {
            gbcCalendar.gridy = i / 7;
            gbcCalendar.gridx = i % 7 * 2;

            gbcCalendar.insets.right = 4;
            gbcCalendar.insets.left = 8;
            availabilityPanel.add(MyComponents.bodyText("" + (i + 1)), gbcCalendar);
            gbcCalendar.gridx++;
            gbcCalendar.insets.right = 8;
            gbcCalendar.insets.left = 4;
            availabilityPanel.add(infoAvailabilityDate[i], gbcCalendar);
        }

        infoPanel.add(infoRoomName, gbc);
        infoPanel.add(infoRoomType, gbc);
        infoPanel.add(infoNightlyRate, gbc);
        infoPanel.add(MyComponents.headerText("Availability", SwingConstants.CENTER), gbc);
        infoPanel.add(availabilityPanel, gbc);
        infoPanel.add(MyComponents.bodyText("✓ - Room is available | ✗ - Room is Reserved"), gbc);
        add(infoPanel, BorderLayout.CENTER);
    }

    public void dispRoomInfo(Room room) {
        this.infoRoomName.setText("Room " + room.getName());
        this.infoRoomType.setText(room.getRoomType() + " Room");
        this.infoNightlyRate.setText(String.format("Base Nightly Rate: %.2f", room.getPrice()));

        for (int i = 0; i < this.infoAvailabilityDate.length; i++) {
            boolean isAvailable = room.getDayAvailability(i);
            this.infoAvailabilityDate[i].setText("" + ((isAvailable) ? '✗' : '✓'));
            this.infoAvailabilityDate[i].setForeground((isAvailable) ? MyStyles.color.RED : MyStyles.color.GREEN);
        }
    }

    public ArrayList<JButton> getRoomSelectButtons() {
        return ((RoomSelector) this.roomSelectPanel).getRoomSelectButtons();
    }
}
