package view.managehotel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Hotel;
import view.common.MyStyles;
import view.common.components.MyComponents;

public class ChangeHotelName extends JPanel{
    private Hotel hotel;
    private JTextField hotelName;
    private JButton confirmButton;
    private GridBagConstraints gbc;

    public ChangeHotelName(Hotel hotel){
        super();
        this.hotel = hotel;
        
        setLayout(new GridBagLayout());

        initFrame();

        setBackground(MyStyles.color.BACKGROUND);
    }

    public void initFrame(){
        JPanel container = new JPanel();
        this.gbc = new GridBagConstraints();
        confirmButton = MyComponents.button("Confirm");
        JLabel labelTxtBox = MyComponents.headerText("New Hotel Name:");
        hotelName = new JTextField(this.hotel.getName(), 20); 

        container.setLayout(new FlowLayout());
        container.setBackground(MyStyles.color.BACKGROUND);

        hotelName.setFont(MyStyles.font.BODY);

        container.add(labelTxtBox);
        gbc.insets = new Insets(0, 0, 0, 20);
        container.add(hotelName, gbc);
        container.add(confirmButton);
        this.add(container);
    }

    public JButton getConfirmButton(){
        return this.confirmButton;
    }

    public JTextField getHotelNameField(){
        return this.hotelName;
    }
}
