package view.managehotel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import model.Hotel;
import model.NightRates;
import view.common.MyStyles;
import view.common.auxiliary.Alert;
import view.common.components.MyComponents;

public class ManageHotelPanel extends JPanel {
    private Hotel hotel;

    private JTextField changeHotelName, changeBasePrice;
    private JPanel panelDateModifier;
    private JSpinner changeDateModifier[];
    private JButton deleteHotel, saveChanges;

    public ManageHotelPanel(Hotel hotel) {
        super(new BorderLayout());
        this.hotel = hotel;
        setBackground(MyStyles.color.BACKGROUND);
        setForeground(MyStyles.color.FOREGROUND);
        this.initComponents();
        this.updateValidity();
        this.initFrame();
    }

    private void initComponents() {
        this.changeHotelName = MyComponents.textField(this.hotel.getName());
        this.changeBasePrice = MyComponents.textField(String.format("%.2f", this.hotel.getBasePrice()));
        this.deleteHotel = MyComponents.button("Delete Hotel");
        this.saveChanges = MyComponents.button("Save Change");
        this.changeDateModifier = new JSpinner[hotel.getNightRates().length];
        this.initDatePriceModifier();
    }

    private void initFrame() {
        JPanel containerPanel = new JPanel(new GridBagLayout());
        containerPanel.setBackground(MyStyles.color.BACKGROUND);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        containerPanel.add(MyComponents.headerText("New Name: "), gbc);
        gbc.gridx = 1;
        containerPanel.add(this.changeHotelName, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        containerPanel.add(MyComponents.headerText("Update Base Price:"), gbc);
        gbc.gridx = 1;
        containerPanel.add(this.changeBasePrice, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        containerPanel.add(this.panelDateModifier, gbc);
        gbc.gridy++;
        containerPanel.add(this.saveChanges, gbc);
        gbc.gridy++;
        containerPanel.add(this.deleteHotel, gbc);

        add(containerPanel, BorderLayout.CENTER);
    }

    private void initDatePriceModifier() {
        this.panelDateModifier = new JPanel(new GridBagLayout());
        this.panelDateModifier.setBorder(BorderFactory.createLineBorder(MyStyles.color.FOREGROUND, 2));
        NightRates[] nightRates = this.hotel.getNightRates();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        /**
         * 
         * gbc.gridy = 0;
         * gbc.gridx = 0;
         * JLabel index = MyComponents.bodyText("" + (i + 1));
         * index.setPreferredSize(new Dimension(20,
         * (int)index.getPreferredSize().getHeight()));
         * entryPanel.add(index, gbc);
         * gbc.gridx = 1;
         * entryPanel.add(
         * MyComponents.bodyText("" + (this.hotel.getNightRate(i).getNightRate() *
         * this.hotel.getBasePrice())),
         * gbc);
         * 
         * gbc.gridy = i / 7;
         * gbc.gridx = i % 7;
         */

        for (int i = 0; i < nightRates.length; i++) {
            JPanel sliderPanel = new JPanel(new GridBagLayout());
            sliderPanel.setBackground(MyStyles.color.BACKGROUND);
            sliderPanel.setBorder(
                    BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(MyStyles.color.FOREGROUND, 2),
                            new EmptyBorder(4, 4, 4, 4)));

            JLabel dateLabel = MyComponents.bodyText("" + nightRates[i].getDate());
            dateLabel.setPreferredSize(new Dimension(20, (int) dateLabel.getPreferredSize().getHeight()));

            this.changeDateModifier[i] = new JSpinner(
                    new SpinnerNumberModel((int) (100 * nightRates[i].getNightRate()), 50, 150, 1));
            this.changeDateModifier[i].setForeground(MyStyles.color.FOREGROUND);
            this.changeDateModifier[i].setBackground(MyStyles.color.BACKGROUND);

            // only relevant when disabled
            ((JSpinner.NumberEditor) this.changeDateModifier[i].getEditor()).getTextField()
                    .setDisabledTextColor(MyStyles.color.LIGHTGRAY);

            gbc.gridy = 0;
            gbc.gridx = 0;
            sliderPanel.add(dateLabel, gbc);
            gbc.gridx = 1;
            sliderPanel.add(this.changeDateModifier[i], gbc);

            gbc.gridy = i / 7;
            gbc.gridx = i % 7;
            this.panelDateModifier.add(sliderPanel, gbc);
        }
    }

    /*
     * This method essentially updates specific fields dependent
     * whether there are still active reservations or not
     */
    public void updateValidity() {
        boolean isValid = this.hotel.getNumReservations() == 0;
        this.changeBasePrice.setEditable(isValid);
        for (int i = 0; i < changeDateModifier.length; i++) {
            this.changeDateModifier[i].setEnabled(isValid);
        }
    }

    public String getHotelName() {
        return this.changeHotelName.getText();
    }

    public float getBasePrice() throws Exception {
        try {
            return Float.parseFloat(this.changeBasePrice.getText());
        } catch (Exception e) {
            throw new Exception("Invalid input!");
        }
    }

    public float[] getDateModifier() {
        float[] dateModifier = new float[this.changeDateModifier.length];

        for (int i = 0; i < this.changeDateModifier.length; i++) {
            dateModifier[i] = (float) this.changeDateModifier[i].getValue() / 100.0f;
        }

        return dateModifier;
    }

    public JButton getDeleteHotelButton() {
        return this.deleteHotel;
    }

    public JButton getSaveChangesButton() {
        return this.saveChanges;
    }

}
