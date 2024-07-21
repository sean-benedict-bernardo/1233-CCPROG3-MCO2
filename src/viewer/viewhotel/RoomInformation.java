package viewer.viewhotel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.rooms.Room;
import viewer.common.MyComponents;
import viewer.common.MyStyles;

public class RoomInformation extends JPanel {
    private JPanel roomSelectPanel;
    private JButton roomButtons[];
    private JScrollPane roomSelect;

    private JLabel infoRoomName;
    private JLabel infoNightlyRate;
    private JLabel infoAvailabilityDate[] = new JLabel[31];

    public RoomInformation(ArrayList<Room> rooms) {
        super();
        setLayout(new BorderLayout());
        this.roomButtons = new JButton[rooms.size()];
        this.roomSelectPanel = new JPanel();
        this.roomSelectPanel.setLayout(new BoxLayout(this.roomSelectPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < rooms.size(); i++) {
            this.roomButtons[i] = MyComponents.JButton(rooms.get(i).getName());
            this.roomButtons[i].setSize(new Dimension(50, 16));
            this.roomSelectPanel.add(this.roomButtons[i]);
        }

        this.initFrame();
    }

    private void initFrame() {
        setBackground(MyStyles.color.BACKGROUND);
        setBackground(MyStyles.color.FOREGROUND);

        // WEST
        this.roomSelect = new JScrollPane(this.roomSelectPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        roomSelect.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        Dimension scrollPanelSize = new Dimension(roomSelect.getPreferredSize());
        scrollPanelSize.width += scrollPanelSize.getWidth() + 10;
        this.roomSelect.setSize(scrollPanelSize);
        add(this.roomSelect, BorderLayout.WEST);

        this.infoRoomName = MyComponents.JLabel("<Select a Room>");
        this.infoNightlyRate = MyComponents.JLabel("");
        for (int i = 0; i < infoAvailabilityDate.length; i++) {
            infoAvailabilityDate[i] = MyComponents.JLabel(String.format("%d - %c", i + 1, '✓'));
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipadx = 4;
        gbc.ipady = 4;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // CENTER
        JPanel infoPanel = new JPanel(new GridBagLayout());
        JPanel availabilityPanel = new JPanel(new GridLayout(0, 7, 4, 4));

        infoPanel.setBackground(MyStyles.color.BACKGROUND);
        availabilityPanel.setBackground(MyStyles.color.BACKGROUND);

        for (JLabel jLabel : infoAvailabilityDate) {
            availabilityPanel.add(jLabel);
        }

        infoPanel.add(infoRoomName, gbc);
        infoPanel.add(infoNightlyRate, gbc);
        infoPanel.add(availabilityPanel, gbc);
        add(infoPanel, BorderLayout.CENTER);
    }

    /**
     * Room Name
     * Price Per Night
     * JPanel containing availability
     */
    public void dispRoomInfo(Room room) {
        this.infoRoomName.setText("Room " + room.getName());
        this.infoNightlyRate.setText("Nightly Rate: " + room.getPrice());

        for (int i = 0; i < this.infoAvailabilityDate.length; i++) {
            this.infoAvailabilityDate[i]
                    .setText(String.format("%d - %c", i + 1, !room.getDayAvailability(i) ? '✓' : '✗'));
        }
    }

    public JButton[] getRoomSelectButtons() {
        return this.roomButtons;
    }
}
