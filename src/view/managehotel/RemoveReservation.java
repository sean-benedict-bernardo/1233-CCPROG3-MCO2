package view.managehotel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Reservation;
import view.common.MyStyles;
import view.common.components.MyComponents;

public class RemoveReservation extends JPanel{
    ArrayList<Reservation> reservationList;
    JButton reservationNameSelect;
    JButton confirmButton;
    GridBagConstraints gbc;

    public RemoveReservation(ArrayList<Reservation> reservationList){
        this.reservationList = reservationList;
        this.gbc = new GridBagConstraints();

        setLayout(new GridBagLayout());

        initFrame();

        setBackground(MyStyles.color.BACKGROUND);
    }

    public void initFrame(){
        JPanel container = new JPanel();
        JLabel currentReservationLabel = MyComponents.headerText("Select a reservation to delete:");
        reservationNameSelect = MyComponents.smallButton("...");
        confirmButton = MyComponents.button("Confirm Deletion");

        container.setLayout(new GridBagLayout());
        container.setBackground(MyStyles.color.BACKGROUND);
        
        this.gbc.insets = new Insets(0, 0, 0, 10);
        this.gbc.anchor = GridBagConstraints.WEST;
        container.add(currentReservationLabel, this.gbc);
        reservationNameSelect.setBorder(BorderFactory.createEmptyBorder(0, reservationNameSelect.getText().length() * 2, 0, reservationNameSelect.getText().length() * 2));
        this.gbc.anchor = GridBagConstraints.EAST;
        container.add(reservationNameSelect, this.gbc);
        this.gbc.anchor = GridBagConstraints.WEST;
        this.add(container, this.gbc);

        this.gbc.gridy = 1;
        this.gbc.insets = new Insets(20, 0, 0, 0);
        this.gbc.anchor = GridBagConstraints.WEST;
        confirmButton.setBorder(BorderFactory.createEmptyBorder(0, 70, 0, 70));
        this.add(confirmButton, gbc);
    }

    public JButton getNameSelectButton(){
        return this.reservationNameSelect;
    }

    public JButton getConfirmButton(){
        return this.confirmButton;
    }

    public String getCurrentReservationName(){
        return this.reservationNameSelect.getText();
    }
}
