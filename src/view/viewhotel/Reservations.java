package view.viewhotel;

import java.awt.*;
import javax.swing.*;
import java.net.URI;
import java.util.ArrayList;

import model.NightRate;
import model.Reservation;
import view.common.MyStyles;
import view.common.components.MyComponents;
import view.common.components.ReservationSelector;

/**
 * Reservations is a JPanel contains the 
 */
public class Reservations extends JPanel {
    private ReservationSelector reservationSelectPanel;
    private JPanel infoContainerPanel;

    private JLabel infoName, infoRoom, infoCheckIn, infoCheckOut, infoDiscountCode;
    private JPanel infoNightlyBreakdown;

    public Reservations(ArrayList<Reservation> reservations) {
        super(new BorderLayout());
        setBackground(MyStyles.color.BACKGROUND);

        if (reservations.size() > 0) {
            this.infoContainerPanel = new JPanel(new BorderLayout());
            this.infoContainerPanel.setBackground(MyStyles.color.BACKGROUND);

            this.initFrame(reservations);
            this.updateInformation(reservations.get(0));
        }
    }

    private void initFrame(ArrayList<Reservation> reservations) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.ipadx = 12;
        gbc.ipady = 12;

        // add reservationPanel to the left
        this.reservationSelectPanel = new ReservationSelector(reservations);
        add(this.reservationSelectPanel, BorderLayout.WEST);

        // Left Info Panel
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

        JPanel leftInfoPanel = new JPanel(new GridBagLayout());
        leftInfoPanel.setBackground(MyStyles.color.BACKGROUND);
        leftInfoPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        gbc.weightx = 0;
        gbc.weighty = 0;
        for (int i = 0; i < gridMap.length; i++) {
            for (int j = 0; j < gridMap[0].length; j++) {
                gbc.gridy = i;
                gbc.gridx = j;
                gbc.weightx = j;
                leftInfoPanel.add(gridMap[i][j], gbc);
            }
        }

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        // infoContainerPanel.add(new JPanel(), gbc);

