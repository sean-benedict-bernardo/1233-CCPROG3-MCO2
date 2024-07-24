package view.managehotel;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Hotel;
import view.common.MyStyles;
import view.common.components.MyComponents;

public class RemoveHotel extends JPanel{
    Hotel hotel;
    JButton deleteButton;

    public RemoveHotel(Hotel hotel){
        this.hotel = hotel;
        setLayout(new GridBagLayout());

        this.initFrame();

        setBackground(MyStyles.color.BACKGROUND);
    }

    public void initFrame(){
        deleteButton = MyComponents.button("Delete "+ this.hotel.getName());
        this.add(deleteButton);
    }

    public JButton getDeleteButton(){
        return this.deleteButton;
    }
}
