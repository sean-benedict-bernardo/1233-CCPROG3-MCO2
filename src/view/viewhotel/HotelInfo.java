package view.viewhotel;

import java.awt.*;
import javax.swing.*;

import model.Hotel;
import view.common.MyStyles;
import view.common.components.MyComponents;

public class HotelInfo extends JPanel {
    private Hotel hotel;
    private JPanel nightlyRates;

    public HotelInfo(Hotel hotel) {
        super();
        this.hotel = hotel;

        setLayout(new BorderLayout());
        this.initFrame();

        setBackground(MyStyles.color.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
    }

    public void initFrame() {
        JPanel tablePanel = new JPanel(new GridBagLayout());
        tablePanel.setBackground(MyStyles.color.BACKGROUND);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(16, 64, 16, 64));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.ipadx = 4;
        gbc.ipady = 4;

        String data[][] = {
                { "Name", this.hotel.getName() },
                { "Room Count", "" + this.hotel.getNumRooms() },
                { "Reservation Count", "" + this.hotel.getNumReservations() },
                { "Base Price", String.format("%.2f", this.hotel.getBasePrice()) },
                { "Total Revenue", String.format("%.2f", this.hotel.getTotalRevenue()) }
        };

        int roomCtr = this.hotel.getNumRooms();
        JProgressBar numRooms = MyComponents.progressBar(0, 50, roomCtr, roomCtr + " / 50 Rooms");

        for (int i = 0; i < data.length; i++) {
            gbc.gridy = i;
            for (int j = 0; j < data[0].length; j++) {
                gbc.gridx = j;
                JComponent obj = (i == 1 && j == 1)
                        ? numRooms
                        : MyComponents.headerText(data[i][j], SwingConstants.LEFT);
                tablePanel.add(obj, gbc);
            }
        }

        this.initPriceRange();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets.top = 12;
        tablePanel.add(MyComponents.headerText("Rates for Each Night", SwingConstants.CENTER), gbc);
        gbc.insets.top = 4;
        gbc.gridy++;
        tablePanel.add(this.nightlyRates, gbc);

        add(tablePanel, BorderLayout.CENTER);
    }

    private void initPriceRange() {
        this.nightlyRates = new JPanel(new GridBagLayout());
        this.nightlyRates.setBackground(MyStyles.color.BACKGROUND);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0;
        gbc.insets = new Insets(2, 4, 2, 4);

        // lb 50
        // middle 100
        // upperbound 150

        for (int i = 0; i < this.hotel.getNightRates().length; i++) {
            JPanel entryPanel = new JPanel(new GridBagLayout());
            entryPanel.setBackground(MyStyles.color.BACKGROUND);

            gbc.gridy = (i / 7) * 2;
            gbc.gridx = (i % 7) * 2;

            float nightPrice = this.hotel.getNightRate(i).getNightRate() * this.hotel.getBasePrice();
            int rate = (int) (this.hotel.getNightRate(i).getNightRate() * 100.0);

            JProgressBar nightPriceObj = MyComponents.progressBar(50, 150, rate);
            nightPriceObj.setPreferredSize(new Dimension(200, 50));

            gbc.weightx = 0;
            gbc.gridheight = 2;
            this.nightlyRates.add(MyComponents.headerText("" + (i + 1)), gbc);

            gbc.gridx++;
            gbc.weightx = 1;
            gbc.gridheight = 1;
            gbc.weighty = 0;
            this.nightlyRates.add(MyComponents.bodyText("" + nightPrice), gbc);
            gbc.weighty = 1;
            gbc.gridy++;
            this.nightlyRates.add(nightPriceObj, gbc);
        }

    }
}
