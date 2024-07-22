package viewer.viewhotel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import main.rooms.Room;
import viewer.common.MyComponents;
import viewer.common.MyStyles;

public class RoomInformation extends JPanel {
    private JPanel roomSelectPanel;
    private JButton roomButtons[];
    private JScrollPane roomSelect;

    // Info blocks
    private JLabel infoRoomName;
    private JLabel infoRoomType;
    private JLabel infoNightlyRate;
    private JLabel infoAvailabilityDate[] = new JLabel[31];
    private JLabel infoLegend;

    public RoomInformation(ArrayList<Room> rooms) {
        super();
        setLayout(new BorderLayout());
        this.roomButtons = new JButton[rooms.size()];
        this.roomSelectPanel = new JPanel();

        this.initFrame(rooms);
    }

    private void initFrame(ArrayList<Room> rooms) {
        setBackground(MyStyles.color.BACKGROUND);
        setBackground(MyStyles.color.FOREGROUND);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // WEST
        this.roomSelectPanel.setLayout(new GridBagLayout());

        // Buttons for room select
        for (int i = 0; i < rooms.size(); i++) {
            this.roomButtons[i] = MyComponents.JButton(rooms.get(i).getName());
            this.roomButtons[i].setSize(new Dimension(50, 16));
            this.roomSelectPanel.add(this.roomButtons[i], gbc);
        }
        // dummy element
        gbc.weighty = 1;
        this.roomSelectPanel.add(new JPanel(), gbc);

        // Room Select
        this.roomSelect = new JScrollPane(this.roomSelectPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        roomSelect.setBackground(MyStyles.color.GRAY);
        roomSelect.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
        add(this.roomSelect, BorderLayout.WEST);

        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.ipadx = 4;
        gbc.ipady = 4;

        // CENTER
        JPanel infoPanel = new JPanel(new GridBagLayout());
        JPanel availabilityPanel = new JPanel(new GridBagLayout());
        GridBagConstraints calendarConstraints = new GridBagConstraints();

        infoPanel.setBackground(MyStyles.color.BACKGROUND);
        availabilityPanel.setBackground(MyStyles.color.BACKGROUND);

        // Info text
        this.infoRoomName = MyComponents.bodyText("<Select a Room>");
        this.infoRoomType = MyComponents.bodyText();
        this.infoNightlyRate = MyComponents.bodyText();
        this.infoLegend = MyComponents.bodyText();
        for (int i = 0; i < infoAvailabilityDate.length; i++)
            infoAvailabilityDate[i] = MyComponents.bodyText("", JLabel.RIGHT);

        calendarConstraints.ipadx = 5;
        calendarConstraints.ipady = 2;
        calendarConstraints.weightx = 1;
        calendarConstraints.fill = GridBagConstraints.HORIZONTAL;
        for (int i = 0; i < infoAvailabilityDate.length; i++) {
            calendarConstraints.gridx = i % 7;
            calendarConstraints.gridy = i / 7;

            availabilityPanel.add(infoAvailabilityDate[i], calendarConstraints);
        }

        ++calendarConstraints.gridx;
        infoPanel.add(infoRoomName, gbc);
        infoPanel.add(infoNightlyRate, gbc);
        infoPanel.add(availabilityPanel, gbc);
        infoPanel.add(infoLegend, gbc);
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
        this.infoLegend.setText("✓ - Room is available | ✗ - Room is Reserved");

        for (int i = 0; i < this.infoAvailabilityDate.length; i++) {
            this.infoAvailabilityDate[i]
                    .setText(String.format("%d - %c", i + 1, !room.getDayAvailability(i) ? '✓' : '✗'));
        }
    }

    public JButton[] getRoomSelectButtons() {
        return this.roomButtons;
    }
}
