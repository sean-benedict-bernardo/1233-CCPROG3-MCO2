package view.managehotel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import model.Hotel;
import view.common.MyStyles;
import view.common.components.MyComponents;
import view.common.components.RoomTypeSelector;

public class ManageRooms extends JPanel {
    private Hotel hotel;

    private RoomTypeSelector newRoomType;
    private JComboBox<String> roomSelect;

    private JButton roomAdd, roomDelete, roomDeleteAll;

    public ManageRooms(Hotel hotel) {
        super(new BorderLayout());
        this.hotel = hotel;

        this.initComponents();
        this.initFrame();
    }

    private void initComponents() {
        this.roomAdd = MyComponents.button("Add Room");
        this.roomDelete = MyComponents.button("Delete Room");
        this.roomDeleteAll = MyComponents.button("Delete All Rooms");

        this.newRoomType = new RoomTypeSelector();
        this.roomSelect = new JComboBox<>(this.hotel.getRoomNames());
        this.roomSelect.setFont(MyStyles.font.HEADER);
    }

    private void initFrame() {
        setBackground(MyStyles.color.BACKGROUND);
        setForeground(MyStyles.color.FOREGROUND);

        JPanel containerPanel = new JPanel(new GridBagLayout());
        containerPanel.setBackground(MyStyles.color.BACKGROUND);
        containerPanel.setForeground(MyStyles.color.FOREGROUND);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);

        JComponent gridMap[][] = {
                { MyComponents.smallTitleText("Manage Rooms") },
                { MyComponents.bodyText("Add and Delete Rooms", SwingConstants.CENTER, MyComponents.ITALICS) },
                { MyComponents.headerText("Select Type of Room to Add"), this.newRoomType },
                { this.roomAdd },
                { new JSeparator() },
                { MyComponents.headerText("Select Room to Delete"), this.roomSelect },
                { this.roomDelete, this.roomDeleteAll },
                { MyComponents.bodyText(
                        "<html><body style='text-align: center;'>NOTE: Rooms with active reservations<br>cannot be delted</body></html>",
                        SwingConstants.CENTER, MyComponents.ITALICS) }
        };

        for (int i = 0; i < gridMap.length; i++) {
            for (int j = 0; j < gridMap[i].length; j++) {
                gbc.gridy = i;
                gbc.gridx = j;
                gbc.gridwidth = (j + 1 == gridMap[i].length) ? GridBagConstraints.REMAINDER : 1;
                containerPanel.add(gridMap[i][j], gbc);
            }
        }

        add(containerPanel, BorderLayout.CENTER);
    }

    /**
     * This method updates the content after each addition or deletion
     */
    public void updateContent() {
        this.roomSelect.setModel(new DefaultComboBoxModel<>(this.hotel.getRoomNames()));
    }

    public char getRoomType() {
        return this.newRoomType.getSelected();
    }

    public String getSelectedRoom() {
        return this.roomSelect.getSelectedItem().toString();
    }

    public JButton getRoomAdd() {
        return this.roomAdd;
    }

    public JButton getRoomDelete() {
        return this.roomDelete;
    }

    public JButton getRoomDeleteAll() {
        return this.roomDeleteAll;
    }
}
