package view.common.components;

import javax.swing.*;
import java.awt.*;

import model.rooms.Room;
import view.common.MyStyles;

import java.util.ArrayList;

public class RoomSelector extends JPanel {
    private ArrayList<JButton> roomButtons;
    private JScrollPane roomSelect;

    public RoomSelector(ArrayList<Room> rooms) {
        super(new BorderLayout());
        this.roomButtons = new ArrayList<>();

        this.initFrame(rooms);
    }

    private void initFrame(ArrayList<Room> rooms) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel scrollArea = new JPanel(new GridBagLayout());

        for (Room room : rooms) {
            JButton button = MyComponents.button(room.getName());
            button.setName(room.getName());
            button.setHorizontalAlignment(SwingConstants.LEFT);
            this.roomButtons.add(button);
            scrollArea.add(button, gbc);
        }

        // Room Select
        this.roomSelect = new JScrollPane(scrollArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
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
