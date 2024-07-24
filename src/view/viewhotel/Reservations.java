package view.viewhotel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.NightRates;
import model.Reservation;
import view.common.MyStyles;
import view.common.components.MyComponents;
import view.common.components.ReservationSelector;

public class Reservations extends JPanel {
    private ReservationSelector reservationSelectPanel;

    private JLabel infoName, infoRoom, infoCheckIn, infoCheckOut, infoDiscountCode;
    private JPanel infoNightlyBreakdown;

    public Reservations(ArrayList<Reservation> reservations) {
        super(new BorderLayout());
        setBackground(MyStyles.color.BACKGROUND);

        if (reservations.size() > 0) {
            this.initFrame(reservations);
            this.updateInformation(reservations.get(0));
        }
    }

    private void initFrame(ArrayList<Reservation> reservations) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.ipadx = 12;
        gbc.ipady = 12;

        // add reservationPanel to the left
        this.reservationSelectPanel = new ReservationSelector(reservations);
        add(this.reservationSelectPanel, BorderLayout.WEST);

        // Information Panel
        this.infoName = MyComponents.headerText();
        this.infoRoom = MyComponents.headerText();
        this.infoCheckIn = MyComponents.headerText();
        this.infoCheckOut = MyComponents.headerText();
        this.infoDiscountCode = MyComponents.headerText();

        JComponent gridMap[][] = {
                { MyComponents.headerText("Guest Name"), this.infoName },
                { MyComponents.headerText("Room"), this.infoRoom },
                { MyComponents.headerText("Check-in"), this.infoCheckIn },
                { MyComponents.headerText("Check-out"), this.infoCheckOut },
                { MyComponents.headerText("Discount Code"), this.infoDiscountCode },
        };

        JPanel infoContainerPanel = new JPanel(new GridBagLayout());

        gbc.weightx = 0;
        gbc.weighty = 0;
        for (int i = 0; i < gridMap.length; i++) {
            for (int j = 0; j < gridMap[0].length; j++) {
                gbc.gridy = i;
                gbc.gridx = j;
                infoContainerPanel.add(gridMap[i][j], gbc);
            }
        }

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        infoContainerPanel.add(new JPanel(), gbc);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = GridBagConstraints.REMAINDER;

        this.makeNightlyBreakdown(reservations.get(0));
        gbc.gridy = 0;
        gbc.gridx = 3;
        infoContainerPanel.add(this.infoNightlyBreakdown, gbc);
        add(infoContainerPanel, BorderLayout.CENTER);
    }

    public void updateInformation(Reservation reservation) {
        this.infoName.setText(reservation.getGuestName());
        this.infoRoom.setText(reservation.getRoom().getName());
        this.infoCheckIn.setText("" + reservation.getCheckInDate());
        this.infoCheckOut.setText("" + reservation.getCheckOutDate());
        this.infoDiscountCode.setText(
                (!reservation.getDiscountCode().equals(Reservation.NODISCOUNT)) ? reservation.getDiscountCode() : "");
    }

    private void makeNightlyBreakdown(Reservation reservation) {
        float basePrice = reservation.getRoom().getPrice();
        NightRates nightRates[] = reservation.getNightRates();
        this.infoNightlyBreakdown = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.ipadx = 12;

        gbc.gridx = 0;
        this.infoNightlyBreakdown.add(MyComponents.headerText("Date"), gbc);
        gbc.gridx = 1;
        this.infoNightlyBreakdown.add(MyComponents.headerText("Night Rate"), gbc);

        for (NightRates night : nightRates) {
            JLabel leftLabel = MyComponents.bodyText("" + night.getDate()),
                    rightLabel = MyComponents.bodyText(String.format("%.2f", basePrice * night.getNightRate()));

            rightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            gbc.gridx = 0;
            this.infoNightlyBreakdown.add(leftLabel, gbc);
            gbc.gridx = 1;
            this.infoNightlyBreakdown.add(rightLabel, gbc);
        }

        gbc.gridx = 0;
        this.infoNightlyBreakdown.add(MyComponents.bodyText("Total"), gbc);
        gbc.gridx = 1;
        JLabel totalPrice = MyComponents.bodyText(String.format("%.2f", reservation.getTotalPrice()));
        totalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        this.infoNightlyBreakdown.add(totalPrice,
                gbc);
        revalidate();
        repaint();
    }

    

    public ArrayList<JButton> getReservationSelectButtons() {
        return reservationSelectPanel.getReservationSelectButtons();
    }
}
