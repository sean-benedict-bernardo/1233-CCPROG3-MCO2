package viewer.createhotel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import viewer.common.MyComponents;

public class CreateHotel extends JDialog {
    private JPanel createHotel;

    private JTextField hotelNameField;

    private ButtonGroup radioButtonGroup;

    private JRadioButton roomType[] = new JRadioButton[3];

    private JButton createHotelButton, cancelCreateButton;

    public CreateHotel() {
        this.createHotel = new JPanel(new GridBagLayout());
        this.radioButtonGroup = new ButtonGroup();
        setTitle("Hotel Creation");

        this.initFrame();

        setMinimumSize(new Dimension(720, 405));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
    }

    public void initFrame() {
        GridBagConstraints gbc = new GridBagConstraints();
        this.createHotelButton = MyComponents.JButton("Create Hotel");
        this.cancelCreateButton = MyComponents.JButton("Return to Main Menu");
        this.hotelNameField = new JTextField("", 20);
        this.initRadioButton();

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 0;
        gbc.gridx = 0;
        this.createHotel.add(MyComponents.bodyText("Hotel name: "), gbc);
        gbc.gridx = 1;
        this.createHotel.add(this.hotelNameField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        this.createHotel.add(MyComponents.bodyText("First room type: "), gbc);
        gbc.gridx = 1;
        for (int i = 0; i < roomType.length; i++) {
            this.createHotel.add(this.roomType[i], gbc);
            gbc.gridy++;
        }

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        this.createHotel.add(this.createHotelButton, gbc);
        gbc.gridx = 1;
        this.createHotel.add(this.cancelCreateButton, gbc);

        add(this.createHotel);
    }

    private void initRadioButton() {
        this.roomType[0] = new JRadioButton("Standard");
        this.roomType[1] = new JRadioButton("Deluxe");
        this.roomType[2] = new JRadioButton("Executive");

        for (JRadioButton jRadioButton : this.roomType) {
            jRadioButton.setFocusPainted(false);
            this.radioButtonGroup.add(jRadioButton);
        }

        this.roomType[0].setSelected(true);
    };

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
        for (JRadioButton jRadioButton : roomType) {
            if (jRadioButton.isSelected())
                return jRadioButton.getText().charAt(0);
        }
        return '\0';
    }

}
