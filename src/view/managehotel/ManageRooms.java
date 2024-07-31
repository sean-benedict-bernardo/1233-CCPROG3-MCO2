package view.managehotel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import model.Hotel;
import view.common.MyStyles;
import view.common.components.MyComponents;
import view.common.components.RoomTypeSelector;

/**
 * ManageRooms contains the GUI component
 * that allows user to add and delete rooms
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class ManageRooms extends JPanel {
    private Hotel hotel;

    private RoomTypeSelector newRoomType;
    private JComboBox<String> roomSelect;
    private JProgressBar numRooms;

    private JButton roomAdd, roomDelete;

    /**
     * ManageRooms Constructor
     * 
     * @param hotel where rooms will be added to and deleted from
     */
    public ManageRooms(Hotel hotel) {
        super(new BorderLayout());
        this.hotel = hotel;

        this.initComponents();
        this.initFrame();
    }

    /**
     * Initializes components
     */
    private void initComponents() {
        this.roomAdd = MyComponents.button("Add Room");
        this.roomDelete = MyComponents.button("Delete Room");

        this.newRoomType = new RoomTypeSelector();
        this.roomSelect = new JComboBox<>(this.hotel.getRoomNames());
        this.roomSelect.setFont(MyStyles.font.HEADER);
    }

    /**
     * Initializes frame
     */
    private void initFrame() {
        setBackground(MyStyles.color.BACKGROUND);
        setForeground(MyStyles.color.FOREGROUND);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);

        JPanel containerPanel = new JPanel(new GridBagLayout());
        containerPanel.setBackground(MyStyles.color.BACKGROUND);
        containerPanel.setForeground(MyStyles.color.FOREGROUND);

        this.numRooms = MyComponents.progressBar(0, 50, this.hotel.getNumRooms());
        Dimension roomCtrDimensions = new Dimension(this.numRooms.getPreferredSize());
        roomCtrDimensions.height = 75;
        this.numRooms.setBounds(new Rectangle(roomCtrDimensions));

        // Set Values
        this.numRooms.setStringPainted(true);
        this.updateRoomCount();

        JComponent gridMap[][] = {
                { MyComponents.smallTitleText("Manage Rooms") },
                { MyComponents.bodyText("Add and Delete Rooms", SwingConstants.CENTER, MyComponents.ITALICS) },
                { MyComponents.headerText("Select Type of Room to Add"), this.newRoomType, this.roomAdd },
                { new JSeparator(SwingConstants.VERTICAL) },
                { MyComponents.headerText("Select Room to Delete"), this.roomSelect, this.roomDelete },
                { this.numRooms },
                { MyComponents.bodyText(
                        "<html><body style='text-align: center;'>NOTE: Rooms with active reservations<br>cannot be deleted</body></html>",
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
        this.updateRoomCount();
    }

    /**
     * This updates the JProgressBar of the room count
     */
    private void updateRoomCount() {
        int roomCtr = this.hotel.getNumRooms();
        this.numRooms.setString(roomCtr + " / 50 Rooms");
        this.numRooms.setValue(roomCtr);
    }

    /**
     * Getter for selected room type
     * 
     * @return selected room type
     */
    public char getRoomType() {
        return this.newRoomType.getSelected();
    }

    /**
     * Getter for selected room
     * 
     * @return selected room
     */
    public String getSelectedRoom() {
        return this.roomSelect.getSelectedItem().toString();
    }

    /**
     * Getter for add room button
     * 
     * @return add room button
     */
    public JButton getRoomAdd() {
        return this.roomAdd;
    }

    /**
     * Getter for remove room button
     * 
     * @return remove room button
     */
    public JButton getRoomDelete() {
        return this.roomDelete;
    }
}