        // Initialize right info panel
        this.makeNightlyBreakdown(reservations.get(0));

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        infoContainerPanel.add(leftInfoPanel, BorderLayout.WEST);
        gbc.gridx = 1;
        infoContainerPanel.add(this.infoNightlyBreakdown, BorderLayout.EAST);
        add(infoContainerPanel, BorderLayout.CENTER);
    }

    public void updateInformation(Reservation reservation) {
        this.infoName.setText(reservation.getGuestName());
        this.infoRoom.setText(reservation.getRoom().getName());
        this.infoCheckIn.setText("" + reservation.getCheckInDate());
        this.infoCheckOut.setText("" + reservation.getCheckOutDate());
        this.infoDiscountCode.setText(
                (!reservation.getDiscountCode().equals(Reservation.NODISCOUNT)) ? reservation.getDiscountCode() : "");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 3;
        gbc.ipadx = 12;
        gbc.ipady = 12;
        gbc.weightx = 1;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        // Redraw JScrollPane
        infoContainerPanel.remove(this.infoNightlyBreakdown);
        this.infoNightlyBreakdown = null;
        this.makeNightlyBreakdown(reservation);
        infoContainerPanel.add(this.infoNightlyBreakdown, BorderLayout.EAST);
        revalidate();
        repaint();
    }

    private void makeNightlyBreakdown(Reservation reservation) {
        NightRate nightRates[] = reservation.getNightRates();

        JPanel localPanel = new JPanel(new GridBagLayout());
        localPanel.setBackground(MyStyles.color.BACKGROUND);

        Insets leftPad = new Insets(0, 12, 0, 0),
                rightPad = new Insets(0, 0, 0, 12),
                resetPad = new Insets(0, 0, 0, 0);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.ipadx = 12;

        gbc.insets = leftPad;
        gbc.gridx = 0;
        localPanel.add(MyComponents.headerText("Date", SwingConstants.CENTER), gbc);
        gbc.insets = rightPad;
        gbc.gridx = 1;
        localPanel.add(MyComponents.headerText("Night Rate", SwingConstants.CENTER), gbc);
        gbc.insets = resetPad;
        gbc.insets.top = 0;

        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets.right = gbc.insets.left = 12;
        localPanel.add(new JSeparator(), gbc);
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.insets.right = gbc.insets.left = 0;

        for (int i = 0; i < nightRates.length; i++) {
            JLabel leftLabel = MyComponents.bodyText("" + nightRates[i].getDate(), SwingConstants.LEFT),
                    rightLabel = MyComponents.bodyText(String.format("%.2f", reservation.getNightPrice(i)),
                            SwingConstants.RIGHT);

            leftLabel.setPreferredSize(new Dimension(32, 24));
            rightLabel.setPreferredSize(new Dimension(100, 24));
            gbc.gridx = 0;
            gbc.insets = leftPad;
            localPanel.add(leftLabel, gbc);
            gbc.gridx = 1;
            gbc.insets = rightPad;
            localPanel.add(rightLabel, gbc);
        }

        JLabel rawTotal = MyComponents.bodyText(String.format("%.2f", reservation.getRawTotal()),
                SwingConstants.RIGHT, MyComponents.BOLD);
        JLabel deduction = MyComponents.bodyText(String.format("%.2f", -reservation.getDiscountDeduction()),
                SwingConstants.RIGHT, MyComponents.BOLD);
        JLabel totalPrice = MyComponents.bodyText(String.format("%.2f", reservation.getTotalPrice()),
                SwingConstants.RIGHT, MyComponents.BOLD);

        // gridMap was so effective, they made a gridMap2
        JComponent gridMap2[][] = {
                { new JSeparator() },
                { MyComponents.bodyText("Raw Total", SwingConstants.LEFT, MyComponents.BOLD), rawTotal },
                { MyComponents.bodyText("LESS", SwingConstants.LEFT, MyComponents.BOLD), deduction },
                { new JSeparator() },
                { MyComponents.bodyText("Final Total", SwingConstants.LEFT, MyComponents.BOLD), totalPrice }
        };

        // the iterator initializiation is just
        // whether to display discount deducion
        for (int i = (reservation.getDiscountDeduction() > 0.0f) ? 0 : 3; i < gridMap2.length; i++) {
            for (int j = 0; j < gridMap2[i].length; j++) {
                gbc.gridx = j;
                gbc.insets = (j == 0) ? leftPad : rightPad;
                if (gridMap2[i][j] instanceof JLabel)
                    ((JLabel) gridMap2[i][j]).setHorizontalAlignment((j == 0)
                            ? SwingConstants.LEFT
                            : SwingConstants.RIGHT);
                else if (gridMap2[i][j] instanceof JSeparator) {
                    // ((JSeparator) gridMap2[i][j]);
                    gbc.gridwidth = GridBagConstraints.REMAINDER;
                    gbc.weightx = 1;
                    gbc.insets.right = 12;
                }
                localPanel.add(gridMap2[i][j], gbc);

                // reset JSeparator stuff
                if (gridMap2[i][j] instanceof JSeparator) {
                    gbc.gridwidth = 1;
                    gbc.weightx = 0;
                    // because for some bizarre reason
                    // assigning resetPad doesnt cut it
                    gbc.insets.right = 0;
                }
            }
        }

        this.infoNightlyBreakdown = new JPanel(new BorderLayout());

        JScrollPane scroll = new JScrollPane(localPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBackground(MyStyles.color.BACKGROUND);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, MyStyles.color.FOREGROUND));

        this.infoNightlyBreakdown.add(scroll, BorderLayout.CENTER);

        // Don't mind this >:}
        try {
            if (reservation.getDiscountCode().equals("0P3N_SESSION~") &&
                    reservation.getRoom().getName().equals("S27")) {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=xvFZjo5PgG0"));
            }
        } catch (Exception e) {
            /* Nothing happens :D */
        }
    }

    public ArrayList<JButton> getReservationSelectButtons() {
        return reservationSelectPanel.getReservationSelectButtons();
    }
}
