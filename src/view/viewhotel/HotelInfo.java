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
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.ipadx = 4;
        gbc.ipady = 4;

        String data[][] = {
                { "Name", this.hotel.getName() },
                { "Room Count", "" + this.hotel.getNumRooms() },
                { "Reservation Count", "" + this.hotel.getNumReservations() },
                { "Total Revenue", String.format("%.2f", this.hotel.getTotalRevenue()) }
        };

        for (int i = 0; i < data.length; i++) {
            gbc.gridy = i;
            for (int j = 0; j < data[0].length; j++) {
                JLabel text = MyComponents.bodyText(data[i][j], SwingConstants.LEFT);
                gbc.gridx = j;

                tablePanel.add(text, gbc);
            }
        }

        this.initPriceRange();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 0;
        gbc.gridy++;
        tablePanel.add(this.nightlyRates, gbc);

        add(tablePanel, BorderLayout.CENTER);
    }

    private void initPriceRange() {
        this.nightlyRates = new JPanel(new GridBagLayout());
        this.nightlyRates.setBackground(MyStyles.color.BACKGROUND);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.ipadx = 4;
        gbc.ipady = 4;

        for (int i = 0; i < this.hotel.getNightRates().length; i++) {
            JPanel entryPanel = new JPanel(new GridBagLayout());
            entryPanel.setBackground(MyStyles.color.BACKGROUND);

            gbc.gridy = 0;
            gbc.gridx = 0;
            JLabel index = MyComponents.bodyText("" + (i + 1));
            index.setPreferredSize(new Dimension(20, (int)index.getPreferredSize().getHeight()));
            entryPanel.add(index, gbc);
            gbc.gridx = 1;
            entryPanel.add(
                    MyComponents.bodyText("" + (this.hotel.getNightRate(i).getNightRate() * this.hotel.getBasePrice())),
                    gbc);

            gbc.gridy = i / 7;
            gbc.gridx = i % 7;

            this.nightlyRates.add(entryPanel, gbc);
        }

    }
}
