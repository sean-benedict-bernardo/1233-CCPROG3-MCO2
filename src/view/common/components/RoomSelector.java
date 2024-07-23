package view.common.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.rooms.Room;
import view.common.MyStyles;

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

        int i = 0;
        for (Room room : rooms) {
            JButton button = MyComponents.button(room.getName());
            button.setPreferredSize(new Dimension(75, 36));
            this.roomButtons.add(button);
            scrollArea.add(this.roomButtons.get(i++), gbc);
        }

        // Room Select
        this.roomSelect = new JScrollPane(scrollArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        roomSelect.setBackground(MyStyles.color.GRAY);
        roomSelect.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        roomSelect.getVerticalScrollBar().setUnitIncrement(16);

        add(roomSelect, BorderLayout.CENTER);

    }

    public ArrayList<JButton> getRoomSelectButtons() {
        return this.roomButtons;
    }
}
