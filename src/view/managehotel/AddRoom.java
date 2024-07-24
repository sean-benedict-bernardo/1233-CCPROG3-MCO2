package view.managehotel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Hotel;
import view.common.MyStyles;
import view.common.components.MyComponents;

public class AddRoom extends JPanel{
    Hotel hotel;
    private ButtonGroup radioButtonGroup;
    private JRadioButton roomType[] = new JRadioButton[3];
    private JButton confirmButton;
    private GridBagConstraints gbc;

    public AddRoom(Hotel hotel){
        super();
        this.hotel = hotel;
        this.radioButtonGroup = new ButtonGroup();
        this.gbc = new GridBagConstraints();

        setLayout(new GridBagLayout());

        initFrame();

        setBackground(MyStyles.color.BACKGROUND);
    }
    
    public void initFrame(){
        JLabel roomTypePrompt = MyComponents.headerText("Select a room type for the new room:");
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
        confirmButton = MyComponents.button("Add Room");

        gbc.insets = new Insets(0, 0, 0, 10);
        this.add(roomTypePrompt, gbc);
        for (int i = 0; i < roomType.length; i++) {
            this.add(this.roomType[i]);
        }
        gbc.insets = new Insets(0, 10, 0, 0);
        this.add(confirmButton, gbc);
    }

    public char getRoomType() {
        for (JRadioButton jRadioButton : roomType) {
            if (jRadioButton.isSelected())
                return jRadioButton.getText().charAt(0);
        }
        return '\0';
    }

    public JButton getConfirmButton(){
        return this.confirmButton;
    }
}
