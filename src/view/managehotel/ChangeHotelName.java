package view.managehotel;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Hotel;
import view.common.MyStyles;
import view.common.components.MyComponents;

public class ChangeHotelName extends JPanel {
    private Hotel hotel;
    private JTextField hotelName;
    private JButton confirmButton;

    public ChangeHotelName(Hotel hotel) {
        super();
        this.hotel = hotel;

        setLayout(new GridBagLayout());

        this.initFrame();

        setBackground(MyStyles.color.BACKGROUND);
    }

    private void initFrame() {
        JPanel container = new JPanel(new GridBagLayout());
        container.setBackground(MyStyles.color.BACKGROUND);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(8, 8, 8, 8);

        JLabel labelTxtBox = MyComponents.headerText("New Hotel Name:");

        this.hotelName = MyComponents.textField(this.hotel.getName());
        hotelName.setFont(MyStyles.font.HEADER);

        this.confirmButton = MyComponents.button("Change Hotel Name");
        this.confirmButton.setBorder(new EmptyBorder(12, 12, 12, 12));

        container.add(labelTxtBox, gbc);
        gbc.gridx = 1;
        container.add(hotelName, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        container.add(confirmButton, gbc);
        this.add(container);
    }

    public JButton getConfirmButton() {
        return this.confirmButton;
    }

    public JTextField getHotelNameField() {
        return this.hotelName;
    }
}
