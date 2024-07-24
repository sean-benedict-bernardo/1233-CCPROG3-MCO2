package view.managehotel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.rooms.Room;
import view.common.MyStyles;
import view.common.components.MyComponents;

public class RemoveRoom extends JPanel{
    ArrayList<Room> roomList;
    JButton roomNameSelect;
    JButton confirmButton;
    GridBagConstraints gbc;

    public RemoveRoom(ArrayList<Room> roomList){
        this.roomList = roomList;
        this.gbc = new GridBagConstraints();

        setLayout(new GridBagLayout());

        initFrame();

        setBackground(MyStyles.color.BACKGROUND);
    }

    public void initFrame(){
        JPanel container = new JPanel();
        JLabel currentRoomLabel = MyComponents.headerText("Select a room to delete:");
        roomNameSelect = MyComponents.smallButton("...");
        confirmButton = MyComponents.button("Confirm deletion");

        container.setLayout(new GridBagLayout());
        container.setBackground(MyStyles.color.BACKGROUND);
        
        this.gbc.insets = new Insets(0, 0, 0, 10);
        this.gbc.anchor = GridBagConstraints.WEST;
        container.add(currentRoomLabel, this.gbc);
        roomNameSelect.setBorder(BorderFactory.createEmptyBorder(0, roomNameSelect.getText().length() * 7, 0, roomNameSelect.getText().length() * 7));
        this.gbc.anchor = GridBagConstraints.EAST;
        container.add(roomNameSelect, this.gbc);
        this.gbc.anchor = GridBagConstraints.WEST;
        this.add(container, this.gbc);

        this.gbc.gridy = 1;
        this.gbc.insets = new Insets(20, 0, 0, 0);
        this.gbc.anchor = GridBagConstraints.WEST;
        confirmButton.setBorder(BorderFactory.createEmptyBorder(0, 70, 0, 70));
        this.add(confirmButton, gbc);
    }

    public JButton getNameSelectButton(){
        return this.roomNameSelect;
    }

    public JButton getConfirmButton(){
        return this.confirmButton;
    }

    public String getCurrentRoomName(){
        return this.roomNameSelect.getText();
    }
}
