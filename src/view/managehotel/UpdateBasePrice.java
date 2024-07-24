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

public class UpdateBasePrice extends JPanel{
    private Hotel hotel;
    private JTextField basePrice;
    private JButton confirmButton;
    private GridBagConstraints gbc;

    public UpdateBasePrice(Hotel hotel){
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
        JLabel labelTxtBox = MyComponents.headerText("New Base Price:");
        basePrice = new JTextField(Float.toString(this.hotel.getBasePrice()), 20); 

        container.setLayout(new FlowLayout());
        container.setBackground(MyStyles.color.BACKGROUND);

        basePrice.setFont(MyStyles.font.BODY);

        container.add(labelTxtBox);
        gbc.insets = new Insets(0, 0, 0, 20);
        container.add(basePrice, gbc);
        container.add(confirmButton);
        this.add(container);
    }

    public JButton getConfirmButton(){
        return this.confirmButton;
    }

    public JTextField getBasePriceField(){
        return this.basePrice;
    }
}
