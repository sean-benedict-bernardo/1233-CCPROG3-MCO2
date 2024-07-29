package view.managehotel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Hotel;
import model.NightRate;
import view.common.MyStyles;
import view.common.components.MyComponents;

public class ManageHotelPanel extends JPanel {
    private Hotel hotel;

    private JTextField changeHotelName, changeBasePrice;
    private JPanel panelDateModifier;
    private JSpinner changeDateModifier[];
    private JButton deleteHotel, saveHotelName, saveBasePrice, saveDateModifier;

    public ManageHotelPanel(Hotel hotel) {
        super(new BorderLayout());
        this.hotel = hotel;
        setBackground(MyStyles.color.BACKGROUND);
        setForeground(MyStyles.color.FOREGROUND);
        setBorder(new EmptyBorder(24, 24, 24, 24));
        this.initComponents();
        this.updateValidity(this.hotel.getNumReservations() == 0);
        this.initFrame();
    }

    private void initComponents() {
        this.changeHotelName = MyComponents.textField(this.hotel.getName());
        this.changeBasePrice = MyComponents.textField(String.format("%.2f", this.hotel.getBasePrice()));
        this.deleteHotel = MyComponents.button("Delete Hotel");
        this.changeDateModifier = new JSpinner[hotel.getNightRates().length];
        this.initDatePriceModifier();

        this.saveDateModifier = MyComponents.button("Update Date Price Modifier");
        this.saveBasePrice = MyComponents.button("Update Base Price");
        this.saveHotelName = MyComponents.button("Update Hotel Name");
    }

    private void initFrame() {
        JPanel containerPanel = new JPanel(new GridBagLayout());
        containerPanel.setBackground(MyStyles.color.BACKGROUND);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.weightx = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Set preferred dimensions of buttons
        Dimension dim = new Dimension(300, (int) this.saveHotelName.getPreferredSize().getHeight());
        this.saveHotelName.setPreferredSize(dim);
        this.saveBasePrice.setPreferredSize(dim);
        this.saveDateModifier.setPreferredSize(dim);

        JComponent gridMap[][] = {
                { MyComponents.smallTitleText("Manage Hotel") },
                { MyComponents.bodyText("Update general hotel information", SwingConstants.CENTER,
                        MyComponents.ITALICS) },
                { MyComponents.headerText("Name:"), this.changeHotelName, this.saveHotelName },
                { MyComponents.headerText("Base Price:"), this.changeBasePrice, this.saveBasePrice },
                { MyComponents.headerText("Date Price Modifier"), this.saveDateModifier },
                {this.panelDateModifier},
                {this.deleteHotel},
                {MyComponents.bodyText(
                    "<html><body style='text-align: center;'>NOTE: Date price modifier and base room rates <br> cannot be changed while there is still an active reservation</body></html>",
                    SwingConstants.CENTER, MyComponents.ITALICS)}
        };

        for (int i = 0; i < gridMap.length; i++) {
            for (int j = 0; j < gridMap[i].length; j++) {
                gbc.gridy = i;
                gbc.gridx = j;

                gbc.weightx = (i < 2 || j == 1) ? 1 : 0;
                gbc.gridwidth = (j == gridMap[i].length - 1) ? GridBagConstraints.REMAINDER : 1;
                gbc.fill = (i == 4 && j == 1 || i == 6) ? GridBagConstraints.NONE : GridBagConstraints.BOTH;

                containerPanel.add(gridMap[i][j], gbc);
            }
        }

        add(containerPanel, BorderLayout.CENTER);
    }

    private void initDatePriceModifier() {
        this.panelDateModifier = new JPanel(new GridBagLayout());
        this.panelDateModifier.setBackground(MyStyles.color.BACKGROUND);
        NightRate[] nightRates = this.hotel.getNightRates();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

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
    public void updateValidity(boolean isValid) {
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
            try {
                this.changeDateModifier[i].commitEdit();
                dateModifier[i] = ((Integer) this.changeDateModifier[i].getValue()) / 100.0f;
            } catch (Exception e) {
                /* Do nothing */}

        }

        return dateModifier;
    }

    public JButton getDeleteHotel() {
        return this.deleteHotel;
    }

    public JButton getSaveHotelName() {
        return this.saveHotelName;
    }

    public JButton getSaveBasePrice() {
        return this.saveBasePrice;
    }

    public JButton getSaveDateModifier() {
        return this.saveDateModifier;
    }

}
