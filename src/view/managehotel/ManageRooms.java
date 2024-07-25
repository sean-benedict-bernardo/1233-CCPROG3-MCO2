package view.managehotel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import model.Hotel;
import view.common.MyStyles;
import view.common.components.MyComponents;
import view.common.components.RoomTypeSelector;

public class ManageRooms extends JPanel {
    private Hotel hotel;

    private RoomTypeSelector newRoomType;
    private JComboBox<String> roomSelect;

    private JButton roomAdd, roomDelete;

    public ManageRooms(Hotel hotel) {
        super(new BorderLayout());
        this.hotel = hotel;

        setBackground(MyStyles.color.BACKGROUND);
        setForeground(MyStyles.color.FOREGROUND);

        this.initComponents();
        this.initFrame();
    }

    private void initComponents() {
        this.roomAdd = MyComponents.button("Add Room");
        this.roomDelete = MyComponents.button("Delete Room");

        this.newRoomType = new RoomTypeSelector();
        this.roomSelect = new JComboBox<>(this.hotel.getRoomNames());
        this.roomSelect.setFont(MyStyles.font.HEADER);
    }

    private void initFrame() {
        setBackground(MyStyles.color.BACKGROUND);
        setForeground(MyStyles.color.FOREGROUND);

        JPanel containerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);

        // First Column
        gbc.gridx = 0;
        gbc.gridy = 0;
        containerPanel.add(MyComponents.headerText("Select Type of Room to Add"), gbc);
        gbc.gridy = 1;
        containerPanel.add(MyComponents.headerText("Select Room to Delete"), gbc);

        // Second Column
        gbc.gridx = 1;
        gbc.gridy = 0;
        containerPanel.add(this.newRoomType, gbc);
        gbc.gridy = 1;
        containerPanel.add(this.roomSelect, gbc);

        // Third Column
        gbc.gridx = 2;
        gbc.gridy = 0;
        containerPanel.add(this.roomAdd, gbc);
        gbc.gridy = 1;
        containerPanel.add(this.roomDelete, gbc);

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
}
