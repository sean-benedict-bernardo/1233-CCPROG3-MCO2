package view.createhotel;

import java.awt.*;
import javax.swing.*;

import view.common.MyStyles;
import view.common.components.MyComponents;
import view.common.components.RoomTypeSelector;

public class CreateHotel extends JDialog {
    private JPanel createHotel;
    private JTextField hotelNameField;
    private RoomTypeSelector roomTypeSelector;
    private JButton createHotelButton, cancelCreateButton;

    public CreateHotel() {
        this.createHotel = new JPanel(new GridBagLayout());
        this.createHotel.setBackground(MyStyles.color.BACKGROUND);
        this.roomTypeSelector = new RoomTypeSelector();
        setTitle("Hotel Creation");

        setBackground(MyStyles.color.BACKGROUND);

        this.initFrame();

        setMinimumSize(MyStyles.misc.SCREEN_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
    }

    public void initFrame() {
        GridBagConstraints gbc = new GridBagConstraints();
        this.createHotelButton = MyComponents.button("Create Hotel");
        this.cancelCreateButton = MyComponents.button("Return to Main Menu");
        this.hotelNameField = new JTextField("", 20);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(3, 0, 3, 0);

        gbc.gridy = 0;
        gbc.gridx = 0;
        this.createHotel.add(MyComponents.headerText("Hotel name: "), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        this.createHotel.add(this.hotelNameField, gbc);
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        this.createHotel.add(MyComponents.headerText("First room type: "), gbc);
        gbc.gridx = 1;
        this.createHotel.add(this.roomTypeSelector, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        this.createHotel.add(this.createHotelButton, gbc);
        gbc.gridx = 1;
        this.createHotel.add(this.cancelCreateButton, gbc);

        add(this.createHotel);
    }

    public JButton getCreateButton() {
        return this.createHotelButton;
    }

    public JButton getCancelButton() {
        return this.cancelCreateButton;
    }

    public String getHotelField() {
        return (!this.hotelNameField.getText().trim().isEmpty()) ? this.hotelNameField.getText() : null;
    }

    public char getFirstRoomType() {
        return this.roomTypeSelector.getSelected();
    }
}
