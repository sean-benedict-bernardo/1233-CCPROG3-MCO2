package viewer.makereservation;

import main.Hotel;
import main.rooms.Room;
import viewer.common.MyComponents;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

public class ReservationForm extends JDialog {
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel containerPanel;

    private JPanel availabilityPanel;
    private JLabel infoAvailabilityDate[] = new JLabel[31];

    // input fields
    private JTextField guestNameField;
    private JComboBox<String> guestRoomSelection;
    private JSpinner guestCheckIn;
    private JSpinner guestCheckOut;
    private JTextField guestDiscountCode;
    private JButton submitReservation;
    private JButton cancelReservation;

    public ReservationForm(Hotel hotel) {
        super();

        this.containerPanel = new JPanel(new GridBagLayout());
        setLayout(new BorderLayout());

        this.initFrame(hotel);
        this.updateCalendar(hotel.getRooms().get(0));

        setMinimumSize(new Dimension(720, 405));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
    }

    public void initFrame(Hotel hotel) {
        this.containerPanel.setBorder(BorderFactory.createEmptyBorder(16, 24, 16, 24));
        this.guestNameField = new JTextField("", 20);
        this.guestRoomSelection = new JComboBox<String>(hotel.getRoomNames());
        this.guestRoomSelection.setSelectedIndex(0);
        this.guestCheckIn = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
        this.guestCheckOut = new JSpinner(new SpinnerNumberModel(2, 2, 31, 1));
        this.guestDiscountCode = new JTextField("", 20);
        this.submitReservation = MyComponents.JButton("Make Reservation");
        this.cancelReservation = MyComponents.JButton("Cancel Reservation");

        this.initAvailabilityCalendar(hotel.getRooms().get(0));

        // NORTH
        add(MyComponents.headerText("Booking under " + hotel.getName()), BorderLayout.NORTH);

        // Adding Components
        gbc.insets = new Insets(2, 4, 2, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        this.containerPanel.add(MyComponents.bodyText("Guest Name: "), gbc);
        gbc.gridx = 1;
        this.containerPanel.add(this.guestNameField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        this.containerPanel.add(MyComponents.bodyText("Select Room: "), gbc);
        gbc.gridx = 1;
        this.containerPanel.add(this.guestRoomSelection, gbc);

        // Room Availability
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        this.containerPanel.add(this.availabilityPanel, gbc);
        gbc.gridy++;
        this.containerPanel.add(MyComponents.bodyText("✓ - Room is available | ✗ - Room is Reserved"), gbc);
        gbc.anchor = GridBagConstraints.WEST;

        // Checkinout dates
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        this.containerPanel.add(MyComponents.bodyText("Check-in Date: "), gbc);
        gbc.gridx = 1;
        this.containerPanel.add(this.guestCheckIn, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        this.containerPanel.add(MyComponents.bodyText("Check-out Date: "), gbc);
        gbc.gridx = 1;
        this.containerPanel.add(this.guestCheckOut, gbc);

        // Discount code
        gbc.gridy++;
        gbc.gridx = 0;
        this.containerPanel.add(MyComponents.bodyText("Discount Code: "), gbc);
        gbc.gridx = 1;
        this.containerPanel.add(this.guestDiscountCode, gbc);

        // Buttons
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridy++;
        gbc.gridx = 0;
        this.containerPanel.add(this.submitReservation, gbc);
        gbc.gridx = 1;
        this.containerPanel.add(this.cancelReservation, gbc);

        add(this.containerPanel, BorderLayout.CENTER);
    }

    private void initAvailabilityCalendar(Room room) {
        this.availabilityPanel = new JPanel(new GridBagLayout());
        GridBagConstraints calendarConstraints = new GridBagConstraints();

        for (int i = 0; i < infoAvailabilityDate.length; i++)
            infoAvailabilityDate[i] = MyComponents.bodyText("", JLabel.RIGHT);

        calendarConstraints.ipadx = 5;
        calendarConstraints.ipady = 2;
        calendarConstraints.weightx = 1;
        calendarConstraints.fill = GridBagConstraints.HORIZONTAL;
        for (int i = 0; i < infoAvailabilityDate.length; i++) {
            calendarConstraints.gridx = i % 7;
            calendarConstraints.gridy = i / 7;

            availabilityPanel.add(infoAvailabilityDate[i], calendarConstraints);
        }
    }

    /**
     * 
     * @return String value of input text box
     */
    public String getGuestNameField() {
        String textValue = this.guestNameField.getText();
        return (!textValue.equals("")) ? textValue : null;
    }

    public String getRoomSelection() {
        return this.guestRoomSelection.getSelectedItem().toString();
    }

    public int getCheckInDate() {
        return Integer.parseInt(this.guestCheckIn.getValue().toString());
    }

    public int getCheckOutDate() {
        return Integer.parseInt(this.guestCheckOut.getValue().toString());
    }

    public String getDiscountCode() {
        String textValue = this.guestDiscountCode.getText();
        return (!textValue.equals("")) ? textValue : null;
    }

    // object getters

    public JButton getSubmitButton() {
        return this.submitReservation;
    }

    public JButton getCancelButton() {
        return this.cancelReservation;
    }

    public JComboBox<String> getRoomSelectionObject() {
        return this.guestRoomSelection;
    }

    public void updateCalendar(Room room) {
        for (int i = 0; i < infoAvailabilityDate.length; i++)
            infoAvailabilityDate[i]
                    .setText(String.format("%d - %c", i + 1, !room.getDayAvailability(i) ? '✓' : '✗'));
    }

    public static void main(String[] args) {
        try {

            Hotel myHotel = new Hotel("HotelGemal", 'D');

            for (int i = 0; i < 10; i++) {
                switch (i % 3) {
                    case 0:
                        myHotel.addRoom('S');
                        break;
                    case 1:
                        myHotel.addRoom('D');
                        break;
                    default:
                        myHotel.addRoom('E');
                        break;
                }
            }

            myHotel.createReservation("Andrew", 1, 5, "S1");

            ReservationForm test = new ReservationForm(myHotel);

            test.pack();
            test.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
