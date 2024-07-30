package view.common.components;

import javax.swing.*;
import java.awt.*;

import model.rooms.Room;
import view.common.MyStyles;

import java.util.ArrayList;

/**
 * RoomSelector is a JPanel that enables
 * users to select a specific room
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class RoomSelector extends JPanel {
    private ArrayList<JButton> roomButtons;
    private JScrollPane roomSelect;

    /**
     * RoomSelector Constructor
     * 
     * @param rooms ArrayList of Room to create buttons with
     */
    public RoomSelector(ArrayList<Room> rooms) {
        super(new BorderLayout());
        this.roomButtons = new ArrayList<>();

        this.initFrame(rooms);
    }

    /**
     * Initializes the Frame
     * 
     * @param rooms ArrayList of rooms to show
     */
    private void initFrame(ArrayList<Room> rooms) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.ipadx = 4;
        gbc.ipady = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel scrollArea = new JPanel(new GridBagLayout());

        for (Room room : rooms) {
            JButton button = MyComponents.button(room.getName());
            button.setName(room.getName());
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.setMargin(new Insets(0, 0, 0, 10));
            button.setPreferredSize(new Dimension(48, 28));
            button.setMinimumSize(new Dimension(48, 28));
            this.roomButtons.add(button);
            scrollArea.add(button, gbc);
        }

        // Room Select
        this.roomSelect = new JScrollPane(scrollArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        roomSelect.setBackground(MyStyles.color.GRAY);

        roomSelect.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        roomSelect.getVerticalScrollBar().setUnitIncrement(16);

        add(roomSelect, BorderLayout.CENTER);
    }

    /**
     * Getter for JButton
     * 
     * @return ArrayList of all room select JButtons
     */
    public ArrayList<JButton> getRoomSelectButtons() {
        return this.roomButtons;
    }
}
