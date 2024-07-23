package view.managehotel;

import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import model.Hotel;
import view.common.MyStyles;
import view.common.components.MyComponents;

public class AddRoom extends JDialog{
    Hotel hotel;
    private ButtonGroup radioButtonGroup;
    private JRadioButton roomType[] = new JRadioButton[3];
    private JButton confirmButton;

    public AddRoom(Hotel hotel){
        super();
        this.hotel = hotel;

        setLayout(new GridBagLayout());

        initFrame();

        setBackground(MyStyles.color.BACKGROUND);
    }
    
    public void initFrame(){
        JLabel roomTypePrompt = MyComponents.bodyText("Select a room type for the new room:");
        this.roomType[0] = new JRadioButton("Standard");
        this.roomType[1] = new JRadioButton("Deluxe");
        this.roomType[2] = new JRadioButton("Executive");

        for (JRadioButton jRadioButton : this.roomType) {
            jRadioButton.setFont(MyStyles.font.BODY);
            jRadioButton.setBackground(MyStyles.color.BACKGROUND);
            jRadioButton.setForeground(MyStyles.color.FOREGROUND);
            jRadioButton.setFocusPainted(false);
            this.radioButtonGroup.add(jRadioButton);
        }

        this.roomType[0].setSelected(true);
        confirmButton = new JButton("Confirm Selection");
    }
}
